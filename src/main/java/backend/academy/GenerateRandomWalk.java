package backend.academy;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;


public class GenerateRandomWalk implements IGenerator {
    private final SecureRandom random = new SecureRandom();
    private static final int MAX_RANDOM_VALUE = 100;

    private static final int SPECIAL_CHANCE_THRESHOLD = 5;
    public static final Integer ROW = 0;
    public static final Integer COL = 1;


    public Maze generate(int height, int width) {
        Maze maze = new Maze(height, width);
        int startingRow =  random.nextInt(height - 2) + 1;
        int startingCol = random.nextInt(width - 2) + 1;
        generatePath(maze, startingRow, startingCol);
        return maze;
    }

    private void generatePath(Maze maze, int startRow, int startCol) {


        maze.setClass(startRow, startCol, Cell.Kind.PASSAGE);
        List<int[]> st = new ArrayList<>();
        st.add(new int[]{startRow, startCol});
        int count = 0;

        while (!st.isEmpty()) {
            int[] now = st.get(st.size() - 1);
            int nowRow = now[ROW];
            int nowCol = now[COL];
            int chance = random.nextInt(MAX_RANDOM_VALUE);
            List<int[]> neighbors;
            neighbors = MazeUtils.addNeighbors(nowRow, nowCol, maze);

            if (!neighbors.isEmpty()) {
                int[] neighbor = neighbors.get(random.nextInt(neighbors.size()));
                int newRow = neighbor[ROW];
                int newCol = neighbor[COL];
                if (chance < SPECIAL_CHANCE_THRESHOLD) {
                    maze.setClass(newRow, newCol, Cell.Kind.MONEY);
                } else {
                    count++;
                    maze.setClass(newRow, newCol, Cell.Kind.PASSAGE);
                }
                st.add(new int[] {newRow, newCol});
            } else {
                st.remove(st.size() - 1);
            }
        }

    }


}



