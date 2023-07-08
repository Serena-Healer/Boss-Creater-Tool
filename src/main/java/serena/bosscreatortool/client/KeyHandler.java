package serena.bosscreatortool.client;


import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;
import serena.bosscreatortool.BossCreatorTool;
import serena.bosscreatortool.util.SoundsHandler;

@SideOnly(Side.CLIENT)
public class KeyHandler {
    public static final KeyBinding OPEN_MENU = new KeyBinding("key.help", Keyboard.KEY_K,"key.categories." + BossCreatorTool.MOD_ID);

    public static void init(){
        ClientRegistry.registerKeyBinding(OPEN_MENU);
    }

    @SubscribeEvent
    public void tickEvent(TickEvent.PlayerTickEvent e) {
        onTick(e);
    }

    private void onTick(TickEvent.PlayerTickEvent e) {
        if (e.side == Side.SERVER)
            return;

        if (e.phase == TickEvent.Phase.START) {
            if (FMLClientHandler.instance().getClient().inGameHasFocus && OPEN_MENU.isPressed()) {
                FMLClientHandler.instance().showGuiScreen(new ManualScreen());
            }
        }
    }
}
