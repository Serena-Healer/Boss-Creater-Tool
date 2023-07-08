package serena.bosscreatortool.util;

import net.minecraft.item.ItemStack;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class Utilities {

    public static String removeSection(String str){
        String ret = "";
        for(int i=0; i<str.length(); i++){
            if(str.charAt(i) == '§'){
                i++;
            }else{
                ret += str.charAt(i);
            }
        }
        return ret;
    }

    public static String connectList(List<String> list, String interval){
        AtomicReference<String> ret = new AtomicReference<>("");
        list.forEach(s -> {
            ret.set(ret.get() + s + interval);
        });
        return ret.get();
    }

    public static String getItemListString(List<ItemStack> list){
        StringBuilder s = new StringBuilder();
        AtomicInteger i = new AtomicInteger();
        list.forEach(m -> {
            if(i.getAndIncrement() > 0)s.append(", ");
            s.append(m.getDisplayName() + " x" + m.getCount());
        });
        return s.toString();
    }
}
