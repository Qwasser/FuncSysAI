package labyrinth.level;

import labyrinth.gfx.Screen;

/**
 * Created with IntelliJ IDEA.
 * User: Users
 * Date: 19.09.13
 * Time: 18:59
 * To change this template use File | Settings | File Templates.
 */
public class WallTile extends Tile {
    protected int tileId;
    protected  int tileColour;
    //protected WallType type;


    public WallTile(int id, int x, int y, int tileColour) {
        super(id, false, false);
        //this.type = type;
        this.tileId = x+y*16;
        this.tileColour = tileColour;
    }

    @Override
    public void render(Screen screen, Level level, int x, int y) {
        screen.render(x, y, tileId, tileColour, 4);

    }
}
