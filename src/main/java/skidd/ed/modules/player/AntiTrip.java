package skidd.ed.modules.player;

import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import skidd.ed.modules.Module;
import skidd.ed.modules.ModuleInfo;

/**
 * Skidded from (https://github.com/master7720/AntiTrip/blob/main/AntiTrip.java)
 */
@ModuleInfo(name = "AntiTrip", description = "Prevents you from tripping", category = Module.Category.Player)
public class AntiTrip extends Module {


    @SubscribeEvent
    public void onUpdate(TickEvent.PlayerTickEvent e) {
        if (mc.player.fallDistance < 3.0) {
            mc.player.connection.sendPacket(new CPacketPlayer.Position(mc.player.posX, mc.player.posY + 2, mc.player.posZ, true));
            this.disableModule();
        }
    }
}
