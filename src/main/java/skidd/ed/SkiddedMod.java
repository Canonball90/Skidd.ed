package skidd.ed;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = SkiddedMod.MOD_ID, name = SkiddedMod.MOD_NAME, version = SkiddedMod.VERSION)
public class SkiddedMod {

    public static final String MOD_ID = "skidded";
    public static final String MOD_NAME = "Skidded";
    public static final String VERSION = "1.0";

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        Skidded.INSTANCE.init();
    }
}
