package com.pixmeow.mc.mod.togglelib;

import com.pixmeow.mc.mod.togglelib.api.API;
import com.pixmeow.mc.mod.togglelib.event.EventManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.EventBus;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)
public class ToggleLib {
    public static final EventBus Toggle_EVENT_BUS = new EventBus();
    @Mod.Instance(Reference.MOD_ID)
    public static ToggleLib INSTANCE;
    public Config config;

    @Mod.EventHandler
    public void preinit(FMLPreInitializationEvent event) {
        com.pixmeow.mc.mod.togglelib.Logger.setLogger(event.getModLog());
        config = new Config(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new EventManager(event));
        Toggle_EVENT_BUS.register(new EventManager(event));
        API.init();
    }

    @Mod.EventHandler
    public void postinit(FMLPostInitializationEvent event) {
        ToggleThreadManager.init();
        EventManager.setRtmEnabled(Loader.isModLoaded("rtm"));
    }
}
