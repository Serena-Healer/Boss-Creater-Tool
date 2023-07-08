package serena.bosscreatortool.proxy;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import serena.bosscreatortool.BossCreatorTool;
import serena.bosscreatortool.blocks.BlockHandler;
import serena.bosscreatortool.blocks.IBlockWithBlockItem;
import serena.bosscreatortool.blocks.IBlockWithTileEntity;
import serena.bosscreatortool.blocks.IBlockWithVariants;
import serena.bosscreatortool.data.entity.EntityCapabilityStorage;
import serena.bosscreatortool.data.entity.EntityDataHandler;
import serena.bosscreatortool.data.player.CapabilityStorage;
import serena.bosscreatortool.data.player.PlayerDataHandler;
import serena.bosscreatortool.entities.EntityHandler;
import serena.bosscreatortool.events.CapabilityEventHandler;
import serena.bosscreatortool.events.PlayerEventHandler;
import serena.bosscreatortool.items.ItemHandler;
import serena.bosscreatortool.network.PacketHandler;
import serena.bosscreatortool.util.GuiHandler;
import serena.bosscreatortool.util.OreDict;
import serena.bosscreatortool.util.SoundsHandler;
import serena.bosscreatortool.util.potion.PotionHandler;
import serena.bosscreatortool.world.BiomeHandler;
import serena.bosscreatortool.world.DimensionHandler;

import java.util.Objects;

public abstract class CommonProxy {

    public void construct(FMLConstructionEvent event){
        MinecraftForge.EVENT_BUS.register(new CapabilityEventHandler());
        MinecraftForge.EVENT_BUS.register(new PlayerEventHandler());
    }

    public void preinit(FMLPreInitializationEvent event) {
        PacketHandler.initPackets();
        System.out.println("Packet inited");
        ItemHandler.init();
        System.out.println("Item inited");
        BlockHandler.init();
        System.out.println("Block inited");
        OreDict.init();
        System.out.println("Ore Dictionary inited");
        EntityHandler.init();
        System.out.println("Entities registered");
        PotionHandler.init();
        System.out.println("Potion inited");
        BiomeHandler.init();
        System.out.println("Biome inited");
        DimensionHandler.init();
        System.out.println("Dimension inited");
        DimensionHandler.registerDims();
        System.out.println("Dimension registered");
        CapabilityManager.INSTANCE.register(PlayerDataHandler.class, new CapabilityStorage(), PlayerDataHandler.class);
        CapabilityManager.INSTANCE.register(EntityDataHandler.class, new EntityCapabilityStorage(), EntityDataHandler.class);
    }

    public void init(FMLInitializationEvent event) {
        System.out.println("Registering GUI");
        NetworkRegistry.INSTANCE.registerGuiHandler(BossCreatorTool.INSTANCE, new GuiHandler());
        System.out.println("Registered GUI");
    }

    public void postinit(FMLPostInitializationEvent event) {
    }

    @Mod.EventBusSubscriber
    public static class ObjectRegistryHandler {
        @SubscribeEvent
        public static void addItems(RegistryEvent.Register<Item> event) {
            ItemHandler.allItems.forEach(i -> {
                event.getRegistry().register((Item) i);
            });
            BlockHandler.allBlocks.forEach(b -> {
                if(b instanceof IBlockWithBlockItem){
                    event.getRegistry().register(((IBlockWithBlockItem) b).getItemBlock().setRegistryName(Objects.requireNonNull(((Block) b).getRegistryName())));
                }else if(b instanceof IBlockWithVariants) {
                    event.getRegistry().register(new ItemBlock((Block) b){
                        @Override
                        public boolean getHasSubtypes() {
                            return true;
                        }

                        @Override
                        public int getMetadata(int damage) {
                            return damage;
                        }

                        @Override
                        public String getTranslationKey(ItemStack stack) {
                            return super.getTranslationKey(stack) + "_" + ((IBlockWithVariants)block).getModelName(stack.getMetadata());
                        }
                    }.setRegistryName(Objects.requireNonNull(((Block) b).getRegistryName())));
                }else{
                    event.getRegistry().register(new ItemBlock((Block) b).setRegistryName(Objects.requireNonNull(((Block) b).getRegistryName())));
                }
            });
        }

        @SubscribeEvent
        public static void addBlocks(RegistryEvent.Register<Block> event) {
            BlockHandler.allBlocks.forEach(b -> {
                event.getRegistry().register((Block) b);
                if(b instanceof IBlockWithTileEntity){
                    GameRegistry.registerTileEntity(((IBlockWithTileEntity) b).getTileEntity(), ((IBlockWithTileEntity) b).getTileEntityName());
                }
            });
        }

        @SubscribeEvent
        public static void addSounds(RegistryEvent.Register<SoundEvent> event){
            SoundsHandler.init();
        }

    }

    public abstract World getWorld();
}
