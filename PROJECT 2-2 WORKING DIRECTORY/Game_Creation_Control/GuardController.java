package Game_Creation_Control;

import Action.Action;
import Geometry.Angle;
import Geometry.Direction;
import Geometry.Distance;
import Geometry.Point;
import Agent.*;
import Action.*;


import java.util.ArrayList;
import java.util.Vector;

public class GuardController {

    private double maxDistanceForMove = MapReader.getMaxMoveDistanceGuard();
    private Point position; //current position of agent
    private Direction direction; // where the agent's is heading to. Might wanna change to vectors later
    private Geometry.Vector directionVector;
    private Angle maxRotationAngleDegrees = MapReader.getMaxRotationAngle();
    private double maxRotationAngleDouble = maxRotationAngleDegrees.getDegrees();
    private Angle maxRotationAngleRadians;
    private final static double radius = 0.5;
    private AgentStateHolder state;

    protected GuardController() { }

    protected GuardController(AgentStateHolder state){
        /*this.direction = state.getDirection();
        this.position = state.getPosition();
        this.directionVector = state.getDirectionVector();
        this.maxRotationAngleRadians = Angle.fromRadians(state.getMaxRotationAngleRadians());
        this.maxRotationAngleDegrees = state.getMaxRotationAngleDegrees();
        this.state = state;*/
        updateState(state);
    }



        public void updateState(AgentStateHolder state){
        // will need to use this method every time we call the GuardController !!!!!!!!!
            // Or directly access every variables of the tate by calling the agentstateholder all the time and updating the state by the gamecontroller
            this.direction = state.getDirection();
            this.position = state.getPosition();
            this.position = state.getPosition();
            this.directionVector = state.getDirectionVector();
            this.maxRotationAngleRadians = Angle.fromRadians(state.getMaxRotationAngleRadians());
            this.maxRotationAngleDegrees = Angle.fromDegrees(state.getMaxRotationAngleDegrees());
            this.state = state;
        }


        public boolean doAction(Action action, AgentStateHolder state){ // return true if the action is performed, otherwise it returns false (noAction was done)

           updateState(state);
            if (action instanceof Move){
                Move m = (Move)action;
                return move(m);
            }

            else if (action instanceof Rotate){
                Rotate r = (Rotate)action;
                return rotate(r);
            }

            // Guards can't sprint
            else if(action instanceof Sprint){
                return false;
            }

            else if(action instanceof Yell){
                Yell y = (Yell)action;
                return yell(y);
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



        // To do check if it works
        public boolean rotate(Rotate rotate){
            double angleInDouble = rotate.getAngle().getDegrees();
            if(angleInDouble <= maxRotationAngleDouble){
                double directionInDegrees = direction.getDegrees();
                double newDirectionInDegrees = directionInDegrees + angleInDouble;
                state.setDirection(Direction.fromDegrees(newDirectionInDegrees));
                return true;
            }
            else {
                return false;
            }
        }


        // TODO finish this method
        public boolean checkObjectCollision(Point centerForm, Point centerTo){
            // TODO for loop to check all the collisonable areas
            ArrayList<Area> coll = MapReader.getCollisionableObjects();
            Geometry.Vector translation = new Geometry.Vector(centerForm,centerTo);

            Geometry.Vector p1 = translation.get2DPerpendicularVector();
            p1.setLength(radius);
            Geometry.Vector p2 = p1.getAntiVector();
        }



        public boolean dropPheromone(DropPheromone pheromone){
            // SmellPerceptType type = pheromone.getType();
            //TODO immplement this method
            return true;
        }



        public boolean noAction(){
            return true;
        }

        public boolean yell(Yell yell){
            // TODO Create the sound (with the percept etc)
            return true;
        }
    }

