package skidd.ed;

import skidd.ed.event.EventListener;
import skidd.ed.hud.HudComponentInitializer;
import skidd.ed.initializers.ConfigInitializer;
import skidd.ed.initializers.FriendInitializer;
import skidd.ed.modules.ModuleInitializer;
import skidd.ed.modules.core.CustomFont;
import skidd.ed.settings.SettingInitializer;
import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.Display;
import skidd.ed.utils.font.CustomFontRenderer;

import java.awt.*;

public class Skidded {
    public static Minecraft mc = Minecraft.getMinecraft();
    public static Skidded INSTANCE = new Skidded();

    public static ConfigInitializer configInitializer;
    public static EventListener eventListener;
    public static ModuleInitializer moduleInitializer;
    public static SettingInitializer settingInitializer;
    public static FriendInitializer friendInitializer;
    public static HudComponentInitializer hudComponentInitializer;
    public static CustomFontRenderer customFontRenderer;

    public void init() {
        Display.setTitle("Skidd.ed \u2764 1.0");
        settingInitializer = new SettingInitializer();
        eventListener = new EventListener();
        eventListener.init(true);
        moduleInitializer = new ModuleInitializer();
        friendInitializer = new FriendInitializer();
        hudComponentInitializer = new HudComponentInitializer();
        customFontRenderer = new CustomFontRenderer(new Font("Arial", Font.PLAIN, 19), CustomFont.INSTANCE.antiAlias.getValue(), CustomFont.INSTANCE.fractional.getValue());
        configInitializer = new ConfigInitializer();
        configInitializer.init();
    }
}
