package Game_Creation_Control;

public class Sentry extends Area {
    private Area insideArea;
    public Sentry(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4,int ix1, int iy1, int ix2, int iy2, int ix3, int iy3, int ix4, int iy4){ // i = inside area
        super(x1, y1, x2, y2,x3, y3, x4, y4);
        this.insideArea = new Area(ix1, iy1, ix2, iy2, ix3, iy3, ix4, iy4);
    }

    public Area getInsideArea() {
        return insideArea;
    }
}