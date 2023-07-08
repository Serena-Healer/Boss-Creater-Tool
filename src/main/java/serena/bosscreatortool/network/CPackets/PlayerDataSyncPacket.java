package serena.bosscreatortool.network.CPackets;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import serena.bosscreatortool.BossCreatorTool;
import serena.bosscreatortool.data.player.CapabilityProvider;
import serena.bosscreatortool.data.player.PlayerDataHandler;

import java.io.IOException;

public class PlayerDataSyncPacket implements IMessage {

    private int entityId;
    private NBTTagCompound nbtTag;

    public PlayerDataSyncPacket() {
        // TODO Auto-generated constructor stub
    }

    public PlayerDataSyncPacket(EntityLivingBase entity) {
        entityId = entity.getEntityId();
        PlayerDataHandler data = CapabilityProvider.getPlayerData(entity);
        nbtTag = (data == null ? new NBTTagCompound() : data.getNBT());
        // ModMain.proxy.log.info("Creating Packet: {} Args: {}", this, entity);
    }

    @Override
    public void fromBytes(ByteBuf buffer) {
        entityId = buffer.readInt();
        try {
            nbtTag = DataSerializers.COMPOUND_TAG.read(new PacketBuffer(buffer));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void toBytes(ByteBuf buffer) {
        buffer.writeInt(entityId);
        DataSerializers.COMPOUND_TAG.write(new PacketBuffer(buffer), nbtTag);
    }

    public static class PacketSyncHandler implements IMessageHandler<PlayerDataSyncPacket, IMessage> {
        @Override
        public IMessage onMessage(PlayerDataSyncPacket message, MessageContext ctx) {
            // System.out.println("Client update");
            Minecraft.getMinecraft().addScheduledTask(new Runnable() {
                @Override
                public void run() {
                    World world = BossCreatorTool.proxy.getWorld();
                    if (world == null || !world.isRemote)
                        return;
                    Entity entity = world.getEntityByID(message.entityId);
                    if (entity != null && entity instanceof EntityPlayer) {
                        PlayerDataHandler cap = CapabilityProvider.getPlayerData(entity);
                        cap.readNBT(message.nbtTag);
                        //cap.markDirty();
                    }
                }
            });
            return null;
        }
    }
}
