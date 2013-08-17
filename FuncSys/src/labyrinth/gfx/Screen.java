package labyrinth.gfx;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 15.08.13
 * Time: 21:04
 * To change this template use File | Settings | File Templates.
 */
public class Screen {
    public static final int MAP_WIDTH = 64;
    public static final int MAP_WIDTH_MASK = MAP_WIDTH-1;

    public int[] pixels;

    public int xOffset = 0;
    public int yOffset = 0;

    public int width;
    public int height;

    public SpriteSheet sheet;

    public Screen (int width, int height, SpriteSheet sheet)
    {
        this.width = width;
        this.height = height;
        this.sheet = sheet;

        pixels = new int[width*height];
    }

    public void render(int xPos, int yPos, int tile, int colour){
        render(xPos, yPos, tile, colour, false, false);
    }

    public void render(int xPos, int yPos, int tile, int colour, boolean  xMirror, boolean yMirror){
        xPos -= xOffset;
        yPos -= yOffset;

        int xTile = tile % 32;
        int yTile = tile/32;
        int tileOffset = (xTile<<3) + (yTile<<3) * sheet.width;
        for (int y = 0; y<8; y++){
            int ySheet;
            if (yMirror) ySheet = 7-y;
            else ySheet = y;
            if (y + yPos < 0 || y + yPos >= height) continue;
            for (int x = 0; x <8; x++){
                if (x + xPos < 0 || x + xPos >= width) continue;
                int xSheet;
                if (xMirror) xSheet = 7-x;
                else xSheet = x;
                int col = (colour >> (sheet.pixels[xSheet + ySheet*sheet.width + tileOffset]*8))&255;
                if (col<255) pixels[x+xPos + (y+yPos)*width]=col;

            }

        }
    }

    public void setOffset(int xOffset, int yOffset) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }
}
