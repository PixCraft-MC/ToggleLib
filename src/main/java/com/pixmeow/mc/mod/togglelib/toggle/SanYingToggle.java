package com.pixmeow.mc.mod.togglelib.toggle;

import com.pixmeow.mc.mod.togglelib.Logger;
import com.pixmeow.mc.mod.togglelib.brand.SanYing;
import net.java.games.input.Controller;
import net.java.games.input.Event;
import net.java.games.input.EventQueue;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

public class SanYingToggle {
    static final SanYingToggle INSTANCE = new SanYingToggle();
    static Controller toggle;
    Map<String, Function<Float, Cmd<?>>> keys = new LinkedHashMap<>();

    public static void registerToggle(Controller toggle) {
        if (SanYingToggle.toggle == null) {
            SanYingToggle.toggle = toggle;
            SanYing.init(toggle);
        }
    }

    public static void main(String[] args) {
        Logger.Info(String.format("============ ToggleLib ============\nToggle Name: %s\nToggle Protocol Version: %d\nScript Author: %s\n", SanYing.name, SanYing.version, SanYing.author), "SanYing Toggle");
        boolean run = true;
        while (run) {
            if (toggle == null) {
                System.out.println("Not detect San Ying controllers.");
                run = false;
            }
            if (run) {
                EventQueue queue = toggle.getEventQueue();
                Event event = new Event();
                toggle.poll();

                while (queue.getNextEvent(event)) {
                    SanYing.on(event);
                }
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    run = false;
                }
            }
        }
    }

    public static class Cmd<T> {
        T value;
        String identity;
    }
}
