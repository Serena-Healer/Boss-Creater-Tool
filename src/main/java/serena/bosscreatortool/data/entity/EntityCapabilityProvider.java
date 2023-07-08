package serena.bosscreatortool.data.entity;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class EntityCapabilityProvider implements ICapabilitySerializable<NBTTagCompound> {

    @CapabilityInject(EntityDataHandler.class)
    public static final Capability<EntityDataHandler> CAPABILITY = null;

    public EntityDataHandler cap;

    public EntityCapabilityProvider(){
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

    public static EntityDataHandler getEntityData(Entity entity){
        EntityDataHandler data = entity.getCapability(CAPABILITY, null);
        if(data == null){
            return null;
        }else{
            data.setOwner(entity);
            return data;
        }
    }

}
