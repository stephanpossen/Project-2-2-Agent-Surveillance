package Game_Creation_Control;

import Action.Action;
import Percept.Percepts;
import Percept.Scenario.GameMode;

import java.util.ArrayList;

public class GameController {
    /*private String mapFile;
    private MapReader mapReader;

    private GameMode gameMode= mapFile.getGameMode();

    boolean GameIsDone = false;

    AgentsFactory factory = new AgentsFactory(); //to create a factory and get the order of the execution of agents
    ArrayList listOfAgents = factory.getListOfAgents(); //to create a factory and get the order of the execution of agents
    int numberOfAgents = listOfAgents.size(); //number of agents

    public GameController(String mapFile) {
        this.mapFile = mapFile;
    }

    //Reads in the mapfile and sets up the game by initializing the guards and intruders
    //Used once in GameLauncher class
    public void setup() {
        mapReader.readMap();
        mapReader.spawnGuards();
        mapReader.spawnIntruders();
    }
    //After everything is setup, this starts the game by initializing the while loop
    //Only stops whenever the time runs out, or winning conditions are met, checked every iteration
    public void start() {
        int turn = 0;
        while (true) {
            while (GameIsDone = false){
                for(int i = 1; i <= numberOfAgents; i++ ){ //get the action request from all agents (in correct order - specified in agents' factory)

                    if(agentCanDoAction(...)){
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
    public boolean handleActionRequest(Agent agent, Action action, Percepts perception) {
        if (...) {
            ...
        }
    }

    public void updateWorldState(){
    }*/
}
