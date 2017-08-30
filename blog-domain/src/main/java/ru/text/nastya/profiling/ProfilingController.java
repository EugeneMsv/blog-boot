package ru.text.nastya.profiling;

public class ProfilingController implements ProfilingControllerMBean {

    private boolean enabled = false;

    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
