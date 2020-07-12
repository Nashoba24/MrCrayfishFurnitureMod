package com.mrcrayfish.furniture.client.gui.screen.inventory;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mrcrayfish.furniture.Reference;
import com.mrcrayfish.furniture.inventory.container.FreezerContainer;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

/**
 * Author: MrCrayfish
 */
public class FreezerScreen extends ContainerScreen<FreezerContainer>
{
    private static final ResourceLocation FREEZER_GUI_TEXTURES = new ResourceLocation(Reference.MOD_ID, "textures/gui/container/freezer.png");

    public FreezerScreen(FreezerContainer container, PlayerInventory playerInventory, ITextComponent title)
    {
        super(container, playerInventory, title);
    }

    @Override
    public void func_230430_a_(MatrixStack mStack, int mouseX, int mouseY, float partialTicks)
    {
        this.func_230446_a_(mStack);
        super.func_230430_a_(mStack, mouseX, mouseY, partialTicks);
        this.func_230459_a_(mStack, mouseX, mouseY);
    }

    @Override
    protected void func_230451_b_(MatrixStack mStack, int mouseX, int mouseY)
    {
        String lvt_3_1_ = this.field_230704_d_.getString();
        this.field_230712_o_.func_238421_b_(mStack, lvt_3_1_, (float) (this.xSize / 2 - this.field_230712_o_.getStringWidth(lvt_3_1_) / 2), 6.0F, 4210752);
        this.field_230712_o_.func_238421_b_(mStack, this.playerInventory.getDisplayName().getString(), 8.0F, (float) (this.ySize - 96 + 2), 4210752);
    }

    @Override
    protected void func_230450_a_(MatrixStack mStack, float partialTicks, int mouseX, int mouseY)
    {
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.field_230706_i_.getTextureManager().bindTexture(FREEZER_GUI_TEXTURES);
        this.func_238474_b_(mStack, this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
        if(this.container.isFueling())
        {
            int fuelLeft = this.container.getFuelLeftScaled();
            this.func_238474_b_(mStack, this.guiLeft + 57, this.guiTop + 37 + 12 - fuelLeft, 176, 12 - fuelLeft, 14, fuelLeft + 1);
        }
        int progress = this.container.getSolidifyProgressionScaled();
        this.func_238474_b_(mStack, this.guiLeft + 79, this.guiTop + 34, 176, 14, progress + 1, 16);
    }
}
