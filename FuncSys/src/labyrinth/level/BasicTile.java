package labyrinth.level;

import labyrinth.gfx.Screen;

/**
 * Created with IntelliJ IDEA.
 * User: Users
 * Date: 17.08.13
 * Time: 3:55
 * To change this template use File | Settings | File Templates.
 */
public class BasicTile extends Tile {
    protected int tileId;
    protected  int tileColour;


    public BasicTile(int id, int x, int y, int tileColour) {
        super(id, false, false);
        this.tileId = x+y*16;
        this.tileColour = tileColour;
    }

    @Override
    public void render(Screen screen, Level level, int x, int y) {
        screen.render(x, y, tileId, tileColour, 2);
        screen.render(x + 16, y, tileId, tileColour, 2);
        screen.render(x, y + 16, tileId, tileColour, 2);
        screen.render(x + 16, y + 16, tileId, tileColour, 2);
    }
}
