package serena.bosscreatortool.items.equipments.items;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import serena.bosscreatortool.items.ItemUtils;
import serena.bosscreatortool.items.equipments.material.IEquipmentItem;

import javax.annotation.Nullable;
import java.util.List;

public abstract class ToolBow extends ItemBow implements IEquipmentItem {

    float bowDamage;

    public ToolBow(float bowDamage){
        super();
        this.bowDamage = bowDamage;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        ItemUtils.setInformation(stack, tooltip);
    }

    @Override
    public EntityArrow customizeArrow(EntityArrow arrow) {
        arrow = super.customizeArrow(arrow);
        arrow.setDamage(arrow.getDamage() / 5.0D * (bowDamage));
        return arrow;
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
        return super.getMaxItemUseDuration(stack);
    }

}
