package com.pixmeow.mc.mod.togglelib.utils;

import com.pixmeow.mc.mod.togglelib.ToggleLib;
import com.pixmeow.mc.mod.togglelib.brand.SanYing;
import com.pixmeow.mc.mod.togglelib.event.ToggleEvent;
import net.minecraftforge.fml.common.eventhandler.EventBus;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

public class Component {
    protected EventBus eventBus = ToggleLib.Toggle_EVENT_BUS;
    protected SanYing.ToggleStatus status;
    private net.java.games.input.Component component;

    protected Component(SanYing.ToggleStatus status, net.java.games.input.Component component) {
        this.status = status;
        this.component = component;
    }

    protected Component(SanYing.ToggleStatus status) {
        this.status = status;
    }

    public static class Handler extends Component {
        HandlerValue value;
        net.java.games.input.Component ket6;
        net.java.games.input.Component ket7;
        net.java.games.input.Component ket8;
        net.java.games.input.Component ket9;

        public Handler(SanYing.ToggleStatus status) {
            super(status);
        }

        public void register(net.java.games.input.Component component) {
            switch (component.getIdentifier().getName()) {
                case "6":
                    ket6 = component;
                    break;
                case "7":
                    ket7 = component;
                    break;
                case "8":
                    ket8 = component;
                    break;
                case "9":
                    ket9 = component;
                    break;
            }
        }

        public void finish() {
            try {
                SanYing.ToggleStatus old = status.copy();
                value = new HandlerData(new float[]{ket6.getPollData(), ket7.getPollData(), ket8.getPollData(), ket9.getPollData()}).getValue();
                if (status.setLevel(value))
                    eventBus.post(new ToggleEvent.HandlerEvent(status, old, ToggleEvent.Comp.Handler, this));
            } catch (Exception e) {
                System.out.println("From Handler Update");
                e.printStackTrace();
            }
        }

        public void on(net.java.games.input.Component component) {
            try {
                if (component.equals(ket6) || component.equals(ket7) || component.equals(ket8) || component.equals(ket9)) {
                    SanYing.ToggleStatus old = status.copy();
                    value = new HandlerData(new float[]{ket6.getPollData(), ket7.getPollData(), ket8.getPollData(), ket9.getPollData()}).getValue();
                    if (status.setLevel(value))
                        eventBus.post(new ToggleEvent.HandlerEvent(status, old, ToggleEvent.Comp.Handler, this));
                }
            } catch (Exception e) {
                System.out.println("From Handler Update");
            }
        }

        public boolean hasComp(net.java.games.input.Component component) {
            return component.equals(ket6) || component.equals(ket7) || component.equals(ket8) || component.equals(ket9);
        }

        public static class HandlerValue {
            public final int value;
            public final String name;

            public HandlerValue(int value, String name) {
                this.value = value;
                this.name = name;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (!(o instanceof HandlerValue)) return false;
                HandlerValue that = (HandlerValue) o;
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

        public static class HandlerData {
            public static Map<int[], HandlerValue> toggleKeyMap = new LinkedHashMap<>();

            int a;
            int b;
            int c;
            int d;

            public HandlerData(float[] data) {
                if (data.length == 4) {
                    this.a = (int) data[0];
                    this.b = (int) data[1];
                    this.c = (int) data[2];
                    this.d = (int) data[3];
                } else {
                    this.a = 0;
                    this.b = 0;
                    this.c = 0;
                    this.d = 0;
                }
            }

            public static void init() {
                if (toggleKeyMap.isEmpty()) {
                    toggleKeyMap.put(new int[]{0, 0, 0, 0}, new HandlerValue(-10, "Unknown"));
                    toggleKeyMap.put(new int[]{0, 0, 0, 1}, new HandlerValue(-9, "EB"));
                    toggleKeyMap.put(new int[]{0, 0, 1, 0}, new HandlerValue(-8, "B8"));
                    toggleKeyMap.put(new int[]{0, 0, 1, 1}, new HandlerValue(-7, "B7"));
                    toggleKeyMap.put(new int[]{0, 1, 0, 0}, new HandlerValue(-6, "B6"));
                    toggleKeyMap.put(new int[]{0, 1, 0, 1}, new HandlerValue(-5, "B5"));
                    toggleKeyMap.put(new int[]{0, 1, 1, 0}, new HandlerValue(-4, "B4"));
                    toggleKeyMap.put(new int[]{0, 1, 1, 1}, new HandlerValue(-3, "B3"));
                    toggleKeyMap.put(new int[]{1, 0, 0, 0}, new HandlerValue(-2, "B2"));
                    toggleKeyMap.put(new int[]{1, 0, 0, 1}, new HandlerValue(-1, "B1"));
                    toggleKeyMap.put(new int[]{1, 0, 1, 0}, new HandlerValue(0, "N"));
                    toggleKeyMap.put(new int[]{1, 0, 1, 1}, new HandlerValue(1, "P1"));
                    toggleKeyMap.put(new int[]{1, 1, 0, 0}, new HandlerValue(2, "P2"));
                    toggleKeyMap.put(new int[]{1, 1, 0, 1}, new HandlerValue(3, "P3"));
                    toggleKeyMap.put(new int[]{1, 1, 1, 0}, new HandlerValue(4, "P4"));
                    toggleKeyMap.put(new int[]{1, 1, 1, 1}, new HandlerValue(5, "P5"));
                }
            }

            public HandlerValue getValue() {
                AtomicReference<HandlerValue> value = new AtomicReference<>(new HandlerValue(-10, "Unknown"));
                toggleKeyMap.forEach((k, v) -> {
                    if (Arrays.equals(k, new int[]{a, b, c, d})) value.set(v);
                });
                return value.get();
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (!(o instanceof HandlerData)) return false;
                HandlerData that = (HandlerData) o;
                return a == that.a && b == that.b && c == that.c && d == that.d;
            }

            @Override
            public int hashCode() {
                return Objects.hash(a, b, c, d);
            }
        }
    }

    public static class Button extends Component {
        ButtonStatus buttonStatus;

        public Button(SanYing.ToggleStatus status, net.java.games.input.Component component, Type type) {
            super(status, component);
            if (component.getPollData() == 1f) {
                this.buttonStatus = ButtonStatus.Pressed;
            } else {
                this.buttonStatus = ButtonStatus.Released;
            }

            SanYing.ToggleStatus old = status.copy();
            switch (type) {
                case ATS:
                    status.setAts(buttonStatus.value);
                    eventBus.post(new ToggleEvent.ButtonEvent.ATSConfirmedEvent(status, old, ToggleEvent.Comp.Button, this));
                    break;
                case CONST:
                    status.setConstSpeed(buttonStatus.value);
                    eventBus.post(new ToggleEvent.ButtonEvent.ConstSpeedEvent(status, old, ToggleEvent.Comp.Button, this));
                    break;
                case HORN:
                    status.setHorn(buttonStatus.value);
                    eventBus.post(new ToggleEvent.ButtonEvent.HornEvent(status, old, ToggleEvent.Comp.Button, this));
                    break;
            }
        }

        public void on(float value, Type type) {
            if (value == 1) {
                this.buttonStatus = ButtonStatus.Pressed;
            } else {
                this.buttonStatus = ButtonStatus.Released;
            }

            SanYing.ToggleStatus old = status.copy();
            switch (type) {
                case ATS:
                    status.setAts(buttonStatus.value);
                    if (buttonStatus.value) {
                        eventBus.post(new ToggleEvent.ButtonEvent.ATSConfirmedEvent(status, old, ToggleEvent.Comp.Button, this));
                    }
                    break;
                case CONST:
                    if (status.setConstSpeed(buttonStatus.value))
                        eventBus.post(new ToggleEvent.ButtonEvent.ConstSpeedEvent(status, old,  ToggleEvent.Comp.Button, this));
                    break;
                case HORN:
                    status.setHorn(buttonStatus.value);
                    eventBus.post(new ToggleEvent.ButtonEvent.HornEvent(status,  old, ToggleEvent.Comp.Button, this));
                    break;
            }
        }

        public enum ButtonStatus {
            Pressed(true),
            Released(false);

            public boolean value;

            ButtonStatus(boolean value) {
                this.value = value;
            }
        }

        public enum Type {
            ATS,
            CONST,
            HORN
        }
    }

    public static class Switch extends Component {
        SanYing.ToggleStatus.Direction direction;

        public Switch(SanYing.ToggleStatus status, net.java.games.input.Component component) {
            super(status, component);
            direction = SanYing.ToggleStatus.Direction.getDirector(component.getPollData());
            SanYing.ToggleStatus old = status.copy();
            status.setDirection(direction);
            eventBus.post(new ToggleEvent.SwitchEvent(status, old, ToggleEvent.Comp.Switch, this));
        }

        public void on(float value) {
            direction = SanYing.ToggleStatus.Direction.getDirector(value);
            SanYing.ToggleStatus old = status.copy();
            status.setDirection(direction);
            eventBus.post(new ToggleEvent.SwitchEvent(status, old, ToggleEvent.Comp.Switch, this));
        }
    }
}
