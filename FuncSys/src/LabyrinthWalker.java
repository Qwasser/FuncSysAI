/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 13.08.13
 * Time: 17:49
 * To change this template use File | Settings | File Templates.
 */

import fs.*;

import java.util.List;

class LabyrinthWalker extends IAcceptor {

    // AI main FS
    public IFunctionalSystem primaryFS;

    // labyrinth
    private LabyrinthGame labyrinth;

    public LabyrinthWalker(LabyrinthGame labyrinth, PredicateSet goal) {
        this.labyrinth = labyrinth;
        primaryFS = (IFunctionalSystem) new FunctionalSystem(goal, 3);
    }


    @Override
    public PredicateSet getCurrentSituation() {
        return labyrinth.getSituationForAnimate(this);
    }

    @Override
    public double getProactivity() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public IAction getRandomAction() {
        List<IAction> actions = labyrinth.getPossibleActionsForAnimate(this);
        IAction action = actions.get((int) (Math.random() * actions.size()));
        return action;
    }

    public final IAction stepForward = new IAction() {
        @Override
        public boolean doAction() {
            labyrinth.stepForward();
            return true;
        }
    };

    public final IAction grabGold = new IAction() {
        @Override
        public boolean doAction() {
            labyrinth.grabGold();
            return true;
        }
    };

    public final IAction turnLeft = new IAction() {
        @Override
        public boolean doAction() {
            labyrinth.turnLeft();
            return true;
        }
    };

    public final IAction turnRight = new IAction() {
        @Override
        public boolean doAction() {
            labyrinth.turnRight();
            return true;
        }
    };
}
