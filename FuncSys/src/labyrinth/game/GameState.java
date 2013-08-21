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
    int playerX;
    int playerY;

    int batteryX;
    int batteryY;

    int stepNumber;

    WalkerDirections walkerDirection;

    boolean isFail;
    boolean isWon;
    boolean  hasGold;

    public int getPlayerXinPixels(){
        return (playerX-1) * 32 + 8;
    }

    public int getPlayerYinPixels(){
        return (playerY-1) * 32 + 12;
    }

    public int getBatteryXinPixels(){
        return (batteryX-1) * 32 + 7;
    }

    public int getBatteryYinPixels(){
        return (batteryY-1) * 32 + 12;
    }

}
