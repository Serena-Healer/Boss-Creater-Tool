package serena.bosscreatortool.entities.instance;

import net.minecraft.world.World;
import serena.bosscreatortool.entities.BossMob;
import serena.bosscreatortool.entities.HostileMob;
import serena.bosscreatortool.entities.ICustomEntity;

public class WhiteGarden extends BossMob implements ICustomEntity {

    public WhiteGarden(World worldIn) {
        super(worldIn);
    }

    @Override
    public float getSpeed() {
        return 0.5F;
    }

    @Override
    public double getEffectiveHealth() {
        return 1000000007;
    }

    @Override
    public double getAttackDamage() {
        return 998244353;
    }

    @Override
    public void onTick() {

    }

}
