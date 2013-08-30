package labyrinth.game;

import labyrinth.level.LabyrinthMap;
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
    public static final int BATTERY_RESPAWN_TIME = 10;

    LabyrinthMap map;
    int hungerLevel;
    int playerX;
    int playerY;

    int stepNumber;
    int [] batteryStates;

    public WalkerDirections walkerDirection;

    boolean isFail;
    boolean isWon;
    boolean  hasGold;

    public GameState(LabyrinthMap map)
    {
        this.map = map;
        batteryStates = new int[map.getBatteryCount()];
        for (int i = 0; i < batteryStates.length; i++)
        {
            batteryStates[i] = -1;
        }
    }
    public boolean isTaken(int num){
        return this.batteryStates[num] != -1;
    }

    public boolean hasBattery(int x, int y)
    {
        if(this.map.ifTileHasBattery(x,y))
            if(!this.isTaken(map.getBatteryNum(x,y)))
                return true;
        return false;
    }


    public void grabBattery(int x, int y){
        this.batteryStates[map.getBatteryNum(x,y)] = 0;
    }

    public int getPlayerXinPixels(){
        return (playerX) * 32 + 8;
    }

    public int getPlayerYinPixels(){
        return (playerY) * 32 + 12;
    }

    public int getBatteryXinPixels(int x){
        return (x) * 32 + 7;
    }

    public int getBatteryYinPixels(int y){
        return (y) * 32 + 12;
    }


    public void tick(){
        for (int i = 0; i < batteryStates.length; i++)
        {
            if (batteryStates[i] >= 0)
                batteryStates[i]++;
            if (batteryStates[i] > BATTERY_RESPAWN_TIME)
                batteryStates[i] = -1;
        }
    }

}
