import backend.academy.Cell;
import backend.academy.Maze;
import backend.academy.Coordinate;
import backend.academy.SearchSolv;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Arrays;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Тесты для класса SearchSolv, реализующего решение лабиринта.
 */
public class BredthTreeTest {
    private Maze maze;
    private SearchSolv solver;

    @BeforeEach
    public void setUp(){
        solver = new SearchSolv();

    }

    /**
     * Тестирует метод solve для поиска пути в лабиринте.
     * Проверяет, что ожидаемый путь совпадает с найденным путем.
     */
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

    /**
     * Тестирует метод solve, когда путь не найден.
     * Проверяет, что возвращается пустой список.
     */
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
