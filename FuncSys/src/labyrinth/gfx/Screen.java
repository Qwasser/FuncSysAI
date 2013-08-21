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

    public void render(int xPos, int yPos, int tile, int colour, int scale){
        render(xPos, yPos, tile, colour, false, false, scale);
    }

    public void render(int xPos, int yPos, int tile, int colour, boolean  xMirror, boolean yMirror, int scale){
        xPos -= xOffset;
        yPos -= yOffset;



        int scaleMap = scale - 1;
        int xTile = tile % 32;
        int yTile = tile/32;
        int tileOffset = (xTile<<3) + (yTile<<3) * sheet.width;
        for (int y = 0; y<8; y++){
            int ySheet;
            if (yMirror) ySheet = 7-y;
            else ySheet = y;

            int yPixel = y + yPos + (y * scaleMap) - ((scaleMap << 3)/2);

            for (int x = 0; x <8; x++){

                int xSheet;
                if (xMirror) xSheet = 7-x;
                else xSheet = x;
                int xPixel = x + xPos + (x * scaleMap) - ((scaleMap << 3)/2);
                int col = (colour >> (sheet.pixels[xSheet + ySheet*sheet.width + tileOffset]*8))&255;
                if (col<255)
                {
                    for (int yScale = 0; yScale < scale; yScale++)
                    {
                        if (yPixel + yScale < 0 || yPixel + yScale >= height) continue;
                        for (int xScale = 0; xScale < scale; xScale++)
                        {
                            if (xPixel + xScale < 0 || xPixel + xScale >= width) continue;
                            pixels[xPixel + xScale + (yPixel + yScale)*width]=col;

                        }
                    }


                }

            }

        }
    }

    public void setOffset(int xOffset, int yOffset) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }
}
