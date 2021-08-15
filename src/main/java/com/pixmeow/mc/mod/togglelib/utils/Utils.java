package com.pixmeow.mc.mod.togglelib.utils;

import jp.ngt.ngtlib.util.NGTUtilClient;
import jp.ngt.rtm.RTMCore;
import jp.ngt.rtm.entity.npc.macro.MacroRecorder;
import jp.ngt.rtm.entity.train.EntityTrainBase;
import jp.ngt.rtm.entity.train.util.TrainState;
import jp.ngt.rtm.modelpack.cfg.TrainConfig;
import jp.ngt.rtm.modelpack.modelset.ModelSetTrain;
import jp.ngt.rtm.network.PacketRTMKey;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class Utils {
    public static void playSound(EntityPlayer player, byte key){
        if (player.isRiding() && player.getRidingEntity() instanceof EntityTrainBase) {
            EntityTrainBase train = (EntityTrainBase)player.getRidingEntity();
            ModelSetTrain modelset = (ModelSetTrain)train.getResourceState().getResourceSet();
            if (modelset != null) {
                String sound = "";
                if (key == 2) {
                    sound = ((TrainConfig)modelset.getConfig()).sound_Horn;
                    MacroRecorder.INSTANCE.recHorn(player.world);
                } else if (key == 3) {
                    int index = train.getVehicleState(TrainState.TrainStateType.Announcement);
                    String[][] sa0 = ((TrainConfig)modelset.getConfig()).sound_Announcement;
                    if (sa0 != null && index < sa0.length) {
                        sound = sa0[index][1];
                        MacroRecorder.INSTANCE.recChime(player.world, sa0[index][1]);
                    }
                }
                if (!sound.isEmpty())
                    sendKeyToServer(key, sound);
            }
        }
    }

    public static void sendKeyToServer(byte keyCode, String sound) {
        EntityPlayerSP entityPlayerSP = (NGTUtilClient.getMinecraft()).player;
        RTMCore.NETWORK_WRAPPER.sendToServer((IMessage)new PacketRTMKey((EntityPlayer)entityPlayerSP, keyCode, sound));
    }
}
