package skidd.ed.modules.core;

import skidd.ed.Skidded;
import skidd.ed.modules.Module;
import skidd.ed.modules.ModuleInfo;
import skidd.ed.settings.impl.ColorSetting;
import skidd.ed.settings.impl.IntegerSetting;
import org.lwjgl.input.Keyboard;

import java.awt.*;

@ModuleInfo(name = "Click Gui", category = Module.Category.Core, description = "Displays the clickgui.")
public class ClickGui extends Module {
    static ClickGui INSTANCE = new ClickGui();
    public ColorSetting color = new ColorSetting("Color", new Color(255, 255, 255, 255), this);
    public ColorSetting backgroundColor = new ColorSetting("Background Color", new Color(0, 0, 0, 50), this);
    public IntegerSetting integerSetting = new IntegerSetting("I", 100, 0, 500, this);
    @Override
    public void initializeModule() {
        setInstance();
        setKeyBind(Keyboard.KEY_RSHIFT);
    }

    @Override
    public void onEnable() {
        mc.displayGuiScreen(skidd.ed.clickgui.ClickGui.getInstance());
    }

    @Override
    public void onDisable() {
        Skidded.configInitializer.save();
    }

    @Override
    public void onTick() {
        if (!(mc.currentScreen instanceof skidd.ed.clickgui.ClickGui) && isEnabled())
            disableModule();
    }

    public static ClickGui getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ClickGui();
        }
        return INSTANCE;
    }

    private void setInstance() {
        INSTANCE = this;
    }

}