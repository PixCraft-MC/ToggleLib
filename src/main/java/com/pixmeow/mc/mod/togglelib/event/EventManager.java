package com.pixmeow.mc.mod.togglelib.event;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber()
public class EventManager {
    public EventManager(FMLInitializationEvent e) {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onReceiveAts(ToggleEvent.ButtonEvent.ATSConfirmedEvent e){
        System.out.printf("ATS %s\n" , (e.getStatus().isAts() ? "Confirmed" : "---"));
    }

    @SubscribeEvent
    public void onReceiveHorn(ToggleEvent.ButtonEvent.HornEvent e){
        System.out.printf("Horn %s\n" ,(e.getStatus().isHorn() ? "On" : "Off"));
    }

    @SubscribeEvent
    public void onReceiveConst(ToggleEvent.ButtonEvent.ConstSpeedEvent e){
        System.out.printf("Const Speed %s\n" ,(e.getStatus().isConstSpeed() ? "On" : "Off"));
    }

    @SubscribeEvent
    public void onReceiveSwitch(ToggleEvent.ButtonEvent.SwitchEvent e){
        System.out.printf("Direction [%s]\n", e.getStatus().getDirection().getName());
    }

    @SubscribeEvent
    public void onReceiveHandler(ToggleEvent.ButtonEvent.HandlerEvent e){
        System.out.printf("Toggle [%s]\n", e.getStatus().getLevel().getName());
    }

}
