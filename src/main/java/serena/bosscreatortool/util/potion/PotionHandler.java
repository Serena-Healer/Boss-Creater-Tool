package serena.bosscreatortool.util.potion;

import net.minecraft.potion.Potion;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class PotionHandler {

    public static Potion DAMAGE_PHYSICAL_1 = new DamagePotionBase("physical_1", true, 1, 0, 0, 1, "mob");
    public static Potion DAMAGE_PHYSICAL_2 = new DamagePotionBase("physical_2", true, 1, 0, 0, 10, "mob");
    public static Potion DAMAGE_PHYSICAL_3 = new DamagePotionBase("physical_3", true, 1, 0, 0, 100, "mob");
    public static Potion DAMAGE_PHYSICAL_4 = new DamagePotionBase("physical_4", true, 1, 0, 0, 1000, "mob");
    public static Potion DAMAGE_MAGIC_1 = new DamagePotionBase("magic_1", true, 1, 0, 0, 1, "magic");
    public static Potion DAMAGE_MAGIC_2 = new DamagePotionBase("magic_2", true, 1, 0, 0, 10, "magic");
    public static Potion DAMAGE_MAGIC_3 = new DamagePotionBase("magic_3", true, 1, 0, 0, 100, "magic");
    public static Potion DAMAGE_MAGIC_4 = new DamagePotionBase("magic_4", true, 1, 0, 0, 1000, "magic");
    public static Potion DAMAGE_VOID_1 = new DamagePotionBase("void_1", true, 1, 0, 0, 1, "void");
    public static Potion DAMAGE_VOID_2 = new DamagePotionBase("void_2", true, 1, 0, 0, 10, "void");
    public static Potion DAMAGE_VOID_3 = new DamagePotionBase("void_3", true, 1, 0, 0, 100, "void");
    public static Potion DAMAGE_VOID_4 = new DamagePotionBase("void_4", true, 1, 0, 0, 1000, "void");
    public static Potion DAMAGE_TRUE_1 = new ForcedDamagePotionBase("true_1", true, 1, 0, 0, 1, "void");
    public static Potion DAMAGE_TRUE_2 = new ForcedDamagePotionBase("true_2", true, 1, 0, 0, 10, "void");
    public static Potion DAMAGE_TRUE_3 = new ForcedDamagePotionBase("true_3", true, 1, 0, 0, 100, "void");
    public static Potion DAMAGE_TRUE_4 = new ForcedDamagePotionBase("true_4", true, 1, 0, 0, 1000, "void");
    public static Potion DAMAGE_PIERCE_1 = new TrueDamagePotionBase("pierce_1", true, 1, 0, 0, 1, "void");
    public static Potion DAMAGE_PIERCE_2 = new TrueDamagePotionBase("pierce_2", true, 1, 0, 0, 10, "void");
    public static Potion DAMAGE_PIERCE_3 = new TrueDamagePotionBase("pierce_3", true, 1, 0, 0, 100, "void");
    public static Potion DAMAGE_PIERCE_4 = new TrueDamagePotionBase("pierce_4", true, 1, 0, 0, 1000, "void");

    public static Potion HEAL_1 = new HealingPotionBase("heal_1", false, 1, 0, 0, 1, "heal");
    public static Potion HEAL_2 = new HealingPotionBase("heal_2", false, 1, 0, 0, 10, "heal");
    public static Potion HEAL_3 = new HealingPotionBase("heal_3", false, 1, 0, 0, 100, "heal");
    public static Potion HEAL_4 = new HealingPotionBase("heal_4", false, 1, 0, 0, 1000, "heal");

    public static Potion PHYSICAL_ATTACK_UP = new CustomPotionBase("p_atk_up", false, 1, 0, 0);
    public static Potion PHYSICAL_ATTACK_DOWN = new CustomPotionBase("p_atk_down", true, 1, 0, 0);
    public static Potion MAGIC_ATTACK_UP = new CustomPotionBase("m_atk_up", false, 1, 0, 0);
    public static Potion MAGIC_ATTACK_DOWN = new CustomPotionBase("m_atk_down", true, 1, 0, 0);
    public static Potion PHYSICAL_RESISTANCE_UP = new CustomPotionBase("p_res_up", false, 1, 0, 0);
    public static Potion PHYSICAL_RESISTANCE_DOWN = new CustomPotionBase("p_res_down", true, 1, 0, 0);
    public static Potion MAGIC_RESISTANCE_UP = new CustomPotionBase("m_res_up", false, 1, 0, 0);
    public static Potion MAGIC_RESISTANCE_DOWN = new CustomPotionBase("m_res_down", true, 1, 0, 0);

    public static Potion FIRE = new CustomPotionBase("fire", true, 1, 0, 0);
    public static Potion POISON = new CustomPotionBase("poison", true, 1, 0, 0);
    public static Potion WITHER = new CustomPotionBase("wither", true, 1, 0, 0);

    public static Potion RESIST_RESET = new ResistResetPotion("res_reset", true, 1, 0, 0);

    public static void init(){
        ForgeRegistries.POTIONS.register(DAMAGE_PHYSICAL_1);
        ForgeRegistries.POTIONS.register(DAMAGE_PHYSICAL_2);
        ForgeRegistries.POTIONS.register(DAMAGE_PHYSICAL_3);
        ForgeRegistries.POTIONS.register(DAMAGE_PHYSICAL_4);
        ForgeRegistries.POTIONS.register(DAMAGE_MAGIC_1);
        ForgeRegistries.POTIONS.register(DAMAGE_MAGIC_2);
        ForgeRegistries.POTIONS.register(DAMAGE_MAGIC_3);
        ForgeRegistries.POTIONS.register(DAMAGE_MAGIC_4);
        ForgeRegistries.POTIONS.register(DAMAGE_VOID_1);
        ForgeRegistries.POTIONS.register(DAMAGE_VOID_2);
        ForgeRegistries.POTIONS.register(DAMAGE_VOID_3);
        ForgeRegistries.POTIONS.register(DAMAGE_VOID_4);
        ForgeRegistries.POTIONS.register(DAMAGE_TRUE_1);
        ForgeRegistries.POTIONS.register(DAMAGE_TRUE_2);
        ForgeRegistries.POTIONS.register(DAMAGE_TRUE_3);
        ForgeRegistries.POTIONS.register(DAMAGE_TRUE_4);
        ForgeRegistries.POTIONS.register(DAMAGE_PIERCE_1);
        ForgeRegistries.POTIONS.register(DAMAGE_PIERCE_2);
        ForgeRegistries.POTIONS.register(DAMAGE_PIERCE_3);
        ForgeRegistries.POTIONS.register(DAMAGE_PIERCE_4);
        ForgeRegistries.POTIONS.register(HEAL_1);
        ForgeRegistries.POTIONS.register(HEAL_2);
        ForgeRegistries.POTIONS.register(HEAL_3);
        ForgeRegistries.POTIONS.register(HEAL_4);
        ForgeRegistries.POTIONS.register(PHYSICAL_ATTACK_UP);
        ForgeRegistries.POTIONS.register(PHYSICAL_ATTACK_DOWN);
        ForgeRegistries.POTIONS.register(MAGIC_ATTACK_UP);
        ForgeRegistries.POTIONS.register(MAGIC_ATTACK_DOWN);
        ForgeRegistries.POTIONS.register(PHYSICAL_RESISTANCE_UP);
        ForgeRegistries.POTIONS.register(PHYSICAL_RESISTANCE_DOWN);
        ForgeRegistries.POTIONS.register(MAGIC_RESISTANCE_UP);
        ForgeRegistries.POTIONS.register(MAGIC_RESISTANCE_DOWN);
        ForgeRegistries.POTIONS.register(FIRE);
        ForgeRegistries.POTIONS.register(POISON);
        ForgeRegistries.POTIONS.register(WITHER);
        ForgeRegistries.POTIONS.register(RESIST_RESET);
    }

}
