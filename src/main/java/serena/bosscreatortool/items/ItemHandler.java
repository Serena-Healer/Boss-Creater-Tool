package serena.bosscreatortool.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import serena.bosscreatortool.items.equipments.CreativeTab;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemHandler {

    public static List<IItemBase> allItems = new ArrayList<>();

    public static void init(){
        //register("resetitem", new ResetItem());
    }

    public static Item register(String name, Item item){
        item.setRegistryName(name);
        item.setTranslationKey(name);
        item.setCreativeTab(CreativeTab.TAB);
        allItems.add((IItemBase) item);
        return item;
    }

}
