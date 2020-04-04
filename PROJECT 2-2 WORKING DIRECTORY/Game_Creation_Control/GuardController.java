package Game_Creation_Control;

import Action.Action;
import Geometry.*;
import Agent.*;
import Action.*;
import Percept.Sound.SoundPercept;
import Percept.Sound.SoundPerceptType;


import java.util.ArrayList;

public class GuardController {


    private static Angle maxRotationAngleDegrees = MapReader.getMaxRotationAngle();
    private static double maxRotationAngleDouble = maxRotationAngleDegrees.getDegrees();
    private static Angle maxRotationAngleRadians = Angle.fromRadians(maxRotationAngleDegrees.getRadians());
    private final static double radius = 0.5;
    private static double maxDistanceForMove = MapReader.getMaxMoveDistanceGuard();

    public static void doAction(Action action, AgentStateHolder state){ //if the action is performed, perform it, otherwise sets no action

        if (action instanceof Move){
            Move m = (Move)action;
            move(m,state);
        }

        else if (action instanceof Rotate){
            Rotate r = (Rotate)action;
            rotate(r,state);
        }

        // Guards can't sprint
        else if(action instanceof Sprint){
            //return false;
        }

        else if(action instanceof Yell){
            Yell y = (Yell)action;
            yell(y,state);
        }

        else if (action instanceof NoAction){
            NoAction na = (NoAction)action;
            noAction(state);
        }

        else if(action instanceof DropPheromone){
            DropPheromone dp = (DropPheromone)action;
            dropPheromone(dp,state);
        }
    }


    public static void move(Move move, AgentStateHolder state){ // if the action move is alwayed to be performed, perform it otherwise just set noAction
        Distance distanceWantedToMove = move.getDistance();

        // TODO The max distance can be modified based on the slowDown parameter in the scenario
        //  maxDistanceForMove = maxDistanceForMove * slowDown ....

        if(distanceWantedToMove.getValue() <= maxDistanceForMove) {
            double possibleNextX = (Math.cos(state.getDirection().getRadians()) * distanceWantedToMove.getValue())+ state.getPosition().getX();
            double possibleNextY = (Math.sin(state.getDirection().getRadians()) * distanceWantedToMove.getValue()) + state.getPosition().getY();
            Point pointWantedToMove = new Point(possibleNextX, possibleNextY);

            if (!checkObjectCollision(state.getPosition(), pointWantedToMove)){
                state.setPosition(pointWantedToMove);
                state.setLastExecutedAction(move);
                // TODO createSound();
            }
            else {
                noAction(state);
            }

        }
        else{
            noAction(state);
        }
    }



    // To do check if it works
    public static void rotate(Rotate rotate, AgentStateHolder state){
        double angleInDouble = rotate.getAngle().getDegrees();
        if(angleInDouble <= maxRotationAngleDouble){
            double directionInDegrees = state.getDirection().getDegrees();
            double newDirectionInDegrees = directionInDegrees + angleInDouble;
            state.setDirection(Direction.fromDegrees(newDirectionInDegrees));
            state.setLastExecutedAction(rotate);
        }
        else {
            noAction(state);
        }
    }


    public static boolean checkObjectCollision(Point centerForm, Point centerTo){
        Geometry.Vector translation = new Geometry.Vector(centerForm,centerTo);

        Geometry.Vector p1 = translation.get2DPerpendicularVector();
        p1.setLength(radius);
        Geometry.Vector p2 = p1.getAntiVector();
        Vector p3 = translation.add2(p1);
        Vector p4 = translation.add2(p2);

        Area transArea = new Area(p1.x,p1.y,p2.x,p2.y,p3.x,p3.y,p4.x,p4.y);

        boolean ok = false;
        for(Area collisionable : MapReader.getCollisionableObjects()){
            if(transArea.isHit(collisionable)){
                ok = true;
            }
        }
        return ok;
    }



    public static void dropPheromone(DropPheromone pheromone, AgentStateHolder state){
        // SmellPerceptType type = pheromone.getType();
        boolean pheromonedropalawed = true;
        if(pheromonedropalawed) {
            state.setLastExecutedAction(pheromone);
        }
        //TODO immplement this method
    }



    public static void noAction(AgentStateHolder state){
        state.setLastExecutedAction(new NoAction());
    }

    public static void yell(Yell yell, AgentStateHolder state){
        // TODO Create the sound (with the percept etc)
        SoundPercept soundYell = new SoundPercept(SoundPerceptType.Yell, state.getDirection());

    }
}