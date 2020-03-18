package Game_Creation_Control;

import Action.Action;

import Geometry.Angle;
import Geometry.Direction;
import Geometry.Distance;
import Geometry.Point;

import java.util.ArrayList;
import Geometry.Vector;

/* This class is meant to do all controllings that needs to be done for the agents. It prevents form GameController being too big.
* */
public class AgentController {

    public static boolean isActionAlwayed(AgentStateHolder h, Action a){

        if(checkObjectCollision(h)) {
            return false;
        }
            //do stuff
        return false;
    }

    private static boolean checkObjectCollision(Point centerForm, Point centerTo, double radius){
        ArrayList<Area> coll = MapReader.getCollisonables();
        Vector translation = new Vector(centerForm,centerTo);

        Vector p1 = translation.get2DPerpendicularVector();
        p1.setLength(radius);
        Vector p2 = p1.getAntiVector();
    }

    public void move(Distance maxDistance, Distance distanceMove){
        if(distanceMove.getValue() <= maxDistance){

            double possibleNextX =  (Math.cos(direction.getRadians()) * distanceMove.getValue()) + position.getX();
            double possibleNextY =  (Math.sin(direction.getRadians()) * distanceMove.getValue()) + position.getY();


        }

    }


}
;