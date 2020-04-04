package Game_Creation_Control;
// part of this code is from Joel


import Geometry.Point;
import Geometry.Segment;
import Geometry.Vector;

import java.util.ArrayList;

public class Area {

    protected double leftBoundary;
    protected double rightBoundary;
    protected double topBoundary;
    protected double bottomBoundary;
    protected ArrayList<Point> points;
    protected ArrayList<Segment> borders;
    protected double x1;
    protected double y1;
    protected double x2;
    protected double y2;
    protected double x3;
    protected double y3;
    protected double x4;
    protected double y4;
    private boolean shaded = false;

//    public Area(){
//        leftBoundary=0;
//        rightBoundary=1;
//        topBoundary=0;
//        bottomBoundary=1;
//    }

    public Area(double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
        this.x4 = x4;
        this.y4 = y4;

        reset();
    }

    public Area(Point a1, Point a2, Point a3, Point a4){
        this.x1 = a1.getX();
        this.y1 = a1.getY();
        this.x2 = a2.getX();
        this.y2 = a2.getY();
        this.x3 = a3.getX();
        this.y3 = a3.getY();
        this.x4 = a4.getX();
        this.y4 = a4.getY();

        reset();
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

        reset();
    }

    public Area(int x1, int y1, int x2, int y2){
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x1;
        this.y3 = y1;
        this.x4 = x2;
        this.y4 = y2;

        reset();
    }

    private void reset(){
        setBoundaries();
        setBordersAndPoints();
    }

    private void setBoundaries(){
        leftBoundary = Math.min(Math.min(x1,x2),Math.min(x3,x4));
        rightBoundary = Math.max(Math.max(x1,x2),Math.max(x3,x4));
        topBoundary = Math.max(Math.max(y1,y2),Math.max(y3,y4));
        bottomBoundary = Math.min(Math.min(y1,y2),Math.min(y3,y4));
    }


    private void setBordersAndPoints(){
        ArrayList<Segment> b = new ArrayList<>();
        ArrayList<Point> points = new ArrayList<>();
        Point topLeft = new Point(leftBoundary,topBoundary);
        points.add(topLeft);
        Point topRight = new Point(rightBoundary,topBoundary);
        points.add(topRight);
        Point bottomRight = new Point(rightBoundary,bottomBoundary);
        points.add(bottomRight);
        Point bottomLeft = new Point(leftBoundary,bottomBoundary);
        points.add(bottomLeft);
        b.add(new Segment(topLeft,topRight));
        b.add(new Segment(bottomRight,bottomLeft));
        b.add(new Segment(bottomLeft,topLeft));
        b.add(new Segment(bottomRight,topRight));
        this.points =points;
        this.borders = b;
    }

    public void translate(Vector v){
        this.x1 +=v.x;
        this.x2 +=v.x;
        this.x3 +=v.x;
        this.x4 +=v.x;
        this.y1 +=v.y;
        this.y2 +=v.y;
        this.y3 +=v.y;
        this.y4 +=v.y;
        reset();
    }

    /*
        Check whether a point is in the target area
    */
    public boolean isHit(double x,double y){
        return (y>bottomBoundary)&(y<topBoundary)&(x>leftBoundary)&(x<rightBoundary);
    }

    public boolean isHit(Point a){
        return isHit(a.getX(),a.getY());
    }
    // Check whether a circle is hitting the area
    public boolean isHit(double centerX ,double centerY ,double radius){
        int precision = 100;
        boolean ok = false;
        for(int i = -precision ; i< precision ; i++){
            double checkX = i*radius/precision;
            double checkY = getRelativeY(checkX,radius);
            double checkY2 = -checkY;
            checkX+=centerX;
            checkY+=centerY;
            checkY2+=centerY;
            if (isHit(checkX, checkY)){
                ok = true;
            }
            if (isHit(checkX, checkY2)){
                ok = true;
            }
        }
        return ok;
    }

    //    check if an area is colliding with another area
    public boolean isHit(Area other){
        boolean hit = false;
        //checks if borders of one area do not intersect the other area's borders
        for(Segment s : this.getBorders()){
            for(Segment b : other.getBorders()){
                //https://openclassrooms.com/forum/sujet/calcul-du-point-d-intersection-de-deux-segments-21661
                if(isIntersect(s, b)){
                    hit=true;
                }
            }
        }
        return hit;
    }

    //checks if two segments are intersecting
    private boolean isIntersect(Segment a, Segment b){
        //if both are vertical -> no intersection
        if(checkVerticality(a)&& checkVerticality(b)){
            return false;
        }
//        if both are parallel -> no intersection
        if(isParallel(a.getx1(),a.gety1(),a.getx2(), b.gety2(),b.getx1(),b.gety1(),b.getx2(), b.gety2())){
            return false;
        }
//        if one of them is vertical, flip both xs and ys of the segments, otherwise there will be null divisions in the further calculations
        if(checkVerticality(a) || checkVerticality(b)){
            a.inverseXsAndYs();
            b.inverseXsAndYs();
        }
        double xi = intersectingX(a.getx1(),a.gety1(),a.getx2(), b.gety2(),b.getx1(),b.gety1(),b.getx2(), b.gety2());
        double yi = intersectingY(a.getx1(),a.gety1(),a.getx2(), b.gety2(),b.getx1(),b.gety1(),b.getx2(), b.gety2());
        double xa = Math.min(a.getx1(),a.getx2());
        double xb = Math.max(a.getx1(),a.getx2());
        double ya = Math.min(a.gety1(),a.gety2());
        double yb = Math.max(a.gety1(),a.gety2());
        return xa<=xi && xi<=xb && ya<=yi && yi<=yb;
    }

    //    computes the the X coordinate of the intersection
    private double intersectingX(double Xa, double Ya, double Xb, double Yb, double Xc, double Yc, double Xd, double Yd){
        return (((Yb-Ya)/(Xb-Xa))*Xa+Ya-((Yd-Yc)/(Xd-Xc))*Xc-Yc)/(((Yb-Ya)/(Xb-Xa))-((Yd-Yc)/(Xd-Xc)));
    }
    //    computes the the Y coordinate of the intersection
    private double intersectingY(double Xa, double Ya, double Xb, double Yb, double Xc, double Yc, double Xd, double Yd){
        return ((Yb-Ya)/(Xb-Xa))*((((Yb-Ya)/(Xb-Xa))*Xa+Ya-((Yd-Yc)/(Xd-Xc))*Xc-Yc)/(((Yb-Ya)/(Xb-Xa))-((Yd-Yc)/(Xd-Xc)))-Xa)+Ya;
    }

    private boolean isParallel(double Xa, double Ya, double Xb, double Yb, double Xc, double Yc, double Xd, double Yd){
        return 0==(((Yb-Ya)/(Xb-Xa))-((Yd-Yc)/(Xd-Xc)));
    }

    private boolean checkVerticality(Segment a){
        if(a.getx1() == a.getx2() ){
            return true;
        }else{
            return false;
        }
    }

    public ArrayList<Point> getPoints() {
        return points;
    }

    public double getX1() {
        return x1;
    }

    public double getY1() {
        return y1;
    }

    public double getX2() {
        return x2;
    }

    public double getY2() {
        return y2;
    }

    public double getX3() {
        return x3;
    }

    public double getY3() {
        return y3;
    }

    public double getX4() {
        return x4;
    }

    public double getY4() {
        return y4;
    }

    public Point getCenter(){
        return  new Point(rightBoundary/2,topBoundary/2);
    }
    // for a given x, returns the corresponding y using the circle equation x^2+y^2=r^2
    private double getRelativeY(double x, double radius){
        return Math.sqrt(Math.pow(radius,2)-Math.pow(x,2));
    }

    public double getBottomBoundary() {
        return bottomBoundary;
    }

    public double getLeftBoundary() {
        return leftBoundary;
    }

    public double getRightBoundary() {
        return rightBoundary;
    }

    public double getTopBoundary() {
        return topBoundary;
    }

    public boolean isShaded() {
        return shaded;
    }

    public void setShaded(boolean shaded) {
        this.shaded = shaded;
    }

    public ArrayList<Segment> getBorders(){
        return borders;
    }

    public Area cloned(){
        Area clone = new Area(x1,y1,x2,y2,x3,y3,x4,y4);
        clone.setShaded(shaded);
        return clone;
    }
    //    to make tests
    public static void main(String[] args){
        Area a = new Area(0,0,30,0,0,20,30,20);
        Area b = new Area(35,25,65,25,35,45,65,45);
        System.out.println(a.isHit(b));
    }
}