package com.pixmeow.mc.mod.togglelib.toggle;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Deprecated
public class Toggle {
    static class Key<T>{
        static class Cmd{
            float value;
            Function<Void, Void> cmd;
        }

        static class KeyMap{

        }

        Map<String, T> keys;
        String name;
        boolean isAnalog;
        boolean isButton;
        boolean isToggle;
        List<Cmd> cmd;

    }

    public static String name = "Unknown";
    public static int version = 1;
    public static String author = "PixMeow";
    List<Key<?>> keys = new ArrayList<>();
}
