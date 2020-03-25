package Game_Creation_Control;

import Geometry.Angle;
import Geometry.Distance;
import Geometry.Point;
import Geometry.Vector;
import Percept.GuardPercepts;
import Percept.IntruderPercepts;
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
        ArrayList<Area> a =  new ArrayList<>();
        a.addAll(MapReader.getSentries());
        addObjects(trans,objectPercepts,ObjectPerceptType.SentryTower,a);

        ObjectPercepts o = new ObjectPercepts(objectPercepts);

        FieldOfView f;
        if(isInShaded(pos)){
            f = new FieldOfView(new Distance(MapReader.getViewRangeGuardShaded()), Angle.fromRadians(MapReader.getViewAngle()));
        }
        else if(isInSentry(pos)) {
            f = new SentryFieldView(new Distance(MapReader.getViewRangeSentry()[0]), new Distance(MapReader.getViewRangeSentry()[1]), Angle.fromRadians(MapReader.getViewAngle()));
        }
        else{
            f = new FieldOfView(new Distance(MapReader.getViewRangeGuardNormal()),Angle.fromRadians(MapReader.getViewAngle()));
        }

        VisionPrecepts vision = new VisionPrecepts(f,o);
        GuardPercepts percepts = new GuardPercepts(vision, sound, smell areaPercepts, SenarioPrecepts);
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
        ArrayList<Area> a =  new ArrayList<>();
        a.addAll(MapReader.getSentries());
        //addObjects(trans,objectPercepts,ObjectPerceptType.SentryTower,a);

        ObjectPercepts o = new ObjectPercepts(objectPercepts);

        FieldOfView f;
        if(isInShaded(pos)){
            f = new FieldOfView(new Distance(MapReader.getViewRangeGuardShaded()), Angle.fromRadians(MapReader.getViewAngle()));
        }
        else{
            f = new FieldOfView(new Distance(MapReader.getViewRangeGuardNormal()),Angle.fromRadians(MapReader.getViewAngle()));
        }


        VisionPrecepts vision = new VisionPrecepts(f,o);
        IntruderPercepts percepts = new IntruderPercepts(vision, sound, smell areaPercepts, SenarioPrecepts);
        return percepts;
    }

    private static boolean isInShaded(Point p){
        boolean ok = false;
        for(Area a : MapReader.getShadeds()){
            if(a.isHit(p)){
                ok =true;
            }
        }
        return ok;
    }

    private static boolean isInSentry(Point p){
        boolean ok = false;
        for(Area a : MapReader.getSentries()){
            if(a.isHit(p)){
                ok =true;
            }
        }
        return ok;
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
