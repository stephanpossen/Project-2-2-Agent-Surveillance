package Game_Creation_Control;

import Agent.Intruder;
import Geometry.Angle;
import Geometry.Distance;
import Geometry.Point;
import Geometry.Vector;
import Percept.GuardPercepts;
import Percept.IntruderPercepts;
import Percept.Percepts;
import Percept.Vision.FieldOfView;
import Percept.Vision.ObjectPercept;
import Percept.Vision.ObjectPerceptType;

import java.util.ArrayList;

public class SensorsController {


    public static GuardPercepts getGuardPercepts(AgentStateHolder h){
        Point pos =  h.getPosition();
        Vector trans = new Vector(pos);
        trans.mul(-1);
        ArrayList<ObjectPercept> objectPercepts = new ArrayList<>();

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

        FieldOfView f;
        if(isInShaded(pos)){
            f = new FieldOfView(new Distance(MapReader.getViewRangeGuardShaded()), Angle.fromRadians(MapReader.getViewAngle()));
        }
        else{
            f = new FieldOfView(new Distance(MapReader.getViewRangeGuardNormal()),Angle.fromRadians(MapReader.getViewAngle()));
        }
        Vision
        GuardPercepts percepts = new GuardPercepts();
        return percepts;
    }


    public static IntruderPercepts getIntruderPercepts(AgentStateHolder h){

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

    private static void addObjects(Vector trans, ArrayList<ObjectPercept> objectPercepts, ObjectPerceptType type, ArrayList<Area> objects){
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
