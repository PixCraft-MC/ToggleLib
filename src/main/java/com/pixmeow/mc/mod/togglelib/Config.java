package com.pixmeow.mc.mod.togglelib;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import org.apache.logging.log4j.Logger;

public class Config {
    private static Configuration config;
    private static Logger logger;
    public static int version;

    public Config(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        config = new Configuration(event.getSuggestedConfigurationFile());
        config.load();
        load();
    }

    public static void load() {
        logger.info("Started loading config. ");
        version = config.get(Configuration.CATEGORY_GENERAL, "version", 1, "Config Version[Do not edit it!]").getInt();
        config.save();
        logger.info("Finished loading config. ");
    }

    public static Logger logger() {
        return logger;
    }
}
