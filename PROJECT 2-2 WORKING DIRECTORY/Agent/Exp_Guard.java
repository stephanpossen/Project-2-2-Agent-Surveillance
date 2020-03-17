
package Agent;
import Action.GuardAction;

import Geometry.Angle;
import Action.GuardAction;
import Action.Move;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Panel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;

import Action.*;
import Geometry.*;
import Percept.*;
import Agent.*;
import Percept.GuardPercepts;
import Percept.Scenario.*;
import Percept.Vision.*;
import Percept.Scenario.*;
import Percept.Smell.*;
import Percept.Sound.*;
import Percept.GuardPercepts;

/**
 * 
 * @author luotianchen
 * In phase one, the main task for the agent is exploring the map and record the information of the map
 * So, my idea is using grid-based (with scale = 10 )Map in the first phase.
 * 
 * 
 * The advantages are : 1: Easier for agent to store the information and update.
 * 						2: In our project, there are only few objects, which is not a very complicated world, Grid-based Map is enough to store information.
 * 						3: Easier to implement and maintain
 * 						4: Since all the walls are either vertical or horizontal, matrix is enough to store all the information
 * 
 * Disadvantages:       1:Less accuracy
 * 						2:Need more space 
 * 						3:Agents are less sensitive to the shape of objects, but in project, all objects are either rectangle or circle. Not like real world.
 * 
 * use prime numbers to represents the objects (easier for future calculation) :
 * 0:  unknown place
 * 13: wall
 * 23: teleport 
 * 37: window
 * 47: door
 * 59: sentry tower
 * -1: empty space
 * 67: target place.
 * 79: shaded area
 * 83: intruder
 * 97: guard
 * 101: itself
 */

public class Exp_Guard implements Guard{
	//with range = 6 
	final public double viewingWidth = 2.296*2;
	
	//with range = 6
	final public double viewingLength = 6;
	
	//state of agent.
	protected double[][] stateSituation;
	
	public int mapWidth = 80;
	
	public int mapLength = 120;
	
	final public int scale = 10;
	
	// agent's initial coordinate
	public int initialY = (mapWidth/2)*scale;
	
	public int initialX = (mapLength/2)*scale;
	
	//agent self location
	private double x;
	
	private double y;
	
	final public double unknownPlace = 0;
	
	final public double wall = 13;
	
	final public double teleport = 23;
	
	final public double window = 37;
	
	final public double door = 47;
	
	final public double sentryTower = 59;
	
	final public double emptySpace = -1;
	
	//even not useful for guard agent
	final public double targetPlace = 67;
	
	final public double shadedArea = 79;
	
	final public double intruder = 83;
	
	final public double guard = 97;
	
	final public double itself = 101;
	
	//sequence of action executed
	private int actionSequence = 1;

	//current rotate angle compare to the initial state -- degree
	private double rotateAngle = 0;

	//check if there is a wall needed to explore completely
	public boolean wallNeedExplore = false;

	//check if there is a door needed to explore completely
	public boolean doorNeedExplore = false;

	//check if there is a window needed to explore completely
	public boolean windowNeedExplore = false;

	//check if there is a shadedArea needed to explore completely
	public boolean shadeAreaNeedExplore = false;

	//check if there is a sentryTower needed to explore completely
	public boolean sentryTowerNeedExplore = false;

	//check if there is a teleport needed to explore completely
	public boolean teleportNeedExplore = false;
	
	public static void main(String[] args) {
		
		
	}

	//constructor
	public Exp_Guard() {
		
		stateSituation = new double[mapWidth*10][mapLength*10];
		
		stateSituation[initialY][initialX] = itself;
		
		setX(initialX);
		
		setY(initialY);
	}

	public int[] coordinateBasedOnInitialPoint(double x, double y) {
		int[] xy = new int[2];

		int previousX = (int) (x*Math.cos(Math.toRadians(getRotateAngle())) + y*Math.sin(Math.toRadians(getRotateAngle())));

		int previousY = (int)(y*Math.cos(Math.toRadians(getRotateAngle())) - x*Math.sin(Math.toRadians(getRotateAngle())));



		return xy;

	}


	/**
	 * update the Map after executing an action
	 * @param
	 * @param action
	 * @return a new map being updated
	 */
	public void updateGridMap( GuardAction action, GuardPercepts percepts){
		
		//currently, the explore agent only need to execute move or rotate
		
		//all the objects in vision
		Set<ObjectPercept> objectPercepts = percepts.getVision().getObjects().getAll();
		
		Iterator<ObjectPercept> iterator = objectPercepts.iterator();
		
		while(iterator.hasNext()) {
			
			ObjectPerceptType type = iterator.next().getType();
			
			//get it relative location, where the agent location is (0,0)
			double objectX = iterator.next().getPoint().getX();
			
			double objectY = iterator.next().getPoint().getY();
			
			
			//if statement to update matrix
//			if(type.equals(ObjectPerceptType.Wall)) {
//				stateSituation[(int)(getY() - objectY)][(int)(getX()+objectX)] = wall;
//			}
			
			//according to the type of object to update the map
			switch (type) {
			case Wall:
				if(stateSituation[(int)(getY() - objectY)][(int)(getX()+objectX)] == unknownPlace) stateSituation[(int)(getY() - objectY)][(int)(getX()+objectX)] = wall;
				break;
				
			case Door  :
				if(stateSituation[(int)(getY() - objectY)][(int)(getX()+objectX)] == unknownPlace) stateSituation[(int)(getY() - objectY)][(int)(getX()+objectX)] = door;
				break;
				
			case Window  :
				if(stateSituation[(int)(getY() - objectY)][(int)(getX()+objectX)] == unknownPlace) stateSituation[(int)(getY() - objectY)][(int)(getX()+objectX)] = window;
				break;
				
			case Teleport:
				if(stateSituation[(int)(getY() - objectY)][(int)(getX()+objectX)] == unknownPlace) stateSituation[(int)(getY() - objectY)][(int)(getX()+objectX)] = teleport;
				break;
				
			case SentryTower:
				if(stateSituation[(int)(getY() - objectY)][(int)(getX()+objectX)] == unknownPlace) stateSituation[(int)(getY() - objectY)][(int)(getX()+objectX)] = sentryTower;
				break;
				
			case EmptySpace:
				if(stateSituation[(int)(getY() - objectY)][(int)(getX()+objectX)] == unknownPlace) stateSituation[(int)(getY() - objectY)][(int)(getX()+objectX)] = emptySpace;
				break;
				
			case ShadedArea:
				if(stateSituation[(int)(getY() - objectY)][(int)(getX()+objectX)] == unknownPlace) stateSituation[(int)(getY() - objectY)][(int)(getX()+objectX)] = shadedArea;
				break;
				
			case Guard:
				if(stateSituation[(int)(getY() - objectY)][(int)(getX()+objectX)] == unknownPlace) stateSituation[(int)(getY() - objectY)][(int)(getX()+objectX)] = guard;
				break;
				
			case Intruder:
				if(stateSituation[(int)(getY() - objectY)][(int)(getX()+objectX)] == unknownPlace) stateSituation[(int)(getY() - objectY)][(int)(getX()+objectX)] = intruder;
				break;
			case TargetArea:
				break;
			
			}
		}
		

	}
	
	
	/**
	 * Based on the current map situation, change the size of the map in order to achieve a better map.
	 * @param lastSituation
	 * @return a map after changed by a proper size.
	 */
	public double[][] changeGridMapSize(double[][] lastSituation){
		double[][] newState = lastSituation.clone();
	
		return newState;
	}
	
	/**
	 * find the location of the agent.
	 * @param state
	 * @return the x-y coordinate of the agent
	 */
	public int[] findSelfLocation(double[][] state) {
		int[] location = new int[2];

		for (int i = 0; i < state.length; i++) {
			for(int j = 0;j<state[0].length;j++) {
				if(state[i][j] == itself) {
					location[0] = i;
					location[1] = j;
				}
			}
		}
		
		return location;
	
	}

	@java.lang.Override
	public GuardAction getAction(GuardPercepts percepts) {
		//First, acquire all the information from this turn.
		
		//move distance
		Distance maxMoveDistance = new Distance(1.4);
		
		
		// Area information
		boolean isInDoor = percepts.getAreaPercepts().isInDoor();
		boolean isInSentryTower = percepts.getAreaPercepts().isInSentryTower();
		boolean isInWindow = percepts.getAreaPercepts().isInWindow();
		boolean isJustTeleported = percepts.getAreaPercepts().isJustTeleported();
		
		//get all the Objects with types and localization.
		Set<ObjectPercept> objectPercepts = percepts.getVision().getObjects().getAll();
		
		
		
		//get view range
		Distance viewRange = percepts.getVision().getFieldOfView().getRange();
		
		//get view angle 
		Angle viewAngle = percepts.getVision().getFieldOfView().getViewAngle();
		
		
		//get all smell with types
		percepts.getSmells().getAll();
		
		//get all sound with types
		percepts.getSounds().getAll();
		
		//the max distance that the agent can move every turn.
		percepts.getScenarioGuardPercepts().getMaxMoveDistanceGuard();
		
		//Get data from the scenrio
		Distance captureDistance = percepts.getScenarioGuardPercepts().getScenarioPercepts().getCaptureDistance();
		GameMode gameMode = percepts.getScenarioGuardPercepts().getScenarioPercepts().getGameMode();
		Angle maxRotationAngle = percepts.getScenarioGuardPercepts().getScenarioPercepts().getMaxRotationAngle();
		double inDoorSlowDownModifier = percepts.getScenarioGuardPercepts().getScenarioPercepts().getSlowDownModifiers().getInDoor();
		double inWindowSlowDownModifier = percepts.getScenarioGuardPercepts().getScenarioPercepts().getSlowDownModifiers().getInWindow();
		double inSenreyTowerSlowDownModifier = percepts.getScenarioGuardPercepts().getScenarioPercepts().getSlowDownModifiers().getInSentryTower();
		Distance radiusPheromone = percepts.getScenarioGuardPercepts().getScenarioPercepts().getRadiusPheromone();
		int pheromoneCoolDown = percepts.getScenarioGuardPercepts().getScenarioPercepts().getPheromoneCooldown();
		
		Iterator<ObjectPercept> iterator = objectPercepts.iterator();


		boolean isSafe = true;
		//if everything in the front is empty, then just move forward.
		while(iterator.hasNext()) {
			if (!iterator.next().getType().equals(ObjectPerceptType.EmptySpace)) {
				isSafe = false;
			}
		}


		if(isSafe) return new Move(maxMoveDistance);

		//if everything in the front is empty, then just move forward.
		if (iterator.next().getType().equals(ObjectPerceptType.EmptySpace)) {
			return new Move(maxMoveDistance);
		}


		//if it is not possible, then what it is in front of the agent, if it is a wall, then explore the wall completely
		// in order to explore the wall, first get close to the wall at distance within viewing width. so, the first thing
		//to do is calculate the distance

		LinkedList<Distance> distanceWallList = new LinkedList<Distance>();

		while(iterator.hasNext()) {
			//if there is wall, calculate the distance and collect all the distance together.
			if (iterator.next().getType().equals(ObjectPerceptType.Wall)) {
				wallNeedExplore = true;
				Distance distance = iterator.next().getPoint().getDistanceFromOrigin();
				distanceWallList.add(distance);
			}

		}

		int sizeOfList = distanceWallList.size();




		//action to move into teleport


		//action to rotate is based on some situation: 1:





		
		
		return new Move(maxMoveDistance);
	}
	
	
	//------- getter and setter -------------------
	public void setX(double value) {
		x = value;
	}
	
	public void setY(double value) {
		y = value;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public int getSequence() {
		return actionSequence;
	}
	
	public void addSequence() {
		actionSequence = actionSequence + 1;
	}
	
	public void setSequence(int value) {
		actionSequence = value;
	}

	public void setRotateAngle(double angle) {
		rotateAngle = angle;
	}

	public double getRotateAngle() {
		return rotateAngle;
	}


}


class Test{
	
	
	
	
	
	
}
