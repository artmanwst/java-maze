package backend.academy;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public final class MazeUtils {
    private MazeUtils() {
        throw new UnsupportedOperationException("Utility class should not be instantiated");
    }

    public static final Integer ROW = 0;
    public static final Integer COL = 1;
    public static final SecureRandom RANDOM = new SecureRandom();
    public static final int TWENTY_PERCENT = 20;


    public static final int MAX_RANDOM_VALUE = 100;
    public static final int THREE_WALLS = 3;



    public static boolean checkBounds(int row, int col, Maze maze) {
        return row >= 0 && row < maze.getHeight() && col >= 0 && col < maze.getWidth();
    }

    public static List<Coordinate> getNeighbors(Coordinate now, Maze maze) {
        int row = now.row();
        int col = now.col();
        List<Coordinate> neighbors = new ArrayList<>();
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        for (int[] dir : directions) {
            int newRow = row + dir[ROW];
            int newCol = col + dir[COL];

            if (newRow >= 0 && newRow < maze.getHeight() && newCol >= 0 && newCol < maze.getWidth()) {
                neighbors.add(new Coordinate(newRow, newCol));
            }
        }
        return neighbors;
    }

    public static List<int[]> addNeighbors(int currentRow, int currentCol, Maze maze) {
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        List<int[]> neighbors = new ArrayList<>();
        for (int[] dir : directions) {
            int newRow = currentRow + dir[ROW];
            int newCol = currentCol + dir[COL];
            if (MazeUtils.checkBounds(newRow, newCol, maze)
                && maze.getCell(newRow, newCol).getKind() == Cell.Kind.WALL) {
                int walls = 0;
                for (int[] check : directions) {
                    int checkRow = newRow + check[ROW];
                    int checkCol = newCol + check[COL];
                    if (MazeUtils.checkBounds(checkRow, checkCol, maze)
                        && maze.getCell(checkRow, checkCol).getKind() == Cell.Kind.WALL) {
                        walls++;
                    }
                }
                if (walls == 2 && isWithinBounds(newRow, newCol, maze)) {
                    int adding = RANDOM.nextInt(MAX_RANDOM_VALUE);
                    if (adding < TWENTY_PERCENT) {
                        neighbors.add(new int[]{newRow, newCol});

                    }
                } else if (walls >= THREE_WALLS && isWithinBounds(newRow, newCol, maze)) {
                    neighbors.add(new int[]{newRow, newCol});
                }
            }
        }
        return neighbors;
    }

    public static boolean isWithinBounds(int row, int col, Maze maze) {
        return row > 0 && row < maze.getHeight() - 1 && col > 0 && col < maze.getWidth() - 1;
    }
}
