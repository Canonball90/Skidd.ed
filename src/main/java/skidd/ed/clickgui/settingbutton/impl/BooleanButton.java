package skidd.ed.clickgui.settingbutton.impl;

import skidd.ed.Skidded;
import skidd.ed.clickgui.settingbutton.Button;
import skidd.ed.modules.core.ClickGui;
import skidd.ed.settings.Setting;
import skidd.ed.utils.RenderUtil;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.init.SoundEvents;

import java.awt.*;

public class BooleanButton extends Button {
    Setting setting;

    public BooleanButton(Setting setting) {
        super(setting);
        this.setting = setting;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        RenderUtil.drawRect(x - 2, y, x + width + 2, y + height, ClickGui.getInstance().backgroundColor.getColor().getRGB());
        if ((boolean) setting.getValue())
            if(ClickGui.getInstance().frameGradient.getValue()){
                RenderUtil.drawGradientSideways(x, y, x + width, y + height, ClickGui.getInstance().gColor1.getValue().getRGB(), ClickGui.getInstance().gColor2.getValue().getRGB());
            }else {
                RenderUtil.drawRect(x, y, x + width, y + height, ClickGui.getInstance().color.getColor().getRGB());
            }

        if (isInside(mouseX, mouseY))
            RenderUtil.drawRect(x, y, x + width, y + height, new Color(0, 0, 0, 100).getRGB());
        Skidded.mc.fontRenderer.drawStringWithShadow(setting.getName(), x + 2, y + (height / 2f) - (Skidded.mc.fontRenderer.FONT_HEIGHT / 2f), -1);
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (mouseButton == 0 && isInside(mouseX, mouseY)) {
            if (getValue())
                setting.setValue(false);
            else setting.setValue(true);
            Skidded.mc.getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0f));
        }

    }

    public boolean getValue() {
        return (boolean) setting.getValue();
    }

}
