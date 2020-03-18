package Geometry;

import java.util.ArrayList;

public class Segment {
    Point a1;
    Point a2;

    public Segment(Point a1, Point a2) {
        this.a1 = a1;
        this.a2 = a2;
    }

    public Point getA1() {
        return a1;
    }

    public Point getA2() {
        return a2;
    }

    public double getx1(){
        return a1.getX();
    }
    public double gety1(){
        return a1.getY();
    }
    public double getx2(){
        return a2.getX();
    }
    public double gety2(){
        return a2.getY();
    }
    public void setPoints(Point a1,Point a2) {
        this.a1 = a1;
        this.a2 = a2;
    }

    public ArrayList<Point> getPoints(){
        ArrayList<Point> result = new ArrayList<Point>();
        result.add(a1);
        result.add(a2);
        return result;
    }

    public Segment cloned(){
        return new Segment(new Point(getx1(),gety1()),new Point(getx2(),gety2()));
    }

    public void inverseXsAndYs(){
        setPoints(new Point(gety1(),getx1()), new Point(gety2(),getx2()));
    }
}
