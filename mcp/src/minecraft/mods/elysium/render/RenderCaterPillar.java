package mods.elysium.render;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.model.ModelSnowMan;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntitySnowman;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;

import net.minecraftforge.client.IItemRenderer;
import static net.minecraftforge.client.IItemRenderer.ItemRenderType.*;
import static net.minecraftforge.client.IItemRenderer.ItemRendererHelper.*;
import net.minecraftforge.client.MinecraftForgeClient;

@SideOnly(Side.CLIENT)
public class RenderCaterPillar extends RenderLiving
{
    private ModelCaterPillar model;

    public RenderCaterPillar()
    {
        super(new ModelCaterPillar(), 1F);
        this.model = (ModelCaterPillar)super.mainModel;
        this.setRenderPassModel(this.model);
    }

}