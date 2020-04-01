package cc.ghast.movemenowreborn.utils;

/**
 * @author Ghast
 * @since 01-Apr-20
 */
public class SCount<Current, Max> {

    private Current current;
    private Max max;

    public SCount(Current current, Max max) {
        this.current = current;
        this.max = max;
    }

    public Current getCurrent() {
        return current;
    }

    public void setCurrent(Current current) {
        this.current = current;
    }

    public Max getMax() {
        return max;
    }

    public void setMax(Max max) {
        this.max = max;
    }
}
