package com.pixmeow.mc.mod.togglelib.event;

import com.pixmeow.mc.mod.togglelib.Logger;
import com.pixmeow.mc.mod.togglelib.ToggleThreadManager;
import com.pixmeow.mc.mod.togglelib.ui.ToggleOverlay;
import com.pixmeow.mc.mod.togglelib.utils.Utils;
import jp.ngt.ngtlib.util.NGTUtilClient;
import jp.ngt.rtm.entity.train.EntityTrainBase;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber()
public class EventManager {
    static boolean rtmEnabled = false;

    public EventManager(FMLInitializationEvent e) {}

    public static void setRtmEnabled(boolean rtmEnabled) {
        Logger.Info("Found RTM, Staring Activating!", "Toggle Loader");
        EventManager.rtmEnabled = rtmEnabled;
    }

    @SubscribeEvent
    public void onRenderGui(RenderGameOverlayEvent.Post event) {
        if (event.getType() != RenderGameOverlayEvent.ElementType.EXPERIENCE) return;
        if (ToggleThreadManager.isIsToggleEnabled()){
            ToggleOverlay.draw();
        }
    }

    @SubscribeEvent
    public void onReceiveAts(ToggleEvent.ButtonEvent.ATSConfirmedEvent e) {
        Logger.Info(String.format("ATS %s\n", (e.getNow().isAts() ? "Confirmed" : "---")), "Toggle Status");
    }

    @SubscribeEvent
    public void onReceiveHorn(ToggleEvent.ButtonEvent.HornEvent e) {
        Logger.Info(String.format("Horn %s\n", (e.getNow().isHorn() ? "On" : "Off")), "Toggle Status");
        if (rtmEnabled) {
            Minecraft mc = NGTUtilClient.getMinecraft();
            EntityPlayerSP entityPlayerSP = mc.player;
            Entity riding = entityPlayerSP.getRidingEntity();
            if (entityPlayerSP.isRiding() && riding instanceof EntityTrainBase)
                Utils.playSound((EntityPlayer) entityPlayerSP, (byte) 2);
        }
    }

    @SubscribeEvent
    public void onReceiveConst(ToggleEvent.ButtonEvent.ConstSpeedEvent e) {
        Logger.Info(String.format("Const Speed %s\n", (e.getNow().isConstSpeed() ? "On" : "Off")), "Toggle Status");
    }

    @SubscribeEvent
    public void onReceiveSwitch(ToggleEvent.ButtonEvent.SwitchEvent e) {
        Logger.Info(String.format("Direction [%s]\n", e.getNow().getDirection().getName()), "Toggle Status");
    }

    @SubscribeEvent
    public void onReceiveHandler(ToggleEvent.ButtonEvent.HandlerEvent e) {
        Logger.Info(String.format("Toggle [%s]\n", e.getNow().getLevel().getName()), "Toggle Status");

        if (rtmEnabled) {
            Minecraft mc = NGTUtilClient.getMinecraft();
            EntityPlayerSP entityPlayerSP = mc.player;
            Entity riding = entityPlayerSP.getRidingEntity();
            if (entityPlayerSP.isRiding() && riding instanceof EntityTrainBase){
                if (e.now.getLevel().getValue() != ((EntityTrainBase) riding).getNotch()) ((EntityTrainBase) riding).setNotch(e.now.getLevel().getValue());
                else ((EntityTrainBase) riding).syncNotch(e.now.getLevel().getValue() - ((EntityTrainBase) riding).getNotch());
            }
        }
    }

    @SubscribeEvent
    public void onEnterWorld(WorldEvent.Load event) {
        ToggleThreadManager.start();
    }

    @SubscribeEvent
    public void onQuitWorld(WorldEvent.Unload event) {
        ToggleThreadManager.stop();
    }
}
