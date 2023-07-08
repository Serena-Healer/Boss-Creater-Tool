package serena.bosscreatortool.items.equipments.items;

import com.google.common.collect.Sets;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;
import serena.bosscreatortool.items.ItemUtils;
import serena.bosscreatortool.items.equipments.material.IEquipmentItem;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Set;

public abstract class ToolAxe extends ItemTool implements IEquipmentItem {

    private static final Set<Block> EFFECTIVE_ON = Sets.newHashSet(Blocks.PLANKS, Blocks.BOOKSHELF, Blocks.LOG, Blocks.LOG2, Blocks.CHEST, Blocks.PUMPKIN, Blocks.LIT_PUMPKIN, Blocks.MELON_BLOCK, Blocks.LADDER, Blocks.WOODEN_BUTTON, Blocks.WOODEN_PRESSURE_PLATE);

    public ToolAxe(String materialName, float damage, int level, float eff){
        super(damage, -2.8F, EnumHelper.addToolMaterial(materialName, level, 1000, eff, damage, 20), EFFECTIVE_ON);
    }

    public ToolAxe(ToolMaterial tm, float damage){
        super(damage, -2.8F, tm, EFFECTIVE_ON);
    }

    public float getDestroySpeed(ItemStack stack, IBlockState state)
    {
        Material material = state.getMaterial();
        return material != Material.WOOD && material != Material.PLANTS && material != Material.VINE ? super.getDestroySpeed(stack, state) : this.efficiency;
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
