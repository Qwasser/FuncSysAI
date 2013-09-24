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
    public static final int UpLeftHasBattery = 4;

    public static final int UpFrontIsEmpty = 5;
    public static final int UpFrontIsWall = 6;
    public static final int UpFrontIsLava = 7;
    public static final int UpFrontHasBattery = 8;

    public static final int UpRightIsEmpty = 9;
    public static final int UpRightIsWall = 10;
    public static final int UpRightIsLava = 11;
    public static final int UpRightHasBattery = 12;

    public static final int RightIsEmpty = 13;
    public static final int RightIsWall = 14;
    public static final int RightIsLava = 15;
    public static final int RightHasBattery = 16;

    public static final int LeftIsEmpty = 17;
    public static final int LeftIsWall = 18;
    public static final int LeftIsLava = 19;
    public static final int LeftHasBattery = 20;

    public static final int DownLeftIsEmpty = 21;
    public static final int DownLeftIsWall = 22;
    public static final int DownLeftIsLava = 23;
    public static final int DownLeftHasBattery = 24;

    public static final int DownFrontIsEmpty = 25;
    public static final int DownFrontIsWall = 26;
    public static final int DownFrontIsLava = 27;
    public static final int DownFrontHasBattery = 28;

    public static final int DownRightIsEmpty = 29;
    public static final int DownRightIsWall = 30;
    public static final int DownRightIsLava = 31;
    public static final int DownRightHasBattery = 32;

    public static final int StandOnBattery = 33;

    public static final int directionLeft = 34;
    public static final int directionRight = 35;
    public static final int directionUp = 36;
    public static final int directionDown = 37;

    public static final int SeeBattery = 38;

    public static final int FoundBattery = 39;

    /*
    public static final int NotHungry = 16;
    public static final int Hungry = 17;
    public static final int VeryHungry = 18;
    */

    public static final int Dead = 40;


    public static String predicatesToString(PredicateSet state)
    {
        String str = "<html><div style=\"text-align: center;\">";

        if (state.getPredicateValue(1)) str = str.concat("Upper left tile is empty. ");
        if (state.getPredicateValue(2)) str = str.concat("Upper left tile is Wall. ");
        if (state.getPredicateValue(3)) str = str.concat("Upper left tile is Lava. ");
        if (state.getPredicateValue(4)) str = str.concat("UL has BATTERY. ");

        if (state.getPredicateValue(5)) str = str.concat("Upper front tile is empty. ");
        if (state.getPredicateValue(6)) str = str.concat("Upper front tile is Wall. ");
        if (state.getPredicateValue(7)) str = str.concat("Upper front tile is Lava. ");
        if (state.getPredicateValue(8)) str = str.concat("UF has BATTERY. ");

        if (state.getPredicateValue(9)) str = str.concat("Upper right tile is empty. ");
        if (state.getPredicateValue(10)) str = str.concat("Upper right tile is Wall. ");
        if (state.getPredicateValue(11)) str = str.concat("Upper right tile is Lava. ");
        if (state.getPredicateValue(12)) str = str.concat("UR has BATTERY. ");

        str = str.concat("<br>");

        if (state.getPredicateValue(13)) str = str.concat("Right tile is empty. ");
        if (state.getPredicateValue(14)) str = str.concat("Right tile is Wall. ");
        if (state.getPredicateValue(15)) str = str.concat("Right tile is Lava. ");
        if (state.getPredicateValue(16)) str = str.concat("R has BATTERY. ");

        if (state.getPredicateValue(17)) str = str.concat("Left tile is empty. ");
        if (state.getPredicateValue(18)) str = str.concat("Left tile is Wall. ");
        if (state.getPredicateValue(19)) str = str.concat("Left tile is Lava. ");
        if (state.getPredicateValue(20)) str = str.concat("L has BATTERY. ");

        if (state.getPredicateValue(21)) str = str.concat("Down left tile is empty. ");
        if (state.getPredicateValue(22)) str = str.concat("Down left tile is Wall. ");
        if (state.getPredicateValue(23)) str = str.concat("Down left tile is Lava. ");
        if (state.getPredicateValue(24)) str = str.concat("DL has BATTERY. ");

        str = str.concat("<br>");

        if (state.getPredicateValue(25)) str = str.concat("Down front tile is empty. ");
        if (state.getPredicateValue(26)) str = str.concat("Down front tile is Wall. ");
        if (state.getPredicateValue(27)) str = str.concat("Down front tile is Lava. ");
        if (state.getPredicateValue(28)) str = str.concat("DF has BATTERY. ");

        if (state.getPredicateValue(29)) str = str.concat("Down right tile is empty. ");
        if (state.getPredicateValue(30)) str = str.concat("Down right tile is Wall. ");
        if (state.getPredicateValue(31)) str = str.concat("Down right tile is Lava. ");
        if (state.getPredicateValue(32)) str = str.concat("DR has BATTERY. ");
        if (state.getPredicateValue(33)) str = str.concat("Stand on BATTERY. ");
        str = str.concat("<br>");

        if (state.getPredicateValue(34)) str = str.concat("My direction is Left. ");
        if (state.getPredicateValue(35)) str = str.concat("My direction is Right. ");
        if (state.getPredicateValue(36)) str = str.concat("My direction is Up. ");
        if (state.getPredicateValue(37)) str = str.concat("My direction is Down. ");

        if (state.getPredicateValue(38)) str = str.concat("See battery. ");
        if (state.getPredicateValue(39)) str = str.concat("Found battery. ");
        if (state.getPredicateValue(40)) str = str.concat("Dead. ");

        str = str.concat("</html>");

        return str;
    }
}
