package com.pixmeow.mc.mod.togglelib.utils;

import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

public class ColorText {
    public enum Format{
        Obfuscated('k'),
        Bold('b'),
        Strikethrough('m'),
        Underline('n'),
        Italic('o'),
        Reset('r')
        ;

        char formatCode;
        private static final char symbol = '\u00A7';

        Format(char formatCode) {
            this.formatCode = formatCode;
        }

        @Override
        public String toString() {
            return String.format("%c%c", symbol, formatCode);
        }
    }

    public static TextComponentString format(String content, TextFormatting color, boolean bold, boolean underline, boolean italic) {
        TextComponentString text = new TextComponentString(content);
        text.getStyle().setColor(color);
        text.getStyle().setBold(bold);
        text.getStyle().setUnderlined(underline);
        text.getStyle().setItalic(italic);
        return text;
    }
}
