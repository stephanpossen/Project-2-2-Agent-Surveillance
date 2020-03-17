package Game_Creation_Control;

import Agent.Intruder;
import Geometry.Angle;
import Geometry.Direction;
import Geometry.Point;

import java.util.Vector;

public class IntruderController extends AgentController{
    public IntruderController(Point position, Direction direction, Vector directionVector, Angle maxRotationAngleDegrees) {
        super(position, direction, directionVector, maxRotationAngleDegrees);
    }

    public boolean isYelling(Intruder agent){
        return false;
    }
}
