package com.pixmeow.mc.mod.togglelib.api;

import com.pixmeow.mc.mod.togglelib.event.ToggleEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public interface ToggleSupported {
    @SubscribeEvent
    public void OnReceiveInput(ToggleEvent event);
}
