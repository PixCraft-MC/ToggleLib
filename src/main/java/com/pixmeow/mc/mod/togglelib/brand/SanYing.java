package com.pixmeow.mc.mod.togglelib.brand;

import com.pixmeow.mc.mod.togglelib.utils.Component.Button;
import com.pixmeow.mc.mod.togglelib.utils.Component.Handler;
import com.pixmeow.mc.mod.togglelib.utils.Component.Switch;
import net.java.games.input.Component;
import net.java.games.input.Controller;
import net.java.games.input.Event;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class SanYing {
    public static String name = "San Ying Controller";
    public static int version = 1;
    public static String author = "PixMeow";
    public static Map<Component, com.pixmeow.mc.mod.togglelib.utils.Component> componentMap = new LinkedHashMap<>();
    public static Component btnHorn;
    public static Component btnConstSpeed;
    public static Component btnAtsConfirm;
    public static Component swtDirection;
    public static ToggleStatus status = new ToggleStatus();
    static Handler handler = new Handler(status);

    public static void init(@NotNull Controller controller) {
        // todo need check the status of the toggle input plug-in

        Handler.HandlerData.init();
        for (Component component : controller.getComponents()) {
            switch (component.getIdentifier().getName()) {
                case "y":
                    swtDirection = component;
                    componentMap.put(component, new Switch(status, swtDirection));
                    break;
                case "0":
                    btnHorn = component;
                    componentMap.put(component, new Button(status, btnHorn, Button.Type.HORN));
                    break;
                case "1":
                    btnAtsConfirm = component;
                    componentMap.put(component, new Button(status, btnAtsConfirm, Button.Type.ATS));
                    break;
                case "2":
                    btnConstSpeed = component;
                    componentMap.put(component, new Button(status, btnConstSpeed, Button.Type.CONST));
                    break;
                case "6":
                case "7":
                case "8":
                case "9":
                    handler.register(component);
                    componentMap.put(component, handler);
                    break;
            }
        }
        handler.finish();
    }

    public static void on(Event event) {
        Component component = event.getComponent();
        if (component.equals(btnHorn))
            ((Button) componentMap.get(component)).on(event.getValue(), Button.Type.HORN);
        else if (component.equals(btnAtsConfirm))
            ((Button) componentMap.get(component)).on(event.getValue(), Button.Type.ATS);
        else if (component.equals(btnConstSpeed))
            ((Button) componentMap.get(component)).on(event.getValue(), Button.Type.CONST);
        else if (component.equals(swtDirection))
            ((Switch) componentMap.get(component)).on(event.getValue());
        else if (handler.hasComp(component)) {
            try {
                ((Handler) componentMap.get(component)).on(component);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static class ToggleStatus {
        private static ToggleStatus current;
        Direction direction = Direction.Unknown;
        ToggleLevel level = ToggleLevel.Unknown;
        private boolean horn;
        private boolean ats;
        private boolean constSpeed;
        public ToggleStatus() {
        }

        private ToggleStatus(ToggleStatus old) {
            this.level = old.level;
            this.horn = old.horn;
            this.ats = old.ats;
            this.constSpeed = old.constSpeed;
            this.direction = old.direction;
        }

        public static ToggleStatus get() {
            return current;
        }

        public ToggleStatus copy() {
            return new ToggleStatus(this);
        }

        public boolean setConstSpeed(boolean constSpeed) {
            if (constSpeed) {
                this.constSpeed = !this.constSpeed;
                ToggleStatus.current = this;
                return true;
            }
            return false;
        }

        public boolean setLevel(com.pixmeow.mc.mod.togglelib.utils.Component.Handler.HandlerValue v) {
            if (v.value != ToggleLevel.Unknown.value && this.level.value != v.value) {
                this.level = ToggleLevel.getLevel(v.value);
                ToggleStatus.current = this;
                return true;
            }
            return false;
        }

        public boolean isHorn() {
            return horn;
        }

        public void setHorn(boolean horn) {
            this.horn = horn;
            ToggleStatus.current = this;
        }

        public boolean isAts() {
            return ats;
        }

        public void setAts(boolean ats) {
            this.ats = ats;
            ToggleStatus.current = this;
        }

        public boolean isConstSpeed() {
            return constSpeed;
        }

        public Direction getDirection() {
            return direction;
        }

        public void setDirection(Direction direction) {
            this.direction = direction;
            ToggleStatus.current = this;
        }

        public ToggleLevel getLevel() {
            return level;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof ToggleStatus)) return false;
            ToggleStatus that = (ToggleStatus) o;
            return isHorn() == that.isHorn() && isAts() == that.isAts() && isConstSpeed() == that.isConstSpeed() && getDirection() == that.getDirection() && getLevel() == that.getLevel();
        }

        @Override
        public int hashCode() {
            return Objects.hash(isHorn(), isAts(), isConstSpeed(), getDirection(), getLevel());
        }

        @Override
        public String toString() {
            return "ToggleStatus{" +
                    "horn=" + horn +
                    ", ats=" + ats +
                    ", constSpeed=" + constSpeed +
                    ", direction=" + direction +
                    ", level=" + level +
                    '}';
        }

        public enum Direction {
            Forward(-1, "Forward"),
            OFF(0, "Stop"),
            Backward(1, "Backward"),
            Unknown(-2, "Unknown");

            int value;
            String name;

            Direction(int value, String name) {
                this.value = value;
                this.name = name;
            }

            public static Direction getDirector(float value) {
                for (Direction i : Direction.values()) {
                    if (i.value == (int) value) return i;
                }
                return Unknown;
            }

            public String getName() {
                return name;
            }
        }

        public enum ToggleLevel {
            Unknown(-10, "--", Integer.parseInt("55555", 16)),
            EB(-9, "EB", Integer.parseInt("FF5555", 16)),
            B8(-8, "B8", Integer.parseInt("CA7A2C", 16)),
            B7(-7, "B7", Integer.parseInt("00A7e", 16)),
            B6(-6, "B6", Integer.parseInt("00A7e", 16)),
            B5(-5, "B5", Integer.parseInt("00A7e", 16)),
            B4(-4, "B4", Integer.parseInt("00A7e", 16)),
            B3(-3, "B3", Integer.parseInt("00A7e", 16)),
            B2(-2, "B2", Integer.parseInt("00A7e", 16)),
            B1(-1, "B1", Integer.parseInt("00A7e", 16)),
            N(0, "N", Integer.parseInt("00AA00", 16)),
            P1(1, "P1", Integer.parseInt("66BAB7", 16)),
            P2(2, "P2", Integer.parseInt("66BAB7", 16)),
            P3(3, "P3", Integer.parseInt("66BAB7", 16)),
            P4(4, "P4", Integer.parseInt("66BAB7", 16)),
            P5(5, "P5", Integer.parseInt("66BAB7", 16));
            int value;
            String name;
            int color = Integer.parseInt("FFFFFF", 16);

            ToggleLevel(int value, String name) {
                this.value = value;
                this.name = name;
            }

            ToggleLevel(int value, String name, int color) {
                this(value, name);
            }

            public static ToggleLevel getLevel(int value) {
                for (ToggleLevel i : ToggleLevel.values()) {
                    if (i.value == value) return i;
                }
                return Unknown;
            }

            public static ToggleLevel getLevel(com.pixmeow.mc.mod.togglelib.utils.Component.Handler.HandlerValue value) {
                for (ToggleLevel i : ToggleLevel.values()) {
                    if (i.value == value.value) return i;
                }
                return Unknown;
            }

            public String getName() {
                return name;
            }

            public int getColor() {
                return color;
            }

            public int getValue() {
                return value;
            }
        }
    }
}
