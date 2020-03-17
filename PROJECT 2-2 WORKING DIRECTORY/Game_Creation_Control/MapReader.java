package Game_Creation_Control;

import Percept.Vision.ObjectPerceptType;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MapReader {

    // General variables
    private int gameMode;
    private int height;
    private int width;
    private int numGuards;
    private int numIntruders;
    private double captureDistance;
    private int winConditionIntruderRounds; // how many rounds does the intruder have to stay in the target area
    private double maxRotationAngle; // in degrees
    private double maxMoveDistanceIntruder;
    private double maxSprintDistanceIntruder;
    private double maxMoveDistanceGuard;
    private int sprintCooldown; // #rounds
    private int pheromoneCooldown;
    private double radiusPheromone;
    private double slowDownModifierWindow;
    private double slowDownModifierDoor;
    private double slowDownModifierSentryTower;
    private double viewAngle; // in degrees
    private int viewRays;
    private double viewRangeIntruderNormal; // length of each vector
    private double viewRangeIntruderShaded; // length of each vector
    private double viewRangeGuardNormal; // length of each vector
    private double viewRangeGuardShaded; // length of each vector
    private double[] viewRangeSentry; // not visible short range, visible high range)
    private double yellSoundRadius;
    private double maxMoveSoundRadius;
    private double windowSoundRadius;
    private double doorSoundRadius;

    // Area definitions
    private Area targetArea;
    private Area spawnAreaIntruders;
    private Area spawnAreaGuards;
    private ArrayList<Area> walls;
    private ArrayList<TelePortal> teleports;
    private ArrayList<Area> shadeds;
    private ArrayList<Area> doors;
    private ArrayList<Area> windows;
    private ArrayList<Sentry> sentries;


    private String mapFile;
    private final Path filePath;
    private final static Charset ENCODING = StandardCharsets.UTF_8;
    //Constructor
    public MapReader(String mapDoc) {
        this.mapFile = mapDoc;

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
                        gameMode = Integer.parseInt(value);
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
                        maxRotationAngle = Double.parseDouble(value);
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
                        shadeds.add(tmps);
                        break;
                    case "door":
                        Area tmpd = new Area(Integer.parseInt(items[0]),Integer.parseInt(items[1]),Integer.parseInt(items[2]),Integer.parseInt(items[3]),Integer.parseInt(items[4]),Integer.parseInt(items[5]),Integer.parseInt(items[6]),Integer.parseInt(items[7]));
                        doors.add(tmpd);
                        break;
                    case "window":
                        Area tmpwi = new Area(Integer.parseInt(items[0]),Integer.parseInt(items[1]),Integer.parseInt(items[2]),Integer.parseInt(items[3]),Integer.parseInt(items[4]),Integer.parseInt(items[5]),Integer.parseInt(items[6]),Integer.parseInt(items[7]));
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

    public Double[] getViewRangeSentry(){
        return viewRangeSentry;
    }
    public int getGameMode() {
        return gameMode;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getNumGuards() {
        return numGuards;
    }

    public int getNumIntruders() {
        return numIntruders;
    }

    public double getCaptureDistance() {
        return captureDistance;
    }

    public int getWinConditionIntruderRounds() {
        return winConditionIntruderRounds;
    }

    public double getMaxRotationAngle() {
        return maxRotationAngle;
    }

    public double getMaxMoveDistanceIntruder() {
        return maxMoveDistanceIntruder;
    }

    public double getMaxSprintDistanceIntruder() {
        return maxSprintDistanceIntruder;
    }

    public double getMaxMoveDistanceGuard() {
        return maxMoveDistanceGuard;
    }

    public int getSprintCooldown() {
        return sprintCooldown;
    }

    public int getPheromoneCooldown() {
        return pheromoneCooldown;
    }

    public double getRadiusPheromone() {
        return radiusPheromone;
    }

    public double getSlowDownModifierWindow() {
        return slowDownModifierWindow;
    }

    public double getSlowDownModifierDoor() {
        return slowDownModifierDoor;
    }

    public double getSlowDownModifierSentryTower() {
        return slowDownModifierSentryTower;
    }

    public double getViewAngle() {
        return viewAngle;
    }

    public int getViewRays() {
        return viewRays;
    }

    public double getViewRangeIntruderNormal() {
        return viewRangeIntruderNormal;
    }

    public double getViewRangeIntruderShaded() {
        return viewRangeIntruderShaded;
    }

    public double getViewRangeGuardNormal() {
        return viewRangeGuardNormal;
    }

    public double getViewRangeGuardShaded() {
        return viewRangeGuardShaded;
    }

    public double getYellSoundRadius() {
        return yellSoundRadius;
    }

    public double getMaxMoveSoundRadius() {
        return maxMoveSoundRadius;
    }

    public double getWindowSoundRadius() {
        return windowSoundRadius;
    }

    public double getDoorSoundRadius() {
        return doorSoundRadius;
    }

    public int[] getSpawnAreaIntruders() {
        return spawnAreaIntruders;
    }

    public int[] getSpawnAreaGuards() {
        return spawnAreaGuards;
    }

    public ArrayList<TelePortal> getTeleports() {
        return teleports;
    }

    public ArrayList<Area> getShadeds() {
        return shadeds;
    }

    public ArrayList<Area> getWindows() {
        return windows;
    }

    public ArrayList<Area> getDoors() {
        return doors;
    }

    public ArrayList<Sentry> getSentries() {
        return sentries;
    }

    public String getMapFile() {
        return mapFile;
    }

    public ArrayList<Area> getWalls(){
        return walls;
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


    public Area getTargetArea(){
        return targetArea;
    }


    public double[][] spawnGuards(){

        double[][] tmp = new double[numGuards][4];

        double dx = spawnAreaGuards.getRightBoundary() - spawnAreaGuards.getLeftBoundary();
        double dy = spawnAreaGuards.getTopBoundary()- spawnAreaGuards.getBottomBoundary();

        for(int i=0; i<numGuards; i++){
            tmp[i][0] = spawnAreaGuards.getLeftBoundary() + Math.random() * dx;
            tmp[i][1] = spawnAreaGuards.getBottomBoundary() + Math.random() * dy;
            tmp[i][2] = Math.random() * 2 * Math.PI;
        }
        return tmp;
    }


    public double[][] spawnIntruders(){

        double[][] tmp = new double[numGuards][4];

        double dx = spawnAreaIntruders.getRightBoundary() - spawnAreaIntruders.getLeftBoundary();
        double dy = spawnAreaIntruders.getTopBoundary()- spawnAreaIntruders.getBottomBoundary();

        for(int i=0; i<numIntruders; i++){
            tmp[i][0] = spawnAreaIntruders.getLeftBoundary() + Math.random() * dx;
            tmp[i][1] = spawnAreaIntruders.getBottomBoundary() + Math.random() * dy;
            tmp[i][2] = Math.random() * 2 * Math.PI;
        }
        return tmp;
    }


    public String getGameFile(){
        return gameFile;
    }

    public double getScaling(){
        return scaling;
    }
}
