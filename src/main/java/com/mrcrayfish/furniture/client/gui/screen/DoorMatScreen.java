package com.mrcrayfish.furniture.client.gui.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mrcrayfish.furniture.Reference;
import com.mrcrayfish.furniture.network.PacketHandler;
import com.mrcrayfish.furniture.network.message.MessageSetDoorMatMessage;
import com.mrcrayfish.furniture.tileentity.DoorMatTileEntity;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.ITextProperties;
import net.minecraft.util.text.TranslationTextComponent;

/**
 * Author: MrCrayfish
 */
public class DoorMatScreen extends Screen
{
    private static final ResourceLocation GUI_TEXTURE = new ResourceLocation(Reference.MOD_ID, "textures/gui/mail_box_settings.png");

    private int xSize = 176;
    private int ySize = 69;

    private DoorMatTileEntity doorMatTileEntity;
    private TextFieldWidget nameField;
    private Button btnSave;

    public DoorMatScreen(DoorMatTileEntity doorMatTileEntity)
    {
        super(new TranslationTextComponent("gui.cfm.door_mat_message"));
        this.doorMatTileEntity = doorMatTileEntity;
    }

    @Override
    protected void func_231160_c_()
    {
        int guiLeft = (this.field_230708_k_ - this.xSize) / 2;
        int guiTop = (this.field_230709_l_ - this.ySize) / 2;

        this.nameField = new TextFieldWidget(this.field_230712_o_, guiLeft + 8, guiTop + 18, 160, 18, ITextComponent.func_241827_a_(""))
        {
            @Override
            public void writeText(String textToWrite)
            {
                int lines = DoorMatScreen.this.field_230712_o_.func_238425_b_(ITextProperties.func_240652_a_(this.getText() + textToWrite), 60).size();
                if(lines <= 2)
                {
                    super.writeText(textToWrite);
                }
            }
        };
        if(this.doorMatTileEntity.getMessage() != null)
        {
            this.nameField.setText(this.doorMatTileEntity.getMessage());
        }
        this.field_230705_e_.add(this.nameField);

        this.btnSave = this.func_230480_a_(new Button(guiLeft + 7, guiTop + 42, 79, 20, ITextComponent.func_241827_a_(I18n.format("gui.button.cfm.save")), button ->
        {
            if(this.isValidName())
            {
                PacketHandler.instance.sendToServer(new MessageSetDoorMatMessage(this.doorMatTileEntity.getPos(), this.nameField.getText()));
                this.field_230706_i_.player.closeScreen();
            }
        }));
        this.btnSave.field_230693_o_ = false;

        this.func_230480_a_(new Button(guiLeft + 91, guiTop + 42, 79, 20, ITextComponent.func_241827_a_(I18n.format("gui.button.cfm.cancel")), button ->
        {
            this.field_230706_i_.player.closeScreen();
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
        int lines = this.field_230712_o_.func_238425_b_(ITextProperties.func_240652_a_(doorMatTileEntity.getMessage()), 45).size();
        return !this.nameField.getText().equals(doorMatTileEntity.getMessage()) && !this.nameField.getText().trim().isEmpty() && lines < 2;
    }
}
