package com.pixmeow.mc.mod.togglelib;

import com.pixmeow.mc.mod.togglelib.utils.ColorText;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextFormatting;

public class Logger {
    static org.apache.logging.log4j.Logger logger;

    public static void setLogger(org.apache.logging.log4j.Logger logger) {
        Logger.logger = logger;
        if (Config.getDoc().isDebug()) logger.info("[ATSX | Logger] Logger System inited!");
    }

    public static void Info(String msg, String id){
        if (Config.getDoc().isDebug()) logger.info(String.format("[ATSX | %S]%S", id, msg));
    }

    public static void Warning(String msg, String id){
        logger.warn(String.format("[ATSX | %S]%S", id, msg));
    }

    public static void Error(String msg, String id){
        logger.error(String.format("[ATSX | %S]%S", id, msg));
    }

    public static void Log(EntityPlayer player, String id, String msg){
        if (Config.getDoc().isDebug()) player.sendMessage(ColorText.format(String.format("[ATSX | %S]%S", id, msg), TextFormatting.GRAY, true, false, false));
    }

    public static void Info(EntityPlayer player, String id, String msg){
        if (Config.getDoc().isDebug())  player.sendMessage(ColorText.format(String.format("[ATSX | %S]%S", id, msg),  TextFormatting.BLUE, true, false, false));
    }

    public static void Warning(EntityPlayer player, String id, String msg){
        player.sendMessage(ColorText.format(String.format("[ATSX | %S]%S", id, msg), TextFormatting.YELLOW, true, false, false));
    }

    public static void Error(EntityPlayer player, String id, String msg){
        player.sendMessage(ColorText.format(String.format("[ATSX | %S]%S", id, msg),  TextFormatting.RED, true, false, false));
    }

    public static void table(EntityPlayer player, String title, String ...content){
        StringBuilder out = new StringBuilder();
    }
}
