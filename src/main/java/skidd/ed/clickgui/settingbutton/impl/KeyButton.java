package skidd.ed.clickgui.settingbutton.impl;

import com.mojang.realmsclient.gui.ChatFormatting;
import skidd.ed.Skidded;
import skidd.ed.clickgui.settingbutton.Button;
import skidd.ed.modules.core.ClickGui;
import skidd.ed.modules.core.CustomFont;
import skidd.ed.settings.Setting;
import skidd.ed.settings.impl.KeySetting;
import skidd.ed.utils.RenderUtil;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.init.SoundEvents;
import org.lwjgl.input.Keyboard;

import java.awt.*;

public class KeyButton extends Button {
    KeySetting keySetting;

    public KeyButton(Setting setting, KeySetting keySetting) {
        super(setting);
        this.keySetting = keySetting;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        RenderUtil.drawRect(x - 2, y, x + width + 2, y + height, ClickGui.getInstance().backgroundColor.getColor().getRGB());
        if (isInside(mouseX, mouseY))
            RenderUtil.drawRect(x, y, x + width, y + height, new Color(0, 0, 0, 100).getRGB());
        if(CustomFont.INSTANCE.isEnabled()){
            Skidded.customFontRenderer.drawStringWithShadow(keySetting.isTyping ? keySetting.getName() + " ..." : keySetting.getName() + " " + ChatFormatting.GRAY + (keySetting.getKey() == -1 ? "None" : Keyboard.getKeyName(keySetting.getKey())), x + 2, y + (height / 2f) - (Skidded.mc.fontRenderer.FONT_HEIGHT / 2f), -1);
        }else {
            Skidded.mc.fontRenderer.drawStringWithShadow(keySetting.isTyping ? keySetting.getName() + " ..." : keySetting.getName() + " " + ChatFormatting.GRAY + (keySetting.getKey() == -1 ? "None" : Keyboard.getKeyName(keySetting.getKey())), x + 2, y + (height / 2f) - (Skidded.mc.fontRenderer.FONT_HEIGHT / 2f), -1);
        }
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (mouseButton == 0 && isInside(mouseX, mouseY)) {
            keySetting.isTyping = !keySetting.isTyping;
            Skidded.mc.getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0f));
        }
    }

    @Override
    public void onKeyTyped(char typedChar, int keyCode) {
        if (!keySetting.isTyping)
            return;
        if (keyCode == Keyboard.KEY_DELETE || keyCode == Keyboard.KEY_ESCAPE)
            keySetting.setBind(0);
        else keySetting.setBind(keyCode);

        Skidded.mc.getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0f));
        keySetting.isTyping = !keySetting.isTyping;
    }
}
