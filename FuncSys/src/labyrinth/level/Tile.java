package labyrinth.level;

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
    public static  final Tile VOID = new BasicTile(0, 16, 3, Colours.get(000, 325, 115, 555));
    public static  final Tile LAVA = new BasicTile(1, 13, 3, Colours.get(222, 540, 520, 310));

    private static final int WallCols = Colours.get(111, 222, 333, 243);

    public static  final Tile [] WALL = {new BasicTile(2, 3, 12, WallCols), // closed

                                        new WallTile(3, 0, 10, WallCols), //corner
                                        new WallTile(4, 1, 10, WallCols), //corner
                                        new WallTile(5, 2, 10, WallCols), //corner
                                        new WallTile(6, 3, 10, WallCols), //corner

                                        new WallTile(7, 0, 13, WallCols), //inner corner
                                        new WallTile(8, 1, 13, WallCols), //inner corner
                                        new WallTile(9, 2, 13, WallCols), //inner corner
                                        new WallTile(10, 3, 13, WallCols), //inner corner

                                        new WallTile(11, 0, 14, WallCols), //2-inner corner
                                        new WallTile(12, 1, 14, WallCols), //2-inner corner
                                        new WallTile(13, 2, 14, WallCols), //2-inner corner
                                        new WallTile(14, 3, 14, WallCols), //2-inner corner

                                        new WallTile(15, 0, 15, WallCols), //3-inner corner
                                        new WallTile(16, 1, 15, WallCols), //3-inner corner
                                        new WallTile(17, 2, 15, WallCols), //3-inner corner
                                        new WallTile(18, 3, 15, WallCols), //3-inner corner

                                        new WallTile(19, 8, 10, WallCols), //3-side
                                        new WallTile(20, 9, 10, WallCols), //3-side
                                        new WallTile(21, 10, 10, WallCols), //3-side
                                        new WallTile(22, 11, 10, WallCols), //3-side

                                        new WallTile(23, 8, 11, WallCols), //4-side
                                        new WallTile(24, 9, 11, WallCols), //4-side
                                       new WallTile(25, 10, 11, WallCols), //2-side
                                        new WallTile(26, 11, 11, WallCols), //2-side

                                        new WallTile(27, 12, 9, WallCols), //corner+
                                        new WallTile(28, 13, 9, WallCols), //corner+
                                        new WallTile(29, 14, 9, WallCols), //corner+
                                        new WallTile(30, 15, 9, WallCols), //corner+

                                        new WallTile(31, 8, 12, WallCols), //side
                                        new WallTile(32, 9, 12, WallCols), //side
                                        new WallTile(33, 10, 12, WallCols), //side
                                        new WallTile(34, 11, 12, WallCols), //side

                                        new WallTile(35, 8, 13, WallCols), //L-side
                                        new WallTile(36, 9, 13, WallCols), //L-side
                                        new WallTile(37, 10, 13, WallCols), //L-side
                                        new WallTile(38, 11, 13, WallCols), //L-side

                                        new WallTile(39, 8, 14, WallCols), //R-side
                                        new WallTile(40, 9, 14, WallCols), //R-side
                                        new WallTile(41, 10, 14, WallCols), //R-side
                                        new WallTile(42, 11, 14, WallCols), //R-side

                                        new WallTile(43, 8, 15, WallCols), //F-side
                                        new WallTile(44, 9, 15, WallCols), //F-side
                                        new WallTile(45, 10, 15, WallCols), //F-side
                                        new WallTile(46, 11, 15, WallCols), //F-side

                                        new WallTile(47, 12, 8, WallCols), //d-corner
                                        new WallTile(48, 13, 8, WallCols), //d-corner
                                        new WallTile(49, 14, 8, WallCols), //d-corner
                                        new WallTile(50, 15, 8, WallCols), //d-corner

                                        };
    public static  final Tile FLOOR = new BasicTile(100, 0, 11, Colours.get(212, 101, 000, 111));
    public static  final Tile GRASS = new BasicTile(101, 2, 0, Colours.get(-1, 133, 141, 151));

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
