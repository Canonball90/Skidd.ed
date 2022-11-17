package skidd.ed.modules.core;

import com.mojang.realmsclient.gui.ChatFormatting;
import skidd.ed.event.events.DeathEvent;
import skidd.ed.event.events.ModuleToggleEvent;
import skidd.ed.modules.Module;
import skidd.ed.modules.ModuleInfo;
import skidd.ed.settings.impl.BooleanSetting;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@ModuleInfo(name = "Chat Notifications", category = Module.Category.Core, description = "Send Notifications in chat when certain things happen.")
public class ChatNotifications extends Module {
    public BooleanSetting modules = new BooleanSetting("Modules", false, this);
 //   public BooleanSetting totemPops = new BooleanSetting("Totem Pops", false, this);
    public BooleanSetting deaths = new BooleanSetting("Deaths", false, this);

    @SubscribeEvent
    public void onModuleEnableEvent(ModuleToggleEvent.Enable event){
        if(modules.getValue())
            mc.ingameGUI.getChatGUI().printChatMessageWithOptionalDeletion(new TextComponentString("\u2764Skidd.ed\u2764 " + ChatFormatting.BOLD + event.getModule().getName() + ChatFormatting.RESET + " has been " + ChatFormatting.GREEN + "Enabled" + ChatFormatting.RESET + "."), 1);
    }

    @SubscribeEvent
    public void onModuleDisableEvent(ModuleToggleEvent.Disable event){
        if(modules.getValue())
            mc.ingameGUI.getChatGUI().printChatMessageWithOptionalDeletion(new TextComponentString("\u2764Skidd.ed\u2764 " + ChatFormatting.BOLD + event.getModule().getName() + ChatFormatting.RESET + " has been " + ChatFormatting.RED + "Disabled" + ChatFormatting.RESET + "."), 1);
    }

    @SubscribeEvent
    public void onDeathEvent(DeathEvent event){
        if(deaths.getValue())
            mc.ingameGUI.getChatGUI().printChatMessageWithOptionalDeletion(new TextComponentString("\u2764Skidd.ed\u2764 " + ChatFormatting.BOLD + event.getPlayer().getName() + ChatFormatting.RESET + " has just died."), 1);

    }
}
