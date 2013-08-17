package enteties;

import labyrinth.gfx.Screen;
import level.Level;
import InputHandler;
/**
 * Created with IntelliJ IDEA.
 * User: Users
 * Date: 17.08.13
 * Time: 16:11
 * To change this template use File | Settings | File Templates.
 */
public class Player extends Mob {
    private final InputHandler input;

    public Player(Level level, int x, int y, InputHandler input) {
        super(level, "Player", x, y, 1);
        this.input = input;
    }

    @Override
    public boolean hasCollided(int xa, int ya) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void tick() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void render(Screen screen) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
