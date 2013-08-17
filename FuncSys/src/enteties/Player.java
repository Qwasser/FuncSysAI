package enteties;

import labyrinth.game.InputHandler;
import labyrinth.gfx.Colours;
import labyrinth.gfx.Screen;
import level.Level;

/**
 * Created with IntelliJ IDEA.
 * User: Users
 * Date: 17.08.13
 * Time: 16:11
 * To change this template use File | Settings | File Templates.
 */
public class Player extends Mob {
    private final InputHandler input;
    private int colour = Colours.get(-1, 111, 115, 510);
    public Player(Level level, int x, int y, InputHandler input) {
        super(level, "Player", x, y, 1);
        this.input = input;

    }

    @Override
    public boolean hasCollided(int xa, int ya) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    //private int x = 0, y = 0;
    @Override
    public void tick() {
        int xa = 0;
        int ya = 0;
        if (input.up.isPressed){
            ya--;
        }
        if (input.down.isPressed){
            ya++;
        }
        if (input.left.isPressed){
            xa--;
        }
        if (input.right.isPressed){
            xa++;
        }

        if(ya!=0 || xa!=0){
            move(xa, ya);

        }
    }

    @Override
    public void render(Screen screen) {
        int xTile = 0;
        int yTile = 2;

        int scale = 2;
        int modifier = 8 * scale;

        int xOffset = x - modifier/2;
        int yOffset = y - modifier/2 - 4;
        screen.render(xOffset , yOffset, xTile + yTile*32, colour, scale);
        //System.out.println("renders");
        screen.render(xOffset + modifier, yOffset, xTile + 1 + yTile*32, colour, scale);
        screen.render(xOffset, yOffset + modifier, xTile + (1 + yTile)*32, colour, scale);
        screen.render(xOffset + modifier, yOffset + modifier, xTile + 1 + (yTile+1)*32, colour, scale);

        //To change body of implemented methods use File | Settings | File Templates.
    }
}
