package serena.bosscreatortool.util;

import net.minecraft.client.audio.MusicTicker;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.client.EnumHelperClient;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import serena.bosscreatortool.BossCreatorTool;

public class SoundsHandler {

    public static void init(){
    }

    public static SoundEvent registerSound(String name){
        ResourceLocation location = new ResourceLocation(BossCreatorTool.MOD_ID,name);
        SoundEvent event = new SoundEvent(location);
        event.setRegistryName(name);
        ForgeRegistries.SOUND_EVENTS.register(event);
        return event;
    }
}
