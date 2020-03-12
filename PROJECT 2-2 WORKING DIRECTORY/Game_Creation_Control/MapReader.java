package Game_Creation_Control;

import Percept.Vision.ObjectPerceptType;
import java.util.Scanner;

public class MapReader {
    private int gameMode;
    private int height;
    private int width;
    private int numGuards;
    private int numIntruders;
    private double captureDistance;
    private int winConditionIntruderRounds;
    private double maxRotationAngle;
    private double maxMoveDistanceIntruder;
    private double maxSprintDistanceIntruder;
    private double maxMoveDistanceGuard;
    private int sprintCooldown;
    private int pheromoneCooldown;
    private double radiusPheromone;
    private double slowDownModifierWindow;
    private double slowDownModifierDoor;
    private double slowDownModifierSentryTower;
    private double viewAngle;
    private int viewRays;
    private double viewRangeIntruderNormal;
    private double viewRangeIntruderShaded;
    private double viewRangeGuardNormal;
    private double viewRangeGuardShaded;
    private double[] viewRangeSentry;
    private double yellSoundRadius;
    private double maxMoveSoundRadius;
    private double windowSoundRadius;
    private double doorSoundRadius;
    private int[] targetArea;
    private int[] spawnAreaIntruders;
    private int[] spawnAreaGuards;
    private ObjectPerceptType[] walls;
    private ObjectPerceptType[] teleports;
    private ObjectPerceptType[] shadeds;
    private ObjectPerceptType[] doors;
    private ObjectPerceptType[] sentries;


    private String mapFile;
    //Constructor
    public MapReader(String mapFile) {
        this.mapFile = mapFile;
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
                        targetArea[0] = Integer.parseInt(items[0]);
                        targetArea[1] = Integer.parseInt(items[1]);
                        targetArea[2] = Integer.parseInt(items[2]);
                        targetArea[3] = Integer.parseInt(items[3]);
                        targetArea[4] = Integer.parseInt(items[4]);
                        targetArea[5] = Integer.parseInt(items[5]);
                        targetArea[6] = Integer.parseInt(items[6]);
                        targetArea[7] = Integer.parseInt(items[7]);
                        break;
                    case "spawnAreaIntruders":
                        spawnAreaIntruders[0] = Integer.parseInt(items[0]);
                        spawnAreaIntruders[1] = Integer.parseInt(items[1]);
                        spawnAreaIntruders[2] = Integer.parseInt(items[2]);
                        spawnAreaIntruders[3] = Integer.parseInt(items[3]);
                        spawnAreaIntruders[4] = Integer.parseInt(items[4]);
                        spawnAreaIntruders[5] = Integer.parseInt(items[5]);
                        spawnAreaIntruders[6] = Integer.parseInt(items[6]);
                        spawnAreaIntruders[7] = Integer.parseInt(items[7]);
                        break;
                    case "spawnAreaGuards":
                        spawnAreaGuards[0] = Integer.parseInt(items[0]);
                        spawnAreaGuards[1] = Integer.parseInt(items[1]);
                        spawnAreaGuards[2] = Integer.parseInt(items[2]);
                        spawnAreaGuards[3] = Integer.parseInt(items[3]);
                        spawnAreaGuards[4] = Integer.parseInt(items[4]);
                        spawnAreaGuards[5] = Integer.parseInt(items[5]);
                        spawnAreaGuards[6] = Integer.parseInt(items[6]);
                        spawnAreaGuards[7] = Integer.parseInt(items[7]);
                        break;
                    case "wall":
                        doorSoundRadius = Double.parseDouble(value);
                        break;
                    case "teleport":
                        doorSoundRadius = Double.parseDouble(value);
                        break;
                    case "shaded":
                        doorSoundRadius = Double.parseDouble(value);
                        break;
                    case "door":
                        doorSoundRadius = Double.parseDouble(value);
                        break;
                    case "window":
                        doorSoundRadius = Double.parseDouble(value);
                        break;
                    case "sentry":
                        doorSoundRadius = Double.parseDouble(value);
                        break;
                }
            }
        } catch (Exception e) {
        }
    }

    public void spawnGuards() {

    }

    public void spawnIntruders() {

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

    public double[] getViewRangeSentry() {
        return viewRangeSentry;
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

    public int[] getTargetArea() {
        return targetArea;
    }

    public int[] getSpawnAreaIntruders() {
        return spawnAreaIntruders;
    }

    public int[] getSpawnAreaGuards() {
        return spawnAreaGuards;
    }

    public ObjectPerceptType[] getWalls() {
        return walls;
    }

    public ObjectPerceptType[] getTeleports() {
        return teleports;
    }

    public ObjectPerceptType[] getShadeds() {
        return shadeds;
    }

    public ObjectPerceptType[] getDoors() {
        return doors;
    }

    public ObjectPerceptType[] getSentries() {
        return sentries;
    }

    public String getMapFile() {
        return mapFile;
    }
}
