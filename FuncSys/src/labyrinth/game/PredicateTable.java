package labyrinth.game;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 13.08.13
 * Time: 18:21
 * To change this template use File | Settings | File Templates.
 */
public class PredicateTable {
    public static final int UpLeftIsEmpty = 0;
    public static final int UpLeftIsWall = 1;
    public static final int UpLeftIsLava = 2;
    public static final int UpLeftIsGold = 3;

    public static final int UpFrontIsEmpty = 4;
    public static final int UpFrontIsWall = 5;
    public static final int UpFrontIsLava = 6;
    public static final int UpFrontIsGold = 7;

    public static final int UpRightIsEmpty = 8;
    public static final int UpRightIsWall = 9;
    public static final int UpRightIsLava = 10;
    public static final int UpRightIsGold = 11;

    public static final int directionLeft = 12;
    public static final int directionRight = 13;
    public static final int directionUp = 14;
    public static final int directionDown = 15;

    public static final int FoundGold = 16;
    public static final int Dead = 17;
}
