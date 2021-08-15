package com.pixmeow.mc.mod.togglelib.ui;

import com.pixmeow.mc.mod.togglelib.brand.SanYing;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;

public class ToggleOverlay extends Gui {
    public ToggleOverlay(Minecraft mc) {
        ScaledResolution scaled = new ScaledResolution(mc);
        int width = scaled.getScaledWidth();
        int height = scaled.getScaledHeight();
        drawCenteredString(mc.fontRenderer, SanYing.ToggleStatus.get().getLevel().getName(), 10, (height / 2) - 4, SanYing.ToggleStatus.get().getLevel().getColor());
        drawHorizontalLine(10, 13, (height / 2) - 5, SanYing.ToggleStatus.get().getLevel().getColor());
        drawHorizontalLine(10, 13, (height / 2) - 2, SanYing.ToggleStatus.get().getLevel().getColor() );
        drawVerticalLine(10, (height / 2) - 5, (height / 2) - 2, SanYing.ToggleStatus.get().getLevel().getColor());
        drawVerticalLine(13, (height / 2) - 5, (height / 2) - 2, SanYing.ToggleStatus.get().getLevel().getColor());
    }

    public static void draw(){
        new ToggleOverlay(Minecraft.getMinecraft());
    }
}