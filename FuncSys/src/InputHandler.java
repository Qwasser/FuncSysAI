import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 16.08.13
 * Time: 1:41
 * To change this template use File | Settings | File Templates.
 */
public class InputHandler implements KeyListener {
    public class Key{
        public boolean isPressed = false;

        public void toggle(boolean isPressed)
        {
            this.isPressed = isPressed;
        }
    }

    public InputHandler(LabyrinthUI ui)
    {
        ui.addKeyListener(this);
    }
    public List<Key> keys = new ArrayList<Key>();

    public Key up = new Key();
    public Key down = new Key();
    public Key left = new Key();
    public Key right = new Key();
    @Override
    public void keyTyped(KeyEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        toggleKey(e.getKeyCode(), true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        toggleKey(e.getKeyCode(), false);
    }

    public void toggleKey(int keyPressed, boolean  isPressed)
    {
        if(keyPressed == KeyEvent.VK_W || keyPressed == KeyEvent.VK_UP) {up.toggle(isPressed);}
        if(keyPressed == KeyEvent.VK_S || keyPressed == KeyEvent.VK_DOWN) {down.toggle(isPressed);}
        if(keyPressed == KeyEvent.VK_A || keyPressed == KeyEvent.VK_LEFT) {left.toggle(isPressed);}
        if(keyPressed == KeyEvent.VK_D || keyPressed == KeyEvent.VK_RIGHT) {right.toggle(isPressed);}

    }
}
