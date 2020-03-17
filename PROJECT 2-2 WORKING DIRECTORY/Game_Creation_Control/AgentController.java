package Game_Creation_Control;


import Geometry.Angle;
import Geometry.Direction;
import Geometry.Distance;
import Geometry.Point;

import java.util.Vector;

public class AgentController {

    private Point position; //current position of agent
    private Direction direction; // where the agent's is heading to. Might wanna change to vectors later
    private Vector directionVector;
    private Angle maxRotationAngleDegrees;
    private Angle maxRotationAngleRadians;
    private double radius = 0.5;

    public AgentController(Point position, Direction direction, Vector directionVector, Angle maxRotationAngleDegrees){
        this.position = position;
        this.direction = direction;
        this.directionVector = directionVector;
        this.maxRotationAngleDegrees = maxRotationAngleDegrees;
    }

    public Direction getDirection() {
        return direction;
    }

    public Point getPosition() {
        return position;
    }

    public Vector getDirectionVector() {
        return directionVector;
    }

    public void move(Distance maxDistance, Distance distanceMove){
        if(distanceMove.getValue() <= maxDistance){

            double possibleNextX =  (Math.cos(direction.getRadians()) * distanceMove.getValue()) + position.getX();
            double possibleNextY =  (Math.sin(direction.getRadians()) * distanceMove.getValue()) + position.getY();


        }

    }

}

