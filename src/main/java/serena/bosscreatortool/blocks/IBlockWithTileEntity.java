package serena.bosscreatortool.blocks;

import net.minecraft.tileentity.TileEntity;

public interface IBlockWithTileEntity {

    Class<? extends TileEntity> getTileEntity();
    String getTileEntityName();

}
