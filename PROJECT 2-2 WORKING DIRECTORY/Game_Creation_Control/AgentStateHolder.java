package Game_Creation_Control;


import Action.Action;
import Agent.Agent;
import Geometry.Angle;
import Geometry.Direction;
import Geometry.Point;

import java.util.Vector;


public class AgentStateHolder {

    private Point position; //current position of agent
    private Direction direction; // where the agent's is heading to. Might wanna change to vectors later
    private Geometry.Vector directionVector;
    private Angle maxRotationAngleDegrees = MapReader.getMaxRotationAngle();
    private double maxRotationAngleRadians = maxRotationAngleDegrees.getRadians();
    private final double radius = 0.5;
    private Action lastExecutedAction;

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

    public double getMaxRotationAngleRadians(){
        return maxRotationAngleRadians;
    }

    public Action getLastExecutedAction(){
        return lastExecutedAction;
    }


    //Might need a cast
    public void setLastExecutedAction(Action action){
        lastExecutedAction = action;
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

