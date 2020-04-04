package Geometry;
public class Vector
{

//    Methods that ends with a 2 are not modifing the involved vector
////        Methods ending with no 2 directly modify the vector

    public double x;
    public double y;
    public double z;

    public Vector(Point a){
        this.x = a.getX();
        this.y = a.getY();
        this.z = 0;
    }

    public Vector(Geometry.Point a, Geometry.Point b) {
        this.x = a.getX()-b.getX();
        this.y = a.getY()-b.getY();
        this.z = 0;
    }
    public Vector(double x, double y)
    {
        this.x = x;
        this.y = y;
        this.z = 0;
    }

    public Vector(double x, double y, double z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public Vector(double[]a){
        this.x= a[0];
        this.y=a[1];
    }
    public Vector(Vector other)
    {
        this.x = other.x;
        this.y = other.y;
        this.z = other.z;
    }
    public double[] getCoo(){
        double result[]= new double[3];
        result[0]=x;
        result[1]=y;
        result[2]=z;
        return result;
    }
    public void add(Vector other)
    {
        if(other != null)
        {
            x += other.x;
            y += other.y;
            z += other.z;
        }

    }

    public Vector add2(Vector other){
        double x = this.x + other.x;
        double y = this.y + other.y;
        double z = this.z + other.z;

        return new Vector(x, y, z);
    }

    public void addLength(double term){
        this.setLength2(this.length()+term);
    }

    public Vector addLength2(double term){
        return this.setLength2(this.length()+term);
    }

    public void addTerm(double term) {
        x+= term;
        y+= term;
        z+= term;
    }

    public void sub(Vector other)
    {
        x -= other.x;
        y -= other.y;
        z -= other.z;
    }

    public Vector sub2(Vector other) {
        double newX = this.x - other.x;
        double newY = this.y - other.y;
        double newZ = this.z - other.z;
        return new Vector(newX, newY, newZ);
    }


    public void mul(double factor)
    {
        if(x != 0)
        {
            this.x *= factor;
        }
        if(y != 0)
        {
            this.y *= factor;
        }
        if(z != 0)
        {
            this.z *= factor;
        }
    }

    public Vector mul2(double factor)
    {
        Vector result = new Vector(this);
        result.mul(factor);
        return result;
    }

    public Vector mulWith2(Vector other) {
        double x = this.x * other.x;
        double y = this.y * other.y;
        double z = this.z * other.z;

        return new Vector(x, y, z);
    }

    public void div(double factor)
    {
        if(x != 0)
        {
            this.x /= factor;
        }
        if(y != 0)
        {
            this.y /= factor;
        }
        if(z != 0)
        {
            this.z /= factor;
        }

    }

    public Vector div2(double factor)
    {
        double X = 0;
        double Y = 0;
        double Z = 0;

        if(x != 0)
        {
            X = this.x / factor;
        }
        if(y != 0)
        {
            Y = this.y / factor;
        }
        if(z != 0)
        {
            Z = this.z / factor;
        }

        if (X == 0 || Y == 0 || Z == 0) System.out.println("ERROR: DIVISION BY ZERO");

        return new Vector(X, Y, Z);

    }


    public void setLength(double distance){ this.mul(distance/this.length());}

    public Vector setLength2(double distance){ return this.mul2(distance/this.length());}

    public void normalize()
    {
        mul(1.0/length());
    }
    public Vector normalize2()
    {
        return mul2(1.0/length());
    }

    public double lengthSquared() {
        double xx = 0;
        double yy = 0;
        double zz = 0;
        if(this.x != 0)
        {
            xx = this.x*this.x;
        }
        if(this.y != 0)
        {
            yy = this.y*this.y;
        }
        if(this.z != 0)
        {
            zz = this.z*this.z;
        }
        return xx + yy + zz;
    }


    public double length() {
        return Math.sqrt(lengthSquared());
    }


    public double distanceSquared(Vector other) {

        double dx = this.x - other.x;
        double dy = this.y - other.y;
        double dz = this.z - other.z;
        return dx*dx+dy*dy+dz*dz;
    }

    //make this method return a perpendicular Vector
    public Vector getPerpendicularVector() {
        //to test if z==0;
        Vector per;
        if(z!=0) {
            per = new Vector(1, 1, -(this.x + this.y) / this.z);
        }else if(y!=0){
            per = new Vector(1, -(this.x + this.z) / this.y,1);
        }else if(x!=0){
            per = new Vector(-(this.y + this.z) / this.x,1,1);
        }else{
            System.out.println("the Vector is the null Vector, cannot build a perpendicular Vector");
            return null;
        }
        return per.setLength2(5);
    }



    public Vector get2DPerpendicularVector() {
        //to test if z==0;
        Vector per;
        if(y!=0){
            per = new Vector(1, -(this.x ) / this.y,0);
        }else if(x!=0){
            per = new Vector(-(this.y ) / this.x,1,0);
        }else{
            System.out.println("the Vector is the null Vector, cannot build a perpendicular Vector");
            return new Vector(0,0,0);
        }
        return per.setLength2(5);
    }


    public Vector getPerpendicularVector(Vector v) {
        //test if x==0
        double y = (this.z*v.x/this.x-v.z)/(-this.y*v.x/this.x+this.y);
        double x = (-this.y*y-this.z)/this.x;
        Vector per = new Vector(x,y,1);
        return per.setLength2(5);
    }


    public double distance(Vector other) {
        return Math.sqrt(distanceSquared(other));
    }


    public void normaliseWith( Vector v){
        this.normalize();
        this.mul(v.length());
    }



    public double[] middleCooWith( Vector v){
        double result[] = new double [3];
        result[0] = (this.x+v.x)/2;
        result[1] = (this.y+v.y)/2;
        result[2] = (this.z+v.z)/2;
        return result;
    }


    public void printLnVector(){
        System.out.println("x"+x+" y"+y+" z"+z);
    }


    public String getString() {
        return "x"+x+" y"+y+" z"+z;
    }


    public Vector getAntiVector(){
        return new Vector(this.mul2(-1));
    }

    public String toString() {
        return "[" + x + ", " + y + ", " + z + "]";
    }

    public String toStringRounded() {
        return "[" + Math.round(x) + ", " + Math.round(y) + ", " + Math.round(z) + "]";
    }

    public double computeAngle(Vector other) {

        double scalarProduct = this.x*other.x + this.y*other.y;
        double angle = Math.acos(scalarProduct/(this.length()*other.length()));
        return angle;

    }

    public double scalarProduct(Vector other){
        return x*other.x + y*other.y + z*other.z;
    }

    public Vector vectorialProduct(Vector other){
        double x = this.y*other.z + this.z* other.y;
        double y = this.x*other.z + this.z* other.x;
        double z = this.y*other.x + this.x* other.y;
        return new Vector(x,-y,z);
    }

    public double convertToAngle() {
        Vector result = new Vector(this);
        result.normalize();
        double angle;
        angle = Math.acos(result.x);
        if(Math.asin(result.y)<0)
        {
            angle = -angle;
        }
        // angle = Constant.reduceToBelowPI(angle);
        return angle;
    }

    public double convertToAngle(Vector other) {
        Vector result = new Vector(this);
        result.normalize();
        Vector v2 = other.normalize2();
        double angle1 = this.convertToAngle();
        double angle2 = v2.convertToAngle();

        return Math.abs(angle1-angle2);
    }
}