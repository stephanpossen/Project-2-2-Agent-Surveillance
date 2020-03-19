/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.geom.Ellipse2D;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.ArrayList;

/**
 *
 * @author joel
 */
public class Scenario {

    public static int getNumGuards;
    protected double baseSpeedIntruder;
    protected double sprintSpeedIntruder;
    protected double baseSpeedGuard;

    protected String mapDoc;
    protected int gameMode;
    private final Path filePath;
    private final static Charset ENCODING = StandardCharsets.UTF_8;

    protected String name;
    protected String gameFile;
    protected static int mapHeight;
    protected static int mapWidth;
    protected double scaling;
    protected int numIntruders;
    protected static int numGuards;
    protected Area spawnAreaIntruders;

    protected static ArrayList<Area> sentryOutside;
    protected static ArrayList<Area> sentryInside;

    protected static ArrayList<Area> targetArea;
    public static ArrayList<Area> walls;
    protected static ArrayList<Area> shaded;
    protected static ArrayList<Ellipse2D> guards;
    private static Area spawnAreaGuards;


    public Scenario(String mapFile){
        // set parameters

        System.out.println("I'm in the Scenario Constructor");
        mapDoc=mapFile;

        // initialize variables
        walls = new ArrayList<>(); // create list of walls
        shaded = new ArrayList<>(); // create list of low-visability areas
        targetArea = new ArrayList<>(); // create list of target(s) areas

        /*
         walls.add(new RectangleArea(20, 20, 60, 60));
         walls.add(new RectangleArea(48, 20, 60, 60));
        */
        // read scenario
        filePath = Paths.get(mapDoc); // get path
        System.out.println(filePath);
        readMap();
    }

    public void readMap(){
        try (Scanner scanner =  new Scanner(filePath, ENCODING.name())){
            while (scanner.hasNextLine()){
                parseLine(scanner.nextLine());
            }
        }
        catch(Exception e)
        {
        }
    }

    /*

     */
    protected void parseLine(String line){
        //use a second Scanner to parse the content of each line
        try(Scanner scanner = new Scanner(line)){
            scanner.useDelimiter("=");
            if (scanner.hasNext()){
                // read id value pair
                String id = scanner.next();
                String value = scanner.next();
                //System.out.println(value);
                // trim excess spaces
                value=value.trim();
                id=id.trim();
                // in case multiple parameters
                String[] items=value.split(" ");
                Area tmp;
                Area tmp1;

                switch(id)
                {
                    case "name":
                        name = value;
                        break;
                    case "gameFile":
                        gameFile = value;
                        break;
                    case "gameMode":
                        gameMode = Integer.parseInt(value); // 0 is exploration, 1 evasion pursuit game
                        break;
                    case "scaling":
                        scaling = Double.parseDouble(value);
                        break;
                    case "height":
                        mapHeight = Integer.parseInt(value);
                        break;
                    case "width":
                        mapWidth = Integer.parseInt(value);
                        break;
                    case "sentryOutside":
                        tmp = new Area(Integer.parseInt(items[0]),Integer.parseInt(items[1]),Integer.parseInt(items[2]),Integer.parseInt(items[3]),Integer.parseInt(items[4]),Integer.parseInt(items[5]),Integer.parseInt(items[6]),Integer.parseInt(items[7]));
                        sentryOutside.add(tmp);
                        tmp1 = new Area(Integer.parseInt(items[0]),Integer.parseInt(items[1]),Integer.parseInt(items[2]),Integer.parseInt(items[3]));
                        sentryInside.add(tmp1);
                        break;

                        
                    case "numGuards":
                        numGuards = Integer.parseInt(value);
                        break;
                        
                        /*
                    case "numIntruders":
                        numIntruders = Integer.parseInt(value);
                        break;
                    case "baseSpeedIntruder":
                        baseSpeedIntruder = Double.parseDouble(value);
                        break;
                    case "sprintSpeedIntruder":
                        sprintSpeedIntruder = Double.parseDouble(value);
                        break;
                    case "baseSpeedGuard":
                        baseSpeedGuard = Double.parseDouble(value);
                        break;

*/
                    case "targetArea":
                       tmp = new Area(Integer.parseInt(items[0]),Integer.parseInt(items[1]),Integer.parseInt(items[2]),Integer.parseInt(items[3]),Integer.parseInt(items[4]),Integer.parseInt(items[5]),Integer.parseInt(items[6]),Integer.parseInt(items[7]));
                       targetArea.add(tmp);
                        break;
                        /*

                    case "spawnAreaIntruders":
                        spawnAreaIntruders = new Area(Integer.parseInt(items[0]),Integer.parseInt(items[1]),Integer.parseInt(items[2]),Integer.parseInt(items[3]),Integer.parseInt(items[4]),Integer.parseInt(items[5]),Integer.parseInt(items[6]),Integer.parseInt(items[7]));
                        break;
                       */

                    case "spawnAreaGuards":
                        spawnAreaGuards = new Area(Integer.parseInt(items[0]),Integer.parseInt(items[1]),Integer.parseInt(items[2]),Integer.parseInt(items[3]),Integer.parseInt(items[4]),Integer.parseInt(items[5]),Integer.parseInt(items[6]),Integer.parseInt(items[7]));
                        break;

                    case "wall":
                        tmp = new Area(Integer.parseInt(items[0]),Integer.parseInt(items[1]),Integer.parseInt(items[2]),Integer.parseInt(items[3]),Integer.parseInt(items[4]),Integer.parseInt(items[5]),Integer.parseInt(items[6]),Integer.parseInt(items[7]));
                        walls.add(tmp);
                        break;


                    case "shaded":
                        tmp = new Area(Integer.parseInt(items[0]),Integer.parseInt(items[1]),Integer.parseInt(items[2]),Integer.parseInt(items[3]),Integer.parseInt(items[4]),Integer.parseInt(items[5]),Integer.parseInt(items[6]),Integer.parseInt(items[7]));
                        shaded.add(tmp);
                        break;

                        /*
                    case "teleport":
                        TelePortal teletmp = new TelePortal(Integer.parseInt(items[0]),Integer.parseInt(items[1]),Integer.parseInt(items[2]),Integer.parseInt(items[3]),Integer.parseInt(items[4]),Integer.parseInt(items[5]),Double.parseDouble(items[6]));
                        teleports.add(teletmp);
                        break;

                         */
                    case "texture":
                        // still to do. First the coordinates, then an int with texture type and then a double with orientation
                }
            }
        }
    }

    public static ArrayList<Area> getWalls(){
        return walls;
    }

    public static void printWalls(){

        System.out.println("Wallsurile din Scenario");
        for(int j=0;j<walls.size();j++){
            System.out.println(walls.get(j));
        }
    }


    public boolean inWall(double x, double y){
        boolean tmp = false;
        for(int j=0;j<walls.size();j++){
            if(walls.get(j).isHit(x,y)){
                tmp=true;
            }
        }
        return(tmp);
    }

    public static ArrayList<Area> getSentryOutside(){
        return sentryOutside;
    }

    public static ArrayList<Area> getSentryInside(){
        return sentryOutside;
    }

    public static ArrayList<Area> getShaded(){
        return shaded;
    }

    public static Area getSpawnAreasGuards(){
        return spawnAreaGuards;
    }

    public static ArrayList<Area> getTargetArea(){

        return targetArea;

    }


    public static double[][] spawnGuard(){
        double[][] tmp = new double[numGuards][4];
        double dx=Math.abs(spawnAreaGuards.rightBoundary-spawnAreaGuards.leftBoundary);
        double dy=Math.abs(spawnAreaGuards.topBoundary-spawnAreaGuards.bottomBoundary);
        for(int i=0; i < numGuards; i++){
            tmp[i][0] = (spawnAreaGuards.leftBoundary + Math.random()*dx);
            System.out.println(tmp[i][0]);
            tmp[i][1] = (spawnAreaGuards.topBoundary + Math.random()*dy);
            System.out.println(tmp[i][1]);
            tmp[i][2] = Math.random()*2 * Math.PI;
            System.out.println(tmp[i][2]);
            System.out.println("--------");
        }
        return tmp;
    }

    /*

    public double[][] spawnIntruders(){
        double[][] tmp = new double[numIntruders][4];
        double dx=spawnAreaIntruders.rightBoundary-spawnAreaIntruders.rightBoundary;
        double dy=spawnAreaIntruders.topBoundary-spawnAreaIntruders.bottomBoundary;
        for(int i=0; i<numIntruders; i++){
            tmp[i][0]=spawnAreaIntruders.leftBoundary+Math.random()*dx;
            tmp[i][1]=spawnAreaIntruders.bottomBoundary+Math.random()*dy;
            tmp[i][2]=Math.random()*2*Math.PI;
        }
        return tmp;
    }


     */
    public int getNumGuards(){
        return numGuards;
    }

    public String getGameFile(){
        return gameFile;
    }

    public String getMapDoc(){
        return mapDoc;
    }

    public double getScaling(){
        return scaling;
    }
}