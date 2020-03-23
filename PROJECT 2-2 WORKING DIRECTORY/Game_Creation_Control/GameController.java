package Game_Creation_Control;

import Action.*;
import Agent.Agent;
import Agent.Guard;
import Geometry.Point;
import Agent.AgentsFactory;
import Percept.GuardPercepts;
import Percept.IntruderPercepts;
import Percept.Percepts;
import Percept.Scenario.GameMode;

import java.util.ArrayList;

public class GameController {
    private MapReader map ;
    private int turn=0;
    private GameMode gameMode;

    boolean GameIsDone = false;

    private int agentsNb ; //number of agents

    public GameController(String mapFile) {
      map = new MapReader(mapFile);
      gameControllerSetup();
    }

    private void gameControllerSetup(){
        AgentsFactory.buildFactory(map.getNumGuards(),map.getNumIntruders());
        gameMode = map.getGameMode();
    }
    //Reads in the mapfile and sets up the game by initializing the guards and intruders
    //Used once in GameLauncher class
    public void setup() {
        map.readMap();
        map.spawnGuards();
        map.spawnIntruders();
    }
    //After everything is setup, this starts the game by initializing the while loop
    //Only stops whenever the time runs out, or winning conditions are met, checked every iteration
    public void start() {
        int turn = 0;
        while (true) {
            while (GameIsDone = false){
                for(int i = 1; i <= agentsNb; i++ ){ //get the action request from all agents (in correct order - specified in agents' factory)
                    AgentStateHolder holder = AgentsFactory.getStateHolder(i);
                    //check if it is a guard
                    if(i<AgentsFactory.getNumGuards()){
                        GuardController g = new GuardController();
                        Action a = holder.getAgent().getAction(new GuardPercepts());
                        if(!g.doAction(a)) {
                            a = new NoAction();
                        }
                        holder.setLastExecutedAction(a);
                        updateWorldState(a,holder);

                    }

                     else{
                        IntruderController intrud = new IntruderController();
                        Action a = holder.getAgent().getAction(new IntruderPercepts());
                        if(!intrud.doAction(a)) {
                            a = new NoAction();
                        }
                        holder.setLastExecutedAction(a);
                         updateWorldState(a,holder);

                    }

                }
            }

            handleActionRequest();
            checkWinConditions();
        }
    }

    public void checkWinConditions() {

    }

    //Receives move request from any agent
    //Returns the action if action is considered valid
    //Returns a NoAction if considered invalid
    public boolean handleActionRequest(Point position,Point possibleNextPosition) {
        ArrayList<Area> walls = map.getWalls();

        for(int i = 0; i < walls.size(); i++){

        }

    }

    public void updateWorldState(Action action, AgentStateHolder holder){
        for(int i = 0; i<AgentsFactory.getNumAgents(); i++){

            if (action instanceof Move){
                Move m = (Move)action;
               holder.setPosition();
            }

            else if (action instanceof Rotate){
                Rotate r = (Rotate)action;
                rotate(r);
            }

            // Guards can't sprint
            else if(action instanceof Sprint){

            }

            else if(action instanceof Yell){
                Yell y = (Yell)action;

            }

            else if (action instanceof NoAction){
                NoAction na = (NoAction)action;

            }

            else if(action instanceof DropPheromone){
                DropPheromone dp = (DropPheromone)action;
            }
            return false;
        }

    }
}
