package skidd.ed.clickgui.settingbutton.impl;

import com.mojang.realmsclient.gui.ChatFormatting;
import skidd.ed.Skidded;
import skidd.ed.clickgui.settingbutton.Button;
import skidd.ed.modules.core.ClickGui;
import skidd.ed.modules.core.CustomFont;
import skidd.ed.settings.Setting;
import skidd.ed.settings.impl.EnumSetting;
import skidd.ed.utils.RenderUtil;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.init.SoundEvents;

import java.awt.*;


public class EnumButton extends Button {
    Setting setting;
    public EnumSetting enumSetting;

    public EnumButton(Setting setting, EnumSetting modeSetting) {
        super(setting);
        this.setting = setting;
        this.enumSetting = modeSetting;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        RenderUtil.drawRect(x - 2, y, x + width + 2, y + height, ClickGui.getInstance().backgroundColor.getColor().getRGB());
        if (isInside(mouseX, mouseY))
            RenderUtil.drawRect(x, y, x + width, y + height, new Color(0, 0, 0, 100).getRGB());
        if(CustomFont.INSTANCE.isEnabled()){
            Skidded.customFontRenderer.drawStringWithShadow(enumSetting.getName() + " " + ChatFormatting.GRAY + enumSetting.getValueAsString(), x + 2, y + (height / 2f) - (Skidded.mc.fontRenderer.FONT_HEIGHT / 2f), -1);
        }else {
            Skidded.mc.fontRenderer.drawStringWithShadow(enumSetting.getName() + " " + ChatFormatting.GRAY + enumSetting.getValueAsString(), x + 2, y + (height / 2f) - (Skidded.mc.fontRenderer.FONT_HEIGHT / 2f), -1);
        }
        int y = this.y;
        if (enumSetting.droppedDown) {
            for (String string : enumSetting.getModes()) {
                y += 10;
                RenderUtil.drawRect(x - 2, y, x + width + 2, y + height, ClickGui.getInstance().backgroundColor.getColor().getRGB());
                if (mouseX > x && mouseX < x + width && mouseY > y && mouseY < y + height)
                    RenderUtil.drawRect(x+ 3, y, x + width - 1, y + 10, new Color(0, 0, 0, 100).getRGB());
                if(CustomFont.INSTANCE.isEnabled()){
                    Skidded.customFontRenderer.drawStringWithShadow(enumSetting.getValue().equals(string) ? string : ChatFormatting.GRAY + string, (mouseX > x && mouseX < x + width && mouseY > y && mouseY < y + height) ? x + 5 : x + 4, y, -1);
                }else {
                    Skidded.mc.fontRenderer.drawStringWithShadow(enumSetting.getValue().equals(string) ? string : ChatFormatting.GRAY + string, (mouseX > x && mouseX < x + width && mouseY > y && mouseY < y + height) ? x + 5 : x + 4, y, -1);
                }
            }
            RenderUtil.drawOutlineRect(x + 3, this.y + height - 1, x + width - 1, y + height - 2, ClickGui.getInstance().color.getColor(), 1f);
        }
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (isInside(mouseX, mouseY) && mouseButton == 1) {
            enumSetting.droppedDown = !enumSetting.droppedDown;
        }
        int y = this.y;
        if (enumSetting.droppedDown)
            for (String string : enumSetting.getModes()) {
                y += 10;
                if (mouseX > x && mouseX < x + width && mouseY > y && mouseY < y + height && mouseButton == 0) {
                    enumSetting.setValue(string);
                }

            }

        Skidded.mc.getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0f));
    }
}
