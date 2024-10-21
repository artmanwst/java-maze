import backend.academy.Cell;
import backend.academy.DepthSolver;
import backend.academy.Maze;
import backend.academy.Coordinate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Arrays;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LabTest {
    private Maze maze;
    private DepthSolver solver;

    @BeforeEach
    public void setUp(){
        solver = new DepthSolver();

    }

    @Test
    public void testSolve(){
        maze = new Maze(5, 5);
        maze.setClass(0, 0, Cell.Kind.START); // Start
        maze.setClass(0, 1, Cell.Kind.PASSAGE);
        maze.setClass(1, 1, Cell.Kind.PASSAGE);
        maze.setClass(2, 1, Cell.Kind.PASSAGE);
        maze.setClass(0, 4, Cell.Kind.MONEY);
        maze.setClass(1, 4, Cell.Kind.PASSAGE);
        maze.setClass(3, 1, Cell.Kind.END); // End
        maze.setClass(0, 3, Cell.Kind.PASSAGE);
        List<Coordinate> expectedPath = Arrays.asList(new Coordinate(0, 0), new Coordinate(0, 1), new Coordinate(1, 1), new Coordinate(2, 1), new Coordinate(3, 1));
        List<Coordinate> resultPath = solver.solve(maze, new Coordinate(0, 0), new Coordinate(3, 1));
        assertEquals(expectedPath, resultPath);

    }

    @Test
    public void testSolveNoPathFound() {
        maze = new Maze(5, 5);
        maze.setClass(0, 0, Cell.Kind.START); // Start
        maze.setClass(0, 1, Cell.Kind.PASSAGE);
        maze.setClass(1, 1, Cell.Kind.PASSAGE);
        maze.setClass(0, 4, Cell.Kind.MONEY);
        maze.setClass(1, 4, Cell.Kind.PASSAGE);
        maze.setClass(3, 1, Cell.Kind.END); // End
        maze.setClass(0, 3, Cell.Kind.PASSAGE);
        List<Coordinate> resultPath = solver.solve(maze, new Coordinate(0, 0), new Coordinate(3, 1));
        assertEquals(Collections.emptyList(), resultPath);

    }


}
