package Game_Creation_Control;

import Action.Action;
import Geometry.Point;
import Agent.AgentsFactory;
import Percept.Percepts;
import Percept.Scenario.GameMode;

import java.util.ArrayList;

public class GameController {
    private MapReader map ;
    private int turn=0;
    private GameMode gameMode;

    boolean GameIsDone = false;

    AgentsFactory factory ; //to create a factory and get the order of the execution of agents
    ArrayList listOfAgents; //to create a factory and get the order of the execution of agents
    private int agentsNb ; //number of agents

    public GameController(String mapFile) {
      map = new MapReader(mapFile);
      gameControllerSetup();
    }

    private void gameControllerSetup(){
        factory = new AgentsFactory(map.getNumGuards(),map.getNumIntruders());
        agentsNb = factory.getNumAgents();
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

                    if(AgentController.isActionAllowed(factory.getStateHolder(i), factory.getAgent(i).getAction(Percepts precept)){
                        doAction(...);
                        updateWorldState(listOfAgents.get(i));
                    }
                     else{
                         doAction(Action.NoAction);
                         updateWorldState(listOfAgents.get(i));

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

    public void updateWorldState(){
    }

    }
}
