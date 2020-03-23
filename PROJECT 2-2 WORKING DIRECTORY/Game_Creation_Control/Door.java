package Game_Creation_Control;

import Geometry.Point;

public class Door extends Area {
    private boolean isOpen = false;

    private static double soundRange;

    public Door(double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4) {
        super(x1, y1, x2, y2, x3, y3, x4, y4);
    }

    public Door(Point a1, Point a2, Point a3, Point a4) {
        super(a1, a2, a3, a4);
    }

    public Door(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
        super(x1, y1, x2, y2, x3, y3, x4, y4);
    }

    public Door(int x1, int y1, int x2, int y2) {
        super(x1, y1, x2, y2);
    }

    public static double getSoundRange() {
        return soundRange;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public static void setSoundRange(double soundRange) {
        Door.soundRange = soundRange;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }
}
