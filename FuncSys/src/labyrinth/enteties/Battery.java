package labyrinth.enteties;

import labyrinth.game.InputHandler;
import labyrinth.gfx.Colours;
import labyrinth.gfx.Screen;
import labyrinth.level.Level;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 21.08.13
 * Time: 22:33
 * To change this template use File | Settings | File Templates.
 */
public class Battery extends Mob {

    private int colour = Colours.get(-1, 225, 141, 150);

    int xTile = 0;

    public Battery(Level level, int x, int y, InputHandler input) {
        super(level, "Battery", x, y, 1);
    }

    @Override
    public boolean hasCollided(int xa, int ya) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void tick() {
        this.x = this.level.state.getBatteryXinPixels();
        this.y = this.level.state.getBatteryYinPixels();
    }

    @Override
    public void render(Screen screen) {
        int yTile = 2;
        int xTile = 6;

        int scale = 2;
        int modifier = 8 * scale;

        int xOffset = x - modifier/2;
        int yOffset = y - modifier/2 - 4;


        screen.render(xOffset , yOffset, xTile + yTile*32, colour, false, false, scale);
        //System.out.println("renders");
        screen.render(xOffset + modifier, yOffset, xTile + 1 + yTile*32, colour, false, false,  scale);
        screen.render(xOffset, yOffset + modifier, xTile + (1 + yTile)*32, colour, false, false, scale);
        screen.render(xOffset + modifier, yOffset + modifier, xTile + 1 + (yTile+1)*32, colour, false, false, scale);

    }
}
