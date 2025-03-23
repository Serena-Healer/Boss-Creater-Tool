package serena.bosscreatortool.events;

import com.google.common.util.concurrent.AtomicDouble;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import serena.bosscreatortool.data.player.CapabilityProvider;
import serena.bosscreatortool.data.player.PlayerDataHandler;
import serena.bosscreatortool.enums.EnumDamageType;
import serena.bosscreatortool.network.PacketHandler;
import serena.bosscreatortool.network.SPackets.LeftClickPacket;
import serena.bosscreatortool.util.calculator.AssistHelper;
import serena.bosscreatortool.util.calculator.DamageTypeHelper;
import serena.bosscreatortool.util.calculator.StatusHelper;
import serena.bosscreatortool.util.potion.PotionHandler;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class PlayerEventHandler {

    //ダメージ計算
    @SubscribeEvent
    public void onDamage(LivingDamageEvent event){
        float damage = event.getAmount();
        DamageSource type = event.getSource();
        EnumDamageType damageType = DamageTypeHelper.getDamageType(type);
        Entity dealer = event.getSource().getTrueSource();

        if(Float.isNaN(event.getEntityLiving().getHealth())){
            event.getEntityLiving().setHealth(event.getEntityLiving().getMaxHealth());
            damage = 0;
        }

        //被弾時
        if(event.getEntity() instanceof EntityLivingBase){

            Map<Potion, Integer> pot = new HashMap<>();
            switch(damageType){
                case PHISYCAL:
                    pot.put(PotionHandler.PHYSICAL_RESISTANCE_UP, -1);
                    pot.put(PotionHandler.PHYSICAL_RESISTANCE_DOWN, 1);
                    break;
                case MAGIC:
                    pot.put(PotionHandler.MAGIC_RESISTANCE_UP, -1);
                    pot.put(PotionHandler.MAGIC_RESISTANCE_DOWN, 1);
                    break;
            }

            AtomicDouble dmg_temp = new AtomicDouble(damage);
            pot.forEach((t, a) -> {
                PotionEffect effect = event.getEntityLiving().getActivePotionEffect(t);
                if(effect != null) {
                    int amplifier = effect.getAmplifier();
                    amplifier++;
                    amplifier *= a;
                    amplifier += 100;
                    dmg_temp.set(dmg_temp.get() * amplifier / 100.0F);
                }
            });
            damage = (float) dmg_temp.get();

        }

        //攻撃時
        if(dealer instanceof EntityLivingBase){

            Map<Potion, Integer> pot = new HashMap<>();
            switch(damageType){
                case PHISYCAL:
                    pot.put(PotionHandler.PHYSICAL_ATTACK_UP, 1);
                    pot.put(PotionHandler.PHYSICAL_ATTACK_DOWN, -1);
                    break;
                case MAGIC:
                    pot.put(PotionHandler.MAGIC_ATTACK_UP, 1);
                    pot.put(PotionHandler.MAGIC_ATTACK_DOWN, -1);
                    break;
            }

            AtomicDouble dmg_temp = new AtomicDouble(damage);
            pot.forEach((t, a) -> {
                PotionEffect effect = event.getEntityLiving().getActivePotionEffect(t);
                if(effect != null) {
                    int amplifier = effect.getAmplifier();
                    amplifier += 101;
                    amplifier *= a;
                    dmg_temp.set(dmg_temp.get() * amplifier / 100.0F);
                }
            });
            damage = (float) dmg_temp.get();

            IAttributeInstance instance = ((EntityLivingBase)dealer).getAttributeMap().getAttributeInstance(SharedMonsterAttributes.ATTACK_DAMAGE);
            if(instance != null) {
                float k = (float) instance.getAttributeValue();
                float l = StatusHelper.getStrength((EntityLivingBase) dealer);
                if(k != 0) {
                    damage *= (l / k);
                }
            }
        }

        if(damage <= 0){
            event.setCanceled(true);
        }else {
            if (dealer instanceof EntityPlayer) {
                AssistHelper.contributeAttacking((EntityPlayer) dealer, event.getEntityLiving(), (int)Math.ceil(damage));
            }

            float k = event.getEntityLiving().getMaxHealth();
            float l = StatusHelper.getEffectiveHealth(event.getEntityLiving());
            if(l != 0) {
                damage *= (k / l);
            }
        }

        event.setAmount(damage);
    }

    //敵を倒した/倒された判定
    @SubscribeEvent
    public void onKill(LivingDeathEvent event){
        if(event.getEntity() instanceof EntityPlayer){
            PlayerDataHandler data = CapabilityProvider.getPlayerData(event.getEntity());
        }else{
            if(event.getSource() != null) {
                Entity source = event.getSource().getTrueSource();
                if (!(source instanceof EntityPlayer)) source = null;
            }
        }
    }

    @SubscribeEvent
    public void onTick(TickEvent.PlayerTickEvent event){
        if(event.phase == TickEvent.Phase.START){
            PlayerDataHandler data = CapabilityProvider.getPlayerData(event.player);
            if(event.player.world.getTotalWorldTime() % 20 == 0){
                Potion[] damagePots = new Potion[]{PotionHandler.FIRE, PotionHandler.POISON, PotionHandler.WITHER};
                String[] sources = new String[]{"onFire", "poison", "wither"};
                for(int i=0; i<3; i++){
                    PotionEffect effect = event.player.getActivePotionEffect(damagePots[i]);
                    if(effect != null){
                        int amplifier = effect.getAmplifier() + 1;
                        event.player.hurtResistantTime = 0;
                        event.player.attackEntityFrom(new DamageSource(sources[i]), amplifier + 1);
                        if(damagePots[i] == PotionHandler.FIRE){
                            if(!event.player.isBurning()){
                                event.player.setFire(1);
                            }
                        }
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void onInteract(PlayerInteractEvent event){
        boolean flag = false;
        if(event instanceof PlayerInteractEvent.RightClickItem && !(event.getItemStack().getItem() instanceof ItemBow))flag = true;
        if((event instanceof PlayerInteractEvent.LeftClickEmpty || event instanceof PlayerInteractEvent.LeftClickBlock) && event.getItemStack().getItem() instanceof ItemBow)flag = true;
        if(flag) {
            if (event.getHand() == EnumHand.MAIN_HAND) {
                ItemStack item = event.getItemStack();
                EntityPlayer player = event.getEntityPlayer();
                PlayerDataHandler data = CapabilityProvider.getPlayerData(player);
                /*
                if(event instanceof PlayerInteractEvent.LeftClickEmpty && event.getSide() == Side.CLIENT){
                    PacketHandler.INSTANCE.sendToServer(new LeftClickPacket());
                }
                */
            }
        }

        if(event instanceof PlayerInteractEvent.EntityInteract){
            if (event.getHand() == EnumHand.MAIN_HAND) {
                ItemStack item = event.getItemStack();
                EntityPlayer player = event.getEntityPlayer();
                Entity target = ((PlayerInteractEvent.EntityInteract) event).getTarget();
            }
        }
    }

    @SubscribeEvent
    public void onMine(BlockEvent.BreakEvent event){
        EntityPlayer player = event.getPlayer();
        if(player != null){

        }
    }
}
