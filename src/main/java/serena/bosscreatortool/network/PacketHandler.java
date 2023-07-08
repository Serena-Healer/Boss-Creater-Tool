package serena.bosscreatortool.network;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import serena.bosscreatortool.BossCreatorTool;
import serena.bosscreatortool.network.CPackets.EntityDataSyncPacket;
import serena.bosscreatortool.network.CPackets.PlayerDataSyncPacket;
import serena.bosscreatortool.network.SPackets.LeftClickPacket;

public class PacketHandler {

    public static SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(BossCreatorTool.MOD_ID);
    static int id = 0;

    public static void initPackets() {
        INSTANCE.registerMessage(PlayerDataSyncPacket.PacketSyncHandler.class, PlayerDataSyncPacket.class, id++, Side.CLIENT);
        INSTANCE.registerMessage(EntityDataSyncPacket.PacketSyncHandler.class, EntityDataSyncPacket.class, id++, Side.CLIENT);
        INSTANCE.registerMessage(LeftClickPacket.PacketSyncHandler.class, LeftClickPacket.class, id++, Side.SERVER);
    }

}