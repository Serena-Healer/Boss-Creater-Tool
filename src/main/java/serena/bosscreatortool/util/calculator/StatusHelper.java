package serena.bosscreatortool.util.calculator;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import serena.bosscreatortool.entities.ICustomEntity;

import java.util.concurrent.atomic.AtomicInteger;

public class StatusHelper {

    public static float getEffectiveHealth(EntityLivingBase entity){
        AtomicInteger ret = new AtomicInteger();
        entity.getTags().forEach(t -> {
            if(t.startsWith("ehp:")){
                String k = t.substring("ehp:".length());
                ret.set(Integer.parseInt(k));
            }
        });
        if(ret.get() != 0)return ret.get();

        if(entity instanceof ICustomEntity)return ((ICustomEntity) entity).getEffectiveHealth();

        return entity.getMaxHealth();
    }

}
