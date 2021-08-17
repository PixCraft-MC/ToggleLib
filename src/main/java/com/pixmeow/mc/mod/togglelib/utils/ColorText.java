package com.pixmeow.mc.mod.togglelib.utils;

import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

public class ColorText {
    public static TextComponentString format(String content, TextFormatting color, boolean bold, boolean underline, boolean italic) {
        TextComponentString text = new TextComponentString(content);
        text.getStyle().setColor(color);
        text.getStyle().setBold(bold);
        text.getStyle().setUnderlined(underline);
        text.getStyle().setItalic(italic);
        return text;
    }
}
