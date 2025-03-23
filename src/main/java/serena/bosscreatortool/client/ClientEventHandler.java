package serena.bosscreatortool.client;

import akka.japi.Pair;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.resources.I18n;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import serena.bosscreatortool.data.player.CapabilityProvider;
import serena.bosscreatortool.data.player.PlayerDataHandler;
import serena.bosscreatortool.items.IItemBase;
import serena.bosscreatortool.items.ItemUtils;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ClientEventHandler {

    @SubscribeEvent
    public void onDrawScreen(RenderGameOverlayEvent event){
        int width = event.getResolution().getScaledWidth();
        int height = event.getResolution().getScaledHeight();
        FontRenderer renderer = Minecraft.getMinecraft().fontRenderer;
        PlayerDataHandler data = CapabilityProvider.getPlayerData(Minecraft.getMinecraft().player);
        //if(event.getType() == RenderGameOverlayEvent.ElementType.ARMOR)event.setCanceled(true);
        if(event.getType() == RenderGameOverlayEvent.ElementType.HEALTH){
            //描画処理
            renderer.drawString("", 0, 0, -1);
            Minecraft.getMinecraft().getTextureManager().bindTexture(Gui.ICONS);
        }
    }

    @SubscribeEvent
    public void onDrawTooltip(ItemTooltipEvent event){
        if(!(event.getItemStack().getItem() instanceof IItemBase)){
            ItemUtils.setInformation(event.getItemStack(), event.getToolTip());
        }
    }

}
