package Game_Creation_Control;


import Agent.Agent;
import Geometry.Angle;
import Geometry.Direction;
import Geometry.Point;

import java.util.Vector;


public class AgentStateHolder {

    private Point position; //current position of agent
    private Direction direction; // where the agent's is heading to. Might wanna change to vectors later
    private Geometry.Vector directionVector;
    private Angle maxRotationAngleDegrees = Angle.fromDegrees(MapReader.getMaxRotationAngle());
    private Angle maxRotationAngleRadians = Angle.fromRadians(MapReader.getMaxRotationAngle());
    private final double radius = 0.5;

    public AgentStateHolder(Agent a){

    }

    public Direction getDirection() {
        return direction;
    }

    public Point getPosition() {
        return position;
    }

    public Geometry.Vector getDirectionVector() {
        return directionVector;
    }

    public Double getRadius(){
        return radius;
    }

    public Angle getMaxRotationAngleDegrees(){
        return maxRotationAngleDegrees;
    }

    public Angle getMaxRotationAngleRadians(){
        return maxRotationAngleRadians;
    }

    public void setPosition(Point position) {
        this.position = position;
    }


    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void setDirectionVector(Geometry.Vector directionVector) {
        this.directionVector = directionVector;
    }

    public void setRadius(Double newRadius){
        this.radius = newRadius;
    }

    public void setMaxRotationAngleDegrees(Double angle){
        this.maxRotationAngleDegrees = Angle.fromDegrees(angle);
    }

    public void setMaxRotationAngleRadians(Double angle){
        this.maxRotationAngleRadians = Angle.fromRadians(angle);
    }




}

