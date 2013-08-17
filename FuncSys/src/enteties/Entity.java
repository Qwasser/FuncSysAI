package enteties;

import labyrinth.gfx.Screen;
import level.Level;

/**
 * Created with IntelliJ IDEA.
 * User: Users
 * Date: 17.08.13
 * Time: 15:57
 * To change this template use File | Settings | File Templates.
 */
public abstract class Entity {
    public int x, y;
    protected Level level;

    public Entity(Level level){

    }

    public final void init(Level level){
        this.level = level;
    }

    public abstract void tick();

    public abstract void render(Screen screen);
}
