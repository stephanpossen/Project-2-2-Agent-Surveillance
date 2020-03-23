package Game_Creation_Control;

import Action.Action;
import Agent.Agent;
import Geometry.Point;
import Agent.AgentsFactory;
import Percept.Percepts;
import Percept.Scenario.GameMode;
import java.util.ArrayList;


public class GameController {

    private MapReader map ;
    private int turn = 0;
    private GameMode gameMode;
    boolean GameIsDone = false;

    AgentsFactory factory; //to create a factory and get the order of the execution of agents
    ArrayList listOfAgents;
    private int numberOfAgents ; //number of agents
    private int numGuards;
    private int numIntruders;
    private ArrayList<AgentStateHolder> states;

    public GameController(String mapFile) {
      map = new MapReader(mapFile);
      gameControllerSetup();
    }

    private void gameControllerSetup(){
        factory = new AgentsFactory(MapReader.getNumGuards(), MapReader.getNumIntruders()); // Initialize the intruders and guards
        numberOfAgents = AgentsFactory.getNumAgents();
        gameMode = MapReader.getGameMode();
        states = AgentsFactory.getAgentsStates();
    }

    //Reads in the mapfile and sets up the game by initializing the guards and intruders
    //Used once in GameLauncher class
    public void setup() {
        map.readMap();
        MapReader.spawnGuards(); // Place the guards on the map
        MapReader.spawnIntruders(); // Place the intruders on the map
    }

    //After everything is setup, this starts the game by initializing the while loop
    //Only stops whenever the time runs out, or winning conditions are met, checked every iteration
    public void start() {

        int turn = 0; // Keeps track of which turn we're in

            while (GameIsDone = false){
                for(int i = 1; i <= numberOfAgents; i++ ){ //get the action request from all agents (in correct order - specified in agents' factory)
                    if (n = 0){




                  //  if(AgentController.isActionAllowed(factory.getStateHolder(i), factory.getAgent(i).getAction(Percepts precept)){

                        doAction(...);
                        updateWorldState(listOfAgents.get(i));
                        // TODO agentStateHolder.setLastExecutedAction()
                    }
                     else{
                         doAction(Action.NoAction);
                         updateWorldState(listOfAgents.get(i));

                    }

                }

            handleActionRequest();
            checkWinConditions();
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
