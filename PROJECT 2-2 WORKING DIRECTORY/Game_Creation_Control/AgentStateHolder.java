package Game_Creation_Control;


import Agent.Agent;
import Geometry.Angle;
import Geometry.Direction;
import Geometry.Point;

import java.util.Vector;

public class AgentStateHolder {

    private Point position; //current position of agent
    private Direction direction; // where the agent's is heading to. Might wanna change to vectors later
    private Vector directionVector;
    private Angle maxRotationAngleDegrees;
    private Angle maxRotationAngleRadians;
    private double radius = 0.5;

    public AgentStateHolder(Agent a){

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

    public void move(double distance){ }

    public void setPosition(Point position) {
        this.position = position;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void setDirectionVector(Vector directionVector) {
        this.directionVector = directionVector;
    }

}

