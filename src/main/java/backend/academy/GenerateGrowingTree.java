package backend.academy;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("RegexpSinglelineJava")
public class GenerateGrowingTree implements IGenerator {
    private final SecureRandom random = new SecureRandom();
    private static final int MAX_RANDOM_VALUE = 100;
    private static final double HALTH = 0.5;
    private static final double FIVE_PERCENT = 5;
    public static final Integer ROW = 0;
    public static final Integer COL = 1;




    public Maze generate(int height, int width) {
        Maze maze = new Maze(height, width);
        int startingRow =  random.nextInt(height - 1);
        int startingCol = random.nextInt(width - 1);
        generatePath(maze, startingRow, startingCol);
        return maze;
    }

    private void generatePath(Maze maze, int startRow, int startCol) {
        maze.setClass(startRow, startCol, Cell.Kind.PASSAGE);
        List<int[]> cellList = new ArrayList<>();
        cellList.add(new int[]{startRow, startCol});
        int count = 0;

        while (!cellList.isEmpty()) {
            double percent = (double) count / (maze.getWidth() * maze.getHeight());
            int ind =  random.nextBoolean() ? cellList.size() - 1 : random.nextInt(cellList.size());
            int[] now = cellList.get(ind);
            int nowRow = now[ROW];
            int nowCol = now[COL];
            int chance = random.nextInt(MAX_RANDOM_VALUE);
            List<int[]> neighbors = MazeUtils.addNeighbors(nowRow, nowCol, maze);
            if (!neighbors.isEmpty() && percent <= HALTH) {
                int[] neighbor = neighbors.get(random.nextInt(neighbors.size()));
                int newRow = neighbor[ROW];
                int newCol = neighbor[COL];
                if (chance < FIVE_PERCENT) {
                    maze.setClass(newRow, newCol, Cell.Kind.MONEY);
                    count++;
                } else {
                    count++;
                    maze.setClass(newRow, newCol, Cell.Kind.PASSAGE);
                }
                cellList.add(new int[]{newRow, newCol});
            } else {
                cellList.remove(ind);
            }
        }
    }




    private boolean isWithinBounds(int row, int col, Maze maze) {
        return row > 0 && row < maze.getHeight() - 1 && col > 0 && col < maze.getWidth() - 1;
    }

}
