package skidd.ed.modules.core;

import skidd.ed.modules.Module;
import skidd.ed.modules.ModuleInfo;
import skidd.ed.settings.impl.BooleanSetting;

@ModuleInfo(name = "CustomFont", description = "Changes the font of the client", category = Module.Category.Core)
public class CustomFont extends Module {

    public BooleanSetting antiAlias = new BooleanSetting("AntiAlias", true, this);
    public BooleanSetting fractional = new BooleanSetting("Fractional Metrics", false, this);

        public static CustomFont INSTANCE;

        public CustomFont() {
            INSTANCE = this;
        }
}
