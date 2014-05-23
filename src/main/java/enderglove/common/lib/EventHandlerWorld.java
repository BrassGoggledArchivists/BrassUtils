/**
 * This class was created by <Surseance> as a part of the
 * EnderGlove mod for Minecraft.
 *
 * This mod is registered under the WTFPL v2.0. Please read the
 * COPYING.WTFPL file for more details.
 *
 * File created @[May 14, 2014, 9:26:54 PM]
 */
package enderglove.common.lib;

import java.util.Iterator;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.event.entity.player.PlayerDropsEvent;
import net.minecraftforge.event.world.BlockEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import enderglove.common.config.Config;
import enderglove.common.item.ItemEnderGlove;

/**
 * @author Surseance (Johnny Eatmon) <jmaeatmon@gmail.com>
 *
 */
public class EventHandlerWorld
{
	@SubscribeEvent
	public void harvestDrops(BlockEvent.HarvestDropsEvent event)
	{
		EntityPlayer player = event.harvester;

		if (event.drops != null && event.drops.size() > 0
				&& Utils.isCarryingGlove(player))
			event.drops.clear();
		else
			return;
	}

	@SubscribeEvent
	public void blockBreak(BlockEvent.BreakEvent event)
	{
		EntityPlayer player = event.getPlayer();
		int affAmount = EnchantmentHelper.getEnchantmentLevel(
				Config.enchAffluencyId, player.inventory.getCurrentItem());

		if (event.getExpToDrop() > 0)
		{
			int XP = event.getExpToDrop();
			int affXP = XP + affAmount * affAmount / 2;

			event.setExpToDrop(affXP);
		}
	}

	@SubscribeEvent
	public void playerDrops(PlayerDropsEvent event)
	{
		Iterator iterator = event.drops.iterator();
		while (iterator.hasNext())
		{
			EntityItem entItem = (EntityItem) iterator.next();
			ItemStack is = entItem.getEntityItem();

			if (is != null && is.getItem() instanceof ItemEnderGlove)
			{
				is.damageItem(1, event.entityPlayer);
				InventoryHelper
				.addItemStackToInventory(InventoryHelper
						.getPlayerEnderChest(event.entityPlayer), is);
				Utils.sendMessage(
						event.entityPlayer,
						EnumChatFormatting.DARK_PURPLE
						+ "Your Ender Glove was succesfully put in your Ender Chest!");
				Utils.playSFX(event.entityPlayer.worldObj,
						(int) entItem.prevPosX, (int) entItem.prevPosY,
						(int) entItem.prevPosZ, "mob.endermen.portal");
				entItem.setDead();
			}
		}
	}
}