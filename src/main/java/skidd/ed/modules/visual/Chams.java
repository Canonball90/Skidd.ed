package skidd.ed.modules.visual;

import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;
import skidd.ed.modules.Module;
import skidd.ed.modules.ModuleInfo;
import skidd.ed.settings.impl.*;

import java.awt.*;
import java.util.Arrays;

@ModuleInfo(name = "Chams", description = "Makes players visible through walls", category = Module.Category.Visual)
public class Chams extends Module {
    ColorSetting color = new ColorSetting("Color", new Color(255, 2, 2, 2), this);
    EnumSetting mode = new EnumSetting("Mode", "XYZ", Arrays.asList("Flat", "XYZ", "Texture"), this);
    BooleanSetting lines = new BooleanSetting("Lines", true, this);
    IntegerSetting width = new IntegerSetting("Width", 40, 0, 100, this);

    BooleanSetting pulse = new BooleanSetting("Pulse", true, this);
    FloatSetting pulseMax = new FloatSetting("Pulse Max", 1.5f, 0.0f, 255.0f, this, v -> pulse.getValue());
    FloatSetting pulseMin = new FloatSetting("Pulse Min", 1.0f, 0.0f, 255.0f, this, v -> pulse.getValue());
    FloatSetting pulseSpeed = new FloatSetting("Pulse Speed", 4.0f, 0.0f, 5.0f, this, v -> pulse.getValue());
    FloatSetting rollingWidth = new FloatSetting("Pulse W", 8.0f, 0.0f, 20.0f, this, v -> pulse.getValue());

    @SubscribeEvent
    public void onPlayerModelPre(final RenderPlayerEvent.Pre event) {
        final Color c = color.getValue();
        GL11.glPushMatrix();
        GL11.glEnable(32823);
        GL11.glPolygonOffset(1.0f, -1.0E7f);
        GL11.glPushAttrib(1048575);
        if (!this.lines.getValue()) {
            GL11.glPolygonMode(1028, 6914);
        } else {
            GL11.glPolygonMode(1028, 6913);
        }
        GL11.glDisable(3553);
        GL11.glDisable(2896);
        GL11.glDisable(2929);
        GL11.glEnable(2848);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        if(mode.getValue().equalsIgnoreCase("XYZ")) {
            GL11.glColor4f(c.getRed() / 255.0f, c.getGreen() / 255.0f, c.getBlue() / 255.0f, c.getAlpha() / 255.0f / 2.0f);
        }else if(mode.getValue().equalsIgnoreCase("Flat")) {
            GL11.glColor3f(c.getRed() / 255.0f, c.getGreen() / 255.0f, c.getBlue() / 255.0f);
        }else if(mode.getValue().equalsIgnoreCase("Texture")) {

        }
        if (this.lines.getValue()) {
            GL11.glLineWidth(this.width.getValue() / 10.0f);
        }
    }

    @SubscribeEvent
    public void onPlayerModelPost(final RenderPlayerEvent.Post event){
        GL11.glPopAttrib();
        GL11.glPolygonOffset(1.0f, 1.0E7f);
        GL11.glDisable(32823);
        GL11.glPopMatrix();
    }

    //ToDo make this work
    private float getRolledHeight(float offset) {
        double s = (System.currentTimeMillis() / (double)pulseSpeed.getValue()) + (offset * rollingWidth.getValue() * 100.0f);
        s %= 300.0;                          //^ not supposed to be there
        s = (150.0f * Math.sin(((s - 75.0f) * Math.PI) / 150.0f)) + 150.0f;
        return pulseMax.getValue() + ((float)s * ((pulseMin.getValue() - pulseMax.getValue()) / 300.0f));
    }
}
