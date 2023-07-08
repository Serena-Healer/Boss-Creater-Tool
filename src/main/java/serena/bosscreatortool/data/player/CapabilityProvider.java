package serena.bosscreatortool.data.player;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class CapabilityProvider implements ICapabilitySerializable<NBTTagCompound> {

    @CapabilityInject(PlayerDataHandler.class)
    public static final Capability<PlayerDataHandler> CAPABILITY = null;

    private PlayerDataHandler cap;

    public CapabilityProvider(){
        cap = CAPABILITY.getDefaultInstance();
    }

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == CAPABILITY;
    }

    @Nullable
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
        return hasCapability(capability, facing) ? (T) cap : null;
    }

    @Override
    public NBTTagCompound serializeNBT() {
        return cap.getNBT();
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        cap.readNBT(nbt);
    }

    public static PlayerDataHandler getPlayerData(Entity player){
        PlayerDataHandler data = player.getCapability(CAPABILITY, null);
        if(data == null){
            return null;
        }else {
            if(player instanceof EntityPlayer) {
                data.setOwner((EntityPlayer) player);
            }
            return data;
        }
    }


    public static PlayerDataHandler justGetPlayerData(Entity player){
        PlayerDataHandler data = player.getCapability(CAPABILITY, null);
        if(data == null){
            return null;
        }else {
            return data;
        }
    }

}
