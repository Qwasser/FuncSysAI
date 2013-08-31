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
        if ((x > width) || (x < 0) ||
                (y > height) || (y < 0))
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
    }

    public void setTyle(int x, int y, TileType tyle)
    {
        if (x >= width || y >= height)
        {
            throw new ArrayIndexOutOfBoundsException();
        }
        this.map[x][y] = tyle;
    }
}
