package Agent;

import Action.GuardAction;
import Action.IntruderAction;
import Percept.GuardPercepts;
import Percept.IntruderPercepts;

public class Agent implements Intruder, Guard {


    @Override
    public GuardAction getAction(GuardPercepts percepts) {
        return null;
    }

    @Override
    public IntruderAction getAction(IntruderPercepts percepts) {
        return null;
    }


}
