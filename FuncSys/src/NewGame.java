import labyrinth.game.GameState;
import labyrinth.game.LabyrinthGame;
import labyrinth.game.LabyrinthUI;
import labyrinth.level.LabyrinthMap;
import labyrinth.level.MapLibrary;
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
        GameState state = new GameState();
        LabyrinthMap map = MapLibrary.simpleMap1();
        LabyrinthGame game = new LabyrinthGame(map, state);

        //System.out.println(state.getPlayerXinPixels());
        new LabyrinthUI(state).start();
        game.start();

    }
}
