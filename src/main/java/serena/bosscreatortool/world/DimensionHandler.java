package serena.bosscreatortool.world;

import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraftforge.common.DimensionManager;

import java.util.ArrayList;
import java.util.List;

public class DimensionHandler {

    public static List<DimensionType> allDimensions = new ArrayList<>();
    public static int id = 0;
    public static final int ID_START = 2565;

    public static void init(){
    }

    public static DimensionType register(int id, String name, String suffix, Class<? extends WorldProvider> generator){
        DimensionType type = DimensionType.register(name, suffix, ID_START + id, generator, false);
        allDimensions.add(type);
        return type;
    }

    public static void registerDims(){
        allDimensions.forEach(d -> {
            DimensionManager.registerDimension(d.getId(), d);
        });
    }

}
