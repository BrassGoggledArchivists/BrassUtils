
package brassutils.common.lib.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.item.ItemBook;
import net.minecraft.item.ItemStack;

import brassutils.common.item.ItemEnderGlove;

/**
 * @author Surseance
 *
 */
public class EnchantmentEnderField extends Enchantment
{
	public EnchantmentEnderField(int id, int rarity)
	{
		super(id, rarity, EnumEnchantmentType.all);
		this.setName("enderfield");
	}

	@Override
	public int getMinEnchantability(int level)
	{
		return 24;
	}

	@Override
	public int getMaxEnchantability(int level)
	{
		return super.getMinEnchantability(level) + 50;
	}

	@Override
	public int getMaxLevel()
	{
		return 1;
	}

	@Override
	public boolean canApply(ItemStack is)
	{
		return (is.getItem() instanceof ItemEnderGlove) || (is.getItem() instanceof ItemBook);
	}

	@Override
	public boolean canApplyAtEnchantingTable(ItemStack is)
	{
		return this.canApply(is);
	}

	@Override
	public boolean canApplyTogether(Enchantment ench)
	{
		return super.canApplyTogether(ench);
	}
}
