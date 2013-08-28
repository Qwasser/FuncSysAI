package labyrinth.game;

import fs.FunctionalSystem;
import fs.IAcceptor;
import fs.IAction;
import fs.PredicateSet;
import fs.Rule;
import labyrinth.level.LabyrinthMap;
import labyrinth.level.TyleType;
import labyrinth.level.WalkerDirections;


import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 12.08.13
 * Time: 22:39
 * To change this template use File | Settings | File Templates.
 */
public class LabyrinthGame {
    LabyrinthMap map;
    LabyrinthWalker walker;
    GameState state;

    public LabyrinthGame(LabyrinthMap map, GameState state)
    {
        this.map = map;
        this.state = state;

        PredicateSet goal = new PredicateSet();
        goal.set(PredicateTable.Dead, false);
        this.walker = new LabyrinthWalker(this, goal);

        this.state.playerX = map.getPlayerStartX();
        this.state.playerY = map.getPlayerStartY();
        this.state.batteryX = map.getBatteryStartX();
        this.state.batteryY = map.getBatteryStartY();
        this.state.isBatteryTaken = false;
        this.state.hungerLevel = 0;
        this.state.walkerDirection = WalkerDirections.DOWN;

        this.state.isFail = false;
        this.state.isWon = false;
        this.state.hasGold = false;

    }

    public List<IAction> getPossibleActionsForAnimate(IAcceptor acceptor)
    {
        List<IAction> actions = new LinkedList<IAction>();
        actions.add(walker.grabGold);
        actions.add(walker.stepForward);
        actions.add(walker.turnLeft);
        actions.add(walker.turnRight);

        return actions;
    }

    public PredicateSet getSituationForAnimate(IAcceptor acceptor)
    {

        PredicateSet situation = new PredicateSet();
        setSensorPredicates(situation);
        setSelfStatePredicates(situation);
        setGameState(situation);
        return situation;
    }

    private void setSensorPredicates(PredicateSet situation)
    {
        TyleType tyle = getUpLeftTyle();

        situation.set(PredicateTable.UpLeftIsEmpty, false);
        situation.set(PredicateTable.UpLeftIsWall, false);
        situation.set(PredicateTable.UpLeftIsLava, false);

        switch (tyle)
        {
            case EMPTY:
                situation.set(PredicateTable.UpLeftIsEmpty, true);
                break;
            case WALL:
                situation.set(PredicateTable.UpLeftIsWall, true);
                break;
            case LAVA:
                situation.set(PredicateTable.UpLeftIsLava, true);
                break;
        }

        tyle = getUpFrontTyle();

        situation.set(PredicateTable.UpFrontIsEmpty, false);
        situation.set(PredicateTable.UpFrontIsWall, false);
        situation.set(PredicateTable.UpFrontIsLava, false);

        switch (tyle)
        {
            case EMPTY:
                situation.set(PredicateTable.UpFrontIsEmpty, true);
                break;
            case WALL:
                situation.set(PredicateTable.UpFrontIsWall, true);
                break;
            case LAVA:
                situation.set(PredicateTable.UpFrontIsLava, true);
                break;

        }
        tyle = getUpRightTyle();

        situation.set(PredicateTable.UpRightIsEmpty, false);
        situation.set(PredicateTable.UpRightIsWall, false);
        situation.set(PredicateTable.UpRightIsLava, false);

        switch (tyle)
        {
            case EMPTY:
                situation.set(PredicateTable.UpRightIsEmpty, true);
                break;
            case WALL:
                situation.set(PredicateTable.UpRightIsWall, true);
                break;
            case LAVA:
                situation.set(PredicateTable.UpRightIsLava, true);
                break;

        }
    }

    private TyleType getUpLeftTyle()
    {
        switch (this.state.walkerDirection)
        {
            case UP:
                return map.getCellType(state.playerX - 1, state.playerY - 1);

            case DOWN:
                return map.getCellType(state.playerX + 1, state.playerY + 1);

            case LEFT:
                return map.getCellType(state.playerX - 1, state.playerY + 1);

            case RIGHT:
                return map.getCellType(state.playerX + 1, state.playerY - 1);

        }
        return TyleType.WALL;
    }

    private TyleType getUpFrontTyle()
    {
        switch (this.state.walkerDirection)
        {
            case UP:
                return map.getCellType(state.playerX, state.playerY - 1);
            case DOWN:
                return map.getCellType(state.playerX, state.playerY + 1);
            case LEFT:
                return map.getCellType(state.playerX - 1, state.playerY);
            case RIGHT:
                return map.getCellType(state.playerX + 1, state.playerY);
        }
        return TyleType.WALL;
    }

    private TyleType getUpRightTyle()
    {
        switch (this.state.walkerDirection)
        {
            case UP:
                return map.getCellType(state.playerX + 1, state.playerY - 1);
            case DOWN:
                return map.getCellType(state.playerX - 1, state.playerY + 1);
            case LEFT:
                return map.getCellType(state.playerX - 1, state.playerY - 1);
            case RIGHT:
                return map.getCellType(state.playerX + 1, state.playerY + 1);
        }
        return TyleType.WALL;
    }

    private void setSelfStatePredicates(PredicateSet situation)
    {
        situation.set(PredicateTable.directionUp, false);
        situation.set(PredicateTable.directionDown, false);
        situation.set(PredicateTable.directionRight, false);
        situation.set(PredicateTable.directionLeft, false);

        switch (this.state.walkerDirection)
        {
            case UP:
                situation.set(PredicateTable.directionUp, true);
                break;
            case DOWN:
                situation.set(PredicateTable.directionDown, true);
                break;
            case LEFT:
                situation.set(PredicateTable.directionLeft, true);
                break;
            case RIGHT:
                situation.set(PredicateTable.directionRight, true);
                break;
        }

        int x = 0, y = 0;
        switch (this.state.walkerDirection)
        {
            case UP:
                x = state.playerX;
                y = state.playerY - 1;
                break;
            case DOWN:
                x = state.playerX;
                y = state.playerY + 1;
                break;
            case LEFT:
                x = state.playerX - 1;
                y = state.playerY;
                break;
            case RIGHT:
                x = state.playerX + 1;
                y = state.playerY;
                break;
        }
        if (x == state.batteryX && y == state.batteryY)
            situation.set(PredicateTable.SeeBattery, true);
        else
            situation.set(PredicateTable.SeeBattery, false);
    }

    private void setGameState(PredicateSet situation)
    {
        if (this.state.isWon)
        {
            situation.set(PredicateTable.FoundBattery, true);
        }
        else
        {
            situation.set(PredicateTable.FoundBattery, false);
        }

        situation.set(PredicateTable.NotHungry, false);
        situation.set(PredicateTable.Hungry, false);
        situation.set(PredicateTable.VeryHungry, false);

        int hungerClass = this.state.hungerLevel/GameState.HUNGER_LIMIT*3;
        if (hungerClass == 0) situation.set(PredicateTable.NotHungry, true);
        if (hungerClass == 1) situation.set(PredicateTable.Hungry, true);
        if (hungerClass >= 2) situation.set(PredicateTable.VeryHungry, true);
        //if (state.hungerLevel == GameState.HUNGER_LIMIT - 1)
        situation.set(PredicateTable.Dead, this.state.isFail);


    }

    private void hungerUpdate(){
        this.state.hungerLevel++;
        if (this.state.hungerLevel >= GameState.HUNGER_LIMIT-1)
            this.state.isFail = true;
    }


    /*
    * Here I added some simple actions
    */

    public void stepForward()
    {
        TyleType frontTyle = this.getUpFrontTyle();
        if (frontTyle == TyleType.LAVA)
        {
            this.state.isFail = true;
        }
        if (frontTyle != TyleType.WALL)
        {
            switch (this.state.walkerDirection)
            {
                case UP:
                    state.playerY =  state.playerY - 1;
                    break;
                case DOWN:
                    state.playerY =  state.playerY + 1;
                    break;
                case LEFT:
                    state.playerX =  state.playerX - 1;
                    break;
                case RIGHT:
                    state.playerX =  state.playerX + 1;
                    break;
            }
        }
    }

    public void grabGold()
    {
        int x = 0, y = 0;
        switch (this.state.walkerDirection)
        {
            case UP:
                x = state.playerX;
                y = state.playerY - 1;
                break;
            case DOWN:
                x = state.playerX;
                y = state.playerY + 1;
                break;
            case LEFT:
                x = state.playerX - 1;
                y = state.playerY;
                break;
            case RIGHT:
                x = state.playerX + 1;
                y = state.playerY;
                break;
        }
        if (x == state.batteryX && y == state.batteryY && !state.isBatteryTaken)
        {
            state.hasGold = true;
            state.isBatteryTaken = true;
            state.isWon = true;
            state.hungerLevel = 0;
        }
    }

    public void turnLeft()
    {
        switch (this.state.walkerDirection)
        {
            case UP:
                this.state.walkerDirection = WalkerDirections.LEFT;
                return;
            case DOWN:
                this.state.walkerDirection = WalkerDirections.RIGHT;
                return;
            case LEFT:
                this.state.walkerDirection = WalkerDirections.DOWN;
                return;
            case RIGHT:
                this.state.walkerDirection = WalkerDirections.UP;
                return;
        }
    }

    public void turnRight()
    {
        switch (this.state.walkerDirection)
        {
            case UP:
                this.state.walkerDirection = WalkerDirections.RIGHT;
                return;
            case DOWN:
                this.state.walkerDirection = WalkerDirections.LEFT;
                return;
            case LEFT:
                this.state.walkerDirection = WalkerDirections.UP;
                return;
            case RIGHT:
                this.state.walkerDirection = WalkerDirections.DOWN;
                return;
        }
    }

    public void resetGame()
    {
        this.state.playerX = map.getPlayerStartX();
        this.state.playerY = map.getPlayerStartY();
        this.state.batteryX = map.getBatteryStartX();
        this.state.batteryY = map.getBatteryStartY();
        this.state.isBatteryTaken = false;

        this.state.isFail = false;
        this.state.isWon = false;
        this.state.hasGold = false;

        this.state.walkerDirection = WalkerDirections.RIGHT;
        this.state.hungerLevel = 0;
    }

    public void start()
    {
        //System.out.println(this.state.walkerDirection);
        this.turnLeft();
        //System.out.println(this.state.walkerDirection);
        this.stepForward();
        this.stepForward();
        this.stepForward();
        this.stepForward();
        this.grabGold();
        this.turnRight();

    }

    public void fsTick(int steps)
    {
        int i = 0;
        if (steps==1){
            this.walker.makeAction();
            this.hungerUpdate();
            this.walker.observeResult();
            if (state.isFail)
            {
                this.resetGame();
            }
        }
        else
        {
            while (true)
            {
                i++;
                this.walker.makeAction();
                this.hungerUpdate();
                this.walker.observeResult();



                if (state.isFail)
                {
                    this.resetGame();
                    break;
                    //
                }
            }
        }

        System.out.println("steps:" + i);

        System.out.println(walker.memory.toString());
        System.out.println(walker.primaryFS.probTableToString());
        for (Rule rule: walker.primaryFS.getRules())
        {
            System.out.println(PredicateTable.predicatesToString(rule.getPredicates()) + "Action is "+rule.getAction().toString() + " Prob is is "+rule.getProbability());
        }

        Set<FunctionalSystem> set = walker.primaryFS.getLinkToSubFS();
        for (FunctionalSystem fs: set){
            System.out.println("Depth is near 1");
            for (Rule rule: fs.getRules())
            {
                System.out.println("RULE 1111111111111111");
                System.out.println(PredicateTable.predicatesToString(rule.getPredicates()) + "Action is "+rule.getAction().toString() + " Prob is is "+rule.getProbability());
            }

            Set<FunctionalSystem> set2 = fs.getLinkToSubFS();
            for (FunctionalSystem fs2: set2){
                System.out.println("Depth is near 0");
                for (Rule rule2: fs2.getRules())
                {
                    System.out.println(PredicateTable.predicatesToString(rule2.getPredicates()) + "Action is "+rule2.getAction().toString() + " Prob is is "+rule2.getProbability());
                }
            }
        }
    }
}
