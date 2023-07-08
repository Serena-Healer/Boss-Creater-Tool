package serena.bosscreatortool.data.world;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.storage.WorldSavedData;

public class WorldDataHandler extends WorldSavedData {

    public WorldDataHandler() {
        super("spirit_of_light_data");
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {

    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        return compound;
    }

}
