package level;

import labyrinth.gfx.Colours;
import labyrinth.gfx.Screen;

/**
 * Created with IntelliJ IDEA.
 * User: Users
 * Date: 17.08.13
 * Time: 3:45
 * To change this template use File | Settings | File Templates.
 */
public abstract class Tile {
    public static final Tile[] tiles = new Tile[256];
    public static  final Tile VOID = new BasicTile(0, 0, 0, Colours.get(000, -1, -1, -1));
    public static  final Tile LAVA = new BasicTile(1, 1, 0, Colours.get(-1, 510, 520, 530));
    public static  final Tile GRASS = new BasicTile(2, 2, 0, Colours.get(-1, 133, 141, 151));

    protected byte id;
    protected boolean solid;
    protected boolean emitter;

    public Tile(int id, boolean isSolid, boolean  isEmitter)
    {
        this.id = (byte)id;
        if(tiles[id] != null) throw new RuntimeException("Duplicate tile id" + id);

        this.solid = isSolid;
        this.emitter = isEmitter;
        tiles[id] = this;
    }
    public abstract void render(Screen screen, Level level, int x, int y);

    public byte getid() {
        return id;
    }
}
