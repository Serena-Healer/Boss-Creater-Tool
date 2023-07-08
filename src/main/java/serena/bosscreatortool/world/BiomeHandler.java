package serena.bosscreatortool.world;

import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;

public class BiomeHandler {

    public static List<ICustomBiome> allBiomes = new ArrayList<>();

    public static void init(){
    }

    public static Biome register(String name, ICustomBiome biome){
        Biome b = (Biome) biome;
        b.setRegistryName(name);
        allBiomes.add(biome);
        ForgeRegistries.BIOMES.register(b);
        BiomeDictionary.addTypes(b, biome.getType());
        BiomeManager.addBiome(biome.getBiomeType(), new BiomeManager.BiomeEntry(b, 1));
        BiomeManager.addSpawnBiome(b);
        return b;
    }

}
