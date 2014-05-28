/**
 * This class was created by <Surseance> as a part of the
 * EnderGlove mod for Minecraft.
 *
 * This mod is registered under the WTFPL v2.0. Please read the
 * COPYING.WTFPL file for more details.
 *
 * File created @[May 14, 2014, 9:26:45 PM]
 */
package enderglove.common.lib;

import java.util.Random;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.EnderTeleportEvent;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import enderglove.common.config.Config;
import enderglove.common.item.ItemEnderGlove;

/**
 * @author Surseance (Johnny Eatmon) 
 * Email: surseance@autistici.org
 * 
 */
public class EventHandlerEntity
{
	public static boolean playerDead = false;
	Random random = new Random();

	/*
		// Tell me there is a better way to do this, pl0x
		Iterator<?> i = event.entityLiving.worldObj.playerEntities.iterator();
		while (i.hasNext())
		{
			EntityPlayer player = (EntityPlayer) i.next();

			if (Utils.isCarryingGlove(player))
			{
				AxisAlignedBB axisalignedbb = AxisAlignedBB.getAABBPool().getAABB(player.posX, player.posY, player.posZ, player.posX + 1, player.posY + 1, player.posY + 1).expand(30, 30, 30);
				List<EntityEnderman> l = player.worldObj.getEntitiesWithinAABB(EntityEnderman.class, axisalignedbb);
				Iterator<EntityEnderman> i2 = l.iterator();

				while (i2.hasNext())
				{
					i2.next();
					event.setCanceled(true);
				}
			}
		}*/

	@SubscribeEvent(priority = EventPriority.HIGH)
	public void itemCrafted(PlayerEvent.ItemCraftedEvent event)
	{
		ItemStack heldItem = event.player.inventory.getCurrentItem();
		int artisanAmount = EnchantmentHelper.getEnchantmentLevel(Config.enchArtisanId, heldItem);

		if (heldItem != null && heldItem.getItem() instanceof ItemEnderGlove && artisanAmount > 0)
		{
			IInventory craft = event.craftMatrix;
			int randomSlot = random.nextInt(8);

			if (craft.getStackInSlot(randomSlot) != null)
			{
				ItemStack result = new ItemStack(craft.getStackInSlot(randomSlot).copy().getItem(), 2);

				if (random.nextInt(Config.artisanBonusChance) == 0)
				{
					// if (!event.player.worldObj.isRemote)
					craft.setInventorySlotContents(randomSlot, result);
					heldItem.damageItem(1, event.player);
				}
			}
		}
	}
}
