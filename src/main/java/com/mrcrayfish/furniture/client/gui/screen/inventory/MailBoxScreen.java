package com.mrcrayfish.furniture.client.gui.screen.inventory;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mrcrayfish.furniture.Reference;
import com.mrcrayfish.furniture.client.gui.screen.MailBoxSettingsScreen;
import com.mrcrayfish.furniture.client.gui.widget.button.IconButton;
import com.mrcrayfish.furniture.inventory.container.MailBoxContainer;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

/**
 * Author: MrCrayfish
 */
public class MailBoxScreen extends ContainerScreen<MailBoxContainer>
{
    private static final ResourceLocation GUI_TEXTURE = new ResourceLocation(Reference.MOD_ID, "textures/gui/container/mail_box.png");
    private static final ResourceLocation ICONS_TEXTURE = new ResourceLocation(Reference.MOD_ID, "textures/gui/icons.png");

    public MailBoxScreen(MailBoxContainer container, PlayerInventory playerInventory, ITextComponent title)
    {
        super(container, playerInventory, title);
        this.ySize = 132;
    }

    @Override
    protected void func_231160_c_()
    {
        super.func_231160_c_();
        this.func_230480_a_(new IconButton(this.guiLeft + this.xSize + 2, this.guiTop + 17, I18n.format("gui.button.cfm.lock"), button -> {
            this.field_230706_i_.displayGuiScreen(new MailBoxSettingsScreen(this.container.getMailBoxTileEntity()));
        }, ICONS_TEXTURE, 48, 0));
    }

    @Override
    protected void func_230450_a_(MatrixStack mStack, float partialTicks, int mouseX, int mouseY)
    {
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.field_230706_i_.getTextureManager().bindTexture(GUI_TEXTURE);
        int startX = (this.field_230708_k_ - this.xSize) / 2;
        int startY = (this.field_230709_l_ - this.ySize) / 2;
        this.func_238474_b_(mStack, startX, startY, 0, 0, this.xSize, this.ySize);
    }

    @Override
    protected void func_230451_b_(MatrixStack mStack, int mouseX, int mouseY)
    {
        this.field_230712_o_.func_238421_b_(mStack, this.field_230704_d_.getString(), 8.0F, 6.0F, 0x404040);
        this.field_230712_o_.func_238421_b_(mStack, this.playerInventory.getDisplayName().getString(), 8.0F, (float) (this.ySize - 96 + 2), 0x404040);
    }

    @Override
    public void func_230430_a_(MatrixStack mStack, int mouseX, int mouseY, float partialTicks)
    {
        this.func_230446_a_(mStack);
        super.func_230430_a_(mStack, mouseX, mouseY, partialTicks);
        this.func_230459_a_(mStack, mouseX, mouseY);
    }
}
