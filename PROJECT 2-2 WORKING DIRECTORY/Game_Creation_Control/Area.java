package Game_Creation_Control;
// part of this code is from Joel


import Geometry.Point;

public class Area {

    protected int leftBoundary;
    protected int rightBoundary;
    protected int topBoundary;
    protected int bottomBoundary;
    protected int x1;
    protected int y1;
    protected int x2;
    protected int y2;
    protected int x3;
    protected int y3;
    protected int x4;
    protected int y4;

    public Area(){
        leftBoundary=0;
        rightBoundary=1;
        topBoundary=0;
        bottomBoundary=1;
    }

    public Area(int x1,int y1,int x2,int y2,int x3,int y3, int x4, int y4){   // General coordinate system: x1,y1,x2,y2,x3,y3,x4,y4
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
        this.x4 = x4;
        this.y4 = y4;

        leftBoundary = Math.min(Math.min(x1,x2),Math.min(x3,x4));
        rightBoundary = Math.max(Math.max(x1,x2),Math.max(x3,x4));
        topBoundary = Math.max(Math.max(y1,y2),Math.max(y3,y4));
        bottomBoundary = Math.min(Math.min(y1,y2),Math.min(y3,y4));
    }

    public Area(int x1, int y1, int x2, int y2){
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;

        leftBoundary=Math.min(x1,x2);
        rightBoundary=Math.max(x1,x2);
        topBoundary=Math.max(y1,y2);
        bottomBoundary=Math.min(y1,y2);
    }



    /*
        Check whether a point is in the target area
    */
    public boolean isHit(double x,double y){
        return (y>bottomBoundary)&(y<topBoundary)&(x>leftBoundary)&(x<rightBoundary);
    }


    public boolean isHit(double centerX ,double centerY ,double radius){
        int precision = 100;
        boolean ok = true;
        for(int i = -precision ; i< precision ; i++){
            double checkX = centerX+i*radius/precision;
            double checkY = centerY+getRelativeY(checkX,radius);
            double checkY2 = centerY-getRelativeY(checkX,radius);
            if (isHit(checkX,checkY)==false){
                ok = false;
            }
            if (isHit(checkX,checkY2)==false){
                ok = false;
            }
        }
        return ok;
    }

    private double getRelativeY(double x, double radius){
        return Math.sqrt(Math.pow(radius,2)-Math.pow(x,2));
    }

    public int getBottomBoundary() {
        return bottomBoundary;
    }

    public int getLeftBoundary() {
        return leftBoundary;
    }

    public int getRightBoundary() {
        return rightBoundary;
    }

    public int getTopBoundary() {
        return topBoundary;
    }

    public static void main(String[] args){
        Area area = new Area(0, 0, 30, 0, 0, 20, 30, 20);
        System.out.println(area.isHit(30, 25, 6));
    }
}
