package serena.bosscreatortool.util.calculator;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import serena.bosscreatortool.data.entity.EntityCapabilityProvider;
import serena.bosscreatortool.data.entity.EntityDataHandler;
import serena.bosscreatortool.data.player.CapabilityProvider;
import serena.bosscreatortool.data.player.PlayerDataHandler;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class AssistHelper {

    static Map<EntityPlayer, Set<Entity>> fightingAgainst = new HashMap<>();
    static Map<Entity, Map<EntityPlayer, Integer>> contribution = new HashMap<>();

    public static void heal(Entity dealer, EntityLivingBase target, double amount){
        if(dealer.world.isRemote)return;
        amount = Math.max(amount, target.getMaxHealth() - target.getHealth());
        if(dealer instanceof EntityPlayer) {
            PlayerDataHandler dealData = CapabilityProvider.getPlayerData(dealer);
            if(target instanceof EntityPlayer){
                contributeAssisting((EntityPlayer) dealer, (EntityPlayer) target, (int) Math.ceil(amount * 0.8));
            }
        }
        target.heal((float) amount);
    }

    public static void contributeAttacking(EntityPlayer dealer, Entity target, int amount){
        if(dealer.world.isRemote)return;
        fightingAgainst.putIfAbsent(dealer, new HashSet<>());
        fightingAgainst.get(dealer).add(target);

        contribution.putIfAbsent(target, new HashMap<>());
        Map<EntityPlayer, Integer> current = contribution.get(target);
        current.putIfAbsent(dealer, 0);
        int c = current.get(dealer);
        current.put(dealer, c + amount);

        clean();
    }

    public static void contributeAssisting(EntityPlayer dealer, EntityPlayer target, int amount){
        if(dealer.world.isRemote)return;
        fightingAgainst.putIfAbsent(target, new HashSet<>());
        fightingAgainst.get(target).forEach(e -> {
            contribution.putIfAbsent(e, new HashMap<>());
            Map<EntityPlayer, Integer> current = contribution.get(e);
            current.putIfAbsent(dealer, 0);
            int c = current.get(dealer);
            current.put(dealer, c + amount);
        });

        clean();
    }

    public static void clean(){
        Collection<EntityPlayer> keyRemoving = new HashSet<>();
        fightingAgainst.forEach((p, s) -> {
            Collection<Entity> keyRemoving2 = new HashSet<>();
            s.forEach(e -> {
                if(e.isDead)keyRemoving2.add(e);
            });
            s.removeAll(keyRemoving2);
        });

        Collection<Entity> keyRemoving3 = new HashSet<>();
        contribution.forEach((e, m) -> {
            if(e.isDead)keyRemoving3.add(e);
        });
        keyRemoving3.forEach(k -> {
            contribution.remove(k);
        });
    }

}
