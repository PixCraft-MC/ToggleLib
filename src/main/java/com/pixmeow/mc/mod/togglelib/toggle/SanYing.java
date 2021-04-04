package com.pixmeow.mc.mod.togglelib.toggle;

import net.java.games.input.Component;
import net.java.games.input.Controller;
import net.java.games.input.Event;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class SanYing {
    public static class ToggleValue{
        public final int value;
        public final String name;

        public ToggleValue(int value, String name) {
            this.value = value;
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof ToggleValue)) return false;
            ToggleValue that = (ToggleValue) o;
            return Float.compare(that.value, value) == 0 && Objects.equals(name, that.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(value, name);
        }

        @Override
        public String toString() {
            return name;
        }
    }
    public static class ToggleData{
        public static Map<int[], ToggleValue> toggleKeyMap = new LinkedHashMap<>();

        int a;
        int b;
        int c;
        int d;

        public static void init(){
            if (toggleKeyMap.isEmpty()){
                toggleKeyMap.put(new int[]{0,0,0,0}, new ToggleValue(-10, "Unknown"));
                toggleKeyMap.put(new int[]{0,0,0,1}, new ToggleValue(-9, "EB"));
                toggleKeyMap.put(new int[]{0,0,1,0}, new ToggleValue(-8, "B8"));
                toggleKeyMap.put(new int[]{0,0,1,1}, new ToggleValue(-7, "B7"));
                toggleKeyMap.put(new int[]{0,1,0,0}, new ToggleValue(-6, "B6"));
                toggleKeyMap.put(new int[]{0,1,0,1}, new ToggleValue(-5, "B5"));
                toggleKeyMap.put(new int[]{0,1,1,0}, new ToggleValue(-4, "B4"));
                toggleKeyMap.put(new int[]{0,1,1,1}, new ToggleValue(-3, "B3"));
                toggleKeyMap.put(new int[]{1,0,0,0}, new ToggleValue(-2, "B2"));
                toggleKeyMap.put(new int[]{1,0,0,1}, new ToggleValue(-1, "B1"));
                toggleKeyMap.put(new int[]{1,0,1,0}, new ToggleValue(0, "N"));
                toggleKeyMap.put(new int[]{1,0,1,1}, new ToggleValue(1, "P1"));
                toggleKeyMap.put(new int[]{1,1,0,0}, new ToggleValue(2, "P2"));
                toggleKeyMap.put(new int[]{1,1,0,1}, new ToggleValue(3, "P3"));
                toggleKeyMap.put(new int[]{1,1,1,0}, new ToggleValue(4, "P4"));
                toggleKeyMap.put(new int[]{1,1,1,1}, new ToggleValue(5, "P5"));
            }
        }

        public ToggleData(int a, int b, int c, int d) {
            this.a = a;
            this.b = b;
            this.c = c;
            this.d = d;
        }

        public ToggleData(float a, float b, float c, float d) {
            this.a = (int)a;
            this.b = (int)b;
            this.c = (int)c;
            this.d = (int)d;
        }

        public ToggleData(int[] data) {
            if (data.length == 4){
                this.a = data[0];
                this.b = data[1];
                this.c = data[2];
                this.d = data[3];
            }else {
                this.a = 0;
                this.b = 0;
                this.c = 0;
                this.d = 0;
            }
        }

        ToggleValue getValue(){
            AtomicReference<ToggleValue> value = new AtomicReference<>(new ToggleValue(-10, "Unknown"));
            toggleKeyMap.forEach((k,v)->{
                if (Arrays.equals(k, new int[]{a, b, c, d})) value.set(v);
            });
            return value.get();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof ToggleData)) return false;
            ToggleData that = (ToggleData) o;
            return a == that.a && b == that.b && c == that.c && d == that.d;
        }

        @Override
        public int hashCode() {
            return Objects.hash(a, b, c, d);
        }
    }
    public static class ToggleStatus{
        public enum Director{
            Forward(-1, "Frontward"),
            OFF(0, "Stop"),
            Backward(1, "Backward"),
            Unknown(-2, "Unknown");

            int value;
            String name;

            Director(int value, String name) {
                this.value = value;
                this.name = name;
            }
            public String getName() {
                return name;
            }

            public static Director getDirector(int value){
                for (Director i: Director.values()) {
                    if (i.value == value) return i;
                }
                return Unknown;
            }
        }
        public enum ToggleLevel {
            Unknown(-10, "Unknown"),
            EB(-9, "EB"),
            B8(-8, "B8"),
            B7(-7, "B7"),
            B6(-6, "B6"),
            B5(-5, "B5"),
            B4(-4, "B4"),
            B3(-3, "B3"),
            B2(-2, "B2"),
            B1(-1, "B1"),
            N (0, "N"),
            P1(1, "P1"),
            P2(2, "P2"),
            P3(3, "P3"),
            P4(4, "P4"),
            P5(5, "P5");
            int value;
            String name;

            ToggleLevel(int value, String name) {
                this.value = value;
                this.name = name;
            }

            public String getName() {
                return name;
            }

            public static ToggleLevel getLevel(int value){
                for (ToggleLevel i: ToggleLevel.values()) {
                    if (i.value == value) return i;
                }
                return Unknown;
            }
        }

        private boolean horn;
        private boolean ats;
        private boolean constSpeed;
        Director direction = Director.Unknown;
        ToggleLevel level = ToggleLevel.Unknown;

        public void setHorn(boolean horn) {
            this.horn = horn;
            System.out.printf("Horn %s\n" ,(this.horn ? "On" : "Off"));
        }

        public void setAts(boolean ats) {
            this.ats = ats;
            System.out.printf("ATS %s\n" , (this.ats ? "Confirmed" : "---"));
        }

        public void setConstSpeed(boolean constSpeed) {
            if (constSpeed){
                this.constSpeed = !this.constSpeed;
                System.out.printf("Const Speed %s\n", (this.constSpeed ? "On" : "OFF"));
            }
        }

        public void setDirection(Director direction) {
            this.direction = direction;
            System.out.printf("Direction [%s]\n", direction.getName());
        }

        public void setLevel(ToggleValue v) {
            if (v.value != ToggleLevel.Unknown.value && this.level.value != v.value){
                this.level = ToggleLevel.getLevel(v.value);
                System.out.printf("Toggle [%s]\n", level.getName());
            }
        }

        public boolean isHorn() {
            return horn;
        }

        public boolean isAts() {
            return ats;
        }

        public boolean isConstSpeed() {
            return constSpeed;
        }

        public Director getDirection() {
            return direction;
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
    }

    public static String name = "San Ying Controller";
    public static int version = 1;
    public static String author = "PixMeow";
    public static Map<String, Component> toggle = new LinkedHashMap<>();
    public static Component btnHorn;
    public static Component btnConstSpeed;
    public static Component btnAtsConfirm;
    public static Component swtDirection;
    public static ToggleStatus status = new ToggleStatus();

    public static void init(@NotNull Controller controller){
        ToggleData.init();

        for (Component component : controller.getComponents()) {
            switch (component.getIdentifier().getName()) {
                case "y":
                    swtDirection = component;
                    status.setDirection(ToggleStatus.Director.getDirector((int) swtDirection.getPollData()));
                    break;
                case "0":
                    btnHorn = component;
                    status.setHorn(btnHorn.getPollData() == 1.0f);
                    break;
                case "1":
                    btnAtsConfirm = component;
                    status.setAts(btnAtsConfirm.getPollData() == 1.0f);
                    break;
                case "2":
                    btnConstSpeed = component;
                    status.setConstSpeed(btnConstSpeed.getPollData() == 1.0f);
                    break;
                case "6":
                case "7":
                case "8":
                case "9":
                    toggle.put(component.getIdentifier().getName(), component);
                    break;
            }
        }
        status.setLevel(new ToggleData(new int[]{(int) toggle.get("6").getPollData(), (int) toggle.get("7").getPollData(), (int) toggle.get("8").getPollData(), (int) toggle.get("9").getPollData()}).getValue());
    }

    public static void on(Event event){
        Component component = event.getComponent();
        if (component.equals(btnHorn))
            status.setHorn(event.getValue() == 1.0f);
        else if (component.equals(btnAtsConfirm))
            status.setAts(event.getValue() == 1.0f);
        else if (component.equals(btnConstSpeed))
            status.setConstSpeed(event.getValue() == 1.0f);
        else if (component.equals(swtDirection))
            status.setDirection(ToggleStatus.Director.getDirector((int) event.getValue()));
        else if (toggle.containsValue(component)){
            try{
                ToggleData data = new ToggleData(new int[]{(int) toggle.get("6").getPollData(), (int) toggle.get("7").getPollData(), (int) toggle.get("8").getPollData(), (int) toggle.get("9").getPollData()});
                status.setLevel(data.getValue());
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
