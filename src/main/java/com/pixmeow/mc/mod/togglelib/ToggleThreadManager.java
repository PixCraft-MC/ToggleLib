package com.pixmeow.mc.mod.togglelib;

import com.pixmeow.mc.mod.togglelib.toggle.SanYingToggle;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;

public class ToggleThreadManager {
    static Thread t;
    private static boolean isToggleEnabled = false;

    public static void start() {
        if (t == null){
            t = new SanYing(null);
            t.start();
        }else if (!t.isAlive()){
            t.stop();
            t.start();
        }
    }

    public static void stop(){
        if(t != null && t.isAlive()){
            t.stop();
            t = null;
        }
    }

    public static boolean isIsToggleEnabled() {
        return isToggleEnabled;
    }

    public static void init() {
        Controller[] controllers = ControllerEnvironment.getDefaultEnvironment().getControllers();
        for (Controller c : controllers) {
            if (c.getName().equals(Reference.ToggleID)) {
                Logger.Info("Found Toggle (SanYingToggle), registing...", "Toggle Controller");
                SanYingToggle.registerToggle(c);
                isToggleEnabled = true;
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
            SanYingToggle.main(args);
        }
    }
}
