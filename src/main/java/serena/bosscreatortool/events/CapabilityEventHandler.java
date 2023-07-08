package serena.bosscreatortool.events;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import serena.bosscreatortool.BossCreatorTool;
import serena.bosscreatortool.data.entity.EntityCapabilityProvider;
import serena.bosscreatortool.data.entity.EntityDataHandler;
import serena.bosscreatortool.data.player.CapabilityProvider;
import serena.bosscreatortool.data.player.PlayerDataHandler;
import serena.bosscreatortool.entities.ICustomEntity;

import java.util.ArrayList;
import java.util.List;

public class CapabilityEventHandler {

    @SubscribeEvent
    public void initCapabilities(AttachCapabilitiesEvent<Entity> event){
        Entity entity = event.getObject();
        if(entity != null){
            if(entity instanceof EntityPlayer) {
                event.addCapability(new ResourceLocation(BossCreatorTool.MOD_ID, "LightSpiritRPG_player"), new CapabilityProvider());
            }
            event.addCapability(new ResourceLocation(BossCreatorTool.MOD_ID, "LightSpiritRPG_entity"), new EntityCapabilityProvider());
        }
    }

    @SubscribeEvent
    public void reloadPlayerCapability(PlayerEvent.Clone event){
        PlayerDataHandler dataOld = CapabilityProvider.getPlayerData(event.getOriginal());
        PlayerDataHandler dataNew = CapabilityProvider.getPlayerData(event.getEntityPlayer());
        dataNew.readNBT(dataOld.getNBT());
    }

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if(event.phase == TickEvent.Phase.END) {
            PlayerDataHandler data = CapabilityProvider.getPlayerData(event.player);
            if (data != null) {
                data.onTick();
            }
        }
    }

    @SubscribeEvent
    public void onEntityTick(TickEvent.WorldTickEvent event){
        if(event.phase == TickEvent.Phase.END) {
            event.world.getLoadedEntityList().forEach(e -> {
                EntityDataHandler data = EntityCapabilityProvider.getEntityData(e);
                if (data != null) {
                    data.onTick();
                }

                if(e instanceof ICustomEntity){
                    ((ICustomEntity) e).onTick();
                }
            });
        }
    }

}
