package serena.bosscreatortool.blocks;

import net.minecraft.block.Block;
import serena.bosscreatortool.items.equipments.CreativeTab;

import java.util.ArrayList;
import java.util.List;

public class BlockHandler {

    public static List<IBlockBase> allBlocks = new ArrayList<>();
    public static void init(){
    }

    public static Block register(String name, Block block){
        block.setRegistryName(name);
        block.setTranslationKey(name);
        block.setCreativeTab(CreativeTab.TAB);
        allBlocks.add((IBlockBase) block);
        return block;
    }

}
