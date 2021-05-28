package com.pixmeow.mc.mod.togglelib;

import com.pixmeow.mc.mod.togglelib.protocol.SanYingToggle;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;

public class ToggleThreadManager {
    static Thread t;

    public static void StartSanYing() {
        t = new SanYing(null);
        t.start();
    }

    public static void init() {
        Controller[] controllers = ControllerEnvironment.getDefaultEnvironment().getControllers();
        for (Controller c : controllers) {
            if (c.getName().equals(Reference.ToggleID)) {
                SanYingToggle.registerToggle(c);
            }
        }
    }

    public static class SanYing extends Thread {
        String[] args;

        public SanYing(String[] args) {
            this.args = args;
        }

        @Override
        public void run() {
            ToggleThreadManager.init();
            SanYingToggle.main(args);
        }
    }
}
