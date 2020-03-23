package Game_Creation_Control;

import Geometry.Point;

public class Window extends Area {

    private boolean isBroken = false;

    private static double soundRange;

    public Window(double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4) {
        super(x1, y1, x2, y2, x3, y3, x4, y4);
    }

    public Window(Point a1, Point a2, Point a3, Point a4) {
        super(a1, a2, a3, a4);
    }

    public Window(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
        super(x1, y1, x2, y2, x3, y3, x4, y4);
    }

    public Window(int x1, int y1, int x2, int y2) {
        super(x1, y1, x2, y2);
    }

    public static double getSoundRange() {
        return soundRange;
    }

    public boolean isBroken() {
        return isBroken;
    }

    public void setBroken(boolean broken) {
        if(this.isBroken=false) {
            isBroken = broken;
        }
        else{
            //the window is already broken
        }
    }

    public static void setSoundRange(double soundRange) {
        Window.soundRange = soundRange;
    }
}
