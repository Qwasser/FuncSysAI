/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 13.08.13
 * Time: 18:15
 * To change this template use File | Settings | File Templates.
 */

import labyrinth.gfx.Colours;
import labyrinth.gfx.Screen;
import labyrinth.gfx.SpriteSheet;

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

    boolean running;
    int tickCount = 0;
    public  LabyrinthUI()
    {

        setMinimumSize(new Dimension(WIDTH* SCALE, HEIGHT* SCALE));
        setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

        mainFrame = new JFrame(NAME);
        tabbedPane = new JTabbedPane();

        mainFrame.setLayout(new BorderLayout());

        tabbedPane.add("Labyrinth", this);
        JComponent panel2 = new JPanel();
        tabbedPane.add("Functional System", panel2);

        mainFrame.add(tabbedPane, BorderLayout.CENTER);
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

        input = new InputHandler(this);
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
    }

    public void render()
    {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null){
            createBufferStrategy(3);
            return;
        }

        for (int y =0; y<32; y++){
            for (int  x=0; x<32; x++){
                screen.render(x<<3, y<<3, 0, Colours.get(555, 505, 055, 550), false, false);
            }

        }

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
