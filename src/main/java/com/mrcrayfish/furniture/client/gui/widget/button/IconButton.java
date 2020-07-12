package com.mrcrayfish.furniture.client.gui.widget.button;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * Author: MrCrayfish
 */
@OnlyIn(Dist.CLIENT)
public class IconButton extends Button
{
    private ResourceLocation iconResource;
    private int iconU;
    private int iconV;

    public IconButton(int x, int y, String message, IPressable pressable, ResourceLocation iconResource, int iconU, int iconV)
    {
        super(x, y, 20, 20, ITextComponent.func_241827_a_(message), pressable);
        this.iconResource = iconResource;
        this.iconU = iconU;
        this.iconV = iconV;
    }

    public void setIcon(ResourceLocation iconResource, int iconU, int iconV)
    {
        this.iconResource = iconResource;
        this.iconU = iconU;
        this.iconV = iconV;
    }

    @Override
    public void func_230431_b_(MatrixStack mStack, int mouseX, int mouseY, float partialTicks)
    {
        Minecraft.getInstance().getTextureManager().bindTexture(field_230687_i_);
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.enableBlend();
        RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        int offset = this.func_230989_a_(this.func_230449_g_());
        this.func_238474_b_(mStack, this.field_230690_l_, this.field_230691_m_, 0, 46 + offset * 20, this.field_230688_j_ / 2, this.field_230689_k_);
        this.func_238474_b_(mStack, this.field_230690_l_ + this.field_230688_j_ / 2, this.field_230690_l_, 200 - this.field_230688_j_ / 2, 46 + offset * 20, this.field_230688_j_ / 2, this.field_230689_k_);
        if(!this.field_230693_o_)
        {
            RenderSystem.color4f(0.5F, 0.5F, 0.5F, 1.0F);
        }
        Minecraft.getInstance().getTextureManager().bindTexture(this.iconResource);
        this.func_238474_b_(mStack, this.field_230690_l_ + 2, this.field_230691_m_ + 2, this.iconU, this.iconV, 16, 16);
    }
}
