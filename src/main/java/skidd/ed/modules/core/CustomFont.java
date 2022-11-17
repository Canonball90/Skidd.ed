package skidd.ed.modules.core;

import skidd.ed.modules.Module;
import skidd.ed.modules.ModuleInfo;

@ModuleInfo(name = "CustomFont", description = "Changes the font of the client", category = Module.Category.Core)
public class CustomFont extends Module {

        public static CustomFont INSTANCE;

        public CustomFont() {
            INSTANCE = this;
        }
}
