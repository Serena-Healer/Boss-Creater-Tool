package serena.bosscreatortool.entities;

public interface ICustomEntity {

    double getEffectiveHealth();
    double getAttackDamage();
    void onTick();

}
