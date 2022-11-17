package skidd.ed.settings.impl;

import skidd.ed.modules.Module;
import skidd.ed.settings.Setting;

import java.util.function.Predicate;

public class StringSetting extends Setting<String> {
    boolean isOpen = false;

    public StringSetting(String name, String value, Module module) {
        super(name, value, module);
    }

    public StringSetting(String name, String value, Module module, Predicate<String> shown) {
        super(name, value, module, shown);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public StringSetting setParent(ParentSetting parentSetting){
        this.parentSetting = parentSetting;
        hasParentSetting = true;

        return this;
    }
}
