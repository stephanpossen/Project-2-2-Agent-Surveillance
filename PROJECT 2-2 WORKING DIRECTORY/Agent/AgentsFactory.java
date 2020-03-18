package Agent;

import Game_Creation_Control.AgentStateHolder;
import Game_Creation_Control.MapReader;
import Agent.*;

import java.util.ArrayList;
/* This class is meant to store all agent's references, the stateHolders refernces
 This class is also meant to instantiate the agents
* */

public class AgentsFactory {
    private static ArrayList<Agent> agents;
    private static   ArrayList<AgentStateHolder> agentsStates;
    private static int numGuards;
    private static int numIntruders;

    public AgentsFactory(int numGuards, int numIntuders) {
        this.numGuards = numGuards;
        this.numIntruders = numIntuders;
        buildAgents();
    }

//    this method instantiates the agents
//    The agents are then stored in the "agents" arraylist
    private void buildAgents() {
        agents = new ArrayList<>();
        agentsStates = new ArrayList<>();
        for (int i = 0; i < numGuards; i++) {
            //new guard should create a guard agent object
            agents.add(new Guard());
            agentsStates.add(new AgentStateHolder(agents.get(agents.size())));
        }

        for (int i = 0; i < numIntruders; i++) {
            //new guard should create a guard agent object
            agents.add(new Intruder());
            agentsStates.add(new AgentStateHolder(agents.get(agents.size())));
        }
    }




    /*
    * Getters/Setters
    * */
    public static int getNumAgents() {
        return agents.size();
    }

    public static ArrayList<Agent> getIntruders() {
        return (ArrayList<Agent>) agents.subList(numGuards+1, agents.size());
    }

    public static ArrayList<Agent> getGuards() {
        return (ArrayList<Agent>) agents.subList(0, numGuards);
    }

    public static ArrayList<Agent> getAgents() {
        return agents;
    }

    public static Agent getAgent(int nb){
        return agents.get(nb);
    }

    public static ArrayList<AgentStateHolder> getAgentsStates() {
        return agentsStates;
    }

    public static AgentStateHolder getStateHolder(int nb){
        return agentsStates.get(nb);
    }

    public static int getNumGuards() {
        return numGuards;
    }

    public static int getNumIntruders() {
        return numIntruders;
    }
}
