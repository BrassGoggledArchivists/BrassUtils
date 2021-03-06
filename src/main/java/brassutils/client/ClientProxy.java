
package brassutils.client;

import net.minecraft.world.World;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

import boilerplate.client.renderers.block.BlockTESRRenderer;
import boilerplate.client.renderers.block.RenderMinedBlock;
import boilerplate.client.utils.EffectUtils;
import boilerplate.common.entity.EntityMinedBlock;
import brassutils.common.CommonProxy;
import brassutils.common.block.TileCrystal;

/**
 * @author Surseance
 *
 */
public class ClientProxy extends CommonProxy
{
	@Override
	public void registerDisplayInformation()
	{
		RenderingRegistry.registerEntityRenderingHandler(EntityMinedBlock.class, new RenderMinedBlock());

		// Crystal
		ClientRegistry.bindTileEntitySpecialRenderer(TileCrystal.class, new TileCrystalRenderer());
		RenderingRegistry.registerBlockHandler(new BlockTESRRenderer(new TileCrystal(), RenderIDs.blockCrystalRI));
	}

	@Override
	public void blockSparkleFX(World world, int x, int y, int z, int count)
	{
		EffectUtils.blockSparkle(world, x, y, z, count);
	}

	@Override
	public void blockFlameFX(World world, int x, int y, int z, int count)
	{
		EffectUtils.blockFlameFX(world, x, y, z, count);
	}
}
