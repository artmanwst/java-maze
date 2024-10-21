import backend.academy.GenerateRandomWalk;
import backend.academy.Maze;
import backend.academy.Cell;
import org.junit.jupiter.api.Test;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


public class GenerateTest {
    private final GenerateRandomWalk generator = new GenerateRandomWalk();

    @Test
    public void testMazeGenerateRandomWalk(){
        int height = 10;
        int width = 10;
        Maze maze = generator.generate(height, width);
        assertEquals(height,maze.getHeight());
        assertEquals(width, maze.getWidth());


    }

    @Test
    public void testMazePassages() {
        int height = 10;
        int width = 10;
        Maze maze = generator.generate(height, width);
        int passageCount = 0;
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                if (maze.getCell(row, col).getKind() == Cell.Kind.PASSAGE) {
                    passageCount++;
                }
            }
        }
        assertTrue(passageCount>0);
        assertTrue(passageCount<100);
    }
    @Test
    public void testMazeCellsAreValid() {
        int height = 10;
        int width = 10;
        Maze maze = generator.generate(height, width);

        Set<Cell.Kind> validKinds = Set.of(Cell.Kind.PASSAGE, Cell.Kind.WALL, Cell.Kind.MONEY);

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                Cell.Kind kind = maze.getCell(row, col).getKind();
                assertTrue(validKinds.contains(kind));
            }
        }
    }
}
