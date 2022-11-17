package skidd.ed.modules.core;

import skidd.ed.hud.HudWindow;
import skidd.ed.modules.Module;
import skidd.ed.modules.ModuleInfo;

@ModuleInfo(name = "Hud Editor", category = Module.Category.Core, description = "Edits the hud ye")
public class HudEditor extends Module {

    @Override
    public void onEnable() {
        mc.displayGuiScreen(HudWindow.getInstance());
        disableModule();
    }
}
