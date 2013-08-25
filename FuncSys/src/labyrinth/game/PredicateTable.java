package labyrinth.game;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 13.08.13
 * Time: 18:21
 * To change this template use File | Settings | File Templates.
 */
public class PredicateTable {
    public static final int UpLeftIsEmpty = 1;
    public static final int UpLeftIsWall = 2;
    public static final int UpLeftIsLava = 3;
    public static final int UpLeftIsGold = 4;

    public static final int UpFrontIsEmpty = 5;
    public static final int UpFrontIsWall = 6;
    public static final int UpFrontIsLava = 7;
    public static final int UpFrontIsGold = 8;

    public static final int UpRightIsEmpty = 9;
    public static final int UpRightIsWall = 10;
    public static final int UpRightIsLava = 11;
    public static final int UpRightIsGold = 12;

    public static final int directionLeft = 13;
    public static final int directionRight = 14;
    public static final int directionUp = 15;
    public static final int directionDown = 16;

    public static final int SeeBattery = 17;

    public static final int FoundGold = 18;
    public static final int Dead = 19;
}
