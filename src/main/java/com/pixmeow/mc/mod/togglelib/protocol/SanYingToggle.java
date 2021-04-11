package com.pixmeow.mc.mod.togglelib.protocol;

import com.pixmeow.mc.mod.togglelib.brand.SanYing;
import net.java.games.input.*;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

public class SanYingToggle {
    public static class Cmd<T>{
        T value;
        String identity;
    }
    static final SanYingToggle INSTANCE = new SanYingToggle();
    static Controller toggle;

    Map<String, Function<Float, Cmd<?>>> keys = new LinkedHashMap<>();

    public static void registerToggle(Controller toggle){
        if (SanYingToggle.toggle == null) {
            SanYingToggle.toggle = toggle;
            SanYing.init(toggle);
        }
    }

    public static void main(String[] args) {
        System.out.printf("============ ToggleLib ============\nToggle Name: %s\nToggle Protocol Version: %d\nScript Author: %s\n", SanYing.name, SanYing.version, SanYing.author);

        boolean run = true;
        while (run) {
            if (toggle == null) {
                System.out.println("Not detect San Ying controllers.");
                run = false;
            }

            /* Get the controllers event queue */
            EventQueue queue = toggle.getEventQueue();
            Event event = new Event();
            toggle.poll();

            /* For each object in the queue */
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
