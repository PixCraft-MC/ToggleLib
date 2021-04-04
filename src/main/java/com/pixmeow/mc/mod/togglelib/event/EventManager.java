package com.pixmeow.mc.mod.togglelib.event;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod.EventBusSubscriber()
public class EventManager {
    public EventManager(FMLInitializationEvent e) {
        MinecraftForge.EVENT_BUS.register(this);
    }
}
