package serena.bosscreatortool.items.equipments.items;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;
import serena.bosscreatortool.items.ItemUtils;
import serena.bosscreatortool.items.equipments.material.IEquipmentItem;

import javax.annotation.Nullable;
import java.util.List;

public abstract class ToolSword extends ItemSword implements IEquipmentItem {

    public ToolSword(String materialName, float damage){
        super(EnumHelper.addToolMaterial(materialName, 4, 1000, 2, damage - 3, 20));
    }

    public ToolSword(ToolMaterial tm){
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

