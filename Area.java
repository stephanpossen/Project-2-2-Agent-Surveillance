/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.*;
import java.util.ArrayList;

/**
 *
 * @author joel
 */
public class Area {
    protected int leftBoundary, leftBoundaryTemp1,leftBoundaryTemp2;
    protected int rightBoundary, rightBoundaryTemp1,rightBoundaryTemp2;
    protected int topBoundary, topBoundaryTemp1,topBoundaryTemp2;
    protected int bottomBoundary, bottomBoundaryTemp1, bottomBoundaryTemp2;

    int x1, x2, x3, x4;
    int y1, y2, y3, y4;

    int xValues[];
    int yValues[];

    Polygon polygon1;

    public Area() {
        leftBoundary = 0;
        rightBoundary = 10;
        topBoundary = 0;
        bottomBoundary = 10;

        x1 = x2 = x3 = x4 = 0;
        y1 = y2 = y3 = y4 = 0;

        xValues = new int[]{x1, x2, x3, x4};
        yValues = new int[]{y1, y2, y3, y4};


    }

    /*
    public Area(int _x1, int _y1, int _x2, int _y2, int _x3, int _y3, int _x4, int _y4) {

        x1 = _x1;
        x2 = _x2;
        x3 = _x3;
        x4 = _x4;

        y1 = _y1;
        y2 = _y2;
        y3 = _y3;
        y4 = _y4;

        xValues = new int[]{x1, x2, x3, x4};
        yValues = new int[]{y1, y2, y3, y4};

        polygon1 = new Polygon(xValues, yValues, 4);

    }


     */


    public Area(int x1, int y1, int x2, int y2) {

        leftBoundary = Math.min(x1, x2);
        rightBoundary = Math.max(x1, x2);
        bottomBoundary = Math.min(y1, y2);
        topBoundary = Math.max(y1, y2);

    }


    public Area(int x1, int y1, int x2, int y2,int x3, int y3,int x4, int y4) {

        leftBoundaryTemp1 = Math.min(x1, x2);
        leftBoundaryTemp2 = Math.min(x3,x4);
        leftBoundary = Math.min(leftBoundaryTemp1,leftBoundaryTemp2);

        rightBoundaryTemp1 = Math.max(x1, x2);
        rightBoundaryTemp2 = Math.max(x3, x4);
        rightBoundary = Math.max(rightBoundaryTemp1,rightBoundaryTemp2);

        bottomBoundaryTemp1 = Math.min(y1, y2);
        bottomBoundaryTemp2 = Math.min(y3, y4);
        bottomBoundary = Math.min(bottomBoundaryTemp1, bottomBoundaryTemp2);

        topBoundaryTemp1 = Math.max(y1, y2);
        topBoundaryTemp2 = Math.max(y3, y4);
        topBoundary = Math.max(topBoundaryTemp1, topBoundaryTemp2);

    }


    public int getX1() {
        return leftBoundary;
    }

    public int getY1() {
        return bottomBoundary;
    }


    public int getWidth() {
        return topBoundary - bottomBoundary;
    }

    public int getHeight() {
        return rightBoundary - leftBoundary;
    }

    /*
        Check whether a point is in the target area
    */
    public boolean isHit(double x, double y) {
        return (y > bottomBoundary) & (y < topBoundary) & (x > leftBoundary) & (x < rightBoundary);
    }

    /*
        Check whether something with a radius is in the target area
        STILL TO BE IMPLEMENTED
    */
    public boolean isHit(double x, double y, double radius) {
        return false;
    }

    public void draw(Graphics g) {
        g.drawRect(leftBoundary, topBoundary, (rightBoundary - leftBoundary) , (topBoundary - bottomBoundary) );
    }

    public void fill(Graphics g) {

        g.fillRect(leftBoundary, topBoundary, (rightBoundary - leftBoundary), (topBoundary - bottomBoundary));
    }
}