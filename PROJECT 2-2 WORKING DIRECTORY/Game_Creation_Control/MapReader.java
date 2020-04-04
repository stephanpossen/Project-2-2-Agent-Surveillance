package Game_Creation_Control;

import Geometry.Angle;
import Geometry.Direction;
import Geometry.Point;
import Percept.Scenario.GameMode;
import Agent.AgentsFactory;
import java.util.ArrayList;
import java.util.Scanner;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;


/*This class is meant to read mapFiles as well as to store all of the map information
 * */

public class MapReader {

    // General variables
    private static GameMode gameMode;
    private static int gameModeInt;
    private static  int height;
    private static int width;
    private static int numGuards;
    private static int numIntruders;
    private static double captureDistance;
    private static int winConditionIntruderRounds; // how many rounds does the intruder have to stay in the target area
    private static Angle maxRotationAngle; // in degrees
    private static double maxMoveDistanceIntruder;
    private static double maxSprintDistanceIntruder;
    private static double maxMoveDistanceGuard;
    private static int sprintCooldown; // #rounds
    private static int pheromoneCooldown;
    private static double radiusPheromone;
    private static double slowDownModifierWindow;
    private static double slowDownModifierDoor;
    private static double slowDownModifierSentryTower;
    private static double viewAngle; // in degrees
    private static int viewRays;
    private static double viewRangeIntruderNormal; // length of each vector
    private static double viewRangeIntruderShaded; // length of each vector
    private static double viewRangeGuardNormal; // length of each vector
    private static double viewRangeGuardShaded; // length of each vector
    private static double[] viewRangeSentry; // not visible short range, visible high range)
    private static double yellSoundRadius;
    private static double maxMoveSoundRadius;
    private static double windowSoundRadius;
    private static double doorSoundRadius;

    // Area definitions
    private static Area targetArea;
    private static Area spawnAreaIntruders;
    private static Area spawnAreaGuards;
    private static ArrayList<Area> walls;
    private static ArrayList<TelePortal> teleports;
    private static ArrayList<Area> shadeds;
    private static ArrayList<Area> doors;
    private static ArrayList<Area> windows;
    private static  ArrayList<Sentry> sentries;


    private static  String mapFile;
    private final Path filePath;
    private final static Charset ENCODING = StandardCharsets.UTF_8;
    private String gamefile;
    private static double scaling;

    //Constructor
    public MapReader(String mapDoc) {
        mapFile = mapDoc;

        // initialize variables
        walls = new ArrayList<>(); // create list of walls
        shadeds = new ArrayList<>(); // create list of low-visibility areas
        teleports = new ArrayList<>(); // create list of teleports e.g. stairs
        teleports = new ArrayList<>(); // create list of telportation areas
        doors = new ArrayList<>();// create list of doors areas
        windows = new ArrayList<>();// create list of windows areas
        sentries = new ArrayList<>();// create list of sentries areas

        // read scenario
        filePath = Paths.get(mapFile); // get path
        System.out.println(filePath);
        readMap();
    }

    public void readMap() {
        try {
            Scanner scanner = new Scanner(mapFile);
            while (scanner.hasNextLine()) {
                readLine(scanner.nextLine());
            }
        } catch (Exception e) {
        }
    }

    public void readLine(String line) {
        //use a second Scanner to parse the content of each line
        try {
            Scanner scanner = new Scanner(line);
            scanner.useDelimiter("=");
            if (scanner.hasNext()) {
                String id = scanner.next();
                String value = scanner.next();
                id = id.trim();
                value = value.trim();
                String[] items = value.split(" ");
                switch (id) {
                    case "gameMode":
                        int mode = Integer.parseInt(value);
                        if(mode==0){
                            gameMode = GameMode.CaptureAllIntruders;
                        }
                        if(mode==1){
                            gameMode = GameMode.CaptureOneIntruder;
                        }
                        else{
                            System.out.println("mode  = " + mode + " does not exist");
                        }
                        break;
                    case "height":
                        height = Integer.parseInt(value);
                        break;
                    case "width":
                        width = Integer.parseInt(value);
                        break;
                    case "numGuards":
                        numGuards = Integer.parseInt(value);
                        break;
                    case "numIntruders":
                        numIntruders = Integer.parseInt(value);
                        break;
                    case "captureDistance":
                        captureDistance = Double.parseDouble(value);
                        break;
                    case "winConditionIntruderRounds":
                        winConditionIntruderRounds = Integer.parseInt(value);
                        break;
                    case "maxRotationAngle":
                        double maxRotation = Double.parseDouble(value);
                        maxRotationAngle = Angle.fromDegrees(maxRotation);
                        AgentStateHolder.setMaxRotationAngle(maxRotationAngle);
                        break;
                    case "maxMoveDistanceIntruder":
                        maxMoveDistanceIntruder = Double.parseDouble(value);
                        break;
                    case "maxSprintDistanceIntruder":
                        maxSprintDistanceIntruder = Double.parseDouble(value);
                        break;
                    case "maxMoveDistanceGuard":
                        maxMoveDistanceGuard = Double.parseDouble(value);
                        break;
                    case "sprintCooldown":
                        sprintCooldown = Integer.parseInt(value);
                        break;
                    case "pheromoneCooldown":
                        pheromoneCooldown = Integer.parseInt(value);
                        break;
                    case "radiusPheromone":
                        radiusPheromone = Double.parseDouble(value);
                        break;
                    case "slowDownModifierWindow":
                        slowDownModifierWindow = Double.parseDouble(value);
                        break;
                    case "slowDownModifierDoor":
                        slowDownModifierDoor = Double.parseDouble(value);
                        break;
                    case "slowDownModifierSentryTower":
                        slowDownModifierSentryTower = Double.parseDouble(value);
                        break;
                    case "viewAngle":
                        viewAngle = Double.parseDouble(value);
                        break;
                    case "viewRays":
                        viewRays = Integer.parseInt(value);
                        break;
                    case "viewRangeIntruderNormal":
                        viewRangeIntruderNormal = Double.parseDouble(value);
                        break;
                    case "viewRangeIntruderShaded":
                        viewRangeIntruderShaded = Double.parseDouble(value);
                        break;
                    case "viewRangeGuardNormal":
                        viewRangeGuardNormal = Double.parseDouble(value);
                        break;
                    case "viewRangeGuardShaded":
                        viewRangeGuardShaded = Double.parseDouble(value);
                        break;
                    case "viewRangeSentry":
                        viewRangeSentry[0] = Double.parseDouble(items[0]);
                        viewRangeSentry[1] = Double.parseDouble(items[1]);
                        break;
                    case "yellSoundRadius":
                        yellSoundRadius = Double.parseDouble(value);
                        break;
                    case "maxMoveSoundRadius":
                        maxMoveSoundRadius = Double.parseDouble(value);
                        break;
                    case "windowSoundRadius":
                        windowSoundRadius = Double.parseDouble(value);
                        break;
                    case "doorSoundRadius":
                        doorSoundRadius = Double.parseDouble(value);
                        break;
                    case "targetArea":
                       /* targetArea[0] = Integer.parseInt(items[0]);
                        targetArea[1] = Integer.parseInt(items[1]);
                        targetArea[2] = Integer.parseInt(items[2]);
                        targetArea[3] = Integer.parseInt(items[3]);
                        targetArea[4] = Integer.parseInt(items[4]);
                        targetArea[5] = Integer.parseInt(items[5]);
                        targetArea[6] = Integer.parseInt(items[6]);
                        targetArea[7] = Integer.parseInt(items[7]);*/
                        targetArea = new Area(Integer.parseInt(items[0]),Integer.parseInt(items[1]),Integer.parseInt(items[2]),Integer.parseInt(items[3]),Integer.parseInt(items[4]),Integer.parseInt(items[5]),Integer.parseInt(items[6]),Integer.parseInt(items[7]));
                        break;
                    case "spawnAreaIntruders":
                        /*spawnAreaIntruders[0] = Integer.parseInt(items[0]);
                        spawnAreaIntruders[1] = Integer.parseInt(items[1]);
                        spawnAreaIntruders[2] = Integer.parseInt(items[2]);
                        spawnAreaIntruders[3] = Integer.parseInt(items[3]);
                        spawnAreaIntruders[4] = Integer.parseInt(items[4]);
                        spawnAreaIntruders[5] = Integer.parseInt(items[5]);
                        spawnAreaIntruders[6] = Integer.parseInt(items[6]);
                        spawnAreaIntruders[7] = Integer.parseInt(items[7]);*/
                        spawnAreaIntruders = new Area(Integer.parseInt(items[0]),Integer.parseInt(items[1]),Integer.parseInt(items[2]),Integer.parseInt(items[3]),Integer.parseInt(items[4]),Integer.parseInt(items[5]),Integer.parseInt(items[6]),Integer.parseInt(items[7]));
                        break;
                    case "spawnAreaGuards":
                       /* spawnAreaGuards[0] = Integer.parseInt(items[0]);
                        spawnAreaGuards[1] = Integer.parseInt(items[1]);
                        spawnAreaGuards[2] = Integer.parseInt(items[2]);
                        spawnAreaGuards[3] = Integer.parseInt(items[3]);
                        spawnAreaGuards[4] = Integer.parseInt(items[4]);
                        spawnAreaGuards[5] = Integer.parseInt(items[5]);
                        spawnAreaGuards[6] = Integer.parseInt(items[6]);
                        spawnAreaGuards[7] = Integer.parseInt(items[7]);*/
                        spawnAreaGuards = new Area(Integer.parseInt(items[0]),Integer.parseInt(items[1]),Integer.parseInt(items[2]),Integer.parseInt(items[3]),Integer.parseInt(items[4]),Integer.parseInt(items[5]),Integer.parseInt(items[6]),Integer.parseInt(items[7]));
                        break;
                    case "wall":
                        Area tmpwa = new Area(Integer.parseInt(items[0]),Integer.parseInt(items[1]),Integer.parseInt(items[2]),Integer.parseInt(items[3]),Integer.parseInt(items[4]),Integer.parseInt(items[5]),Integer.parseInt(items[6]),Integer.parseInt(items[7]));
                        walls.add(tmpwa);
                        break;
                    case "teleport":
                        TelePortal teletmp = new TelePortal(Integer.parseInt(items[0]),Integer.parseInt(items[1]),Integer.parseInt(items[2]),Integer.parseInt(items[3]),Integer.parseInt(items[4]),Integer.parseInt(items[5]),Integer.parseInt(items[6]),Integer.parseInt(items[7]),Integer.parseInt(items[8]),Integer.parseInt(items[9]));
                        teleports.add(teletmp);
                        break;
                    case "shaded":
                        Area tmps = new Area(Integer.parseInt(items[0]),Integer.parseInt(items[1]),Integer.parseInt(items[2]),Integer.parseInt(items[3]),Integer.parseInt(items[4]),Integer.parseInt(items[5]),Integer.parseInt(items[6]),Integer.parseInt(items[7]));
                        tmps.setShaded(true);
                        shadeds.add(tmps);
                        break;
                    case "door":
                        Area tmpd = new Area(Integer.parseInt(items[0]),Integer.parseInt(items[1]),Integer.parseInt(items[2]),Integer.parseInt(items[3]),Integer.parseInt(items[4]),Integer.parseInt(items[5]),Integer.parseInt(items[6]),Integer.parseInt(items[7]));
                        Door.setSoundRange(doorSoundRadius);
                        doors.add(tmpd);
                        break;
                    case "window":
                        Area tmpwi = new Area(Integer.parseInt(items[0]),Integer.parseInt(items[1]),Integer.parseInt(items[2]),Integer.parseInt(items[3]),Integer.parseInt(items[4]),Integer.parseInt(items[5]),Integer.parseInt(items[6]),Integer.parseInt(items[7]));
                        Window.setSoundRange(windowSoundRadius);
                        windows.add(tmpwi);
                        break;
                    case "sentry":
                        Sentry tmpsen = new Sentry(Integer.parseInt(items[0]),Integer.parseInt(items[1]),Integer.parseInt(items[2]),Integer.parseInt(items[3]),Integer.parseInt(items[4]),Integer.parseInt(items[5]),Integer.parseInt(items[6]),Integer.parseInt(items[7]),Integer.parseInt(items[8]),Integer.parseInt(items[9]),Integer.parseInt(items[10]),Integer.parseInt(items[11]),Integer.parseInt(items[12]),Integer.parseInt(items[13]),Integer.parseInt(items[14]),Integer.parseInt(items[15]));
                        sentries.add(tmpsen);
                        break;
                }
            }
        } catch (Exception e) {
        }
    }

    public static  double[] getViewRangeSentry(){
        return viewRangeSentry;
    }
    public static GameMode getGameMode() {
        return gameMode;
    }

    public static int getHeight() {
        return height;
    }

    public static int getWidth() {
        return width;
    }

    public static int getNumGuards() {
        return numGuards;
    }

    public static int getNumIntruders() {
        return numIntruders;
    }

    public static double getCaptureDistance() {
        return captureDistance;
    }

    public static int getWinConditionIntruderRounds() {
        return winConditionIntruderRounds;
    }

    public static Angle getMaxRotationAngle() {
        return maxRotationAngle;
    }

    public static double getMaxMoveDistanceIntruder() {
        return maxMoveDistanceIntruder;
    }

    public static double getMaxSprintDistanceIntruder() {
        return maxSprintDistanceIntruder;
    }

    public static double getMaxMoveDistanceGuard() {
        return maxMoveDistanceGuard;
    }

    public static int getSprintCooldown() {
        return sprintCooldown;
    }

    public static int getPheromoneCooldown() {
        return pheromoneCooldown;
    }

    public static  double getRadiusPheromone() {
        return radiusPheromone;
    }

    public static double getSlowDownModifierWindow() {
        return slowDownModifierWindow;
    }

    public static double getSlowDownModifierDoor() {
        return slowDownModifierDoor;
    }

    public static double getSlowDownModifierSentryTower() {
        return slowDownModifierSentryTower;
    }

    public static double getViewAngle() {
        return viewAngle;
    }

    public static int getViewRays() {
        return viewRays;
    }

    public static double getViewRangeIntruderNormal() {
        return viewRangeIntruderNormal;
    }

    public static double getViewRangeIntruderShaded() {
        return viewRangeIntruderShaded;
    }

    public static double getViewRangeGuardNormal() {
        return viewRangeGuardNormal;
    }

    public static double getViewRangeGuardShaded() {
        return viewRangeGuardShaded;
    }

    public static double getYellSoundRadius() {
        return yellSoundRadius;
    }

    public static double getMaxMoveSoundRadius() {
        return maxMoveSoundRadius;
    }

    public static double getWindowSoundRadius() {
        return windowSoundRadius;
    }

    public static double getDoorSoundRadius() {
        return doorSoundRadius;
    }

    public static Area getSpawnAreaIntruders() {
        return spawnAreaIntruders;
    }

    public static Area getSpawnAreaGuards() {
        return spawnAreaGuards;
    }

    public static ArrayList<TelePortal> getTeleports() {
        return teleports;
    }

    public static ArrayList<Area> getShadeds() {
        return shadeds;
    }

    public static ArrayList<Area> getWindows() {
        return windows;
    }

    public static ArrayList<Area> getDoors() {
        return doors;
    }

    public static ArrayList<Sentry> getSentries() {
        return sentries;
    }

    public static String getMapFile() {
        return mapFile;
    }

    public static ArrayList<Area> getWalls(){
        return walls;
    }

    public static int getGameModeInt(){
        return gameModeInt;
    }

    // get all the possible objects to enter into a collision with an agent.
    public static ArrayList<Area> getCollisionableObjects(){
        ArrayList<Area> objects = new ArrayList<>();
        objects.addAll(walls);
        objects.addAll(doors);
        objects.addAll(windows);
        objects.addAll(sentries);
        return objects;
    }

    public static ArrayList<Area> getAllObjects(){
        ArrayList<Area> objects = new ArrayList<>();
        objects.addAll(walls);
        objects.addAll(doors);
        objects.addAll(windows);
        objects.addAll(sentries);
        objects.addAll(teleports);
        objects.addAll(shadeds);
        return objects;
    }

    // Object opaque from outside
    // An agent can not see in the area when the agent is outside of the area
    public static ArrayList<Area> getOpaqueObjectsFromOutside(){
        ArrayList<Area> objects = new ArrayList<>();
        objects.addAll(doors);
        objects.addAll(sentries);
        objects.addAll(shadeds);
        return objects;
    }

    public static boolean inWall(double x, double y){
        boolean tmp = false;
        for(int j=0;j<walls.size();j++){
            if(walls.get(j).isHit(x,y)){
                tmp=true;
            }
        }
        return(tmp);
    }

    public static Area getTargetArea(){
        return targetArea;
    }

    public static double[][] spawnGuards(){
        ArrayList<AgentStateHolder> h = AgentsFactory.getAgentsStates();
        double[][] tmp = new double[numGuards][4];

        double dx = spawnAreaGuards.getRightBoundary() - spawnAreaGuards.getLeftBoundary();
        double dy = spawnAreaGuards.getTopBoundary()- spawnAreaGuards.getBottomBoundary();

        for(int i=0; i<numGuards; i++){
            tmp[i][0] = spawnAreaGuards.getLeftBoundary() + Math.random() * dx;
            tmp[i][1] = spawnAreaGuards.getBottomBoundary() + Math.random() * dy;
            tmp[i][2] = Math.random() * 2 * Math.PI;
            AgentsFactory.getStateHolder(i).setPosition(new Point(tmp[i][0],tmp[i][1]));
            AgentsFactory.getStateHolder(i).setDirection(Direction.fromRadians(tmp[i][2]));
        }
        return tmp;
    }


    public static double[][] spawnIntruders(){

        double[][] tmp = new double[numIntruders][4];

        double dx = spawnAreaIntruders.getRightBoundary() - spawnAreaIntruders.getLeftBoundary();
        double dy = spawnAreaIntruders.getTopBoundary()- spawnAreaIntruders.getBottomBoundary();

        for(int i=0; i<numIntruders; i++){
            tmp[i][0] = spawnAreaIntruders.getLeftBoundary() + Math.random() * dx;
            tmp[i][1] = spawnAreaIntruders.getBottomBoundary() + Math.random() * dy;
            tmp[i][2] = Math.random() * 2 * Math.PI;
            AgentsFactory.getStateHolder(i).setPosition(new Point(tmp[i][0],tmp[i][1]));
            AgentsFactory.getStateHolder(i).setDirection(Direction.fromRadians(tmp[i][2]));
        }
        return tmp;
    }


    public static String getGameFile(){
        //  return gamefile;
        return "";
    }

    public static double getScaling(){
        return scaling;
    }
}