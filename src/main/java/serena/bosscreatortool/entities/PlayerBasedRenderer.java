package serena.bosscreatortool.entities;

import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerArrow;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.client.renderer.entity.layers.LayerElytra;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;
import serena.bosscreatortool.BossCreatorTool;

import javax.annotation.Nullable;

public class PlayerBasedRenderer extends RenderBiped<EntityLiving> {

    private final boolean smallArms;
    protected String filename = "";

    public PlayerBasedRenderer(RenderManager manager, String name, boolean slim){
        super(manager, new ModelPlayer(0.0F,slim),0.5F);
        this.smallArms = slim;
        this.addLayer(new LayerBipedArmor(this));
        this.addLayer(new LayerHeldItem(this));
        this.addLayer(new LayerArrow(this));
        this.addLayer(new LayerElytra(this));
        filename = name;
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityLiving entity) {
        return new ResourceLocation(BossCreatorTool.MOD_ID + ":textures/entity/"+filename+".png");
    }

}
