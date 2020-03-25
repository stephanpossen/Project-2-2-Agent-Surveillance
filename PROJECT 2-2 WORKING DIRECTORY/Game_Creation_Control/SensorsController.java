package Game_Creation_Control;

import Action.NoAction;
import Geometry.*;
import Geometry.Vector;
import Percept.AreaPercepts;
import Percept.GuardPercepts;
import Percept.IntruderPercepts;
import Percept.Scenario.ScenarioGuardPercepts;
import Percept.Scenario.ScenarioIntruderPercepts;
import Percept.Scenario.ScenarioPercepts;
import Percept.Scenario.SlowDownModifiers;
import Percept.Smell.SmellPercept;
import Percept.Smell.SmellPercepts;
import Percept.Sound.SoundPercept;
import Percept.Sound.SoundPercepts;
import Percept.Scenario.ScenarioPercepts;
import Percept.Smell.SmellPercepts;
import Percept.Sound.SoundPercepts;
import Percept.Vision.*;

import java.util.*;

public class SensorsController {


    public static GuardPercepts getGuardPercepts(AgentStateHolder h){
        Point pos =  h.getPosition();
        Vector trans = new Vector(pos);
        trans.mul(-1);
        Set<ObjectPercept> objectPercepts = new HashSet<>();

        addObjects(trans,objectPercepts,ObjectPerceptType.Wall,MapReader.getWalls());
        addObjects(trans,objectPercepts,ObjectPerceptType.Door, MapReader.getDoors());
        addObjects(trans,objectPercepts,ObjectPerceptType.Window,MapReader.getWindows());
        addObjects(trans,objectPercepts,ObjectPerceptType.ShadedArea,MapReader.getShadeds());
        ArrayList<Area> b =  new ArrayList<>();
        b.addAll(MapReader.getSentries());
        addObjects(trans,objectPercepts,ObjectPerceptType.Teleport,b);
        ArrayList<Area> sentries =  new ArrayList<>();
        sentries.addAll(MapReader.getSentries());
        addObjects(trans,objectPercepts,ObjectPerceptType.SentryTower,sentries);
        ArrayList<Area> teleports =  new ArrayList<>();
        teleports.addAll(MapReader.getTeleports());
        addObjects(trans,objectPercepts,ObjectPerceptType.Teleport,teleports);

        ObjectPercepts o = new ObjectPercepts(objectPercepts);

        FieldOfView f;
        if(isInArea(pos,MapReader.getShadeds())){
            f = new FieldOfView(new Distance(MapReader.getViewRangeGuardShaded()), Angle.fromRadians(MapReader.getViewAngle()));
        }
        else if(isInArea(pos, sentries)) {
            f = new SentryFieldView(new Distance(MapReader.getViewRangeSentry()[0]), new Distance(MapReader.getViewRangeSentry()[1]), Angle.fromRadians(MapReader.getViewAngle()));
        }
        else{
            f = new FieldOfView(new Distance(MapReader.getViewRangeGuardNormal()),Angle.fromRadians(MapReader.getViewAngle()));
        }

        VisionPrecepts vision = new VisionPrecepts(f,o);

        Set<SoundPercept> soundPercepts = new HashSet<>();
        SoundPercepts sound = new SoundPercepts(soundPercepts);

        Set<SmellPercept> smellPercepts = new HashSet<>();
        SmellPercepts smell = new SmellPercepts(smellPercepts);


        AreaPercepts areaPercepts = new AreaPercepts(isInArea(pos,MapReader.getWindows()),isInArea(pos,MapReader.getDoors()), isInArea(pos,sentries),isInArea(pos,teleports));

        SlowDownModifiers slowDownModifiers = new SlowDownModifiers(MapReader.getSlowDownModifierWindow(),MapReader.getSlowDownModifierDoor(),MapReader.getSlowDownModifierSentryTower());
        ScenarioPercepts scenarioPercepts = new ScenarioPercepts(MapReader.getGameMode(), new Distance(MapReader.getCaptureDistance()), MapReader.getMaxRotationAngle(), slowDownModifiers , new Distance(MapReader.getRadiusPheromone()),MapReader.getPheromoneCooldown());
        ScenarioGuardPercepts scenarioGuardPercepts = new ScenarioGuardPercepts(scenarioPercepts, new Distance(MapReader.getMaxMoveDistanceGuard()));


        GuardPercepts percepts = new GuardPercepts(vision, sound, smell, areaPercepts, scenarioGuardPercepts,wasLastActionExecuted(h));
        return percepts;
    }

    public static IntruderPercepts getIntruderPercepts(AgentStateHolder h){
        Point pos =  h.getPosition();
        Vector trans = new Vector(pos);
        trans.mul(-1);
        Set<ObjectPercept> objectPercepts = new HashSet<>();

        addObjects(trans,objectPercepts,ObjectPerceptType.Wall,MapReader.getWalls());
        addObjects(trans,objectPercepts,ObjectPerceptType.Door, MapReader.getDoors());
        addObjects(trans,objectPercepts,ObjectPerceptType.Window,MapReader.getWindows());
        addObjects(trans,objectPercepts,ObjectPerceptType.ShadedArea,MapReader.getShadeds());
        ArrayList<Area> b =  new ArrayList<>();
        b.addAll(MapReader.getSentries());
        addObjects(trans,objectPercepts,ObjectPerceptType.Teleport,b);
        ArrayList<Area> sentries =  new ArrayList<>();
        sentries.addAll(MapReader.getSentries());
        //addObjects(trans,objectPercepts,ObjectPerceptType.SentryTower,sentries);
        ArrayList<Area> teleports =  new ArrayList<>();
        teleports.addAll(MapReader.getTeleports());
        addObjects(trans,objectPercepts,ObjectPerceptType.Teleport,teleports);

        ObjectPercepts o = new ObjectPercepts(objectPercepts);

        FieldOfView f;
        if(isInArea(pos,MapReader.getShadeds())){
            f = new FieldOfView(new Distance(MapReader.getViewRangeGuardShaded()), Angle.fromRadians(MapReader.getViewAngle()));
        }
        else{
            f = new FieldOfView(new Distance(MapReader.getViewRangeGuardNormal()),Angle.fromRadians(MapReader.getViewAngle()));
        }


        VisionPrecepts vision = new VisionPrecepts(f,o);

        Set<SoundPercept> soundPercepts = new HashSet<>();
        SoundPercepts sound = new SoundPercepts(soundPercepts);

        Set<SmellPercept> smellPercepts = new HashSet<>();
        SmellPercepts smell = new SmellPercepts(smellPercepts);


        AreaPercepts areaPercepts = new AreaPercepts(isInArea(pos,MapReader.getWindows()),isInArea(pos,MapReader.getDoors()), isInArea(pos,sentries),isInArea(pos,teleports));

        SlowDownModifiers slowDownModifiers = new SlowDownModifiers(MapReader.getSlowDownModifierWindow(),MapReader.getSlowDownModifierDoor(),MapReader.getSlowDownModifierSentryTower());
        ScenarioPercepts scenarioPercepts = new ScenarioPercepts(MapReader.getGameMode(), new Distance(MapReader.getCaptureDistance()), MapReader.getMaxRotationAngle(), slowDownModifiers , new Distance(MapReader.getRadiusPheromone()),MapReader.getPheromoneCooldown());
        ScenarioIntruderPercepts scenarioIntruderPercepts = new ScenarioIntruderPercepts(scenarioPercepts,MapReader.getWinConditionIntruderRounds() ,new Distance(MapReader.getMaxMoveDistanceIntruder()), new Distance( MapReader.getMaxSprintDistanceIntruder()),MapReader.getSprintCooldown());

        IntruderPercepts percepts = new IntruderPercepts(getTargetDirection(h),vision, sound, smell, areaPercepts, scenarioIntruderPercepts, wasLastActionExecuted(h));
        return percepts;
    }

    public static boolean isInView(Point point, double maxRange, Angle viewAngle) {
        return isInRange(point,maxRange) && isInViewAngle(point ,viewAngle);
    }

    private static boolean isInViewAngle(Point point, Angle viewAngle) {
        return Angle.fromRadians(0).getDistance(point.getClockDirection()).getRadians() <= viewAngle.getRadians() / 2;
    }

    private static boolean isInRange(Point point, double maxRange) {
        return point.getDistanceFromOrigin().getValue() <= maxRange;
    }


    /**
     * to check if a specific guard sees an intruder or not.
     * @param guard
     * @param intruder
     * @return true if the intruder is in the field of view of the guard. Otherwise no.
     */
    public static boolean isInfieldView(AgentStateHolder guard, AgentStateHolder intruder){

            int precision = 100;
            boolean ok = false;
            double centerX = intruder.getPosition().getX();
            double centerY = intruder.getPosition().getY();
            Vector center = new Vector(centerX,centerY);
            double radius = intruder.getRadius();

            Point pos =  guard.getPosition();
            Vector trans = new Vector(pos);
            trans.mul(-1);

            for(int i = -precision ; i< precision ; i++){
                double checkX = i*radius/precision;
                double checkY = getRelativeY(checkX,radius);
                double checkY2 = -checkY;
                checkX+=centerX;
                checkY+=centerY;
                checkY2+=centerY;
                Vector intrud2 = new Vector(checkX,checkY);
                intrud2.add(center);
                intrud2.add(trans);
                Vector intrud = new Vector(checkX,checkY2);
                intrud.add(center);
                intrud.add(trans);

                if (isInView( new Point(intrud.x, intrud.y), MapReader.getViewRangeGuardNormal(), Angle.fromRadians(MapReader.getViewAngle()))){
                    ok = true;
                }
                if (isInView( new Point(intrud2.x, intrud2.y), MapReader.getViewRangeGuardNormal(), Angle.fromRadians(MapReader.getViewAngle()))){
                    ok = true;
                }
            }
            return ok;
    }

    /**
     * to check if a specific guard captures an intruder or not.
     * @param guard
     * @param intruder
     * @return true if the intruder is in the field of capture of the guard. Otherwise no.
     */
    public static boolean isInfieldCapture(AgentStateHolder guard, AgentStateHolder intruder){

        int precision = 100;
        boolean ok = false;
        double centerX = intruder.getPosition().getX();
        double centerY = intruder.getPosition().getY();
        Vector center = new Vector(centerX,centerY);
        double radius = intruder.getRadius();

        Point pos =  guard.getPosition();
        Vector trans = new Vector(pos);
        trans.mul(-1);

        for(int i = -precision ; i< precision ; i++){
            double checkX = i*radius/precision;
            double checkY = getRelativeY(checkX,radius);
            double checkY2 = -checkY;
            checkX+=centerX;
            checkY+=centerY;
            checkY2+=centerY;
            Vector intrud2 = new Vector(checkX,checkY);
            intrud2.add(center);
            intrud2.add(trans);
            Vector intrud = new Vector(checkX,checkY2);
            intrud.add(center);
            intrud.add(trans);

            if (isInView( new Point(intrud.x, intrud.y), MapReader.getCaptureDistance(), Angle.fromRadians(MapReader.getViewAngle()))){
                ok = true;
            }
            if (isInView( new Point(intrud2.x, intrud2.y), MapReader.getCaptureDistance(), Angle.fromRadians(MapReader.getViewAngle()))){
                ok = true;
            }
        }
        return ok;
    }

    // for a given x, returns the corresponding y using the circle equation x^2+y^2=r^2
    private static double getRelativeY(double x, double radius){
        return Math.sqrt(Math.pow(radius,2)-Math.pow(x,2));
    }

    private static boolean isInArea(Point p, ArrayList<Area> areas){
        boolean ok = false;
        for(Area a : areas){
            //radius is the size of the agent
            if(a.isHit(p.getX(),p.getY(),0.5)){
                ok =true;
            }
        }
        return ok;
    }

    private static boolean wasLastActionExecuted(AgentStateHolder h){
        if(h.getLastExecutedAction() instanceof NoAction){
            return false;
        }else{
            return false;
        }
    }

    private static Direction getTargetDirection(AgentStateHolder h){
        Vector vectorDirection = h.getDirectionVector();
        Vector toTarget = new Vector(h.getPosition(),MapReader.getTargetArea().getCenter());
        return Direction.fromRadians(toTarget.computeAngle(vectorDirection));
    }
    private static void addObjects(Vector trans, Set<ObjectPercept> objectPercepts, ObjectPerceptType type, ArrayList<Area> objects){
        for(Area a : objects){
            Area aa = a.cloned();
            aa.translate(trans);
            double stepSize = 0.01;
            for(double i = aa.getLeftBoundary(); i<aa.getRightBoundary(); i+=stepSize ) {
                objectPercepts.add(new ObjectPercept(type,new Point(i,aa.getTopBoundary())));
                objectPercepts.add(new ObjectPercept(type,new Point(i,aa.getBottomBoundary())));
            }
            for(double i = aa.getBottomBoundary(); i<aa.getTopBoundary();  i+=stepSize){
                objectPercepts.add(new ObjectPercept(type,new Point(i,aa.getLeftBoundary())));
                objectPercepts.add(new ObjectPercept(type,new Point(i,aa.getRightBoundary())));
            }
        }
    }
}
