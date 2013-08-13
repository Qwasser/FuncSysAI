/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 12.08.13
 * Time: 22:39
 * To change this template use File | Settings | File Templates.
 */
public class LabyrinthGame {
    public class GameState
    {
        int playerX;
        int playerY;

        int stepNumber;

        boolean isFail;
        boolean isWon;
    }

    public class LabyrinthMap
    {
        int width;
        int height;

        private TyleType[][] map;

        public TyleType getCellType(int x, int y)
        {
            return map[x][y];
        }

        public LabyrinthMap(int width, int height)
        {
            this.width = width;
            this.height = height;

            this.map = new TyleType[width][height];
        }
    }
}
