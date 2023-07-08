package serena.bosscreatortool.data.entity;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

public class EntityCapabilityStorage implements Capability.IStorage<EntityDataHandler> {

    @Nullable
    @Override
    public NBTBase writeNBT(Capability<EntityDataHandler> capability, EntityDataHandler instance, EnumFacing side) {
        return instance.getNBT();
    }

    @Override
    public void readNBT(Capability<EntityDataHandler> capability, EntityDataHandler instance, EnumFacing side, NBTBase nbt) {
        instance.readNBT(nbt);
    }

}
