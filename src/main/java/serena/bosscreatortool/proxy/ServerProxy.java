package serena.bosscreatortool.proxy;

import net.minecraft.world.World;
import net.minecraftforge.fml.server.FMLServerHandler;

public class ServerProxy extends CommonProxy{
    @Override
    public World getWorld() {
        return FMLServerHandler.instance().getServer().getEntityWorld();
    }
}