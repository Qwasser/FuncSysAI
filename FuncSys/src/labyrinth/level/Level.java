package labyrinth.level;

import labyrinth.enteties.Entity;
import labyrinth.enteties.Player;
import labyrinth.game.GameState;
import labyrinth.gfx.Screen;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Users
 * Date: 17.08.13
 * Time: 3:24
 * To change this template use File | Settings | File Templates.
 */
public class Level {
    private byte[] tiles;
    public int width;
    public int height;

    public GameState state;
    public LabyrinthMap map;

    public List<Entity> enteties = new ArrayList<Entity>();

    public Level(LabyrinthMap map, GameState state){
        this.state = state;
        this.width = map.width;
        this.height = map.height;
        tiles = new byte[width*height];
        this.map = map;
        this.generateLevel();
    }

    public void generateLevel() {
        for (int y = 0; y < height; y++){
            for (int x = 0; x < width; x++){
                 tiles[x + y*width] = TyleType.convertToTile(map.getCellType(x, y)).getid();
            }
        }
    }

    public void renderTile(Screen screen, int xOffset, int yOffset){
        if(xOffset < 0) xOffset = 0;
        if(xOffset>((width*2*2<<3) - screen.width)) xOffset = ((width*2*2<<3) - screen.width);
        if(yOffset < 0) yOffset = 0;
        if(yOffset>((height*2*2<<3) - screen.height - 4)) yOffset = ((height*2*2<<3) - screen.height - 4);

        screen.setOffset(xOffset, yOffset);

        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                getTile(x,y).render(screen, this, x*2*2<<3, y*2*2<<3);
            }
        }
    }

    private Tile getTile(int x, int y) {
        if(x<0 || x > width || y < 0 || y > height) return Tile.VOID;
        return Tile.tiles[tiles[x + y*width]];  //To change body of created methods use File | Settings | File Templates.
    }

    public void tick() {
        for (Entity e: enteties)
        {
            e.tick();
        }

    }

    public void renderEntities(Screen screen){
        for (Entity e: enteties)
        {
            e.render(screen);
        }

    }

    public void addEntity(Entity entity) {
        this.enteties.add(entity);
    }
}
