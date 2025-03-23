package serena.bosscreatortool.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import serena.bosscreatortool.BossCreatorTool;
import serena.bosscreatortool.entities.instance.WhiteGarden;

import java.util.ArrayList;
import java.util.List;

public class EntityHandler {

    private static int id = 0;
    public static List<Class<? extends IEntityBase>> allEntities = new ArrayList<>();

    public static void init(){
        register("white_garden", WhiteGarden.class);
    }

    @SideOnly(Side.CLIENT)
    public static void registerModels(){
        playerEntityRender("white_garden", WhiteGarden.class, true);
    }

    @SideOnly(Side.CLIENT)
    public static void playerEntityRender(String key, Class<? extends EntityLiving> c, boolean slim){
        RenderingRegistry.registerEntityRenderingHandler(c, manager -> new PlayerBasedRenderer(manager,key,slim));
    }

    public static Class<? extends IEntityBase> register(String name, Class<? extends IEntityBase> entity){
        EntityRegistry.registerModEntity(new ResourceLocation(BossCreatorTool.MOD_ID, name), (Class<? extends Entity>) entity, name, id++, BossCreatorTool.INSTANCE,50,1,true,-1,-1);
        allEntities.add(entity);
        return entity;
    }

}
