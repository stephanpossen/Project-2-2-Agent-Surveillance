package Game_Creation_Control;


import Geometry.Angle;
import Geometry.Direction;
import Geometry.Point;

import java.util.Vector;

public class AgentController {

    private Point position; //current position of agent
    private Direction direction; // where the agent's is heading to. Might wanna change to vectors later
    private Angle maxRotationAngleDegrees;
    private Angle maxRotationAngleRadians;
    private double radius = 0.5;

    public AgentController(Point position, Direction direction, Angle maxRotationAngleDegrees){
        this.position = position;
        this.direction = direction;
        this.maxRotationAngleDegrees = maxRotationAngleDegrees;
    }


}

