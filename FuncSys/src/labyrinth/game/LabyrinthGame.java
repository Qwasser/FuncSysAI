package labyrinth.game;

import fs.FunctionalSystem;
import fs.IAcceptor;
import fs.IAction;
import fs.PredicateSet;
import fs.Rule;
import labyrinth.level.LabyrinthMap;
import labyrinth.level.TileType;
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
        goal.set(PredicateTable.Dead, true);
        this.walker = new LabyrinthWalker(this, goal);

        this.state.playerX = map.getPlayerStartX();
        this.state.playerY = map.getPlayerStartY();

        this.state.hungerLevel = 0;
        this.state.walkerDirection = WalkerDirections.DOWN;

        this.state.isFail = false;
        this.state.isWon = false;
        this.state.gotBattery = false;

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
        int offset = 0;

        switch (this.state.walkerDirection)
        {
            case UP:
                offset = 0;
                break;
            case DOWN:
                offset = 4;
                break;
            case LEFT:
                offset = 6;
                break;
            case RIGHT:
                offset = 2;
                break;
        }

        situation.set(PredicateTable.UpLeftIsEmpty, false);
        situation.set(PredicateTable.UpLeftIsWall, false);
        situation.set(PredicateTable.UpLeftIsLava, false);

        situation.set(PredicateTable.UpFrontIsEmpty, false);
        situation.set(PredicateTable.UpFrontIsWall, false);
        situation.set(PredicateTable.UpFrontIsLava, false);

        situation.set(PredicateTable.UpRightIsEmpty, false);
        situation.set(PredicateTable.UpRightIsWall, false);
        situation.set(PredicateTable.UpRightIsLava, false);

        situation.set(PredicateTable.LeftIsEmpty, false);
        situation.set(PredicateTable.LeftIsWall, false);
        situation.set(PredicateTable.LeftIsLava, false);

        situation.set(PredicateTable.RightIsEmpty, false);
        situation.set(PredicateTable.RightIsWall, false);
        situation.set(PredicateTable.RightIsLava, false);

        situation.set(PredicateTable.DownLeftIsEmpty, false);
        situation.set(PredicateTable.DownLeftIsWall, false);
        situation.set(PredicateTable.DownLeftIsLava, false);

        situation.set(PredicateTable.DownRightIsEmpty, false);
        situation.set(PredicateTable.DownRightIsWall, false);
        situation.set(PredicateTable.DownRightIsLava, false);

        situation.set(PredicateTable.DownFrontIsEmpty, false);
        situation.set(PredicateTable.DownFrontIsWall, false);
        situation.set(PredicateTable.DownFrontIsLava, false);

        TileType [] tiles = this.getTiles();

        switch (tiles[(0+offset)%8])
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

        switch (tiles[(1+offset)%8])
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

        switch (tiles[(2+offset)%8])
        {
            case EMPTY:
                situation.set(PredicateTable.RightIsEmpty, true);
                break;
            case WALL:
                situation.set(PredicateTable.RightIsWall, true);
                break;
            case LAVA:
                situation.set(PredicateTable.RightIsLava, true);
                break;
        }

        switch (tiles[(3+offset)%8])
        {
            case EMPTY:
                situation.set(PredicateTable.DownRightIsEmpty, true);
                break;
            case WALL:
                situation.set(PredicateTable.DownRightIsWall, true);
                break;
            case LAVA:
                situation.set(PredicateTable.DownRightIsLava, true);
                break;
        }

        switch (tiles[(4+offset)%8])
        {
            case EMPTY:
                situation.set(PredicateTable.DownFrontIsEmpty, true);
                break;
            case WALL:
                situation.set(PredicateTable.DownFrontIsWall, true);
                break;
            case LAVA:
                situation.set(PredicateTable.DownFrontIsLava, true);
                break;
        }

        switch (tiles[(5+offset)%8])
        {
            case EMPTY:
                situation.set(PredicateTable.DownLeftIsEmpty, true);
                break;
            case WALL:
                situation.set(PredicateTable.DownLeftIsWall, true);
                break;
            case LAVA:
                situation.set(PredicateTable.DownLeftIsLava, true);
                break;
        }

        switch (tiles[(6+offset)%8])
        {
            case EMPTY:
                situation.set(PredicateTable.LeftIsEmpty, true);
                break;
            case WALL:
                situation.set(PredicateTable.LeftIsWall, true);
                break;
            case LAVA:
                situation.set(PredicateTable.LeftIsLava, true);
                break;
        }

        switch (tiles[(7+offset)%8])
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
    }

    private TileType[] getTiles()
    {
        TileType[] tiles = new TileType[8];
        tiles[0] = this.map.getCellType(state.playerX, state.playerY - 1);
        tiles[1] = this.map.getCellType(state.playerX + 1, state.playerY - 1);
        tiles[2] = this.map.getCellType(state.playerX + 1 , state.playerY);
        tiles[3] = this.map.getCellType(state.playerX + 1, state.playerY + 1);
        tiles[4] = this.map.getCellType(state.playerX, state.playerY + 1);
        tiles[5] = this.map.getCellType(state.playerX - 1, state.playerY + 1);
        tiles[6] = this.map.getCellType(state.playerX - 1, state.playerY);
        tiles[7] = this.map.getCellType(state.playerX - 1, state.playerY - 1);
        return tiles;
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
        if (state.hasBattery(x, y))
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
        /*
        situation.set(PredicateTable.NotHungry, false);
        situation.set(PredicateTable.Hungry, false);
        situation.set(PredicateTable.VeryHungry, false);

        // hungerClass = (this.state.hungerLevel*3)/GameState.HUNGER_LIMIT;
        //System.out.println(hungerClass);
        //if (hungerClass == 0) situation.set(PredicateTable.NotHungry, true);
        //if (hungerClass == 1) situation.set(PredicateTable.Hungry, true);
        //if (hungerClass >= 2) situation.set(PredicateTable.VeryHungry, true);
        //if (state.hungerLevel == GameState.HUNGER_LIMIT - 1)
        */
        situation.set(PredicateTable.Dead, this.state.isFail);


    }

    private TileType getUpFrontTyle()
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
        return TileType.WALL;
    }

    /*
    * Here I added some simple actions
    */

    public void stepForward()
    {
        TileType frontTyle = this.getUpFrontTyle();
        if (frontTyle == TileType.LAVA)
        {
            this.state.isFail = true;
        }
        if (frontTyle != TileType.WALL)
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

        if (state.hasBattery(x, y))
            state.grabBattery(x, y);
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

        this.state.isFail = false;
        this.state.isWon = false;
        this.state.gotBattery = false;

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

    public void tick()
    {
        PredicateSet predicates =  this.getSituationForAnimate(walker);
        state.setPredicates(predicates);
        this.state.tick();
    }

    public void removeBattery()
    {
        state.gotBattery = false;
    }


    public void fsTick(int steps)
    {
        int i = 0;
        if (steps==1){
            this.walker.makeAction();
            //this.hungerUpdate();

            this.walker.observeResult();
            this.tick();
            this.removeBattery();

        }
        else
        {
            while (i < 100)
            {
                i++;
                this.walker.makeAction();
                //this.hungerUpdate();
                this.walker.observeResult();
                this.tick();
                this.removeBattery();
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
