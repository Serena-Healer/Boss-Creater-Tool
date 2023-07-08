package serena.bosscreatortool.util.potion;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;

import javax.annotation.Nullable;

public class HealingPotionBase extends CustomPotionBase{

    double multiplier;
    String type;

    public HealingPotionBase(String name, boolean isBadEffectIn, int liquidColorIn, int iconX, int iconY, double multiplier, String type) {
        super(name, isBadEffectIn, liquidColorIn, iconX, iconY);
        this.multiplier = multiplier;
        this.type = type;
    }

    @Override
    public void affectEntity(@Nullable Entity source, @Nullable Entity indirectSource, EntityLivingBase entityLivingBaseIn, int amplifier, double health) {
        super.affectEntity(source, indirectSource, entityLivingBaseIn, amplifier, health);
        entityLivingBaseIn.heal((float) ((amplifier + 1) * multiplier));
    }

    @Override
    public void performEffect(EntityLivingBase entityLivingBaseIn, int amplifier) {
        super.performEffect(entityLivingBaseIn, amplifier);
        entityLivingBaseIn.heal((float) ((amplifier + 1) * multiplier));
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
