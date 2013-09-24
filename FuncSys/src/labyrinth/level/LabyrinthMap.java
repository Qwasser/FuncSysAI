package labyrinth.level;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 17.08.13
 * Time: 20:56
 * To change this template use File | Settings | File Templates.
 */
public class LabyrinthMap
{
    int width;
    int height;

    int playerStartX;
    int playerStartY;

    int batteryCount;

    int [] batteryX;
    int [] batteryY;

    private TileType[][] map;
    private int[][] batteryLocation;

    public void setBatteryPos(int x, int y, int num)
    {
        if (num > this.batteryY.length - 1)
            throw new ArrayIndexOutOfBoundsException();
        this.batteryX[num] = x;
        this.batteryY[num] = y;

        this.batteryLocation[x][y] = num + 1;
    }


    public void setWidth(int width)
    {
        this.width = width;
    }

    public int getWidth()
    {
        return this.width;
    }

    public void setHeight(int height)
    {
        this.height = height;
    }

    public int getHeight()
    {
        return this.height;
    }

    public void setPlayerStartX(int x)
    {
        this.playerStartX = x;
    }

    public void setPlayerStartY(int y)
    {
        this.playerStartY = y;
    }

    public int getPlayerStartX()
    {
        return this.playerStartX;
    }

    public int getPlayerStartY()
    {
        return this.playerStartY;
    }

    public int getBatteryStartX(int num)
    {
        if (num > this.batteryX.length - 1)
            throw new ArrayIndexOutOfBoundsException();
        return this.batteryX[num];
    }

    public int getBatteryStartY(int num)
    {
        if (num > this.batteryY.length - 1)
            throw new ArrayIndexOutOfBoundsException();
        return this.batteryY[num];
    }

    public boolean ifTileHasBattery(int x, int y){
        return this.batteryLocation[x][y] > 0;
    }

    public int getBatteryNum(int x, int y){
        return this.batteryLocation[x][y] - 1;
    }

    public int getBatteryCount()
    {
        return this.batteryCount;
    }

    public TileType getCellType(int x, int y)
    {
        if ((x >= width) || (x < 0) ||
                (y >= height) || (y < 0))
        {
            return TileType.WALL;
        }
        return map[x][y];
    }

    public LabyrinthMap(int width, int height, int batteryCount)
    {
        this.batteryCount = batteryCount;

        this.width = width;
        this.height = height;

        this.batteryY = new int[batteryCount];
        this.batteryX = new  int[batteryCount];

        this.map = new TileType[width][height];

        this.batteryLocation = new int[width][height];
        for (int i = 0; i < width; i++)
            for (int j = 0; j < height; j++)
                this.batteryLocation[i][j] = 0;

    }

    public void setTyle(int x, int y, TileType tyle)
    {
        if (x >= width || y >= height)
        {
            throw new ArrayIndexOutOfBoundsException();
        }
        this.map[x][y] = tyle;
    }

    public byte getTyleID(int x, int y)
    {
        switch (getCellType(x, y)){
            case EMPTY:
                return Tile.FLOOR.id;
            case WALL:
                return wallTileID(x, y);
            case LAVA:
                return Tile.LAVA.id;
            case GOLD:
                return Tile.VOID.id;
        }
        return Tile.VOID.id;
    }

    private byte wallTileID(int x, int y)
    {
        StringBuilder str = new StringBuilder();
        TileType tile;

        tile = getCellType(x, y - 1);
        if (tile == TileType.WALL)
            str.append("1");
        else str.append("0");

        tile = getCellType(x +1, y - 1);
        if (tile == TileType.WALL)
            str.append("1");
        else str.append("0");

        tile = getCellType(x + 1, y);
        if (tile == TileType.WALL)
            str.append("1");
        else str.append("0");

        tile = getCellType(x + 1, y + 1);
        if (tile == TileType.WALL)
            str.append("1");
        else str.append("0");

        tile = getCellType(x, y + 1);
        if (tile == TileType.WALL)
            str.append("1");
        else str.append("0");

        tile = getCellType(x - 1, y + 1);
        if (tile == TileType.WALL)
            str.append("1");
        else str.append("0");

        tile = getCellType(x - 1, y);
        if (tile == TileType.WALL)
            str.append("1");
        else str.append("0");

        tile = getCellType(x - 1, y - 1);
        if (tile == TileType.WALL)
            str.append("1");
        else str.append("0");

        String res = str.toString();
        if (ifTypesEqual(res, "1*0*0*11"))
            return Tile.WALL[1].id;
        if (ifTypesEqual(res, "111*0*0*"))
            return Tile.WALL[2].id;
        if (ifTypesEqual(res, "0*0*111*"))
            return Tile.WALL[3].id;
        if (ifTypesEqual(res, "0*111*0*"))
            return Tile.WALL[4].id;

        if (res.equals("11111011"))
            return Tile.WALL[5].id;
        if (res.equals("11101111"))
            return Tile.WALL[6].id;
        if (res.equals("11111110"))
            return Tile.WALL[7].id;
        if (res.equals("10111111"))
            return Tile.WALL[8].id;

        if (res.equals("11111010"))
            return Tile.WALL[9].id;
        if (res.equals("11101011"))
            return Tile.WALL[10].id;
        if (res.equals("10101111"))
            return Tile.WALL[11].id;
        if (res.equals("10111110"))
            return Tile.WALL[12].id;

        if (res.equals("11101010"))
            return Tile.WALL[13].id;
        if (res.equals("10101011"))
            return Tile.WALL[14].id;
        if (res.equals("10101110"))
            return Tile.WALL[15].id;
        if (res.equals("10111010"))
            return Tile.WALL[16].id;

        if (ifTypesEqual(res, "1*0*0*0*"))
            return Tile.WALL[17].id;

        if (ifTypesEqual(res, "0*1*0*0*"))
            return Tile.WALL[18].id;

        if (ifTypesEqual(res, "0*0*1*0*"))
            return Tile.WALL[19].id;

        if (ifTypesEqual(res, "0*0*0*1*"))
            return Tile.WALL[20].id;

        if (ifTypesEqual(res, "10101010"))
            return Tile.WALL[21].id;

        if (ifTypesEqual(res, "01010101"))
            return Tile.WALL[22].id;

        if (ifTypesEqual(res, "1*0*1*0*"))
            return Tile.WALL[23].id;

        if (ifTypesEqual(res, "0*1*0*1*"))
            return Tile.WALL[24].id;

        if (ifTypesEqual(res, "1*0*0*10"))
            return Tile.WALL[25].id;

        if (ifTypesEqual(res, "101*0*0*"))
            return Tile.WALL[26].id;

        if (ifTypesEqual(res, "0*0*101*"))
            return Tile.WALL[27].id;

        if (ifTypesEqual(res, "0*101*0*0"))
            return Tile.WALL[28].id;

        if (ifTypesEqual(res, "111*0*11"))
            return Tile.WALL[29].id;

        if (ifTypesEqual(res, "11111*0*"))
            return Tile.WALL[30].id;

        if (ifTypesEqual(res, "1*0*1111"))
            return Tile.WALL[31].id;

        if (ifTypesEqual(res, "0*11111*"))
            return Tile.WALL[32].id;

        if (ifTypesEqual(res, "111*0*10"))
            return Tile.WALL[33].id;

        if (ifTypesEqual(res, "11101*0*"))
            return Tile.WALL[34].id;

        if (ifTypesEqual(res, "1*0*1110"))
            return Tile.WALL[35].id;

        if (ifTypesEqual(res, "0*10111*"))
            return Tile.WALL[36].id;

        if (ifTypesEqual(res, "101*0*11"))
            return Tile.WALL[37].id;

        if (ifTypesEqual(res, "10111*0*"))
            return Tile.WALL[38].id;

        if (ifTypesEqual(res, "1*0*1011"))
            return Tile.WALL[39].id;

        if (ifTypesEqual(res, "0*11101*"))
            return Tile.WALL[40].id;

        if (ifTypesEqual(res, "101*0*10"))
            return Tile.WALL[41].id;

        if (ifTypesEqual(res, "10101*0*"))
            return Tile.WALL[42].id;

        if (ifTypesEqual(res, "1*0*1010"))
            return Tile.WALL[43].id;

        if (ifTypesEqual(res, "0*10101*"))
            return Tile.WALL[44].id;

        if (ifTypesEqual(res, "10111011"))
            return Tile.WALL[45].id;

        if (ifTypesEqual(res, "11101110"))
            return Tile.WALL[46].id;

        //System.out.println(str);
        return Tile.WALL[0].id;

    }

    private boolean ifTypesEqual(String str, String type)
    {
        for (int i = 0; i < 8; i++)
        {
            if (str.charAt(i) == type.charAt(i) || type.charAt(i) == '*')
            {}
            else
                return false;
        }
        return true;
    }
}
