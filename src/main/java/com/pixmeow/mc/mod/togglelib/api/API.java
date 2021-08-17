package com.pixmeow.mc.mod.togglelib.api;

import com.pixmeow.mc.mod.togglelib.ToggleLib;

import java.util.ArrayList;

public class API {
    static ArrayList<ToggleSupported> subscribers = new ArrayList<>();

    public static void register(ToggleSupported subscribe){
        subscribers.add(subscribe);
    }

    public static void init(){
        subscribers.forEach(ToggleLib.Toggle_EVENT_BUS::register);
    }
}
