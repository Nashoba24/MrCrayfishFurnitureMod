package com.mrcrayfish.furniture.client.gui.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mrcrayfish.furniture.Reference;
import com.mrcrayfish.furniture.network.PacketHandler;
import com.mrcrayfish.furniture.network.message.MessageOpenMailBox;
import com.mrcrayfish.furniture.network.message.MessageSetMailBoxName;
import com.mrcrayfish.furniture.tileentity.MailBoxTileEntity;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

/**
 * Author: MrCrayfish
 */
public class MailBoxSettingsScreen extends Screen
{
    private static final ResourceLocation GUI_TEXTURE = new ResourceLocation(Reference.MOD_ID, "textures/gui/mail_box_settings.png");

    private int xSize = 176;
    private int ySize = 69;

    private MailBoxTileEntity mailBoxTileEntity;
    private TextFieldWidget nameField;
    private Button btnSave;

    public MailBoxSettingsScreen(MailBoxTileEntity mailBoxTileEntity)
    {
        super(new TranslationTextComponent("gui.cfm.mail_box_settings"));
        this.mailBoxTileEntity = mailBoxTileEntity;
    }

    @Override
    protected void func_231160_c_()
    {
        int guiLeft = (this.field_230708_k_ - this.xSize) / 2;
        int guiTop = (this.field_230709_l_ - this.ySize) / 2;

        this.nameField = new TextFieldWidget(this.field_230712_o_, guiLeft + 8, guiTop + 18, 160, 18, ITextComponent.func_241827_a_(""));
        if(this.mailBoxTileEntity.getMailBoxName() != null)
        {
            this.nameField.setText(this.mailBoxTileEntity.getMailBoxName());
        }
        this.field_230705_e_.add(this.nameField);

        this.btnSave = this.func_230480_a_(new Button(guiLeft + 7, guiTop + 42, 79, 20, ITextComponent.func_241827_a_(I18n.format("gui.button.cfm.save")), button ->
        {
            if(this.isValidName())
            {
                PacketHandler.instance.sendToServer(new MessageSetMailBoxName(mailBoxTileEntity.getId(), this.nameField.getText(), mailBoxTileEntity.getPos()));
            }
        }));
        this.btnSave.field_230693_o_ = false;

        this.func_230480_a_(new Button(guiLeft + 91, guiTop + 42, 79, 20, ITextComponent.func_241827_a_(I18n.format("gui.button.cfm.back")), button ->
        {
            PacketHandler.instance.sendToServer(new MessageOpenMailBox(mailBoxTileEntity.getPos()));
        }));
    }

    @Override
    public void func_231023_e_()
    {
        super.func_231023_e_();
        this.nameField.tick();
        this.btnSave.field_230693_o_ = this.isValidName();
    }

    @Override
    public void func_230430_a_(MatrixStack mStack, int mouseX, int mouseY, float partialTicks)
    {
        this.func_230446_a_(mStack);

        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.field_230706_i_.getTextureManager().bindTexture(GUI_TEXTURE);
        int startX = (this.field_230708_k_ - this.xSize) / 2;
        int startY = (this.field_230709_l_ - this.ySize) / 2;
        this.func_238474_b_(mStack, startX, startY, 0, 0, this.xSize, this.ySize);

        super.func_230430_a_(mStack, mouseX, mouseY, partialTicks);

        this.field_230712_o_.func_238421_b_(mStack, this.field_230704_d_.getString(), startX + 8.0F, startY + 6.0F, 0x404040);

        this.nameField.func_230430_a_(mStack, mouseX, mouseY, partialTicks);
    }

    private boolean isValidName()
    {
        return !this.nameField.getText().equals(mailBoxTileEntity.getMailBoxName()) && !this.nameField.getText().trim().isEmpty();
    }
}
