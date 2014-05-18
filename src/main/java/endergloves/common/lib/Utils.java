/**
 * This class was created by <Surseance> as a part of the
 * EnderGloves mod for Minecraft. 
 *
 * This mod is registered under the WTFPL v2.0. Please read the
 * COPYING.WTFPL file for more details.
 *
 * File created @[May 14, 2014, 9:07:32 PM] 
 */
package endergloves.common.lib;

import java.util.List;

import endergloves.common.item.ItemEnderGlove;
import net.minecraft.block.Block;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;

/**
 * @author Surseance (Johnny Eatmon)
 * <jmaeatmon@gmail.com>
 *
 */
public class Utils
{
	public static void sendMessage(EntityPlayer player, String message)
	{
		IChatComponent chat = new ChatComponentText(message);

		if (!player.worldObj.isRemote)
			player.addChatMessage(chat);
	}

	public static ItemStack getDroppedItemStack(World world, EntityLivingBase entityLiving, Block block, int x, int y, int z)
	{
		List<ItemStack>drops = block.getDrops(world, x, y, z, world.getBlockMetadata(x, y, z), EnchantmentHelper.getFortuneModifier(entityLiving));
		ItemStack is = null;

		if ((drops != null) && (drops.size() > 0))
		{
			for (int size = 0; size < drops.size(); size++)
			{
				is = (ItemStack)drops.get(size);
			}
		}

		return is;
	}

	public static boolean isSmeltable(ItemStack is) 
	{
		return ((is == null) || (FurnaceRecipes.smelting().getSmeltingResult(is) == null)) ? false : true;
	}

	public static void playSFX(World world, int x, int y, int z, String sound)
	{
		world.playSoundEffect((double)x + 0.5D, (double)y + 0.5D, (double)z + 0.5D, sound, 1.0F, world.rand.nextFloat() * 0.4F + 0.8F);
	}

	public static boolean isCarryingGlove(EntityPlayer player)
	{
		if ((player != null) && (player.inventory.getCurrentItem() != null) && (player.inventory.getCurrentItem().getItem() instanceof ItemEnderGlove))
			return true;

		return false;
	}
	
	public static ItemStack createStackedBlock(Block block, int metadata)
    {
        int md = 0;
        Item item = Item.getItemFromBlock(block);

        if (item != null && item.getHasSubtypes())
        {
            md = metadata;
        }

        return new ItemStack(item, 1, md);
    }
	
	/*
	\247# +

	0: Black
	1: Dark Blue
	2: Dark Green
	3: Dark Aqua
	4: Dark Red
	5: Purple
	6: Gold
	7: Light Grey
	8: Dark Grey
	9: Indigo
	A: Light Green
	B: Aqua
	C: Pink
	D: Pink
	E: Yellow
	F: White

	K: Random Text
	L: Bold
	N: Underline
	O: Italics
	M: Strike-through
	 */
}
