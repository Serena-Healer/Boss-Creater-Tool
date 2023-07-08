package serena.bosscreatortool.proxy;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import serena.bosscreatortool.blocks.BlockHandler;
import serena.bosscreatortool.blocks.IBlockWithVariants;
import serena.bosscreatortool.client.ClientEventHandler;
import serena.bosscreatortool.client.KeyHandler;
import serena.bosscreatortool.entities.EntityHandler;
import serena.bosscreatortool.items.ItemHandler;

public class ClientProxy extends CommonProxy{


    public void construct(FMLConstructionEvent event) {
        super.construct(event);
        MinecraftForge.EVENT_BUS.register(this);
    }

    public void preinit(FMLPreInitializationEvent event) {
        super.preinit(event);
        MinecraftForge.EVENT_BUS.register(new KeyHandler());
        MinecraftForge.EVENT_BUS.register(new ClientEventHandler());
        KeyHandler.init();
    }

    public void init(FMLInitializationEvent event) {
        super.init(event);
    }

    public void postinit(FMLPostInitializationEvent event) {
        super.postinit(event);
    }


    @Mod.EventBusSubscriber
    public static class ObjectRegistryHandler {
        @SubscribeEvent
        @SideOnly(Side.CLIENT)
        public static void registerModels(ModelRegistryEvent event) {
            ItemHandler.allItems.forEach(i -> {
                ModelLoader.setCustomModelResourceLocation((Item) i, 0, new ModelResourceLocation(i.modelJsonName(), "inventory"));
            });
            BlockHandler.allBlocks.forEach(b -> {
                if(b instanceof IBlockWithVariants){
                    for(int i=0; i<((IBlockWithVariants) b).getModelCount(); i++){
                        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock((Block) b), i, new ModelResourceLocation(((Block) b).getRegistryName() + "_" + ((IBlockWithVariants) b).getModelName(i), "inventory"));
                    }
                }else {
                    ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock((Block) b), 0, new ModelResourceLocation(((Block) b).getRegistryName(), "inventory"));
                }
            });
            EntityHandler.registerModels();
        }
    }

    @Override
    public World getWorld() {
        return FMLClientHandler.instance().getWorldClient();
    }
}
