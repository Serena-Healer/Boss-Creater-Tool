package serena.bosscreatortool.util.calculator;

import net.minecraft.util.DamageSource;
import serena.bosscreatortool.enums.EnumDamageType;

public class DamageTypeHelper {

    public static EnumDamageType getDamageType(DamageSource source){
        if(source.isExplosion() || source.isFireDamage() || source.isMagicDamage() || source.getDamageType().equals("magic")){
            return EnumDamageType.MAGIC;
        }else if(source.isProjectile() || source.getDamageType().equals("mob") || source.getDamageType().equals("player")){
            return EnumDamageType.PHISYCAL;
        }else{
            return EnumDamageType.TRUE_DAMAGE;
        }
    }

}
