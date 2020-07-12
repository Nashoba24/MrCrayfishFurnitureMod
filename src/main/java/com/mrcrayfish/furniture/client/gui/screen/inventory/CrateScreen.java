package com.mrcrayfish.furniture.client.gui.screen.inventory;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mrcrayfish.furniture.Reference;
import com.mrcrayfish.furniture.client.gui.widget.button.IconButton;
import com.mrcrayfish.furniture.inventory.container.CrateContainer;
import com.mrcrayfish.furniture.network.PacketHandler;
import com.mrcrayfish.furniture.network.message.MessageLockCrate;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.ITextProperties;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.UUID;

/**
 * Author: MrCrayfish
 */
@OnlyIn(Dist.CLIENT)
public class CrateScreen extends ContainerScreen<CrateContainer>
{
    private static final ResourceLocation GUI_TEXTURE = new ResourceLocation(Reference.MOD_ID, "textures/gui/container/crate.png");
    private static final ResourceLocation ICONS_TEXTURE = new ResourceLocation(Reference.MOD_ID, "textures/gui/icons.png");

    private IconButton button;
    private boolean locked;

    public CrateScreen(CrateContainer container, PlayerInventory playerInventory, ITextComponent title)
    {
        super(container, playerInventory, title);
    }

    @Override
    protected void func_231160_c_()
    {
        super.func_231160_c_();
        this.button = this.func_230480_a_(new IconButton(this.guiLeft + this.xSize + 2, this.guiTop + 17, I18n.format("gui.button.cfm.lock"), button -> PacketHandler.instance.sendToServer(new MessageLockCrate()), ICONS_TEXTURE, 0, 0));
        this.updateLockButton();
    }

    @Override
    public void func_231023_e_()
    {
        super.func_231023_e_();
        if(this.locked != this.container.getCrateTileEntity().isLocked())
        {
            this.locked = this.container.getCrateTileEntity().isLocked();
            this.updateLockButton();
        }
    }

    private void updateLockButton()
    {
        this.locked = this.container.getCrateTileEntity().isLocked();
        this.button.setIcon(ICONS_TEXTURE, this.locked ? 0 : 16, 0);
        this.button.func_238482_a_(ITextComponent.func_241827_a_(I18n.format(this.locked ? "gui.button.cfm.locked" : "gui.button.cfm.unlocked")));
        UUID ownerUuid = this.container.getCrateTileEntity().getOwner();
        this.button.field_230694_p_ = ownerUuid == null || this.playerInventory.player.getUniqueID().equals(ownerUuid);
    }

    @Override
    public void func_230430_a_(MatrixStack mStack, int mouseX, int mouseY, float partialTicks)
    {
        this.func_230446_a_(mStack);
        super.func_230430_a_(mStack, mouseX, mouseY, partialTicks);
        this.func_230459_a_(mStack, mouseX, mouseY);
        if(this.button.func_231047_b_(mouseX, mouseY))
        {
            this.func_238652_a_(mStack, ITextProperties.func_240652_a_(locked ? I18n.format("gui.button.cfm.locked") : I18n.format("gui.button.cfm.unlocked")), mouseX, mouseY);
        }
    }

    @Override
    protected void func_230451_b_(MatrixStack mStack, int mouseX, int mouseY)
    {
        this.field_230712_o_.func_238421_b_(mStack, this.field_230704_d_.getString(), 8.0F, 6.0F, 0x404040);
        this.field_230712_o_.func_238421_b_(mStack, this.playerInventory.getDisplayName().getString(), 8.0F, (float) (this.ySize - 96 + 2), 0x404040);
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
}
