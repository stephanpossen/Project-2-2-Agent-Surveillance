package Game_Creation_Control;

import Geometry.Angle;
import Geometry.Direction;
import Geometry.Point;
import Agent.*;


import java.util.Vector;

public class GuardController extends AgentController{
    public GuardController(Point position, Direction direction, Vector directionVector, Angle maxRotationAngleDegrees) {
        super(position, direction, directionVector, maxRotationAngleDegrees);
    }

    public boolean isSprinting(Guard agent){
        return false;
    }
}
