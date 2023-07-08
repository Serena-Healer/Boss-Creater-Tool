package serena.bosscreatortool.items.equipments;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import serena.bosscreatortool.BossCreatorTool;

public class CreativeTab extends CreativeTabs {

    public static final CreativeTab TAB = new CreativeTab();
    public CreativeTab() {
        super(BossCreatorTool.MOD_ID);
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(Items.GLOWSTONE_DUST);
    }
}
