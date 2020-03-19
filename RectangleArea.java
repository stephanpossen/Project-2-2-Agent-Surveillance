import java.awt.*;

public class RectangleArea extends Area {
    public RectangleArea(int x1, int y1, int x2,int y2, int x3, int y3, int x4, int y4) {
        super(x1,y1,x2,y2,x3,y3,x4,y4);
    }

    public RectangleArea() {
    }

    @Override
    public void draw(Graphics g) {
        g.drawRect(getX1(), getY1(), getWidth(),getHeight());
    }

    public void fill(Graphics g) {
        g.fillRect(getX1(), getY1(), getWidth(),getHeight());
    }


}