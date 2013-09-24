package labyrinth.game; /**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 13.08.13
 * Time: 18:15
 * To change this template use File | Settings | File Templates.
 */

import labyrinth.enteties.Battery;
import labyrinth.enteties.Player;
import labyrinth.gfx.Screen;
import labyrinth.gfx.SpriteSheet;
import labyrinth.level.LabyrinthMap;
import labyrinth.level.Level;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.IOException;

public class LabyrinthUI extends Canvas implements Runnable{
    private int WIDTH = 0;
    private int HEIGHT = 0;
    public static final int SCALE = 2;
    public static final String NAME = "Labyrinth";

    private BufferedImage image;
    private int[] pixele;
    private int[] colours;

    private Screen screen;
    public InputHandler input;
    private Level level;
    public Player player;
    public LabyrinthMap map;

    public GameState state;
    boolean running;
    int tickCount = 0;
    public  LabyrinthUI(GameState state, LabyrinthMap map)
    {
        WIDTH = map.getWidth()*32;
        HEIGHT = map.getHeight()*32;

        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        pixele = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
        colours = new int[6*6*6];

        this.state = state;
        this.map = map;

        setMinimumSize(new Dimension(WIDTH* SCALE, HEIGHT* SCALE));
        setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double nsPerick = 1000000000D/60D;

        int frames = 0;
        int ticks = 0;

        long lastTimer = System.currentTimeMillis();
        double delta = 0;

        init();

        while(running)
        {
            long now = System.nanoTime();
            delta += (now - lastTime)/nsPerick;
            lastTime = now;
            boolean shouldRender = false;
            while (delta >= 1)
            {
                tick();
                ticks++;
                delta-=1;
                shouldRender = true;
            }



            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            if (shouldRender)
            {
                frames++;
                render();
            }

            if (System.currentTimeMillis() - lastTimer > 1000)
            {
                lastTimer += 1000;
                //System.out.println(ticks + " ticks, " + frames + " frames");
                frames = 0;
                ticks = 0;

            }
        }
    }

    public void init(){
        int index = 0;
        for (int r = 0; r < 6; r++){
            for (int g = 0; g < 6; g++){
                for (int b = 0; b < 6; b++){
                    int rr = (r*255/5);
                    int gg = (g*255/5);
                    int bb = (b*255/5);

                    colours[index++] = rr<<16|gg<<8|bb;
                }
            }
        }

        try
        {
            screen = new Screen(WIDTH, HEIGHT, new SpriteSheet("/res/tex_bw.png"));
        }catch (IOException e)
        {
            e.printStackTrace();
        }




        input = new InputHandler(this);

        level = new Level(map, state);
        System.out.println(this.level.state.playerX);
        for (int i = 0; i < this.map.getBatteryCount(); i++)
        {
            Battery battery = new Battery(level, 167, 76, i, input);
            level.addEntity(battery);
        }
        player = new Player(level, 72, 76, input);
        level.addEntity(player);




    }

    public synchronized  void start()
    {
        new Thread(this).start();
        running = true;
    }

    public synchronized  void stop()
    {
        running = false;

    }


    public void tick()
    {
        tickCount++;
        if(input.up.isPressed)
        {
            screen.yOffset--;

        }

        if(input.down.isPressed)
        {
            screen.yOffset++;
        }
        if(input.left.isPressed)
        {
            screen.xOffset--;
        }

        if(input.right.isPressed)
        {
            screen.xOffset++;
        }

        level.tick();
    }

    public void render()
    {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null){
            createBufferStrategy(3);
            return;
        }

        int xOffset = player.x - (screen.width/2);
        int yOffset = player.y - (screen.height/2);
        level.renderTile(screen, screen.xOffset, screen.yOffset);
        level.renderEntities(screen);
        for (int y =0; y<screen.height; y++){
            for (int  x=0; x<screen.width; x++){
                int colorCode = screen.pixels[x+y*screen.width];
                if (colorCode<255)  pixele[x+y*WIDTH] = colours[colorCode];


            }

        }



        Graphics g = bs.getDrawGraphics();
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        g.dispose();
        bs.show();
    }
}
