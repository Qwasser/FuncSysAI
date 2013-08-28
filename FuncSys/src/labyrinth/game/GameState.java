package labyrinth.game;

import labyrinth.level.WalkerDirections;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 21.08.13
 * Time: 22:59
 * To change this template use File | Settings | File Templates.
 */
public class GameState {
    public static final int HUNGER_LIMIT = 120;

    int hungerLevel;
    int playerX;
    int playerY;

    int batteryX;
    int batteryY;

    int stepNumber;

    public WalkerDirections walkerDirection;

    boolean isFail;
    boolean isWon;
    boolean  hasGold;

    boolean  isBatteryTaken;

    public boolean isTaken(){
        return this.isBatteryTaken;
    }

    public int getPlayerXinPixels(){
        return (playerX) * 32 + 8;
    }

    public int getPlayerYinPixels(){
        return (playerY) * 32 + 12;
    }

    public int getBatteryXinPixels(){
        return (batteryX) * 32 + 7;
    }

    public int getBatteryYinPixels(){
        return (batteryY) * 32 + 12;
    }

}
