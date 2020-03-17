package Agent;

import Game_Creation_Control.AgentStateHolder;
import Game_Creation_Control.MapReader;
import Agent.*;

import java.util.ArrayList;

public class AgentsFactory {
    ArrayList<Agent> agents;
    ArrayList<AgentStateHolder> agentsStates;
    private int numGuards;
    private int numIntruders;

    public AgentsFactory(int numGuards, int numIntuders) {
        this.numGuards = numGuards;
        this.numIntruders = numIntuders;
        buildAgents();
    }

    private void buildAgents() {
        agents = new ArrayList<>();
        agentsStates = new ArrayList<>();
        for (int i = 0; i < numGuards; i++) {
            agents.add(new Guard());
            agentsStates.add(new AgentStateHolder(agents.get(agents.size())));
        }

        for (int i = 0; i < numIntruders; i++) {
            agents.add(new Intruder());
            agentsStates.add(new AgentStateHolder(agents.get(agents.size())));
        }
    }

    public int getNumAgents() {
        return agents.size();
    }

    public ArrayList<Agent> getIntruders() {
        return (ArrayList<Agent>) agents.subList(numGuards+1, agents.size());
    }

    public ArrayList<Agent> getGuards() {
        return (ArrayList<Agent>) agents.subList(0, numGuards);
    }

    public ArrayList<Agent> getAgents() {
        return agents;
    }

    public Agent getAgent(int nb){
        return agents.get(nb);
    }

    public ArrayList<AgentStateHolder> getAgentsStates() {
        return agentsStates;
    }

    public AgentStateHolder getStateHolder(int nb){
        return agentsStates.get(nb);
    }

    public int getNumGuards() {
        return numGuards;
    }

    public int getNumIntruders() {
        return numIntruders;
    }
}
