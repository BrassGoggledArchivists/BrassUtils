package enderglove.common.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import boilerplate.common.baseclasses.RootItem;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import enderglove.common.EnderGlove;
import enderglove.common.lib.LibInfo;

public class ItemEnderPocket extends RootItem
{
	public ItemEnderPocket()
	{
		super();
		this.setFull3D();
		this.setCreativeTab(CreativeTabs.tabTools);
		this.setNoRepair();
	}
	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister ir)
	{
		this.itemIcon = ir.registerIcon(LibInfo.PREFIX + "enderpocket");
	}
	/**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
    {
    	player.openGui(EnderGlove.instance, 0, world, (int)player.posX, (int)player.posY, (int)player.posZ);
        return stack;
    }
}
