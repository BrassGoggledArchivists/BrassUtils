/**
 * This class was created by <Surseance> as a part of the
 * EnderGlove mod for Minecraft.
 *
 * This mod is registered under the WTFPL v2.0. Please read the
 * COPYING.WTFPL file for more details.
 *
 * File created @[May 14, 2014, 9:27:33 PM]
 */
package enderglove.common.lib;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.registry.GameRegistry;
import enderglove.common.config.Config;
import enderglove.common.config.ConfigItems;

/**
 * @author Surseance (Johnny Eatmon) <jmaeatmon@gmail.com>
 *
 */
public class EGCraftingManager
{
	public static void init()
	{
		if(Config.line1 != null)
		{
	FMLLog.severe(Config.line1.toString());
	/**	GameRegistry.addShapedRecipe(new ItemStack(ConfigItems.itemEnderGlove),
				new Object[] {Config.recipeLine1.toString(), Config.recipeLine2.toCharArray().toString(),
						Config.recipeLine3.toString(), 'L', Items.leather, 'N',
						Items.nether_star, 'E', Items.ender_eye/*, 'P',
						Items.ender_pearl, 'B', Items.blaze_rod, 'D',
						Blocks.dragon_egg, 'S', Blocks.end_stone, 'd',
						Blocks.diamond_block, 'b', Blocks.obsidian });

	*/
		}
		else
		{
			GameRegistry.addRecipe(new ItemStack(ConfigItems.itemEnderGlove), new Object[] {"EEE", "LNL", "LLL", 'L', Items.leather, 'N', Items.nether_star, 'E', Items.ender_eye});
		}
	}
}
