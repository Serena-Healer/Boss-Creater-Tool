package serena.bosscreatortool.items.equipments.items;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;
import serena.bosscreatortool.items.ItemUtils;
import serena.bosscreatortool.items.equipments.material.IEquipmentItem;

import javax.annotation.Nullable;
import java.util.List;

public abstract class ToolShovel extends ItemSpade implements IEquipmentItem {

    public ToolShovel(String materialName, float damage, int level, float eff){
        super(EnumHelper.addToolMaterial(materialName, level, 1000, eff, damage, 20));
    }

    public ToolShovel(ToolMaterial tm){
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
