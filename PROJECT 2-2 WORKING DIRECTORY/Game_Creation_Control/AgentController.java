package Game_Creation_Control;

import Action.Action;

import Agent.Agent;
import Agent.AgentsFactory;
import Geometry.Angle;
import Geometry.Direction;
import Geometry.Distance;
import Geometry.Point;
import Action.*;

import java.util.ArrayList;
import Geometry.Vector;

/* This class is meant to do all controllings that needs to be done for the agents. It prevents form GameController being too big.
* */
public class AgentController {

    private Point position; //current position of agent
    private Direction direction; // where the agent's is heading to. Might wanna change to vectors later
    private Geometry.Vector directionVector;
    private Angle maxRotationAngleDegrees;
    private Angle maxRotationAngleRadians;
    private final double radius = 0.5;


    protected AgentController(){
//        this.direction = state.getDirection();
//        this.position = state.getPosition();
//        this.radius = state.getRadius();
//        this.position = state.getPosition();
//        this.directionVector = state.getDirectionVector();
//        this.maxRotationAngleRadians = state.getMaxRotationAngleRadians();
//        this.maxRotationAngleDegrees = state.getMaxRotationAngleDegrees();
//        this.state = state;
    }

    public static boolean isActionAllowed(AgentStateHolder h, Action a){

//        if(checkObjectCollision(h.getPosition(),a)) {
//            return false;
//        }
//            //do stuff
      return false;
    }

    public static boolean checkObjectCollision(Point centerForm, Point centerTo){
        ArrayList<Area> coll = MapReader.getCollisionableObjects();
        Vector translation = new Vector(centerForm,centerTo);

        Vector p1 = translation.get2DPerpendicularVector();
//        p1.setLength(radius);
        Vector p2 = p1.getAntiVector();
        Vector p3 = translation.add2(p1);
        Vector p4 = translation.add2(p2);
        Area translationArea = new Area(p1.x,p1.y,p2.x,p2.y,p3.x,p3.y,p4.x,p4.y);

        boolean ok = true;

        for(Area a : coll){

        }
        return ok;
    }

    public void move(Move move, AgentStateHolder state){}


}
