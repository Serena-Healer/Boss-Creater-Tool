package serena.bosscreatortool.entities;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public abstract class PlayerShapedFriendly extends EntitySkeleton implements IEntityBase {

    public PlayerShapedFriendly(World worldIn) {
        super(worldIn);
        this.setHeldItem(EnumHand.MAIN_HAND,ItemStack.EMPTY);
        this.isImmuneToFire = true;
        this.inventoryArmorDropChances = new float[]{-1000,-1000,-1000,-1000};
        this.inventoryHandsDropChances = new float[]{-1000,-1000};
    }

    protected void initEntityAI()
    {
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWanderAvoidWater(this, 1.0D));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
    }

    protected void setEquipmentBasedOnDifficulty(DifficultyInstance difficulty)
    {

    }

    @Override
    protected SoundEvent getAmbientSound() {
        return null;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.ENTITY_PLAYER_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_PLAYER_DEATH;
    }

    @Override
    protected SoundEvent getStepSound() {
        return null;
    }

    @Nullable
    @Override
    protected ResourceLocation getLootTable() {
        return null;
    }

    @Override
    public boolean getCanSpawnHere() {
        IBlockState iblockstate = this.world.getBlockState((new BlockPos(this)).down());
        return iblockstate.canEntitySpawn(this);
    }

}
