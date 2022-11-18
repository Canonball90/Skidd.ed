package skidd.ed.event.events;

import net.minecraft.util.EnumHandSide;
import net.minecraftforge.fml.common.eventhandler.Event;

public class EventTransform extends Event {
    private final EnumHandSide enumHandSide;

    public EventTransform(EnumHandSide var1) {
        this.enumHandSide = var1;
    }

    public EnumHandSide getEnumHandSide() {
        return this.enumHandSide;
    }
}

