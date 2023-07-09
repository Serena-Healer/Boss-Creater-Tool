package serena.bosscreatortool;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.registry.GameRegistry;
import serena.bosscreatortool.proxy.CommonProxy;

@Mod(
        modid = BossCreatorTool.MOD_ID,
        name = BossCreatorTool.MOD_NAME,
        version = BossCreatorTool.VERSION
)
public class BossCreatorTool {

    public static final String MOD_ID = "bosscreatortool";
    public static final String MOD_NAME = "BossCreatorTool";
    public static final String VERSION = "0.2";

    /**
     * This is the instance of your mod as created by Forge. It will never be null.
     */
    @Mod.Instance(MOD_ID)
    public static BossCreatorTool INSTANCE;

    @SidedProxy(clientSide = "serena.bosscreatortool.proxy.ClientProxy",serverSide = "serena.bosscreatortool.proxy.ServerProxy")
    public static CommonProxy proxy;


    @Mod.EventHandler
    public void construct(FMLConstructionEvent event){
        proxy.construct(event);
    }
    /**
     * This is the first initialization event. Register tile entities here.
     * The registry events below will have fired prior to entry to this method.
     */
    @Mod.EventHandler
    public void preinit(FMLPreInitializationEvent event) {
        proxy.preinit(event);
    }

    /**
     * This is the second initialization event. Register custom recipes
     */
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    /**
     * This is the final initialization event. Register actions from other mods here
     */
    @Mod.EventHandler
    public void postinit(FMLPostInitializationEvent event) {
        proxy.postinit(event);
    }

}
