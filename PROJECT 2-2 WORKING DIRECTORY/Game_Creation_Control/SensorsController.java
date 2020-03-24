package Game_Creation_Control;

import Geometry.Point;
import Geometry.Vector;
import Percept.GuardPercepts;
import Percept.IntruderPercepts;
import Percept.Percepts;
import Percept.Vision.FieldOfView;
import Percept.Vision.ObjectPercept;

import java.util.ArrayList;

public class SensorsController {


    public static GuardPercepts getGuardPercepts(AgentStateHolder h){
        Point pos =  h.getPosition();
        Vector trans = new Vector(pos);
        trans.mul(-1);
        ArrayList<ObjectPercept> objectPercepts = new ArrayList<>();
        for(Area a : MapReader.getAllObjects()){
            Area aa = a.cloned();
            aa.translate(trans);
            double stepSize = 0.01;
            for(double i = aa.getLeftBoundary(); i<aa.getRightBoundary(); i+=stepSize ) {
                objectPercepts.add(aa);
            }
            for(double i = aa.getBottomBoundary(); i<aa.getTopBoundary();  i+=stepSize){

            }
        }

        public static IntruderPercepts getIntruderPercepts(AgentStateHolder h){
            Point pos =  h.getPosition();
            Vector trans = new Vector(pos);
            trans.mul(-1);
            ArrayList<ObjectPercept> objectPercepts = new ArrayList<>();
            for(Area a : MapReader.getAllObjects()){
                Area aa = a.cloned();
                aa.translate(trans);
                double stepSize = 0.01;
                for(double i = aa.getLeftBoundary(); i<aa.getRightBoundary(); i+=stepSize ) {
                    objectPercepts.add(aa);
                }
                for(double i = aa.getBottomBoundary(); i<aa.getTopBoundary();  i+=stepSize){

                }
            }

        FieldOfView f = new FieldOfView(h.MapReader.getViewAngle());
        GuardPercepts percepts = new GuardPercepts();
        return percepts;
    }

}
