package serena.bosscreatortool.util.potion;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.ResourceLocation;
import serena.bosscreatortool.BossCreatorTool;

import javax.annotation.Nullable;

public class ResistResetPotion extends Potion {

    public static ResourceLocation texture = new ResourceLocation(BossCreatorTool.MOD_ID, "textures/gui/potion.png");

    public ResistResetPotion(String name, boolean isBadEffectIn, int liquidColorIn, int iconX, int iconY) {
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


    @Override
    public void affectEntity(@Nullable Entity source, @Nullable Entity indirectSource, EntityLivingBase entityLivingBaseIn, int amplifier, double health) {
        super.affectEntity(source, indirectSource, entityLivingBaseIn, amplifier, health);
        entityLivingBaseIn.hurtResistantTime = 0;
    }

    @Override
    public void performEffect(EntityLivingBase entityLivingBaseIn, int amplifier) {
        super.performEffect(entityLivingBaseIn, amplifier);
        entityLivingBaseIn.hurtResistantTime = 0;
    }

    @Override
    public boolean isInstant() {
        return true;
    }

    @Override
    public boolean isReady(int duration, int amplifier) {
        return true;
    }
}
