package Game_Creation_Control;

import Action.*;
import Agent.Agent;
import Agent.Guard;
import Geometry.Angle;
import Geometry.Direction;
import Geometry.Point;
import Agent.*;
import Geometry.Vector;
import Percept.GuardPercepts;
import Percept.IntruderPercepts;
import Percept.Percepts;
import Percept.Scenario.GameMode;

import java.util.ArrayList;

public class GameController {
    private MapReader map ;
    private int turn = 0;
    private GameMode gameMode;
    private int gameModeInt;
    boolean GameIsDone = false;
    private Area targetArea= MapReader.getTargetArea();

    private int numberOfAgents ; //number of agents

    public GameController(String mapFile) {
        map = new MapReader(mapFile);
        gameControllerSetup();
    }


    private void gameControllerSetup(){
        AgentsFactory.buildFactory(MapReader.getNumGuards(), MapReader.getNumIntruders());
        gameMode = MapReader.getGameMode();
        gameModeInt = MapReader.getGameModeInt();
    }


    //Reads in the mapfile and sets up the game by initializing the guards and intruders
    //Used once in GameLauncher class
    public void setup() {
        map.readMap();
        MapReader.spawnGuards();
        MapReader.spawnIntruders();
    }


    //After everything is setup, this starts the game by initializing the while loop
    //Only stops whenever the time runs out, or winning conditions are met, checked every iteration
    public void start() {
        while (GameIsDone = false){
            for(int i = 0; i < numberOfAgents; i++ ){ //get the action request from all agents (in correct order - specified in agents' factory)

                AgentStateHolder holder = AgentsFactory.getStateHolder(i); //gets the current state and thus the infos for an agent

                //check if it is a guard
                if(i<AgentsFactory.getNumGuards()-1){
                    GuardController g = new GuardController();
                    Action a = holder.getAgent().getAction(SensorsController.getGuardPercepts(holder)); //TODO call the GuardPercept with the right parameters
                    g.doAction(a,holder);

                    holder.setLastExecutedAction(a);
                }

                else{
                    IntruderController intrud = new IntruderController();
                    Action a;
                    if(holder.getAgent() instanceof Exp_Guard) {
                        Exp_Guard guardInstance = new Exp_Guard();
                        a = guardInstance.getAction(SensorsController.getIntruderPercepts(holder));
                    } else{
                        a = null;
                    }
//                        if(!intrud.doAction(a)) {
//                            a = new NoAction();
//                        }

                    //this method will check if it is allowed if it is possible to perform the action and apply changes of the stateHolder
                    //if the action is doable
                    intrud.doAction(a, holder);

                    holder.setLastExecutedAction(a);
                    // updateWorldState(a,holder);
                }
                GuiController.updateGui();
            }

            if(checkWinConditions() == 1 || checkWinConditions() == 2){
                GameIsDone = true;
            }
        }
    }


    /**
     * Method to check if the game is done or not. It depends on the game mode we're in.
     * @return 0 if the game is not done - returns 1 if the game is won by the guards - returns 2 if the game is won by the intruders
     */
    private int checkWinConditions() {

        ArrayList<AgentStateHolder> intrudersStates = AgentsFactory.getIntruderStates();
        ArrayList<AgentStateHolder> guardsSates = AgentsFactory.getGuardsStates();

        if (gameModeInt == 0) { // -> need to capture all intruders in order to win (guards' point of view)

            int numberOfCapturedIntruders = 0;

            for (AgentStateHolder intrudersState : intrudersStates) {
                if (intrudersState.getNumberRoundsInTargetArea() == MapReader.getWinConditionIntruderRounds()) {
                    return 2; // the intruders won because one of them managed to stay enough rounds in the target area
                }
            }

            for(int j = 1; j <= guardsSates.size(); j++){
                for ( int i = 1; i <= intrudersStates.size(); i++){
                    if(SensorsController.isInfieldCapture(guardsSates.get(j-1),intrudersStates.get(i-1))){
                        intrudersStates.remove(i-1); // remove the intruder who has been captured by a guard
                        AgentsFactory.removeIntruder(i-1); // remove the intruder who has been captured by a guard
                        numberOfCapturedIntruders += 1;  // increment the number of captured intruders by one
                    }
                }
            }

            if(numberOfCapturedIntruders == MapReader.getNumIntruders()){
                return 1;
            }



        }

        else if (gameModeInt == 1) {  // ->  need to capture only 1 intruders in order to win (guards' point of view)

            int numberOfCapturedIntruders = 0;

            for (AgentStateHolder intrudersState : intrudersStates) {
                if (intrudersState.getNumberRoundsInTargetArea() == MapReader.getWinConditionIntruderRounds()) {
                    return 2; // the intruders won because one of them managed to stay enough rounds in the target area
                }
            }

            for(int j = 1; j <= guardsSates.size(); j++){
                for ( int i = 1; i <= intrudersStates.size(); i++){
                    if(SensorsController.isInfieldCapture(guardsSates.get(j-1),intrudersStates.get(i-1))){
                        intrudersStates.remove(i-1); // remove the intruder who has been captured by a guard
                        AgentsFactory.removeIntruder(i-1); // remove the intruder who has been captured by a guard
                        numberOfCapturedIntruders += 1;  // increment the number of captured intruders by one
                        if(numberOfCapturedIntruders >= 1){ // if 1 intruder is captured, the guards won
                            return 1; // guards won
                        }
                    }
                }
            }
        }
        return 0; // the game is not done yet; no agent has won the game
    }

}
