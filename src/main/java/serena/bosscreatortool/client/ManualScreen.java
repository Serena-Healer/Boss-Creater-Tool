package serena.bosscreatortool.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.ResourceLocation;
import serena.bosscreatortool.BossCreatorTool;

import java.io.IOException;

public class ManualScreen extends GuiScreen {

    Mode mode = Mode.TOP;
    int scrollY = 0;

    int holdStarted = 0;

    enum Mode{
        TOP("top"),
        DEFINITION("definition"),
        POTIONS("potions"),
        TAGS("tags"),
        LICENSE("license");

        public String name;
        Mode(String name){
            this.name = name;
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
        ScaledResolution res = new ScaledResolution(mc);
        int width = res.getScaledWidth();
        int height = res.getScaledHeight();
        int defaultColor = 9127187;

        Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(BossCreatorTool.MOD_ID, "gui/mainscreen.png"));
        drawTexturedModalRect(width / 2 - 128, height / 2 - 96, 0, 0, 256, 192);

        drawRect(width / 2 - 46, height / 2 - 88, width / 2 - 45, height / 2 + 88, -7650029);

        int i = 0;
        for(Mode mode : Mode.values()) {
            fontRenderer.drawString((mode == this.mode ? "§n" : "") + I18n.format(mode.name + ".title"), width / 2 - 120, height / 2 - 88 + i * fontRenderer.FONT_HEIGHT, defaultColor);
            i++;
        }

        int j = 0;
        int l = 0;
        while(true){
            if(I18n.hasKey(mode.name + ".str" + l)){
                String str = I18n.format(mode.name + ".str" + l);
                int x = 0;
                for(int k=0; k<str.length(); k++) {
                    if(x > 44 + 128 - 8){
                        j++;
                        x = 0;
                    }
                    int y = fontRenderer.FONT_HEIGHT * (j - (scrollY / fontRenderer.FONT_HEIGHT));
                    if (y >= 0 && y < 172) {
                        fontRenderer.drawString(String.valueOf(str.charAt(k)), width / 2 - 44 + x, y + height / 2 - 88, defaultColor);
                    }
                    x += fontRenderer.getStringWidth(String.valueOf(str.charAt(k)));
                }
                j++;
                l++;
            }else{
                break;
            }
        }
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);

        int i = 0;
        for (Mode mode : Mode.values()) {
            if (mouseX > width / 2 - 120 && mouseX < width / 2 - 43) {
                if (mouseY >= height / 2 - 88 + i * fontRenderer.FONT_HEIGHT && mouseY < height / 2 - 88 + (i + 1) * fontRenderer.FONT_HEIGHT) {
                    this.mode = mode;
                    Minecraft.getMinecraft().player.playSound(SoundEvents.BLOCK_STONE_BUTTON_CLICK_ON, 1, 2);
                    scrollY = 0;
                }
            }
            i++;
        }
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    @Override
    protected void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
        super.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
        if(holdStarted == -1){
            holdStarted = scrollY + mouseY;
        }
        scrollY = holdStarted - mouseY;
        scrollY = Math.max(scrollY, 0);
    }

    @Override
    protected void mouseReleased(int mouseX, int mouseY, int state) {
        super.mouseReleased(mouseX, mouseY, state);
        holdStarted = -1;
    }
}
