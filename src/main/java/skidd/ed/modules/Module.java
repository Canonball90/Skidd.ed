package skidd.ed.modules;

import skidd.ed.event.events.ModuleToggleEvent;
import skidd.ed.settings.Setting;
import skidd.ed.settings.impl.BooleanSetting;
import skidd.ed.settings.impl.KeySetting;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;
import java.util.List;

public class Module {
    public static Minecraft mc = Minecraft.getMinecraft();
    public List<Setting> settings = new ArrayList<>();
    public String name = getModuleInfo().name();
    public String description = getModuleInfo().description();
    public Category category = getModuleInfo().category();
    public KeySetting keyBind = new KeySetting("Keybind", Keyboard.KEY_NONE, this);
    public BooleanSetting enabled = new BooleanSetting("Enabled", false, this);
    public boolean isOpened = false;

    public Module() {
        initializeModule();
    }

    public void initializeModule() {
    }

    public void onEnable() {
    }

    public void onDisable() {
    }

    public void onTick(){
    }

    public void onWorldRender(){
    }

    public void enableModule() {
        enabled.setValue(true);
        onEnable();
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.post(new ModuleToggleEvent.Enable(this));
    }

    public void disableModule() {
        enabled.setValue(false);
        onDisable();
        MinecraftForge.EVENT_BUS.unregister(this);
        MinecraftForge.EVENT_BUS.post(new ModuleToggleEvent.Disable(this));
    }

    public void setEnabled(boolean enabled) {
        this.enabled.setValue(enabled);
    }

    public boolean isEnabled() {
        return enabled.getValue();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setKeyBind(int key) {
        keyBind.setValue(key);
    }

    public int getKeyBind() {
        return keyBind.getKey();
    }

    public String getKeyBindAsString() {
        return Keyboard.getKeyName(keyBind.getKey());
    }

    public Category getCategory() {
        return category;
    }

    public ModuleInfo getModuleInfo() {
        return getClass().getAnnotation(ModuleInfo.class);
    }

    public List<Setting> getSettings() {
        return settings;
    }

    public enum Category {
        Combat,
        Core,
        Misc,
        Movement,
        Player,
        Visual
    }
}
