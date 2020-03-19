/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.*;


//



public class CustomArea {
    public int []xValues;
    public int []yValues;
    protected int leftBoundary;
    protected int rightBoundary;
    protected int topBoundary;
    protected int bottomBoundary;

    public CustomArea(){
        xValues = new int[]{0,0,0,0};
        yValues = new int[]{0,0,0,0};
    }

    public CustomArea(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
        xValues = new int[]{x1, x2, x3, x4};
        yValues = new int[]{y1, y2, y3, y4};
    }


    Polygon polygon1 = new Polygon(xValues,yValues,4);


    public int getX1() {
        return leftBoundary;
    }

    public int getX2() {
        return rightBoundary;
    }

    public int getY1() {
        return bottomBoundary;
    }

    public int getY2() { return topBoundary; }

    public int getWidth() { return topBoundary - bottomBoundary; }

    public int getHeight() {
        return rightBoundary - leftBoundary;
    }

    /*
        Check whether a point is in the target area
    */
    public boolean isHit(double x,double y){
        return (y>bottomBoundary)&(y<topBoundary)&(x>leftBoundary)&(x<rightBoundary);
    }

    /*
        Check whether something with a radius is in the target area
        STILL TO BE IMPLEMENTED
    */
    public boolean isHit(double x,double y,double radius){
        return false;
    }

    public void drawPolygon(Graphics g) {

        g.drawPolygon(polygon1);

    }
}