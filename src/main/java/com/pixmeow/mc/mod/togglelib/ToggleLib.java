package com.pixmeow.mc.mod.togglelib;

import com.pixmeow.mc.mod.togglelib.event.EventManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.EventBus;
import net.minecraftforge.fml.common.Mod;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)
public class ToggleLib {
    @Mod.Instance(Reference.MOD_ID)
    public static ToggleLib INSTANCE;
    public static final EventBus Toggle_EVENT_BUS = new EventBus();
    public Config config;

    @Mod.EventHandler
    public void preinit(FMLPreInitializationEvent event) {
        config = new Config(event);
        ToggleThreadManager.StartSanYing();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new EventManager(event));
        Toggle_EVENT_BUS.register(new EventManager(event));
    }

    @Mod.EventHandler
    public void postinit(FMLPostInitializationEvent event) {

    }

}
