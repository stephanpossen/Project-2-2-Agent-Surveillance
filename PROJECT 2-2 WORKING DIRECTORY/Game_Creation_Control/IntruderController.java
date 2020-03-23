package Game_Creation_Control;

import Action.Action;
import Action.Move;
import Agent.Agent;
import Agent.Intruder;
import Geometry.*;
import Geometry.Point;
import Action.*;
import Percept.Smell.SmellPerceptType;

import java.awt.*;
import java.util.ArrayList;

public class IntruderController {

    protected IntruderController(){ }

    protected IntruderController(AgentStateHolder state){
        this.direction = state.getDirection();
        this.position = state.getPosition();
        this.directionVector = state.getDirectionVector();
        this.maxRotationAngleRadians = state.getMaxRotationAngleRadians();
        this.maxRotationAngleDegrees = state.getMaxRotationAngleDegrees();
        this.state = state;
    }

    private double maxDistanceForMove = MapReader.getMaxMoveDistanceIntruder();
    private double maxDistanceForSprint = MapReader.getMaxSprintDistanceIntruder();
    private Point position; //current position of agent
    private Direction direction; // where the agent's is heading to. Might wanna change to vectors later
    private Geometry.Vector directionVector;
    private Angle maxRotationAngleDegrees = MapReader.getMaxRotationAngle();
    private double maxRotationAngleDouble = maxRotationAngleDegrees.getDegrees();
    private Angle maxRotationAngleRadians;
    private final double radius = 0.5;
    private AgentStateHolder state;

    public void updateState(AgentStateHolder state){ // will need to use this method every time we call the IntrudeController !!!!!!!!!
        this.direction = state.getDirection();
        this.position = state.getPosition();
        this.position = state.getPosition();
        this.directionVector = state.getDirectionVector();
        this.maxRotationAngleRadians = state.getMaxRotationAngleRadians();
        this.maxRotationAngleDegrees = state.getMaxRotationAngleDegrees();
        this.state = state;
    }

    public boolean doAction(Action action){ // return true if the action is performed, otherwise it returns false (noAction was done)
        if (action instanceof Move){
            Move m = (Move)action;
            return move(m);
        }

        else if (action instanceof Rotate){
            Rotate r = (Rotate)action;
            return rotate(r);
        }

        else if(action instanceof Sprint){
            Sprint s = (Sprint)action;
            return sprint(s);
        }

        // Intruders can't yell
        else if(action instanceof Yell){
            return false;
        }

        else if (action instanceof NoAction){
            NoAction na = (NoAction)action;
            return noAction();
        }

        else if(action instanceof DropPheromone){
            DropPheromone dp = (DropPheromone)action;
            return dropPheromone(dp);
        }
        return false;
    }


    public boolean move(Move move){ // return true if the move is performed, otherwise it returns false (noAction was done)
    Distance distanceWantedToMove = move.getDistance();

        // TODO The max distance can be modified based on the slowDown parameter in the scenario
        //  maxDistanceForMove = maxDistanceForMove * slowDown ....

        if(distanceWantedToMove.getValue() <= maxDistanceForMove) {
            double possibleNextX = (Math.cos(state.getDirection().getRadians()) * distanceWantedToMove.getValue())+ state.getPosition().getX();
            double possibleNextY = (Math.sin(state.getDirection().getRadians()) * distanceWantedToMove.getValue()) + state.getPosition().getY();
            Point pointWantedToMove = new Point(possibleNextX, possibleNextY);

            if (!checkObjectCollision(position, pointWantedToMove)){
                state.setPosition(pointWantedToMove);
                // TODO createSound();
                return true;
            }
            else {
                noAction();
                return false;
            }

        }
        else{
            noAction();
            return false;
        }
    }



    public boolean sprint(Sprint sprint){ // return true if the move is performed, otherwise it returns false (noAction was done)
        Distance distanceWantedToSprint = sprint.getDistance();

        // TODO The max distance can be modified based on the slowDown parameter in the scenario
        //  maxDistanceForSprint = maxDistanceForSprint * slowDown ....

        if(distanceWantedToSprint.getValue() <= maxDistanceForSprint) {
            double possibleNextX = (Math.cos(state.getDirection().getRadians()) * distanceWantedToSprint.getValue())+ state.getPosition().getX();
            double possibleNextY = (Math.sin(state.getDirection().getRadians()) * distanceWantedToSprint.getValue()) + state.getPosition().getY();
            Point pointWantedToSprint = new Point(possibleNextX, possibleNextY);

            if (!checkObjectCollision(position, pointWantedToSprint)){
                state.setPosition(pointWantedToSprint);
                // TODO createSound();
                // TODO cooldown (Scenario specifies the cooldown period (number of turns))
                return true;
            }
            else {
                noAction();
                return false;
            }

        }
        else{
            noAction();
            return false;
        }
    }


    // To check if it works

    public boolean rotate(Rotate rotate){
      double angleInDouble = rotate.getAngle().getDegrees();
        if(angleInDouble <= maxRotationAngleDouble){
            double directionInDegrees = direction.getDegrees();
            double newDirectionInDegrees = directionInDegrees + angleInDouble;
            state.setDirection(Direction.fromDegrees(newDirectionInDegrees));
        )
            return true;
        }
        else {
            return false;
        }
    }



    public static boolean checkObjectCollision(Point centerForm, Point centerTo){
        // TODO for loop to check all the collisonable areas
        ArrayList<Area> coll = MapReader.getCollisionableObjects();
        Geometry.Vector translation = new Geometry.Vector(centerForm,centerTo);

        Geometry.Vector p1 = translation.get2DPerpendicularVector();
        p1.setLength(radius);
        Vector p2 = p1.getAntiVector();
    }



    public boolean dropPheromone(DropPheromone pheromone){
        // SmellPerceptType type = pheromone.getType();
        //TODO
    }



    public boolean noAction(){
        return true;
    }
}
