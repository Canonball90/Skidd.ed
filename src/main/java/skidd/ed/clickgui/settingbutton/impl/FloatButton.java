package skidd.ed.clickgui.settingbutton.impl;

import com.mojang.realmsclient.gui.ChatFormatting;
import skidd.ed.Skidded;
import skidd.ed.clickgui.settingbutton.Button;
import skidd.ed.modules.core.ClickGui;
import skidd.ed.modules.core.CustomFont;
import skidd.ed.settings.Setting;
import skidd.ed.settings.impl.FloatSetting;
import skidd.ed.utils.RenderUtil;
import org.lwjgl.input.Mouse;

import java.awt.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class FloatButton extends Button {

    int minimax;
    FloatSetting floatSetting;
    Number min;
    Number max;

    public FloatButton(Setting setting, FloatSetting floatSetting) {
        super(setting);
        this.floatSetting = floatSetting;
        min = floatSetting.getMinimum();
        max = floatSetting.getMaximum();
        minimax = max.intValue() - min.intValue();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        dragSlider(mouseX, mouseY);
        RenderUtil.drawRect(x - 2, y, x + width + 2, y + height, ClickGui.getInstance().backgroundColor.getColor().getRGB());
        if(ClickGui.getInstance().frameGradient.getValue()){
            RenderUtil.drawGradientSideways(x, y, ((Number) floatSetting.getValue()).floatValue() <= floatSetting.getMinimum() ? x : x + ((float) width + 2f) * ((((Number) floatSetting.getValue()).floatValue() - floatSetting.getMinimum()) / (float) (floatSetting.getMaximum() - floatSetting.getMinimum())) - 2, y + (float) height, ClickGui.getInstance().gColor1.getColor().getRGB(), ClickGui.getInstance().gColor2.getColor().getRGB());
        }else {
            RenderUtil.drawRect(x, y, ((Number) floatSetting.getValue()).floatValue() <= floatSetting.getMinimum() ? x : x + ((float) width + 2f) * ((((Number) floatSetting.getValue()).floatValue() - floatSetting.getMinimum()) / (floatSetting.getMaximum() - floatSetting.getMinimum())) - 2, y + (float) height, ClickGui.getInstance().color.getColor().getRGB());
        }
        if (isInside(mouseX, mouseY))
            RenderUtil.drawRect(x, y, x + width, y + height, new Color(0, 0, 0, 100).getRGB());
        if(CustomFont.INSTANCE.isEnabled()){
            Skidded.customFontRenderer.drawStringWithShadow(floatSetting.getName() + " " + ChatFormatting.GRAY + roundNumber(floatSetting.getValue(), 1), x + 2, y + (height / 2f) - (Skidded.mc.fontRenderer.FONT_HEIGHT / 2f), -1);
        }else {
            Skidded.mc.fontRenderer.drawStringWithShadow(floatSetting.getName() + " " + ChatFormatting.GRAY + roundNumber(floatSetting.getValue(), 1), x + 2, y + (height / 2f) - (Skidded.mc.fontRenderer.FONT_HEIGHT / 2f), -1);
        }
    }

    void dragSlider(int mouseX, int mouseY) {
        if (isInsideProper(mouseX, mouseY) && Mouse.isButtonDown(0))
            setSliderValue(mouseX);
    }

    public boolean isInsideProper(int mouseX, int mouseY) {
        return (mouseX > x && mouseX < x + width - 3) && (mouseY > y && mouseY < y + height);
    }

    public static float roundNumber(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }
        BigDecimal decimal = BigDecimal.valueOf(value);
        decimal = decimal.setScale(places, RoundingMode.FLOOR);
        return decimal.floatValue();
    }


    void setSliderValue(int mouseX) {
        float percent = ((float) mouseX - x - 1) / ((float) width - 5);
        if (floatSetting.getValue() != null) {
            float result = floatSetting.getMinimum() + (float) minimax * percent;
            floatSetting.setValue((float) Math.round(10.0f * result) / 10.0f);
        }
    }

}
