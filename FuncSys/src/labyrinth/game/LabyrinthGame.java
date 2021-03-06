package labyrinth.game;

import fs.IAcceptor;
import fs.IAction;
import fs.PredicateSet;
import labyrinth.level.LabyrinthMap;
import labyrinth.level.TileType;
import labyrinth.level.WalkerDirections;


import java.util.LinkedList;
import java.util.List;

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

    //Statistics
    int batteriesEaten = 0;
    int stepsMade = 0;

    public LabyrinthGame(LabyrinthMap map, GameState state)
    {
        this.map = map;
        this.state = state;

        PredicateSet goal = new PredicateSet();
        goal.set(PredicateTable.FoundBattery, true);
        this.walker = new LabyrinthWalker(this, goal);

        this.state.playerX = map.getPlayerStartX();
        this.state.playerY = map.getPlayerStartY();

        this.state.hungerLevel = 0;
        this.state.walkerDirection = WalkerDirections.DOWN;

        this.state.isDead = false;
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

    /*
    Sets states of eight cells around animat
     */
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

        situation.set(PredicateTable.UpFrontHasBattery, false);
        situation.set(PredicateTable.UpRightHasBattery, false);
        situation.set(PredicateTable.RightHasBattery, false);
        situation.set(PredicateTable.DownRightHasBattery, false);
        situation.set(PredicateTable.DownFrontHasBattery, false);
        situation.set(PredicateTable.DownLeftHasBattery, false);
        situation.set(PredicateTable.LeftHasBattery, false);
        situation.set(PredicateTable.UpLeftHasBattery, false);

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

        int [][] directions = new int [][]{
                {state.playerX, state.playerX+1, state.playerX+1, state.playerX+1, state.playerX, state.playerX-1, state.playerX-1, state.playerX-1},
                {state.playerY-1, state.playerY-1, state.playerY, state.playerY+1, state.playerY+1, state.playerY+1, state.playerY, state.playerY-1}
        };


        situation.set(PredicateTable.UpFrontHasBattery, state.hasBattery(directions[0][(0+offset)%8], directions[1][(0+offset)%8]));
        situation.set(PredicateTable.UpRightHasBattery, state.hasBattery(directions[0][(1+offset)%8], directions[1][(1+offset)%8]));
        situation.set(PredicateTable.RightHasBattery, state.hasBattery(directions[0][(2+offset)%8], directions[1][(2+offset)%8]));
        situation.set(PredicateTable.DownRightHasBattery, state.hasBattery(directions[0][(3+offset)%8], directions[1][(3+offset)%8]));
        situation.set(PredicateTable.DownFrontHasBattery, state.hasBattery(directions[0][(4+offset)%8], directions[1][(4+offset)%8]));
        situation.set(PredicateTable.DownLeftHasBattery, state.hasBattery(directions[0][(5+offset)%8], directions[1][(5+offset)%8]));
        situation.set(PredicateTable.LeftHasBattery, state.hasBattery(directions[0][(6+offset)%8], directions[1][(6+offset)%8]));
        situation.set(PredicateTable.UpLeftHasBattery, state.hasBattery(directions[0][(7+offset)%8], directions[1][(7+offset)%8]));
        situation.set(PredicateTable.StandOnBattery, state.hasBattery(state.playerX, state.playerY));

    }

    /*
    Creates an array of tiles around animate for efficient predicate generation (direction permutation)
     */

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


    /*
    sets predicates of direction and battery seeing
     */
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

    /**
    sets predicates of game state
    found battery
    dead
     **/
    private void setGameState(PredicateSet situation)
    {
        if (this.state.gotBattery)
        {
            situation.set(PredicateTable.FoundBattery, true);
        }
        else
        {
            situation.set(PredicateTable.FoundBattery, false);
        }

        situation.set(PredicateTable.Dead, this.state.isDead);

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

    /**
     * step actions
     * makes dead if in steps in lava
     * do nothing if steps in wall
     */

    public void stepForward()
    {
        TileType frontTyle = this.getUpFrontTyle();
        if (frontTyle == TileType.LAVA)
        {
            this.state.isDead = true;
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
        {
            state.grabBattery(x, y);
            state.gotBattery = true;
            batteriesEaten += 1;
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

        this.state.isDead = false;
        this.state.gotBattery = false;

        this.state.walkerDirection = WalkerDirections.RIGHT;
        this.state.hungerLevel = 0;
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

    /**
     * counts amount of batteries found
     */

    public void testOne()
    {

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
            stepsMade += 1;
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
            stepsMade += 100;
        }


        System.out.println(stepsMade);
        System.out.println(batteriesEaten);
        System.out.println(walker.primaryFS.toString());

    }
}
