package serena.bosscreatortool.items.equipments.items;

import net.minecraft.client.resources.I18n;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import serena.bosscreatortool.items.ItemHandler;
import java.util.Map;

public abstract class ArmorSet {

    ItemArmor.ArmorMaterial material;

    public SettedArmor HELMET;
    public SettedArmor CHESTPLATE;
    public SettedArmor LEGGINGS;
    public SettedArmor BOOTS;

    public ArmorSet(ItemArmor.ArmorMaterial am){
        this.material = am;
        HELMET = (SettedArmor) ItemHandler.register(am.getName() + "_helmet", new SettedArmor(am, EntityEquipmentSlot.HEAD, this));
        CHESTPLATE = (SettedArmor) ItemHandler.register(am.getName() + "_chestplate", new SettedArmor(am, EntityEquipmentSlot.CHEST, this));
        LEGGINGS = (SettedArmor) ItemHandler.register(am.getName() + "_leggings", new SettedArmor(am, EntityEquipmentSlot.LEGS, this));
        BOOTS = (SettedArmor) ItemHandler.register(am.getName() + "_boots", new SettedArmor(am, EntityEquipmentSlot.FEET, this));
    }

    static class SettedArmor extends ToolArmor{

        ArmorSet set;

        public SettedArmor(ArmorMaterial am, EntityEquipmentSlot slot, ArmorSet set) {
            super(am, slot);
            this.set = set;
        }

        public ArmorSet getArmorSet(){
            return set;
        }

        @Override
        public String getItemStackDisplayName(ItemStack stack) {
            String name = I18n.format("item.tools.name", I18n.format("armor." + set.material.getName() + ".name"), I18n.format("tools." + getEquipmentSlot().getName() + ".name"));
            return name;
        }
    }

}
