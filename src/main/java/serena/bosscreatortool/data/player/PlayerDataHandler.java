package serena.bosscreatortool.data.player;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.WorldServer;
import serena.bosscreatortool.network.CPackets.PlayerDataSyncPacket;
import serena.bosscreatortool.network.PacketHandler;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class PlayerDataHandler {

    //Memo チュートリアルは創世神のお告げという設定にする

    public EntityPlayer owner;
    boolean reserveUpdate = true;

    public static final int dataVersion = 1;

    public void setOwner(EntityPlayer player){
        owner = player;
    }

    public NBTTagCompound getNBT(){
        NBTTagCompound nbt = new NBTTagCompound();

        nbt.setInteger("version", dataVersion);
        return nbt;
    }

    public void readNBT(NBTBase nbtin){
        if(nbtin instanceof NBTTagCompound){
            NBTTagCompound nbt = (NBTTagCompound) nbtin;
            if(nbt.hasKey("version")) {
                int version = nbt.getInteger("version");
                if(version != dataVersion){
                    adjustVersion(version);
                }
            }else{
                reset();
            }
        }
    }

    public void reset(){
        update();
    }

    //アップデートする
    public void adjustVersion(int oldVersion){

    }

    public void syncCapability(){
        if(owner != null && !owner.world.isRemote){
            PlayerDataSyncPacket packet = new PlayerDataSyncPacket(owner);
            ((WorldServer)(owner.world)).getPlayers(EntityPlayerMP.class, entityPlayerMP -> true).forEach(p -> {
                if(p != null){
                    PacketHandler.INSTANCE.sendTo(packet, (EntityPlayerMP) p);
                }
            });
        }
    }

    public void onTick(){
        if(reserveUpdate){
            if(!owner.isDead && !owner.world.isRemote){
                reserveUpdate = false;
                syncCapability();
            }
        }
    }

    public void update(){
        reserveUpdate = true;
    }

}
