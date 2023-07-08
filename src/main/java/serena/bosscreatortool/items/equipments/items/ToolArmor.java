package serena.bosscreatortool.items.equipments.items;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import serena.bosscreatortool.items.ItemUtils;
import serena.bosscreatortool.items.equipments.material.IEquipmentItem;

import javax.annotation.Nullable;
import java.util.List;

public abstract class ToolArmor extends ItemArmor implements IEquipmentItem {

    public ToolArmor(ArmorMaterial am, EntityEquipmentSlot slot) {
        super(am, 0, slot);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        ItemUtils.setInformation(stack, tooltip);
    }

    @Override
    public String modelJsonName() {
        return getRegistryName().toString();
    }

}
