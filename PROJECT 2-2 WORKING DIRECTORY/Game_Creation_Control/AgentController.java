package Game_Creation_Control;

import Action.Action;

import Geometry.Angle;
import Geometry.Direction;
import Geometry.Distance;
import Geometry.Point;

import java.util.Vector;

public class AgentController {

    public static boolean isActionAlwayed(AgentStateHolder h, Action a){

        if(checkWallCollision(h)){
            return false
        }
            //do stuff
        return false;
    }

    private static boolean checkWallCollision(AgentStateHolder h){
        return true;
    }
   
    public void move(Distance maxDistance, Distance distanceMove){
        if(distanceMove.getValue() <= maxDistance){

            double possibleNextX =  (Math.cos(direction.getRadians()) * distanceMove.getValue()) + position.getX();
            double possibleNextY =  (Math.sin(direction.getRadians()) * distanceMove.getValue()) + position.getY();


        }

    }
}
