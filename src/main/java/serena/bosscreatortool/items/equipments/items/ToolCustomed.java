package serena.bosscreatortool.items.equipments.items;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;
import serena.bosscreatortool.items.equipments.material.IEquipmentItem;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.List;

public abstract class ToolCustomed extends ItemTool implements IEquipmentItem {

    public ToolCustomed(String materialName, float damage, float atkSpd){
        super(EnumHelper.addToolMaterial(materialName, 4, 1000, 2, damage, 20), new HashSet<>());
        this.attackSpeed = atkSpd;
    }

    public ToolCustomed(ToolMaterial tm, float damageModifier, float atkSpd){
        super(tm, new HashSet<>());
        this.attackDamage = tm.getAttackDamage() + damageModifier;
        this.attackSpeed = atkSpd;
    }

    @Override
    public String modelJsonName() {
        return getRegistryName().toString();
    }

}
