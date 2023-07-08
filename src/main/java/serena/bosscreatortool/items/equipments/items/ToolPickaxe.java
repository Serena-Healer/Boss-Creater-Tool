package serena.bosscreatortool.items.equipments.items;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;
import serena.bosscreatortool.items.ItemUtils;
import serena.bosscreatortool.items.equipments.material.IEquipmentItem;

import javax.annotation.Nullable;
import java.util.List;

public abstract class ToolPickaxe extends ItemPickaxe implements IEquipmentItem {

    public ToolPickaxe(String materialName, float damage, int level, float eff){
        super(EnumHelper.addToolMaterial(materialName, level, 1000, eff, damage - 2, 20));
    }

    public ToolPickaxe(ToolMaterial tm){
        super(tm);
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
