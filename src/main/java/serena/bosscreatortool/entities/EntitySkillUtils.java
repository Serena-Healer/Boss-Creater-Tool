package serena.bosscreatortool.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class EntitySkillUtils {

    public static void saySomething(Entity entity, String str){
        sendMessageToAll(entity, "<" + entity.getDisplayName() + ">" + str);
    }

    public static void sendMessageToAll(Entity entity, String str){
        entity.getEntityWorld().getLoadedEntityList().forEach(e -> {
            if(e instanceof EntityPlayer && entity.getDistance(e) <= 50){
                e.sendMessage(new TextComponentString(str));
            }
        });
    }

}
