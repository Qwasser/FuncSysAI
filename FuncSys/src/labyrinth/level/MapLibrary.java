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
        LabyrinthMap map = new LabyrinthMap(7, 5);
        map.setPlayerStartY(2);
        map.setPlayerStartX(1);

        map.setBatteryStartX(5);
        map.setBatteryStartY(2);

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
        LabyrinthMap map = new LabyrinthMap(8, 5);
        map.setPlayerStartY(2);
        map.setPlayerStartX(1);

        map.setBatteryStartX(6);
        map.setBatteryStartY(2);

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
}
