package com.pixmeow.mc.mod.togglelib;

import com.pixmeow.mc.mod.togglelib.event.EventManager;
import com.pixmeow.mc.mod.togglelib.io.SanYingToggle;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)
public class ToggleLib {
    @Mod.Instance(Reference.MOD_ID)
    public static ToggleLib INSTANCE;

    @Mod.EventHandler
    public void preinit(FMLPreInitializationEvent event) {

    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new EventManager(event));

        // todo [json config reader]

    }

    @Mod.EventHandler
    public void postinit(FMLPostInitializationEvent event) {
        ToggleController.StartSanYing();
    }
}
