import ru.nsu.alife.fs.IAcceptor;
import ru.nsu.alife.fs.IAction;
import ru.nsu.alife.fs.PredicateSet;
import sun.font.TrueTypeFont;

import java.security.PublicKey;
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

    public class GameState
    {
        int playerX;
        int playerY;

        int stepNumber;

        WalkerDirections walkerDirection;

        boolean isFail;
        boolean isWon;
        boolean  hasGold;
    }

    public class LabyrinthMap
    {
        int width;
        int height;

        private TyleType[][] map;

        public TyleType getCellType(int x, int y)
        {
            if ((x > width) || (x < 0) ||
                (y > height) || (y < 0))
            {
                return TyleType.WALL;
            }
            return map[x][y];
        }

        public LabyrinthMap(int width, int height)
        {
            this.width = width;
            this.height = height;

            this.map = new TyleType[width][height];
        }

        public void setTyle(int x, int y, TyleType tyle)
        {
            if (x >= width || y >= height)
            {
                throw new ArrayIndexOutOfBoundsException();
            }
            this.map[x][y] = tyle;
        }
    }

    /*
    smple map
    # ## ##
         G#
    # ## ##
     */

    LabyrinthMap map;
    LabyrinthWalker walker;
    GameState state;

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
        switch (tyle)
        {
            case EMPTY:
                situation.set(PredicateTable.UpLeftIsEmpty, true);
            case WALL:
                situation.set(PredicateTable.UpLeftIsWall, true);
            case LAVA:
                situation.set(PredicateTable.UpLeftIsLava, true);
            case  GOLD:
                situation.set(PredicateTable.UpLeftIsGold, true);
        }
        tyle = getUpFrontTyle();
        switch (tyle)
        {
            case EMPTY:
                situation.set(PredicateTable.UpFrontIsEmpty, true);
            case WALL:
                situation.set(PredicateTable.UpFrontIsWall, true);
            case LAVA:
                situation.set(PredicateTable.UpFrontIsLava, true);
            case  GOLD:
                situation.set(PredicateTable.UpFrontIsGold, true);
        }
        tyle = getUpRightTyle();
        switch (tyle)
        {
            case EMPTY:
                situation.set(PredicateTable.UpRightIsEmpty, true);
            case WALL:
                situation.set(PredicateTable.UpRightIsWall, true);
            case LAVA:
                situation.set(PredicateTable.UpRightIsLava, true);
            case  GOLD:
                situation.set(PredicateTable.UpRightIsGold, true);
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
        switch (this.state.walkerDirection)
        {
            case UP:
                situation.set(PredicateTable.directionUp, true);
            case DOWN:
                situation.set(PredicateTable.directionDown, true);
            case LEFT:
                situation.set(PredicateTable.directionLeft, true);
            case RIGHT:
                situation.set(PredicateTable.directionRight, true);
        }
    }

    private void setGameState(PredicateSet situation)
    {
        if (this.state.isWon)
        {
            situation.set(PredicateTable.FoundGold, true);
        }

        if (this.state.isFail)
        {
            situation.set(PredicateTable.Dead, true);
        }
    }


    /*
    * Here I added some simple actions
    */

    public void stepForward()
    {
        TyleType frontTyle = this.getUpFrontTyle();
        if (frontTyle != TyleType.WALL)
        {
            switch (this.state.walkerDirection)
            {
                case UP:
                    state.playerY =  state.playerY - 1;
                case DOWN:
                    state.playerY =  state.playerY + 1;
                case LEFT:
                    state.playerX =  state.playerX - 1;
                case RIGHT:
                    state.playerX =  state.playerX + 1;
            }
        }
    }

    public void grabGold()
    {
        if (map.getCellType(state.playerX, state.playerY) == TyleType.GOLD)
        {
            state.hasGold = true;
            map.setTyle(state.playerX, state.playerY, TyleType.EMPTY);
        }
    }

    public void turnLeft()
    {
        switch (this.state.walkerDirection)
        {
            case UP:
                this.state.walkerDirection = WalkerDirections.LEFT;
            case DOWN:
                this.state.walkerDirection = WalkerDirections.RIGHT;
            case LEFT:
                this.state.walkerDirection = WalkerDirections.DOWN;
            case RIGHT:
                this.state.walkerDirection = WalkerDirections.UP;
        }
    }

    public void turnRight()
    {
        switch (this.state.walkerDirection)
        {
            case UP:
                this.state.walkerDirection = WalkerDirections.RIGHT;
            case DOWN:
                this.state.walkerDirection = WalkerDirections.LEFT;
            case LEFT:
                this.state.walkerDirection = WalkerDirections.UP;
            case RIGHT:
                this.state.walkerDirection = WalkerDirections.DOWN;
        }
    }
}
