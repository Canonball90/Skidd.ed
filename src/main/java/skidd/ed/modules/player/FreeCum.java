package skidd.ed.modules.player;

import net.minecraft.block.material.Material;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import skidd.ed.modules.Module;
import skidd.ed.modules.ModuleInfo;
import skidd.ed.settings.impl.FloatSetting;

@ModuleInfo(name = "FreeCum", description = "", category = Module.Category.Player)
public class FreeCum
        extends Module {
    private float yaw;
    private float pitch;
    private float yawHead;
    private float gamma;
    private EntityOtherPlayerMP other;
    private float old;
    private EntityOtherPlayerMP fakePlayer = null;
    private double oldX;
    private double oldY;
    private double oldZ;

    FloatSetting speed = new FloatSetting("Speed", 0.5f, 0.1f, 1.0f, this);

    @Override
    public void onDisable() {
        FreeCum.mc.player.capabilities.isFlying = false;
        FreeCum.mc.player.capabilities.setFlySpeed(this.old);
        FreeCum.mc.player.rotationPitch = this.pitch;
        FreeCum.mc.player.rotationYaw = this.yaw;
        FreeCum.mc.world.removeEntityFromWorld(-1);
        FreeCum.mc.player.noClip = false;
        FreeCum.mc.renderGlobal.loadRenderers();
        FreeCum.mc.player.noClip = false;
        FreeCum.mc.player.setPositionAndRotation(this.oldX, this.oldY, this.oldZ, FreeCum.mc.player.rotationYaw, FreeCum.mc.player.rotationPitch);
        FreeCum.mc.world.removeEntityFromWorld(-69);
        this.fakePlayer = null;
        super.onDisable();
    }

    @Override
    public void onEnable() {
        super.onEnable();
        this.oldX = FreeCum.mc.player.posX;
        this.oldY = FreeCum.mc.player.posY;
        this.oldZ = FreeCum.mc.player.posZ;
        FreeCum.mc.player.noClip = true;
        EntityOtherPlayerMP fakePlayer = new EntityOtherPlayerMP((World)FreeCum.mc.world, FreeCum.mc.player.getGameProfile());
        fakePlayer.copyLocationAndAnglesFrom((Entity)FreeCum.mc.player);
        fakePlayer.posY -= 0.0;
        fakePlayer.rotationYawHead = FreeCum.mc.player.rotationYawHead;
        FreeCum.mc.world.addEntityToWorld(-69, (Entity)fakePlayer);
    }

    @SubscribeEvent
    public void onRenderWorld(RenderWorldLastEvent event) {
        FreeCum.mc.player.noClip = true;
        FreeCum.mc.player.onGround = false;
        FreeCum.mc.player.capabilities.setFlySpeed(speed.getValue());
        FreeCum.mc.player.capabilities.isFlying = true;
        if (!(FreeCum.mc.player.isInsideOfMaterial(Material.AIR) || FreeCum.mc.player.isInsideOfMaterial(Material.LAVA) || FreeCum.mc.player.isInsideOfMaterial(Material.WATER))) {
            if (!(FreeCum.mc.gameSettings.gammaSetting < 100.0f)) {
                return;
            }
            FreeCum.mc.gameSettings.gammaSetting += 0.08f;
            return;
        }
        FreeCum.mc.gameSettings.gammaSetting = this.gamma;
    }
}
