package com.pixmeow.mc.mod.togglelib;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class Config {
    private static Configuration config;
    private static final Document doc = new Document();

    public static Document getDoc() {
        return doc;
    }

    public Config(FMLPreInitializationEvent event) {
        config = new Configuration(event.getSuggestedConfigurationFile());
        config.load();
        load();
    }

    public static void save(){

        config.save();
    }

    public static void load() {
        Logger.Info("Started loading config", "Config Loader");
        doc.setDebug(config.get(Configuration.CATEGORY_GENERAL, "debug", false, "Enable debug mode to show detail info of toggle runtime").getBoolean());
        doc.setVersion(config.get(Configuration.CATEGORY_GENERAL, "version", 1, "Config Version[Do not edit it!]").getInt());
        config.save();
        Logger.Info("Finished loading config", "Config Loader");
    }

    public static class Document {
        private static final int DOC_VERSION = 1;
        int version = -1;
        boolean debug = false;

        public void save(Configuration config){

        }

        public int getVersion() {
            if (DOC_VERSION != version){
                // version checker
            }
            return version;
        }

        public void setVersion(int version) {
            this.version = version;
        }

        public boolean isDebug() {
            return debug;
        }

        public void setDebug(boolean debug) {
            this.debug = debug;
        }
    }
}
