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

    public AgentsFactory(int numGuards, int numIntruders) {
        AgentsFactory.numGuards = numGuards;
        AgentsFactory.numIntruders = numIntruders;
        buildAgents();
    }

//    this method instantiates the agents
//    The agents are then stored in the "agents" arraylist and in intruder or guards arraylist as well
    private void buildAgents() {

        agentsArraylist = new ArrayList<>();
        intruderArrayList = new ArrayList<>();
        guardsArraylist = new ArrayList<>();
        agentsStates = new ArrayList<>();

        for (int i = 0; i < numGuards; i++) {
            //new guard should create a guard agent object
            // agents.add(new Guard());
            Agent guard = new Agent();
            agentsStates.add(new AgentStateHolder(guard));
            guardsArraylist.add(guard);
            agentsArraylist.add(guard);
        }

        for (int i = 0; i < numIntruders; i++) {
            //new guard should create a guard agent object
            // agents.add(new Intruder());
            // agentsStates.add(new AgentStateHolder(agents.get(agents.size())));
            Agent intruder = new Agent();
            agentsStates.add(new AgentStateHolder(intruder));
            intruderArrayList.add(intruder);
            intruderArrayList.add(intruder);
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
