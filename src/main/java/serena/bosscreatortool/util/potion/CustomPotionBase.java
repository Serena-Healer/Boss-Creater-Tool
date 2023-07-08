package serena.bosscreatortool.util.potion;

import net.minecraft.client.Minecraft;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import serena.bosscreatortool.BossCreatorTool;

public class CustomPotionBase extends Potion {

    public static ResourceLocation texture = new ResourceLocation(BossCreatorTool.MOD_ID, "textures/gui/potion.png");

    public CustomPotionBase(String name, boolean isBadEffectIn, int liquidColorIn, int iconX, int iconY) {
        super(isBadEffectIn, liquidColorIn);
        setPotionName("effect." + name);
        setIconIndex(iconX, iconY);
        setRegistryName(new ResourceLocation(BossCreatorTool.MOD_ID, name));
    }

    @Override
    public boolean hasStatusIcon() {
        Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
        return true;
    }

}
