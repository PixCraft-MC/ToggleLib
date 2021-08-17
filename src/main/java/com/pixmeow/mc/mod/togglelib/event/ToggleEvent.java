package com.pixmeow.mc.mod.togglelib.event;

import com.pixmeow.mc.mod.togglelib.utils.Component;
import com.pixmeow.mc.mod.togglelib.brand.SanYing;
import net.minecraftforge.fml.common.eventhandler.Cancelable;
import net.minecraftforge.fml.common.eventhandler.Event;

import java.util.Objects;

@Cancelable
public class ToggleEvent extends Event {
    public final SanYing.ToggleStatus now;
    public final SanYing.ToggleStatus from;
    private final Comp comp;
    private final Component component;

    public ToggleEvent(SanYing.ToggleStatus now, SanYing.ToggleStatus from, Comp comp, Component component) {
        this.now = now;
        this.from = from;
        this.comp = comp;
        this.component = component;
    }

    public SanYing.ToggleStatus getNow() {
        return now;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ToggleEvent)) return false;
        ToggleEvent that = (ToggleEvent) o;
        return Objects.equals(now, that.now) && comp == that.comp && Objects.equals(component, that.component);
    }

    @Override
    public int hashCode() {
        return Objects.hash(now, comp, component);
    }

    public enum Comp {
        Handler,
        Button,
        Switch,
        Other
    }

    public static class HandlerEvent extends ToggleEvent {
        public HandlerEvent(SanYing.ToggleStatus status, SanYing.ToggleStatus from, Comp comp, Component.Handler handler) {
            super(status, from, comp, handler);
        }
    }

    public static class ButtonEvent extends ToggleEvent {
        public ButtonEvent(SanYing.ToggleStatus status, SanYing.ToggleStatus from, Comp comp, Component.Button button) {
            super(status, from, comp, button);
        }

        public static class ATSConfirmedEvent extends ButtonEvent {
            public ATSConfirmedEvent(SanYing.ToggleStatus status, SanYing.ToggleStatus from, Comp comp, Component.Button button) {
                super(status, from, comp, button);
            }
        }

        public static class HornEvent extends ButtonEvent {
            public HornEvent(SanYing.ToggleStatus status, SanYing.ToggleStatus from, Comp comp, Component.Button button) {
                super(status, from, comp, button);
            }
        }

        public static class ConstSpeedEvent extends ButtonEvent {
            public ConstSpeedEvent(SanYing.ToggleStatus status, SanYing.ToggleStatus from, Comp comp, Component.Button button) {
                super(status, from, comp, button);
            }
        }
    }

    public static class SwitchEvent extends ToggleEvent {
        public SwitchEvent(SanYing.ToggleStatus status, SanYing.ToggleStatus from, Comp comp, Component.Switch aSwitch) {
            super(status, from, comp, aSwitch);
        }
    }
}

