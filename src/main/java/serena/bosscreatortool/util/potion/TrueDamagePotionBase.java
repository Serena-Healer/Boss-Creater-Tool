package serena.bosscreatortool.util.potion;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;

import javax.annotation.Nullable;

public class TrueDamagePotionBase extends CustomPotionBase{

    double multiplier;
    String type;

    public TrueDamagePotionBase(String name, boolean isBadEffectIn, int liquidColorIn, int iconX, int iconY, double multiplier, String type) {
        super(name, isBadEffectIn, liquidColorIn, iconX, iconY);
        this.multiplier = multiplier;
        this.type = type;
    }

    @Override
    public void affectEntity(@Nullable Entity source, @Nullable Entity indirectSource, EntityLivingBase entityLivingBaseIn, int amplifier, double health) {
        super.affectEntity(source, indirectSource, entityLivingBaseIn, amplifier, health);
    }

    @Override
    public void performEffect(EntityLivingBase entityLivingBaseIn, int amplifier) {
        super.performEffect(entityLivingBaseIn, amplifier);
        double damage = (amplifier + 1) * multiplier;
        if(entityLivingBaseIn.getHealth() < damage){
            entityLivingBaseIn.setHealth(0);
            entityLivingBaseIn.setDead();
        }else {
            entityLivingBaseIn.setHealth((float) (entityLivingBaseIn.getHealth() - damage));
        }
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
