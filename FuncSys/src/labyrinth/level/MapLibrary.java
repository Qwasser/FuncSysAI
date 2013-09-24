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

        map.setTyle(0, 0, TileType.WALL);
        map.setTyle(1, 0, TileType.WALL);
        map.setTyle(2, 0, TileType.WALL);
        map.setTyle(3, 0, TileType.WALL);
        map.setTyle(4, 0, TileType.WALL);
        map.setTyle(5, 0, TileType.WALL);
        map.setTyle(6, 0, TileType.WALL);

        map.setTyle(0, 1, TileType.WALL);
        map.setTyle(1, 1, TileType.WALL);
        map.setTyle(2, 1, TileType.LAVA);
        map.setTyle(3, 1, TileType.WALL);
        map.setTyle(4, 1, TileType.LAVA);
        map.setTyle(5, 1, TileType.WALL);
        map.setTyle(6, 1, TileType.WALL);

        map.setTyle(0, 2, TileType.WALL);
        map.setTyle(1, 2, TileType.EMPTY);
        map.setTyle(2, 2, TileType.EMPTY);
        map.setTyle(3, 2, TileType.EMPTY);
        map.setTyle(4, 2, TileType.EMPTY);
        map.setTyle(5, 2, TileType.EMPTY);
        map.setTyle(6, 2, TileType.WALL);

        map.setTyle(0, 3, TileType.WALL);
        map.setTyle(1, 3, TileType.WALL);
        map.setTyle(2, 3, TileType.LAVA);
        map.setTyle(3, 3, TileType.WALL);
        map.setTyle(4, 3, TileType.LAVA);
        map.setTyle(5, 3, TileType.WALL);
        map.setTyle(6, 3, TileType.WALL);

        map.setTyle(0, 4, TileType.WALL);
        map.setTyle(1, 4, TileType.WALL);
        map.setTyle(2, 4, TileType.WALL);
        map.setTyle(3, 4, TileType.WALL);
        map.setTyle(4, 4, TileType.WALL);
        map.setTyle(5, 4, TileType.WALL);
        map.setTyle(6, 4, TileType.WALL);

        return map;
    }

    public static LabyrinthMap simpleMap2()
    {
        LabyrinthMap map = new LabyrinthMap(8, 5, 1);
        map.setPlayerStartY(2);
        map.setPlayerStartX(1);

        map.setBatteryPos(3, 3, 0);

        map.setTyle(0, 0, TileType.WALL);
        map.setTyle(1, 0, TileType.EMPTY);
        map.setTyle(2, 0, TileType.EMPTY);
        map.setTyle(3, 0, TileType.WALL);
        map.setTyle(4, 0, TileType.WALL);
        map.setTyle(5, 0, TileType.WALL);
        map.setTyle(6, 0, TileType.WALL);
        map.setTyle(7, 0, TileType.WALL);

        map.setTyle(0, 1, TileType.WALL);
        map.setTyle(1, 1, TileType.WALL);
        map.setTyle(2, 1, TileType.WALL);
        map.setTyle(3, 1, TileType.WALL);
        map.setTyle(4, 1, TileType.WALL);
        map.setTyle(5, 1, TileType.WALL);
        map.setTyle(6, 1, TileType.WALL);
        map.setTyle(7, 1, TileType.WALL);

        map.setTyle(0, 2, TileType.WALL);
        map.setTyle(1, 2, TileType.EMPTY);
        map.setTyle(2, 2, TileType.WALL);
        map.setTyle(3, 2, TileType.WALL);
        map.setTyle(4, 2, TileType.EMPTY);
        map.setTyle(5, 2, TileType.EMPTY);
        map.setTyle(6, 2, TileType.EMPTY);
        map.setTyle(7, 2, TileType.WALL);

        map.setTyle(0, 3, TileType.WALL);
        map.setTyle(1, 3, TileType.WALL);
        map.setTyle(2, 3, TileType.WALL);
        map.setTyle(3, 3, TileType.EMPTY);
        map.setTyle(4, 3, TileType.WALL);
        map.setTyle(5, 3, TileType.WALL);
        map.setTyle(6, 3, TileType.WALL);
        map.setTyle(7, 3, TileType.WALL);

        map.setTyle(0, 4, TileType.WALL);
        map.setTyle(1, 4, TileType.WALL);
        map.setTyle(2, 4, TileType.WALL);
        map.setTyle(3, 4, TileType.WALL);
        map.setTyle(4, 4, TileType.WALL);
        map.setTyle(5, 4, TileType.WALL);
        map.setTyle(6, 4, TileType.WALL);
        map.setTyle(7, 4, TileType.WALL);

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

        map.setTyle(0, 0, TileType.WALL);
        map.setTyle(1, 0, TileType.WALL);
        map.setTyle(2, 0, TileType.WALL);
        map.setTyle(3, 0, TileType.WALL);
        map.setTyle(4, 0, TileType.WALL);
        map.setTyle(5, 0, TileType.WALL);
        map.setTyle(6, 0, TileType.WALL);
        map.setTyle(7, 0, TileType.WALL);
        map.setTyle(8, 0, TileType.WALL);
        map.setTyle(9, 0, TileType.WALL);


        map.setTyle(0, 1, TileType.WALL);
        map.setTyle(1, 1, TileType.EMPTY);
        map.setTyle(2, 1, TileType.EMPTY);
        map.setTyle(3, 1, TileType.EMPTY);
        map.setTyle(4, 1, TileType.EMPTY);
        map.setTyle(5, 1, TileType.EMPTY);
        map.setTyle(6, 1, TileType.EMPTY);
        map.setTyle(7, 1, TileType.EMPTY);
        map.setTyle(8, 1, TileType.EMPTY);
        map.setTyle(9, 1, TileType.WALL);

        map.setTyle(0, 2, TileType.WALL);
        map.setTyle(1, 2, TileType.EMPTY);
        map.setTyle(2, 2, TileType.WALL);
        map.setTyle(3, 2, TileType.WALL);
        map.setTyle(4, 2, TileType.EMPTY);
        map.setTyle(5, 2, TileType.EMPTY);
        map.setTyle(6, 2, TileType.WALL);
        map.setTyle(7, 2, TileType.WALL);
        map.setTyle(8, 2, TileType.EMPTY);
        map.setTyle(9, 2, TileType.WALL);

        map.setTyle(0, 3, TileType.WALL);
        map.setTyle(1, 3, TileType.EMPTY);
        map.setTyle(2, 3, TileType.WALL);
        map.setTyle(3, 3, TileType.LAVA);
        map.setTyle(4, 3, TileType.EMPTY);
        map.setTyle(5, 3, TileType.EMPTY);
        map.setTyle(6, 3, TileType.LAVA);
        map.setTyle(7, 3, TileType.WALL);
        map.setTyle(8, 3, TileType.EMPTY);
        map.setTyle(9, 3, TileType.WALL);

        map.setTyle(0, 4, TileType.WALL);
        map.setTyle(1, 4, TileType.EMPTY);
        map.setTyle(2, 4, TileType.EMPTY);
        map.setTyle(3, 4, TileType.EMPTY);
        map.setTyle(4, 4, TileType.LAVA);
        map.setTyle(5, 4, TileType.LAVA);
        map.setTyle(6, 4, TileType.EMPTY);
        map.setTyle(7, 4, TileType.EMPTY);
        map.setTyle(8, 4, TileType.EMPTY);
        map.setTyle(9, 4, TileType.WALL);

        map.setTyle(0, 5, TileType.WALL);
        map.setTyle(1, 5, TileType.EMPTY);
        map.setTyle(2, 5, TileType.EMPTY);
        map.setTyle(3, 5, TileType.EMPTY);
        map.setTyle(4, 5, TileType.LAVA);
        map.setTyle(5, 5, TileType.LAVA);
        map.setTyle(6, 5, TileType.EMPTY);
        map.setTyle(7, 5, TileType.EMPTY);
        map.setTyle(8, 5, TileType.EMPTY);
        map.setTyle(9, 5, TileType.WALL);

        map.setTyle(0, 6, TileType.WALL);
        map.setTyle(1, 6, TileType.EMPTY);
        map.setTyle(2, 6, TileType.WALL);
        map.setTyle(3, 6, TileType.LAVA);
        map.setTyle(4, 6, TileType.EMPTY);
        map.setTyle(5, 6, TileType.EMPTY);
        map.setTyle(6, 6, TileType.LAVA);
        map.setTyle(7, 6, TileType.WALL);
        map.setTyle(8, 6, TileType.EMPTY);
        map.setTyle(9, 6, TileType.WALL);

        map.setTyle(0, 7, TileType.WALL);
        map.setTyle(1, 7, TileType.EMPTY);
        map.setTyle(2, 7, TileType.WALL);
        map.setTyle(3, 7, TileType.WALL);
        map.setTyle(4, 7, TileType.EMPTY);
        map.setTyle(5, 7, TileType.EMPTY);
        map.setTyle(6, 7, TileType.WALL);
        map.setTyle(7, 7, TileType.WALL);
        map.setTyle(8, 7, TileType.EMPTY);
        map.setTyle(9, 7, TileType.WALL);

        map.setTyle(0, 8, TileType.WALL);
        map.setTyle(1, 8, TileType.EMPTY);
        map.setTyle(2, 8, TileType.EMPTY);
        map.setTyle(3, 8, TileType.EMPTY);
        map.setTyle(4, 8, TileType.EMPTY);
        map.setTyle(5, 8, TileType.EMPTY);
        map.setTyle(6, 8, TileType.EMPTY);
        map.setTyle(7, 8, TileType.EMPTY);
        map.setTyle(8, 8, TileType.EMPTY);
        map.setTyle(9, 8, TileType.WALL);

        map.setTyle(0, 9, TileType.WALL);
        map.setTyle(1, 9, TileType.WALL);
        map.setTyle(2, 9, TileType.WALL);
        map.setTyle(3, 9, TileType.WALL);
        map.setTyle(4, 9, TileType.WALL);
        map.setTyle(5, 9, TileType.WALL);
        map.setTyle(6, 9, TileType.WALL);
        map.setTyle(7, 9, TileType.WALL);
        map.setTyle(8, 9, TileType.WALL);
        map.setTyle(9, 9, TileType.WALL);


        return map;
    }

    public static LabyrinthMap mediumMapWithoutLava()
    {
        LabyrinthMap map = new LabyrinthMap(10, 10, 4);
        map.setPlayerStartY(2);
        map.setPlayerStartX(1);

        map.setBatteryPos(3, 3, 0);
        map.setBatteryPos(3, 6, 1);
        map.setBatteryPos(6, 3, 2);
        map.setBatteryPos(6, 6, 3);

        map.setTyle(0, 0, TileType.WALL);
        map.setTyle(1, 0, TileType.WALL);
        map.setTyle(2, 0, TileType.WALL);
        map.setTyle(3, 0, TileType.WALL);
        map.setTyle(4, 0, TileType.WALL);
        map.setTyle(5, 0, TileType.WALL);
        map.setTyle(6, 0, TileType.WALL);
        map.setTyle(7, 0, TileType.WALL);
        map.setTyle(8, 0, TileType.WALL);
        map.setTyle(9, 0, TileType.WALL);


        map.setTyle(0, 1, TileType.WALL);
        map.setTyle(1, 1, TileType.EMPTY);
        map.setTyle(2, 1, TileType.EMPTY);
        map.setTyle(3, 1, TileType.EMPTY);
        map.setTyle(4, 1, TileType.EMPTY);
        map.setTyle(5, 1, TileType.EMPTY);
        map.setTyle(6, 1, TileType.EMPTY);
        map.setTyle(7, 1, TileType.EMPTY);
        map.setTyle(8, 1, TileType.EMPTY);
        map.setTyle(9, 1, TileType.WALL);

        map.setTyle(0, 2, TileType.WALL);
        map.setTyle(1, 2, TileType.EMPTY);
        map.setTyle(2, 2, TileType.WALL);
        map.setTyle(3, 2, TileType.WALL);
        map.setTyle(4, 2, TileType.EMPTY);
        map.setTyle(5, 2, TileType.EMPTY);
        map.setTyle(6, 2, TileType.WALL);
        map.setTyle(7, 2, TileType.WALL);
        map.setTyle(8, 2, TileType.EMPTY);
        map.setTyle(9, 2, TileType.WALL);

        map.setTyle(0, 3, TileType.WALL);
        map.setTyle(1, 3, TileType.EMPTY);
        map.setTyle(2, 3, TileType.WALL);
        map.setTyle(3, 3, TileType.EMPTY);
        map.setTyle(4, 3, TileType.EMPTY);
        map.setTyle(5, 3, TileType.EMPTY);
        map.setTyle(6, 3, TileType.EMPTY);
        map.setTyle(7, 3, TileType.WALL);
        map.setTyle(8, 3, TileType.EMPTY);
        map.setTyle(9, 3, TileType.WALL);

        map.setTyle(0, 4, TileType.WALL);
        map.setTyle(1, 4, TileType.EMPTY);
        map.setTyle(2, 4, TileType.EMPTY);
        map.setTyle(3, 4, TileType.EMPTY);
        map.setTyle(4, 4, TileType.EMPTY);
        map.setTyle(5, 4, TileType.EMPTY);
        map.setTyle(6, 4, TileType.EMPTY);
        map.setTyle(7, 4, TileType.EMPTY);
        map.setTyle(8, 4, TileType.EMPTY);
        map.setTyle(9, 4, TileType.WALL);

        map.setTyle(0, 5, TileType.WALL);
        map.setTyle(1, 5, TileType.EMPTY);
        map.setTyle(2, 5, TileType.EMPTY);
        map.setTyle(3, 5, TileType.EMPTY);
        map.setTyle(4, 5, TileType.EMPTY);
        map.setTyle(5, 5, TileType.EMPTY);
        map.setTyle(6, 5, TileType.EMPTY);
        map.setTyle(7, 5, TileType.EMPTY);
        map.setTyle(8, 5, TileType.EMPTY);
        map.setTyle(9, 5, TileType.WALL);

        map.setTyle(0, 6, TileType.WALL);
        map.setTyle(1, 6, TileType.EMPTY);
        map.setTyle(2, 6, TileType.WALL);
        map.setTyle(3, 6, TileType.EMPTY);
        map.setTyle(4, 6, TileType.EMPTY);
        map.setTyle(5, 6, TileType.EMPTY);
        map.setTyle(6, 6, TileType.EMPTY);
        map.setTyle(7, 6, TileType.WALL);
        map.setTyle(8, 6, TileType.EMPTY);
        map.setTyle(9, 6, TileType.WALL);

        map.setTyle(0, 7, TileType.WALL);
        map.setTyle(1, 7, TileType.EMPTY);
        map.setTyle(2, 7, TileType.WALL);
        map.setTyle(3, 7, TileType.WALL);
        map.setTyle(4, 7, TileType.EMPTY);
        map.setTyle(5, 7, TileType.EMPTY);
        map.setTyle(6, 7, TileType.WALL);
        map.setTyle(7, 7, TileType.WALL);
        map.setTyle(8, 7, TileType.EMPTY);
        map.setTyle(9, 7, TileType.WALL);

        map.setTyle(0, 8, TileType.WALL);
        map.setTyle(1, 8, TileType.EMPTY);
        map.setTyle(2, 8, TileType.EMPTY);
        map.setTyle(3, 8, TileType.EMPTY);
        map.setTyle(4, 8, TileType.EMPTY);
        map.setTyle(5, 8, TileType.EMPTY);
        map.setTyle(6, 8, TileType.EMPTY);
        map.setTyle(7, 8, TileType.EMPTY);
        map.setTyle(8, 8, TileType.EMPTY);
        map.setTyle(9, 8, TileType.WALL);

        map.setTyle(0, 9, TileType.WALL);
        map.setTyle(1, 9, TileType.WALL);
        map.setTyle(2, 9, TileType.WALL);
        map.setTyle(3, 9, TileType.WALL);
        map.setTyle(4, 9, TileType.WALL);
        map.setTyle(5, 9, TileType.WALL);
        map.setTyle(6, 9, TileType.WALL);
        map.setTyle(7, 9, TileType.WALL);
        map.setTyle(8, 9, TileType.WALL);
        map.setTyle(9, 9, TileType.WALL);


        return map;
    }
}
