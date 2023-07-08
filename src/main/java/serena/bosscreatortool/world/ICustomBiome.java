package serena.bosscreatortool.world;

import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;

public interface ICustomBiome {

    BiomeManager.BiomeType getBiomeType();
    BiomeDictionary.Type getType();

}
