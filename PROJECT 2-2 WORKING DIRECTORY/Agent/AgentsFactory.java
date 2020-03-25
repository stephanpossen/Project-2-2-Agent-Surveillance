package Agent;

import Game_Creation_Control.AgentStateHolder;

import java.util.ArrayList;

/* This class is meant to store all agent's references, the stateHolders references
 This class is also meant to instantiate the agents
* */

public class AgentsFactory {
    private static ArrayList<Agent> agentsArraylist;
    private static ArrayList<Agent> intruderArrayList;
    private static ArrayList<Agent> guardsArraylist;
    private static ArrayList<AgentStateHolder> agentsStates;
    private static int numGuards;
    private static int numIntruders;

    public static  void buildFactory(int numGuards, int numIntruders) {
        AgentsFactory.numGuards = numGuards;
        AgentsFactory.numIntruders = numIntruders;
        buildAgents();
    }

//    this method instantiates the agents
//    The agents are then stored in the "agents" arraylist and in intruder or guards arraylist as well
    private static void buildAgents() {

        agentsArraylist = new ArrayList<>();
        intruderArrayList = new ArrayList<>();
        guardsArraylist = new ArrayList<>();
        agentsStates = new ArrayList<>();

        for (int i = 0; i < numGuards; i++) {
            //new guard should create a guard agent object
            Agent guard = new Agent();
            agentsStates.add(new AgentStateHolder(guard));
            guardsArraylist.add(guard);
            agentsArraylist.add(guard);
        }

        for (int i = 0; i < numIntruders; i++) {
            //new guard should create a guard agent object
            Agent intruder = new Agent();
            agentsStates.add(new AgentStateHolder(intruder));
            intruderArrayList.add(intruder);
            agentsArraylist.add(intruder);
        }
    }


    /*
    * Getters/Setters
    * */
    public static ArrayList<Agent> getIntruders() {
        return intruderArrayList;
    }

    public static ArrayList<Agent> getGuards() {
        return guardsArraylist;
    }

    public static ArrayList<Agent> getAgents() {
        return agentsArraylist;
    }

    public static Agent getSpecificAgent(int index){
        return agentsArraylist.get(index);
    }

    public static ArrayList<AgentStateHolder> getAgentsStates() {
        return agentsStates;
    }

    /**
     * This method is used to check the winning conditions
     * Right now it simply  uses indexes because we currently first create the guards and then the intruders so
     * we know that the first X elements of the arraylist states are guards' elements and the rest of the elements in the array are intruders' states
     * We'll need to change this method if we decide to change the way we create the agents in this AgentFactory
     * @return an arraylist of the states of the intruders at the current turn.
     */
    public static ArrayList<AgentStateHolder> getIntruderStates(){
        ArrayList<AgentStateHolder> outcome = new ArrayList<>();
        for(int i = numGuards; i < numIntruders+numGuards; i++){
            outcome.add(agentsStates.get(i));
        }
        return outcome;
    }

    /**
     * Method to remove an intruder if he has been captured.
     * @param intruderIndex
     */
    public static void removeIntruder(int intruderIndex){
        intruderArrayList.remove(intruderIndex);
        agentsArraylist.remove(numGuards + intruderIndex);
        agentsStates.remove(numGuards + intruderIndex);
    }

    /**
     * This method is used to check the winning conditions
     * Right now it simply  uses indexes because we currently first create the guards and then the intruders so
     * we know that the first X elements of the arraylist states are guards' elements and the rest of the elements in the array are intruders' states
     * We'll need to change this method if we decide to change the way we create the agents in this AgentFactory
     * @return an arraylist of the states of the guards at the current turn.
     */
    public static ArrayList<AgentStateHolder> getGuardsStates(){
        ArrayList<AgentStateHolder> outcome = new ArrayList<>();
        for(int i = 0; i < numGuards; i++){
            outcome.add(agentsStates.get(i));
        }
        return outcome;
    }


    public static AgentStateHolder getStateHolder(int index){
        return agentsStates.get(index);
    }

    public static int getNumGuards() {
        return numGuards;
    }

    public static int getNumIntruders() {
        return numIntruders;
    }

    public static int getNumAgents(){
        return agentsArraylist.size();
    }
}
