package com.pixmeow.mc.mod.togglelib.event;

import com.pixmeow.mc.mod.togglelib.Component;
import com.pixmeow.mc.mod.togglelib.brand.SanYing;
import net.minecraftforge.fml.common.eventhandler.Cancelable;
import net.minecraftforge.fml.common.eventhandler.Event;

import java.util.Objects;

@Cancelable
public class ToggleEvent extends Event {
    public enum Comp{
        Handler,
        Button,
        Switch,
        Other
    }

    private final SanYing.ToggleStatus status;
    private final Comp comp;
    private final Component component;

    public SanYing.ToggleStatus getStatus() {
        return status;
    }

    public ToggleEvent(SanYing.ToggleStatus status, Comp comp, Component component) {
        this.status = status;
        this.comp = comp;
        this.component = component;
    }

    public static class HandlerEvent extends ToggleEvent{
        public HandlerEvent(SanYing.ToggleStatus status, Comp comp, Component.Handler handler) {
            super(status, comp, handler);
        }
    }

    public static class ButtonEvent extends ToggleEvent{
        public ButtonEvent(SanYing.ToggleStatus status, Comp comp, Component.Button button) {
            super(status, comp, button);
        }

        public static class ATSConfirmedEvent extends ButtonEvent{
            public ATSConfirmedEvent(SanYing.ToggleStatus status, Comp comp, Component.Button button) {
                super(status, comp, button);
            }
        }

        public static class HornEvent extends ButtonEvent{
            public HornEvent(SanYing.ToggleStatus status, Comp comp, Component.Button button) {
                super(status, comp,  button);
            }
        }

        public static class ConstSpeedEvent extends ButtonEvent{
            public ConstSpeedEvent(SanYing.ToggleStatus status, Comp comp, Component.Button button) {
                super(status, comp, button);
            }
        }
    }

    public static class SwitchEvent extends ToggleEvent{
        public SwitchEvent(SanYing.ToggleStatus status, Comp comp, Component.Switch aSwitch) {
            super(status, comp, aSwitch);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ToggleEvent)) return false;
        ToggleEvent that = (ToggleEvent) o;
        return Objects.equals(status, that.status) && comp == that.comp && Objects.equals(component, that.component);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, comp, component);
    }
}

