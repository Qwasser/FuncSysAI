package enteties;

import level.Level;

/**
 * Created with IntelliJ IDEA.
 * User: Users
 * Date: 17.08.13
 * Time: 16:00
 * To change this template use File | Settings | File Templates.
 */
public abstract class Mob extends Entity{

    protected String name;
    protected int speed;
    protected int numSteps = 0;
    protected int movingDir = 1;
    public Mob(Level level, String name, int x, int y, int speed) {
        super(level);
        this.name = name;
        this.x = x;
        this.y = y;
        this.speed = speed;
    }

    public void move(int x0, int y0){
        if (x0 != 0 && y0 !=0){
            move(x0, 0);
            move(y0,0);
            numSteps--;
            return;
        }
        numSteps++;
        if (!hasCollided(x0, y0)){
            if (y0 < 0)
                movingDir = 0;
            if (y0 > 0)
                movingDir = 1;
            if (x0 < 0)
                movingDir = 2;
            if (x0 > 0)
                movingDir = 3;
            x += x0*speed;
            y += y0*speed;
        }
    }

    public abstract boolean hasCollided(int xa, int ya);

    public String getName()
    {
        return this.name;
    }
}
