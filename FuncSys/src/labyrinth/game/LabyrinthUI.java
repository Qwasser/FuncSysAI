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
import labyrinth.level.TyleType;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.IOException;

public class LabyrinthUI extends Canvas implements Runnable{
    JFrame mainFrame;
    JTabbedPane tabbedPane;

    public static final int WIDTH = 160;
    public static final int HEIGHT = WIDTH/12*9;
    public static final int SCALE = 3;
    public static final String NAME = "Labyrinth";

    private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    private int[] pixele = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
    private int[] colours = new int[6*6*6];

    private Screen screen;
    public InputHandler input;
    private Level level;
    public Player player;

    boolean running;
    int tickCount = 0;
    public  LabyrinthUI()
    {

        setMinimumSize(new Dimension(WIDTH* SCALE, HEIGHT* SCALE));
        setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

        mainFrame = new JFrame(NAME);
        //tabbedPane = new JTabbedPane();

        //mainFrame.setLayout(new BorderLayout());

        //tabbedPane.add("Labyrinth", this);
        //JComponent panel2 = new JPanel();
        //tabbedPane.add("Functional System", panel2);

        mainFrame.add(this, BorderLayout.CENTER);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.pack();
        mainFrame.setResizable(false);
        mainFrame.setVisible(true);
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
                System.out.println(ticks + " ticks, " + frames + " frames");
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
            screen = new Screen(WIDTH, HEIGHT, new SpriteSheet("/res/sprite_sheet.png"));
        }catch (IOException e)
        {
            e.printStackTrace();
        }


        LabyrinthMap map = new LabyrinthMap(100, 100);
        map.setPlayerStartY(2);
        map.setPlayerStartX(1);
        for (int i =0; i< 100; i++){
            for (int j =0; j< 100; j++)
            {
                map.setTyle(i, j, TyleType.GOLD);
            }
        }
        map.setTyle(0, 0, TyleType.WALL);
        map.setTyle(1, 0, TyleType.WALL);
        map.setTyle(2, 0, TyleType.WALL);
        map.setTyle(3, 0, TyleType.WALL);
        map.setTyle(4, 0, TyleType.WALL);
        map.setTyle(5, 0, TyleType.WALL);
        map.setTyle(6, 0, TyleType.WALL);


        map.setTyle(0, 1, TyleType.WALL);
        map.setTyle(1, 1, TyleType.WALL);
        map.setTyle(2, 1, TyleType.LAVA);
        map.setTyle(3, 1, TyleType.WALL);
        map.setTyle(4, 1, TyleType.LAVA);
        map.setTyle(5, 1, TyleType.WALL);
        map.setTyle(6, 1, TyleType.WALL);


        map.setTyle(0, 2, TyleType.WALL);
        map.setTyle(1, 2, TyleType.EMPTY);
        map.setTyle(2, 2, TyleType.EMPTY);
        map.setTyle(3, 2, TyleType.EMPTY);
        map.setTyle(4, 2, TyleType.EMPTY);
        map.setTyle(5, 2, TyleType.EMPTY);
        map.setTyle(6, 2, TyleType.WALL);


        map.setTyle(0, 3, TyleType.WALL);
        map.setTyle(1, 3, TyleType.WALL);
        map.setTyle(2, 3, TyleType.LAVA);
        map.setTyle(3, 3, TyleType.WALL);
        map.setTyle(4, 3, TyleType.LAVA);
        map.setTyle(5, 3, TyleType.WALL);
        map.setTyle(6, 3, TyleType.WALL);


        map.setTyle(0, 4, TyleType.WALL);
        map.setTyle(1, 4, TyleType.WALL);
        map.setTyle(2, 4, TyleType.WALL);
        map.setTyle(3, 4, TyleType.WALL);
        map.setTyle(4, 4, TyleType.WALL);
        map.setTyle(5, 4, TyleType.WALL);
        map.setTyle(6, 4, TyleType.WALL);

        input = new InputHandler(this);
        level = new Level(map);
        Battery battery = new Battery(level, 167, 76, input);
        level.addEntity(battery);
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
        level.renderTile(screen, xOffset, yOffset);
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
