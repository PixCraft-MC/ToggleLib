package com.pixmeow.mc.mod.togglelib;

import com.pixmeow.mc.mod.togglelib.io.SanYingToggle;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;

import java.util.PriorityQueue;
import java.util.Queue;

public class ToggleController {
    static Queue<Thread> threads = new PriorityQueue<>();

    public static class SanYing extends Thread{
        String[] args;

        public SanYing(String[] args) {
            this.args = args;
        }

        @Override
        public void run() {
            ToggleController.init();
            SanYingToggle.main(args);
        }
    }

    public static void StartSanYing(){
        SanYing t;
        if (threads.peek() instanceof SanYing){
            t = (SanYing) threads.poll();
            t.stop();
        }else t = new SanYing(null);
        t.start();
        threads.add(t);
    }

    public static void init(){
        Controller[] controllers = ControllerEnvironment.getDefaultEnvironment().getControllers();
        for (Controller c: controllers) {
            if (c.getName().equals(Reference.ToggleID)){
                // register San Ying Toggle
                SanYingToggle.registerToggle(c);
            }
        }

    }
}
