package serena.bosscreatortool.client.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;

public class GuiUtils {

    public static void drawNumberRight(FontRenderer renderer, int number, int rightX, int y, int color, boolean shadow){
        drawStringRight(renderer, Integer.toString(number), rightX, y, color, shadow);
    }

    public static void drawStringRight(FontRenderer renderer, String str, int rightX, int y, int color, boolean shadow){
        if(shadow) {
            renderer.drawStringWithShadow(str, rightX - renderer.getStringWidth(str), y, color);
        }else{
            renderer.drawString(str, rightX - renderer.getStringWidth(str), y, color);
        }
    }

}
