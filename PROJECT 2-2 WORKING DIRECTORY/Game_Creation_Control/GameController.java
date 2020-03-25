//package Game_Creation_Control;
//
//import Action.*;
//import Agent.Agent;
//import Agent.Guard;
//import Geometry.Angle;
//import Geometry.Direction;
//import Geometry.Point;
//import Agent.AgentsFactory;
//import Geometry.Vector;
//import Percept.GuardPercepts;
//import Percept.IntruderPercepts;
//import Percept.Percepts;
//import Percept.Scenario.GameMode;
//
//import java.util.ArrayList;
//
//public class GameController {
//    private MapReader map ;
//    private int turn = 0;
//    private GameMode gameMode;
//    boolean GameIsDone = false;
//
//    private int numberOfAgents ; //number of agents
//
//    public GameController(String mapFile) {
//      map = new MapReader(mapFile);
//      gameControllerSetup();
//    }
//
//
//    private void gameControllerSetup(){
//        AgentsFactory.buildFactory(MapReader.getNumGuards(), MapReader.getNumIntruders());
//        gameMode = MapReader.getGameMode();
//    }
//
//
//    //Reads in the mapfile and sets up the game by initializing the guards and intruders
//    //Used once in GameLauncher class
//    public void setup() {
//        map.readMap();
//        MapReader.spawnGuards();
//        MapReader.spawnIntruders();
//    }
//
//
//    //After everything is setup, this starts the game by initializing the while loop
//    //Only stops whenever the time runs out, or winning conditions are met, checked every iteration
//    public void start() {
//            while (GameIsDone = false){
//                for(int i = 1; i <= numberOfAgents; i++ ){/get the action request from all agents (in correct order - specified in agents' factory)
//
//                    AgentStateHolder holder = AgentsFactory.getStateHolder(i); //gets the current state and thus the infos for an agent
//
//                    //check if it is a guard
//                    if(i<AgentsFactory.getNumGuards()){
//                        GuardController g = new GuardController();
//                        Action a = holder.getAgent().getAction(new GuardPercepts()); //TODO call the GuardPercept with the right parameters
//                        g.doAction(a,holder);
//
//                        holder.setLastExecutedAction(a);
//                    }
//
//                     else{
//                        IntruderController intrud = new IntruderController();
//                        Action a = holder.getAgent().getAction(new IntruderPercepts());
////                        if(!intrud.doAction(a)) {
////                            a = new NoAction();
////                        }
//
//                        //this method will check if it is allowed if it is possible to perform the action and apply changes of the stateHolder
//                        //if the action is doable
//                        intrud.doAction(a, holder);
//
//                        holder.setLastExecutedAction(a);
//                         //updateWorldState(a,holder);
//                    }
//                     GuiController.updateGui();
//                }
//
//            handleActionRequest();
//            checkWinConditions();
//        }
//}
//
//
//    public void checkWinConditions() {
//
//    }
//
//    //Receives move request from any agent
//    //Returns the action if action is considered valid
//    //Returns a NoAction if considered invalid
//    public boolean handleActionRequest(Point position,Point possibleNextPosition) {
//        ArrayList<Area> walls = map.getWalls();
//         boolean ok = true;
//        for(int i = 0; i < walls.size(); i++){
//       if(walls.get(i).isHit(position)){
//           ok= false;
//       }
//        }
//       return ok;
//    }
//
////    public void updateWorldState(Action action, AgentStateHolder holder){
////        for(int i = 0; i<AgentsFactory.getNumAgents(); i++){
////
////            if (action instanceof Move){
////                Move m = (Move)action;
////                Vector translation = holder.getDirectionVector();
////                translation.setLength(m.getDistance().getValue());
////                Vector newPosVect = new Vector(holder.getPosition().getX(),holder.getPosition().getY());
////                newPosVect.add(translation);
////                Point newPos = new Point(newPosVect.x,newPosVect.y);
////               holder.setPosition(newPos);
////            }
////
////            else if (action instanceof Rotate){
////                Rotate r = (Rotate)action;
////                Angle rotation = r.getAngle();
////                holder.setDirection(Direction.fromRadians(rotation.getRadians()));
////            }
////
////            // Guards can't sprint
////            else if(action instanceof Sprint){
////                System.out.println("do nothing");
////            }
////
////            else if(action instanceof Yell){
////                Yell y = (Yell)action;
////                //Percepts.add(new Yell());
////            }
////
////            else if (action instanceof NoAction){
////                NoAction na = (NoAction)action;
////                System.out.println("no action");
////            }
////
////            else if(action instanceof DropPheromone){
////                DropPheromone dp = (DropPheromone)action;
////                //Percepts.add(new ph)
////            }
////           else{
////                System.out.println("action "+action+" not found");
////            }
////        }
////
////    }
//}
