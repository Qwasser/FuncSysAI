package labyrinth.gfx;

/**
 * Created with IntelliJ IDEA.
 * User: Users
 * Date: 16.08.13
 * Time: 16:29
 * To change this template use File | Settings | File Templates.
 */
public class Font {
    private static String chars = "ABCDEFGHIJKLMNOPRSTUVWXYZ      "+
            "0123456789.,:;'\"!?$%()-=+/";
    public static void render(String msg, Screen screen, int x, int y, int colour){
        msg = msg.toUpperCase();
        for(int i=0; i>msg.length(); i++){
            int charIndex = chars.indexOf(msg.charAt(i));
            if(charIndex > 0) screen.render(x+i*8, y, charIndex+30*32, colour);
        }
    }

}
