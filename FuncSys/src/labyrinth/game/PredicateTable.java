package labyrinth.game;

import fs.PredicateSet;

import javax.sql.rowset.Predicate;

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

    public static final int UpFrontIsEmpty = 4;
    public static final int UpFrontIsWall = 5;
    public static final int UpFrontIsLava = 6;

    public static final int UpRightIsEmpty = 7;
    public static final int UpRightIsWall = 8;
    public static final int UpRightIsLava = 9;

    public static final int RightIsEmpty = 10;
    public static final int RightIsWall = 11;
    public static final int RightIsLava = 12;

    public static final int LeftIsEmpty = 13;
    public static final int LeftIsWall = 14;
    public static final int LeftIsLava = 15;

    public static final int DownLeftIsEmpty = 16;
    public static final int DownLeftIsWall = 17;
    public static final int DownLeftIsLava = 18;

    public static final int DownFrontIsEmpty = 19;
    public static final int DownFrontIsWall = 20;
    public static final int DownFrontIsLava = 21;

    public static final int DownRightIsEmpty = 22;
    public static final int DownRightIsWall = 23;
    public static final int DownRightIsLava = 24;

    public static final int directionLeft = 25;
    public static final int directionRight = 26;
    public static final int directionUp = 27;
    public static final int directionDown = 28;

    public static final int SeeBattery = 29;

    public static final int FoundBattery = 30;

    /*
    public static final int NotHungry = 16;
    public static final int Hungry = 17;
    public static final int VeryHungry = 18;
    */

    public static final int Dead = 31;


    public static String predicatesToString(PredicateSet state)
    {
        String str = "";
        if (state.getPredicateValue(1)) str = str.concat("Upper left tile is empty. ");
        if (state.getPredicateValue(2)) str = str.concat("Upper left tile is Wall. ");
        if (state.getPredicateValue(3)) str = str.concat("Upper left tile is Lava. ");

        if (state.getPredicateValue(4)) str = str.concat("Upper front tile is empty. ");
        if (state.getPredicateValue(5)) str = str.concat("Upper front tile is Wall. ");
        if (state.getPredicateValue(6)) str = str.concat("Upper front tile is Lava. ");

        if (state.getPredicateValue(7)) str = str.concat("Upper right tile is empty. ");
        if (state.getPredicateValue(8)) str = str.concat("Upper right tile is Wall. ");
        if (state.getPredicateValue(9)) str = str.concat("Upper right tile is Lava. ");

        if (state.getPredicateValue(10)) str = str.concat("Right tile is empty. ");
        if (state.getPredicateValue(11)) str = str.concat("Right tile is Wall. ");
        if (state.getPredicateValue(12)) str = str.concat("Right tile is Lava. ");

        if (state.getPredicateValue(13)) str = str.concat("Left tile is empty. ");
        if (state.getPredicateValue(14)) str = str.concat("Left tile is Wall. ");
        if (state.getPredicateValue(15)) str = str.concat("Left tile is Lava. ");

        if (state.getPredicateValue(16)) str = str.concat("Down left tile is empty. ");
        if (state.getPredicateValue(17)) str = str.concat("Down left tile is Wall. ");
        if (state.getPredicateValue(18)) str = str.concat("Down left tile is Lava. ");

        if (state.getPredicateValue(19)) str = str.concat("Down right tile is empty. ");
        if (state.getPredicateValue(20)) str = str.concat("Down right tile is Wall. ");
        if (state.getPredicateValue(21)) str = str.concat("Down right tile is Lava. ");

        if (state.getPredicateValue(22)) str = str.concat("Down front tile is empty. ");
        if (state.getPredicateValue(23)) str = str.concat("Down front tile is Wall. ");
        if (state.getPredicateValue(24)) str = str.concat("Down front tile is Lava. ");

        if (state.getPredicateValue(25)) str = str.concat("My direction is Left. ");
        if (state.getPredicateValue(26)) str = str.concat("My direction is Right. ");
        if (state.getPredicateValue(27)) str = str.concat("My direction is Up. ");
        if (state.getPredicateValue(28)) str = str.concat("My direction is Down. ");

        if (state.getPredicateValue(29)) str = str.concat("See battery. ");
        if (state.getPredicateValue(30)) str = str.concat("Found battery. ");
        if (state.getPredicateValue(31)) str = str.concat("Dead. ");

        return str;
    }
}
