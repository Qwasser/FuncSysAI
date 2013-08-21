import labyrinth.game.LabyrinthGame;
import labyrinth.game.LabyrinthUI;
import labyrinth.level.LabyrinthMap;
import labyrinth.level.TyleType;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 15.08.13
 * Time: 5:23
 * To change this template use File | Settings | File Templates.
 */
public class NewGame {

    public static void main(String[] args)
    {


        LabyrinthMap map = new LabyrinthMap(7, 5);
        //LabyrinthGame theGame = new LabyrinthGame(map);
        map.setWidth(7);
        map.setHeight(5);
        map.setPlayerStartY(2);
        map.setPlayerStartX(1);

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


        new LabyrinthUI().start();


    }
}
