package labyrinth.level;

/**
 * Created with IntelliJ IDEA.
 * User: Users
 * Date: 22.08.13
 * Time: 21:35
 * To change this template use File | Settings | File Templates.
 */
public class MapLibrary {
    public static LabyrinthMap simpleMap1()
    {
        LabyrinthMap map = new LabyrinthMap(7, 5, 1);
        map.setPlayerStartY(2);
        map.setPlayerStartX(1);

        map.setBatteryPos(3, 3, 0);

        map.setTyle(0, 0, TyleType.WALL);
        map.setTyle(1, 0, TyleType.WALL);
        map.setTyle(2, 0, TyleType.WALL);
        map.setTyle(3, 0, TyleType.WALL);
        map.setTyle(4, 0, TyleType.WALL);
        map.setTyle(5, 0, TyleType.WALL);
        map.setTyle(6, 0, TyleType.WALL);

        map.setTyle(0, 1, TyleType.WALL);
        map.setTyle(1, 1, TyleType.WALL);
        map.setTyle(2, 1, TyleType.LAVA);
        map.setTyle(3, 1, TyleType.WALL);
        map.setTyle(4, 1, TyleType.LAVA);
        map.setTyle(5, 1, TyleType.WALL);
        map.setTyle(6, 1, TyleType.WALL);

        map.setTyle(0, 2, TyleType.WALL);
        map.setTyle(1, 2, TyleType.EMPTY);
        map.setTyle(2, 2, TyleType.EMPTY);
        map.setTyle(3, 2, TyleType.EMPTY);
        map.setTyle(4, 2, TyleType.EMPTY);
        map.setTyle(5, 2, TyleType.EMPTY);
        map.setTyle(6, 2, TyleType.WALL);

        map.setTyle(0, 3, TyleType.WALL);
        map.setTyle(1, 3, TyleType.WALL);
        map.setTyle(2, 3, TyleType.LAVA);
        map.setTyle(3, 3, TyleType.WALL);
        map.setTyle(4, 3, TyleType.LAVA);
        map.setTyle(5, 3, TyleType.WALL);
        map.setTyle(6, 3, TyleType.WALL);

        map.setTyle(0, 4, TyleType.WALL);
        map.setTyle(1, 4, TyleType.WALL);
        map.setTyle(2, 4, TyleType.WALL);
        map.setTyle(3, 4, TyleType.WALL);
        map.setTyle(4, 4, TyleType.WALL);
        map.setTyle(5, 4, TyleType.WALL);
        map.setTyle(6, 4, TyleType.WALL);

        return map;
    }

    public static LabyrinthMap simpleMap2()
    {
        LabyrinthMap map = new LabyrinthMap(8, 5, 1);
        map.setPlayerStartY(2);
        map.setPlayerStartX(1);

        map.setBatteryPos(3, 3, 0);

        map.setTyle(0, 0, TyleType.WALL);
        map.setTyle(1, 0, TyleType.WALL);
        map.setTyle(2, 0, TyleType.WALL);
        map.setTyle(3, 0, TyleType.WALL);
        map.setTyle(4, 0, TyleType.WALL);
        map.setTyle(5, 0, TyleType.WALL);
        map.setTyle(6, 0, TyleType.WALL);
        map.setTyle(7, 0, TyleType.WALL);

        map.setTyle(0, 1, TyleType.WALL);
        map.setTyle(1, 1, TyleType.WALL);
        map.setTyle(2, 1, TyleType.LAVA);
        map.setTyle(3, 1, TyleType.WALL);
        map.setTyle(4, 1, TyleType.LAVA);
        map.setTyle(5, 1, TyleType.WALL);
        map.setTyle(6, 1, TyleType.WALL);
        map.setTyle(7, 1, TyleType.WALL);

        map.setTyle(0, 2, TyleType.WALL);
        map.setTyle(1, 2, TyleType.EMPTY);
        map.setTyle(2, 2, TyleType.EMPTY);
        map.setTyle(3, 2, TyleType.EMPTY);
        map.setTyle(4, 2, TyleType.EMPTY);
        map.setTyle(5, 2, TyleType.EMPTY);
        map.setTyle(6, 2, TyleType.EMPTY);
        map.setTyle(7, 2, TyleType.WALL);

        map.setTyle(0, 3, TyleType.WALL);
        map.setTyle(1, 3, TyleType.WALL);
        map.setTyle(2, 3, TyleType.LAVA);
        map.setTyle(3, 3, TyleType.EMPTY);
        map.setTyle(4, 3, TyleType.LAVA);
        map.setTyle(5, 3, TyleType.WALL);
        map.setTyle(6, 3, TyleType.WALL);
        map.setTyle(7, 3, TyleType.WALL);

        map.setTyle(0, 4, TyleType.WALL);
        map.setTyle(1, 4, TyleType.WALL);
        map.setTyle(2, 4, TyleType.WALL);
        map.setTyle(3, 4, TyleType.WALL);
        map.setTyle(4, 4, TyleType.WALL);
        map.setTyle(5, 4, TyleType.WALL);
        map.setTyle(6, 4, TyleType.WALL);
        map.setTyle(7, 4, TyleType.WALL);

        return map;
    }

    public static LabyrinthMap mediumMap()
    {
        LabyrinthMap map = new LabyrinthMap(10, 10, 4);
        map.setPlayerStartY(2);
        map.setPlayerStartX(1);

        map.setBatteryPos(3, 3, 0);
        map.setBatteryPos(3, 4, 1);
        map.setBatteryPos(3, 5, 2);
        map.setBatteryPos(3, 6, 3);

        map.setTyle(0, 0, TyleType.WALL);
        map.setTyle(1, 0, TyleType.WALL);
        map.setTyle(2, 0, TyleType.WALL);
        map.setTyle(3, 0, TyleType.WALL);
        map.setTyle(4, 0, TyleType.WALL);
        map.setTyle(5, 0, TyleType.WALL);
        map.setTyle(6, 0, TyleType.WALL);
        map.setTyle(7, 0, TyleType.WALL);
        map.setTyle(8, 0, TyleType.WALL);
        map.setTyle(9, 0, TyleType.WALL);


        map.setTyle(0, 1, TyleType.WALL);
        map.setTyle(1, 1, TyleType.EMPTY);
        map.setTyle(2, 1, TyleType.EMPTY);
        map.setTyle(3, 1, TyleType.EMPTY);
        map.setTyle(4, 1, TyleType.EMPTY);
        map.setTyle(5, 1, TyleType.EMPTY);
        map.setTyle(6, 1, TyleType.EMPTY);
        map.setTyle(7, 1, TyleType.EMPTY);
        map.setTyle(8, 1, TyleType.EMPTY);
        map.setTyle(9, 1, TyleType.WALL);

        map.setTyle(0, 2, TyleType.WALL);
        map.setTyle(1, 2, TyleType.EMPTY);
        map.setTyle(2, 2, TyleType.WALL);
        map.setTyle(3, 2, TyleType.WALL);
        map.setTyle(4, 2, TyleType.EMPTY);
        map.setTyle(5, 2, TyleType.EMPTY);
        map.setTyle(6, 2, TyleType.WALL);
        map.setTyle(7, 2, TyleType.WALL);
        map.setTyle(8, 2, TyleType.EMPTY);
        map.setTyle(9, 2, TyleType.WALL);

        map.setTyle(0, 3, TyleType.WALL);
        map.setTyle(1, 3, TyleType.EMPTY);
        map.setTyle(2, 3, TyleType.WALL);
        map.setTyle(3, 3, TyleType.LAVA);
        map.setTyle(4, 3, TyleType.EMPTY);
        map.setTyle(5, 3, TyleType.EMPTY);
        map.setTyle(6, 3, TyleType.LAVA);
        map.setTyle(7, 3, TyleType.WALL);
        map.setTyle(8, 3, TyleType.EMPTY);
        map.setTyle(9, 3, TyleType.WALL);

        map.setTyle(0, 4, TyleType.WALL);
        map.setTyle(1, 4, TyleType.EMPTY);
        map.setTyle(2, 4, TyleType.EMPTY);
        map.setTyle(3, 4, TyleType.EMPTY);
        map.setTyle(4, 4, TyleType.LAVA);
        map.setTyle(5, 4, TyleType.LAVA);
        map.setTyle(6, 4, TyleType.EMPTY);
        map.setTyle(7, 4, TyleType.EMPTY);
        map.setTyle(8, 4, TyleType.EMPTY);
        map.setTyle(9, 4, TyleType.WALL);

        map.setTyle(0, 5, TyleType.WALL);
        map.setTyle(1, 5, TyleType.EMPTY);
        map.setTyle(2, 5, TyleType.EMPTY);
        map.setTyle(3, 5, TyleType.EMPTY);
        map.setTyle(4, 5, TyleType.LAVA);
        map.setTyle(5, 5, TyleType.LAVA);
        map.setTyle(6, 5, TyleType.EMPTY);
        map.setTyle(7, 5, TyleType.EMPTY);
        map.setTyle(8, 5, TyleType.EMPTY);
        map.setTyle(9, 5, TyleType.WALL);

        map.setTyle(0, 6, TyleType.WALL);
        map.setTyle(1, 6, TyleType.EMPTY);
        map.setTyle(2, 6, TyleType.WALL);
        map.setTyle(3, 6, TyleType.LAVA);
        map.setTyle(4, 6, TyleType.EMPTY);
        map.setTyle(5, 6, TyleType.EMPTY);
        map.setTyle(6, 6, TyleType.LAVA);
        map.setTyle(7, 6, TyleType.WALL);
        map.setTyle(8, 6, TyleType.EMPTY);
        map.setTyle(9, 6, TyleType.WALL);

        map.setTyle(0, 7, TyleType.WALL);
        map.setTyle(1, 7, TyleType.EMPTY);
        map.setTyle(2, 7, TyleType.WALL);
        map.setTyle(3, 7, TyleType.WALL);
        map.setTyle(4, 7, TyleType.EMPTY);
        map.setTyle(5, 7, TyleType.EMPTY);
        map.setTyle(6, 7, TyleType.WALL);
        map.setTyle(7, 7, TyleType.WALL);
        map.setTyle(8, 7, TyleType.EMPTY);
        map.setTyle(9, 7, TyleType.WALL);

        map.setTyle(0, 8, TyleType.WALL);
        map.setTyle(1, 8, TyleType.EMPTY);
        map.setTyle(2, 8, TyleType.EMPTY);
        map.setTyle(3, 8, TyleType.EMPTY);
        map.setTyle(4, 8, TyleType.EMPTY);
        map.setTyle(5, 8, TyleType.EMPTY);
        map.setTyle(6, 8, TyleType.EMPTY);
        map.setTyle(7, 8, TyleType.EMPTY);
        map.setTyle(8, 8, TyleType.EMPTY);
        map.setTyle(9, 8, TyleType.WALL);

        map.setTyle(0, 9, TyleType.WALL);
        map.setTyle(1, 9, TyleType.WALL);
        map.setTyle(2, 9, TyleType.WALL);
        map.setTyle(3, 9, TyleType.WALL);
        map.setTyle(4, 9, TyleType.WALL);
        map.setTyle(5, 9, TyleType.WALL);
        map.setTyle(6, 9, TyleType.WALL);
        map.setTyle(7, 9, TyleType.WALL);
        map.setTyle(8, 9, TyleType.WALL);
        map.setTyle(9, 9, TyleType.WALL);


        return map;
    }
}
