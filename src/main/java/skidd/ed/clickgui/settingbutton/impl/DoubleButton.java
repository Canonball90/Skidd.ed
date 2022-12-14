package skidd.ed.clickgui.settingbutton.impl;


import com.mojang.realmsclient.gui.ChatFormatting;
import skidd.ed.Skidded;
import skidd.ed.clickgui.settingbutton.Button;
import skidd.ed.modules.core.ClickGui;
import skidd.ed.modules.core.CustomFont;
import skidd.ed.settings.Setting;
import skidd.ed.settings.impl.DoubleSetting;
import skidd.ed.utils.RenderUtil;
import org.lwjgl.input.Mouse;

import java.awt.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class DoubleButton extends Button {

    int minimax;
    DoubleSetting doubleSetting;
    Number min;
    Number max;

    public DoubleButton(Setting setting, DoubleSetting doubleSetting) {
        super(setting);
        this.doubleSetting = doubleSetting;
        min = doubleSetting.getMinimum();
        max = doubleSetting.getMaximum();
        minimax = max.intValue() - min.intValue();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        dragSlider(mouseX, mouseY);
        RenderUtil.drawRect(x - 2, y, x + width + 2, y + height, ClickGui.getInstance().backgroundColor.getColor().getRGB());
        if(ClickGui.getInstance().frameGradient.getValue()) {
            RenderUtil.drawGradientSideways(x, y, ((Number) doubleSetting.getValue()).intValue() <= doubleSetting.getMinimum() ? x : x + ((float) width + 2f) * ((((Number) doubleSetting.getValue()).intValue() - doubleSetting.getMinimum()) / (float) (doubleSetting.getMaximum() - doubleSetting.getMinimum())) - 2, y + (float) height, ClickGui.getInstance().gColor1.getColor().getRGB(), ClickGui.getInstance().gColor2.getColor().getRGB());
        }else{
            RenderUtil.drawRect(x, y, ((Number) doubleSetting.getValue()).doubleValue() <= doubleSetting.getMinimum() ? x : (float) (x + ((float) width + 2f) * ((((Number) doubleSetting.getValue()).doubleValue() - doubleSetting.getMinimum()) / (doubleSetting.getMaximum() - doubleSetting.getMinimum())) - 2), y + (float) height, ClickGui.getInstance().color.getColor().getRGB());
        }
        if (isInside(mouseX, mouseY))
            RenderUtil.drawRect(x, y, x + width, y + height, new Color(0, 0, 0, 100).getRGB());
        if(CustomFont.INSTANCE.isEnabled()){
            Skidded.customFontRenderer.drawStringWithShadow(doubleSetting.getName() + " " + ChatFormatting.GRAY + roundNumber(doubleSetting.getValue(), 2), x + 2, y + (height / 2f) - (Skidded.mc.fontRenderer.FONT_HEIGHT / 2f), -1);
        }else {
            Skidded.mc.fontRenderer.drawStringWithShadow(doubleSetting.getName() + " " + ChatFormatting.GRAY + roundNumber(doubleSetting.getValue(), 2), x + 2, y + (height / 2f) - (Skidded.mc.fontRenderer.FONT_HEIGHT / 2f), -1);
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
        if (doubleSetting.getValue() != null) {
            double result = doubleSetting.getMinimum() + (double) ((float) minimax * percent);
            doubleSetting.setValue(roundNumber(10.0 * result, 2) / 10.0);
        }
    }

}
