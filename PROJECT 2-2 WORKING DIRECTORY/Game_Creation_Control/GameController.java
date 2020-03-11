package Game_Creation_Control;

import Action.Action;

public class GameController {
    private String mapFile;
    private MapReader mapReader;

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
        while (true) {


            handleActionRequest();
            checkWinConditions();
        }
    }

    public void checkWinConditions() {

    }
    //Receives move request from any agent
    //Returns the action if action is considered valid
    //Returns a NoAction if considered invalid
    public Action handleActionRequest(Action action) {
        if (action instanceof Action.NoAction) {
            return action;
        }
    }
}
