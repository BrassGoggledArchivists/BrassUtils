/**
 * This class was created by <Surseance> as a part of the
 * EnderGloves mod for Minecraft. 
 *
 * This mod is registered under the WTFPL v2.0. Please read the
 * COPYING.WTFPL file for more details.
 *
 * File created @[May 14, 2014, 9:08:06 PM] 
 */
package endergloves.common.lib;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryEnderChest;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * @author Surseance (Johnny Eatmon)
 * <jmaeatmon@gmail.com>
 *
 */
public class InventoryHelper
{
	private static Random random = new Random();

	//@Deprecated
	public static InventoryEnderChest getPlayerEnderChest(EntityPlayer player)
	{
		return player.getInventoryEnderChest();
	}

	public static boolean addItemStackToInventory(IInventory inv, ItemStack is)
	{
		if (!is.isItemDamaged()) 
		{
			int stackSize;
			do 
			{
				stackSize = is.stackSize;
				is.stackSize = storePartialItemStack(inv, is);
			}
			while (is.stackSize > 0 && is.stackSize < stackSize);
			
			return is.stackSize < stackSize;
		}
		
		int slot = getFirstEmptySlot(inv, is);
		
		if (slot >= 0) 
		{
			inv.setInventorySlotContents(slot, ItemStack.copyItemStack(is));
			is.stackSize = 0;
			return true;
		}
		
		return false;
	}
	
	private static int storePartialItemStack(IInventory inventory, ItemStack is)
	{
		Item id = is.getItem();
		int size = is.stackSize;

		if (is.getMaxStackSize() == 1) // Not stackable
		{
			int freeSlot = getFirstEmptySlot(inventory, is);
			
			if (freeSlot < 0)
				return size;
			
			if (inventory.getStackInSlot(freeSlot) == null)
				inventory.setInventorySlotContents(freeSlot, ItemStack.copyItemStack(is));
			
			return 0;
		}

		int targetSlot = getStackWithFreeSpace(inventory, is);
		if (targetSlot < 0) {
			targetSlot = getFirstEmptySlot(inventory, is);
		}

		if (targetSlot < 0) {
			return size;
		}

		if (inventory.getStackInSlot(targetSlot) == null) {
			inventory.setInventorySlotContents(targetSlot, new ItemStack(id, 0, is.getItemDamage()));
		}

		int canStore = size;
		if (canStore > inventory.getStackInSlot(targetSlot).getMaxStackSize() - inventory.getStackInSlot(targetSlot).stackSize) {
			canStore = inventory.getStackInSlot(targetSlot).getMaxStackSize() - inventory.getStackInSlot(targetSlot).stackSize;
		}
		if (canStore > inventory.getInventoryStackLimit() - inventory.getStackInSlot(targetSlot).stackSize) {
			canStore = inventory.getInventoryStackLimit() - inventory.getStackInSlot(targetSlot).stackSize;
		}
		if (canStore == 0) {
			return size;
		} else {
			size -= canStore;
			inventory.getStackInSlot(targetSlot).stackSize += canStore;
			return size;
		}
	}
	
	private static int getStackWithFreeSpace(IInventory inv, ItemStack itemstack) {

		for (int slot = 0; slot < inv.getSizeInventory(); slot++) 
		{

			ItemStack stackAt = inv.getStackInSlot(slot);
			if (stackAt != null && stackAt.itemID == itemstack.itemID && stackAt.isStackable() && stackAt.stackSize < stackAt.getMaxStackSize()
					&& stackAt.stackSize < inv.getInventoryStackLimit()
					&& (!stackAt.getHasSubtypes() || stackAt.getItemDamage() == itemstack.getItemDamage())) {
				return slot;
			}
		}

		return -1;
	}

	private static int getFirstEmptySlot(IInventory inventory, ItemStack is)
	{
		for (int slot = 0; slot < inventory.getSizeInventory(); slot++)
		{
			if (inventory.getStackInSlot(slot) == null)
			{
				return slot;
			}
		}

		return -1;
	}

	/**
	 * Determines if a specific item is in the player's inventory.
	 * 
	 * @param player - the player with the inventory to check
	 * @param item - the item stack to look for
	 * @return - the slot in which the specified item sits
	 */
	public static int isInPlayerInventory(EntityPlayer player, Item item)
	{
		for (int slot = 0; slot < player.inventory.mainInventory.length; slot++)
		{
			if ((player.inventory.mainInventory[slot] != null) && (player.inventory.mainInventory[slot].getItem() == item))
				return slot;
		}

		return -1;
	}
}
