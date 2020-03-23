package Game_Creation_Control;


import Action.Action;
import Agent.Agent;
import Geometry.Angle;
import Geometry.Direction;
import Geometry.Point;

import java.util.Vector;

/*
* This class is meant to gather all the information (the state) of a single agent
* it is mainly composed of getters and setters
* */

public class AgentStateHolder {

    private Point position; //current position of agent
    private Direction direction; // where the agent's is heading to. Might wanna change to vectors later
    private Geometry.Vector directionVector; // same then above but on a vector form
    private static double maxRotationAngleDegrees;
    private static Angle maxRotationAngle;
    private static double maxRotationAngleRadians;
    private final double radius = 0.5; //size of the agent (to be used for the gui)
    private Action lastExecutedAction;
    private Agent agent;

    public AgentStateHolder(Agent a){
      this.agent = a;
    }

    public Direction getDirection() {
        return direction;
    }

    public Point getPosition() {
        return position;
    }

    public Geometry.Vector getDirectionVector() {
        directionVector = new Geometry.Vector(Math.sin(direction.getRadians()),(double)Math.cos(direction.getRadians()));
        return directionVector;
    }

    public Double getRadius(){
        return radius;
    }

    public double getMaxRotationAngleDegrees(){
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

    public static void setMaxRotationAngle(Angle angle){
        maxRotationAngle = angle ;
        maxRotationAngleRadians = angle.getRadians();
        maxRotationAngleDegrees = angle.getDegrees();
    }

    public Angle getMaxRotationAngle() {
        return maxRotationAngle;
    }

    public Agent getAgent() {
        return agent;
    }
}

