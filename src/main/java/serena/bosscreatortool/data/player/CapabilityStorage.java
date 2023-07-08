package serena.bosscreatortool.data.player;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

public class CapabilityStorage implements Capability.IStorage<PlayerDataHandler> {

    @Nullable
    @Override
    public NBTBase writeNBT(Capability<PlayerDataHandler> capability, PlayerDataHandler instance, EnumFacing side) {
        return instance.getNBT();
    }

    @Override
    public void readNBT(Capability<PlayerDataHandler> capability, PlayerDataHandler instance, EnumFacing side, NBTBase nbt) {
        instance.readNBT(nbt);
    }

}
