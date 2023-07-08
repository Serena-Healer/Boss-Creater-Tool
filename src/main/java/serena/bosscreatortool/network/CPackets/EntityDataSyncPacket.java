package serena.bosscreatortool.network.CPackets;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import serena.bosscreatortool.BossCreatorTool;
import serena.bosscreatortool.data.entity.EntityCapabilityProvider;
import serena.bosscreatortool.data.entity.EntityDataHandler;

import java.io.IOException;

public class EntityDataSyncPacket implements IMessage {
    private int entityId;
    private NBTTagCompound nbtTag;

    public EntityDataSyncPacket() {
        // TODO Auto-generated constructor stub
    }

    public EntityDataSyncPacket(Entity entity) {
        entityId = entity.getEntityId();
        EntityDataHandler data = EntityCapabilityProvider.getEntityData(entity);
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

    public static class PacketSyncHandler implements IMessageHandler<EntityDataSyncPacket, IMessage> {
        @Override
        public IMessage onMessage(EntityDataSyncPacket message, MessageContext ctx) {
            // System.out.println("Client update");
            Minecraft.getMinecraft().addScheduledTask(new Runnable() {
                @Override
                public void run() {
                    World world = BossCreatorTool.proxy.getWorld();
                    if (world == null || !world.isRemote)
                        return;
                    Entity entity = world.getEntityByID(message.entityId);
                    if (entity instanceof EntityLivingBase) {
                        EntityDataHandler cap = EntityCapabilityProvider.getEntityData(entity);
                        cap.readNBT(message.nbtTag);
                        //cap.markDirty();
                    }
                }
            });
            return null;
        }
    }

}
