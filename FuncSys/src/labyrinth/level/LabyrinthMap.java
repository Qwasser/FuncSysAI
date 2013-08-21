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

    int batteryX;
    int batteryY;

    private TyleType[][] map;

    public void setBatteryPos(int x, int y)
    {
        this.batteryX = x;
        this.batteryY = y;
    }


    public void setWidth(int width)
    {
        this.width = width;
    }

    public void setHeight(int height)
    {
        this.height = height;
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

    public void setBatteryStartX(int x)
    {
        this.batteryX = x;
    }

    public void setBatteryStartY(int y)
    {
        this.batteryY = y;
    }

    public int getBatteryStartX()
    {
        return this.batteryX;
    }

    public int getBatteryStartY()
    {
        return this.batteryY;
    }



    public TyleType getCellType(int x, int y)
    {
        if ((x > width) || (x < 0) ||
                (y > height) || (y < 0))
        {
            return TyleType.WALL;
        }
        return map[x][y];
    }

    public LabyrinthMap(int width, int height)
    {
        this.width = width;
        this.height = height;

        this.map = new TyleType[width][height];
    }

    public void setTyle(int x, int y, TyleType tyle)
    {
        if (x >= width || y >= height)
        {
            throw new ArrayIndexOutOfBoundsException();
        }
        this.map[x][y] = tyle;
    }
}
