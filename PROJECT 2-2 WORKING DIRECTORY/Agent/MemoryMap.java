package Agent;

import Geometry.Point;
import Geometry.Vector;

import java.util.ArrayList;

public class MemoryMap {

    //any 0 in this array represents a empty space on the or an are which has not already been explored
    //any 1 represents a wall
    private int[][] mapData;
    private static final int Unvisited = 0;
    private static final int Visited = 1;
    private static final int Wall = 2;
    private static final int Door = 3;
    private static final int Teleport = 4;
    //default size is arbitrary 5
    public MemoryMap(){
     new MemoryMap(5, 5);
    }

    public MemoryMap(double height, double width){
        mapData = new int[(int)height][(int)width];
    }

    private void ExtendMap(int height, int with){

    }
    private void resetMap(){
      for(int i=0 ; i<mapData.length-1 ; i++){
          for(int j=0 ; i<mapData.length-1; j++){
              mapData[i][j] =  Unvisited;
          }
      }
    }

    public void visitAera(Point a1, Point a2, Point a3, Point a4){
        updateAreaMemory(a1,a2,a3,a4,Visited);
    }

    public void visit(Point a){
        visit(a.getX(),a.getY());
    }

    public void visit(double x, double y) {
        visit((int)Math.round(x),(int)Math.round(y));
    }

    public void visit(int a, int b){
        mapData[a][b] = Visited;
    }

    public boolean isVisited(double x, double y){
        return isVisited((int)Math.round(x),(int)Math.round(y));
    }

    public boolean isVisited(int a, int b){
        return mapData[a][b]!=Unvisited;
    }

    public int getInfo(int x, int y){
        return mapData[x][y];
    }

    public ArrayList<String> toStringInfo(ArrayList<Integer> a ){
       ArrayList<String> result = new ArrayList<>();
        for(Integer i : a){
            result.add(toStringInfo(i));
        }
        return result;
    }
    public String toStringInfo(int x){
        switch (x){
            case 0:
                return "Unvisited";
            case 1:
                return "Visited";
            case 2:
                return "Wall";
            case 3:
                return "Door";
            case 4:
                return "Teleport";
        }
        System.out.println(x+" is not corresponding to any data.");
        return ("error " + x);
    }

    public ArrayList<Integer> getAreaInfo(Point a1, Point a2, Point a3, Point a4){

        ArrayList<Integer> info = new ArrayList<>();
        int minX=(int)Math.round(a1.getX());
        int maxX=(int)Math.round(a1.getX());
        int minY=(int)Math.round(a1.getY());
        int maxY=(int)Math.round(a1.getY());


        minX = Math.min(minX,(int)Math.round(a2.getX()));
        maxX = Math.max(maxX,(int)Math.round(a2.getX()));
        minY = Math.min(minY,(int)Math.round(a2.getY()));
        maxY = Math.max(maxY,(int)Math.round(a2.getY()));

        minX = Math.min(minX,(int)Math.round(a3.getX()));
        maxX = Math.max(maxX,(int)Math.round(a3.getX()));
        minY = Math.min(minY,(int)Math.round(a3.getY()));
        maxY = Math.max(maxY,(int)Math.round(a3.getY()));

        minX = Math.min(minX,(int)Math.round(a4.getX()));
        maxX = Math.max(maxX,(int)Math.round(a4.getX()));
        minY = Math.min(minY,(int)Math.round(a4.getY()));
        maxY = Math.max(maxY,(int)Math.round(a4.getY()));

        for(int i = minX; i<maxX; i++){
            for(int j = minY; j<maxY; j++){
                if(!info.contains(mapData[i][j])){
                    info.add(mapData[i][j]);
                }
            }
        }
        return info;
    }

    //this area must have a rectangular shape
    public void updateAreaMemory(Point a1, Point a2, Point a3, Point a4, int objectType){
        int minX=(int)Math.round(a1.getX());
        int maxX=(int)Math.round(a1.getX());
        int minY=(int)Math.round(a1.getY());
        int maxY=(int)Math.round(a1.getY());


        minX = Math.min(minX,(int)Math.round(a2.getX()));
        maxX = Math.max(maxX,(int)Math.round(a2.getX()));
        minY = Math.min(minY,(int)Math.round(a2.getY()));
        maxY = Math.max(maxY,(int)Math.round(a2.getY()));

        minX = Math.min(minX,(int)Math.round(a3.getX()));
        maxX = Math.max(maxX,(int)Math.round(a3.getX()));
        minY = Math.min(minY,(int)Math.round(a3.getY()));
        maxY = Math.max(maxY,(int)Math.round(a3.getY()));

        minX = Math.min(minX,(int)Math.round(a4.getX()));
        maxX = Math.max(maxX,(int)Math.round(a4.getX()));
        minY = Math.min(minY,(int)Math.round(a4.getY()));
        maxY = Math.max(maxY,(int)Math.round(a4.getY()));

        for(int i = minX; i<maxX; i++){
            for(int j = minY; j<maxY; j++){
                mapData[i][j] = objectType;
            }
        }
    }

    public void setLineObject(Point a1, Point a2, int objectType){
        updateAreaMemory(a1,a2,a1,a2, objectType);
    }


    public void setCornerWall(Point a1, Point corner, Point a2){
        setWall(a1,corner);
        setWall(corner,a2);
    }

    public void setWall(Point a1, Point a2){
        setLineObject( a1, a2,Wall);
    }

    public void setDoor(Point a1, Point a2){
        setLineObject( a1, a2,Door);
    }

    public void setTeleport(Point a1, Point a2, Point a3, Point a4){
        updateAreaMemory(a1,a2,a3,a4,Teleport);
    }

    public boolean isInTriangle(Point a, Point b, Point c, Point m){
      boolean ok = true;
      if (0 < new Vector(a,b).vectorialProduct(new Vector(a,m)).scalarProduct(new Vector(a,m).vectorialProduct(new Vector(a,c))))
          ok = false;
      if (0 < new Vector(b,a).vectorialProduct(new Vector(b,m)).scalarProduct(new Vector(b,m).vectorialProduct(new Vector(b,c))))
          ok = false;
      if (0 < new Vector(a,c).vectorialProduct(new Vector(c,m)).scalarProduct(new Vector(c,m).vectorialProduct(new Vector(b,c))))
          ok = false;
      return ok;
    }

}
