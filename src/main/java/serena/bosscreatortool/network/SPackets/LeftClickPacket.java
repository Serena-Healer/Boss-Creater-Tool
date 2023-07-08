package serena.bosscreatortool.network.SPackets;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import serena.bosscreatortool.BossCreatorTool;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

public class LeftClickPacket implements IMessage {

    public LeftClickPacket() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public void fromBytes(ByteBuf buffer) {
    }

    @Override
    public void toBytes(ByteBuf buffer) {
    }

    public static class PacketSyncHandler implements IMessageHandler<LeftClickPacket, IMessage> {
        @Override
        public IMessage onMessage(LeftClickPacket message, MessageContext ctx) {
            ctx.getServerHandler().player.getServerWorld().addScheduledTask(new Runnable() {
                @Override
                public void run() {
                    World world = BossCreatorTool.proxy.getWorld();
                    if (world == null || ctx.side == Side.CLIENT)
                        return;
                    EntityPlayer player = ctx.getServerHandler().player;
                    MinecraftForge.EVENT_BUS.post(new PlayerInteractEvent.LeftClickEmpty(player));
                }
            });
            return null;
        }
    }

}
