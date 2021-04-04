package com.pixmeow.mc.mod.togglelib.io.json;

import com.google.gson.Gson;

import java.io.Reader;
import java.lang.reflect.Type;

public class JSONLoader {

    public static <T> T parse(Reader stuff, Type c){
        try {
            Gson obj = new Gson();
            return obj.fromJson(stuff, c);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
