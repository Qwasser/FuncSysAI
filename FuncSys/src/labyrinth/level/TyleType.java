package labyrinth.level;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 12.08.13
 * Time: 22:51
 * To change this template use File | Settings | File Templates.
 */
public enum TyleType {
    EMPTY, WALL, LAVA, GOLD;

    public static Tile convertToTile(TyleType type)
    {
        switch (type){
            case EMPTY:
                return Tile.FLOOR;
            case WALL:
                return Tile.VOID;
            case LAVA:
                return Tile.LAVA;
            case GOLD:
                return Tile.VOID;
        }
        return Tile.VOID;
    }

}

