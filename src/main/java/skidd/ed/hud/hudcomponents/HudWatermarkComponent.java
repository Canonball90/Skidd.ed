package skidd.ed.hud.hudcomponents;

import skidd.ed.Skidded;
import skidd.ed.hud.HudModule;
import skidd.ed.modules.core.ClickGui;
import skidd.ed.modules.core.CustomFont;
import skidd.ed.utils.RenderUtil;

import java.awt.*;

public class HudWatermarkComponent extends HudModule {
    int dragX;
    int dragY;
    boolean isDragging;

    public HudWatermarkComponent() {
        super("Watermark");
        renderX = 0;
        renderY = 0;
    }

    public void dragScreen(int mouseX, int mouseY) {
        if (!isDragging)
            return;
        renderX = dragX + mouseX;
        renderY = dragY + mouseY;
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        dragScreen(mouseX, mouseY);
        if (getValue()) {
            if (isInsideDragField(mouseX, mouseY)) {
                RenderUtil.drawRect(renderX, renderY, renderX + Skidded.mc.fontRenderer.getStringWidth("Mint 0.1.1"), renderY + Skidded.mc.fontRenderer.FONT_HEIGHT, new Color(0, 0, 0, 100).getRGB());
                RenderUtil.drawRect(renderX + Skidded.mc.fontRenderer.getStringWidth("Mint 0.1.1") + 3, renderY - 7, renderX + Skidded.mc.fontRenderer.getStringWidth("Mint 0.1.1") + 3 + Skidded.mc.fontRenderer.getStringWidth("renderX: " + renderX + " renderY: " + renderY), renderY - 7 + Skidded.mc.fontRenderer.FONT_HEIGHT, new Color(0, 0, 0, 100).getRGB());
                Skidded.mc.fontRenderer.drawStringWithShadow("renderX: " + renderX + " renderY: " + renderY, renderX + Skidded.mc.fontRenderer.getStringWidth("Mint 0.1.1") + 3, renderY - 7, -1);
            }
            drawText();
        }
    }

    public void drawText() {
        if(CustomFont.INSTANCE.isEnabled()){
            Skidded.customFontRenderer.drawString("Skidd.ed \u2764 1.0", renderX, renderY, ClickGui.getInstance().color.getColor().getRGB());
        }else {
            Skidded.mc.fontRenderer.drawStringWithShadow("Skidd.ed \u2764 1.0", renderX, renderY, ClickGui.getInstance().color.getColor().getRGB());
        }
    }

    public boolean isInsideDragField(int mouseX, int mouseY) {
        return (mouseX > renderX && mouseX < renderX + Skidded.mc.fontRenderer.getStringWidth("Mint 0.1.1")) && (mouseY > renderY && mouseY < renderY + Skidded.mc.fontRenderer.FONT_HEIGHT);
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (mouseButton == 0 && isInsideDragField(mouseX, mouseY)) {
            dragX = renderX - mouseX;
            dragY = renderY - mouseY;
            isDragging = true;
        }
        if (mouseButton == 0 && isInside(mouseX, mouseY))
            value = !value;
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY, int releaseButton) {
        if (releaseButton == 0)
            isDragging = false;
    }
}
