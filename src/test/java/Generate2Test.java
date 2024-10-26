import backend.academy.GenerateGrowingTree;
import backend.academy.Maze;
import backend.academy.Cell;
import org.junit.jupiter.api.Test;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тесты для класса GenerateRandomWalk, который генерирует лабиринт.
 */
public class Generate2Test {
    private final GenerateGrowingTree generator = new GenerateGrowingTree();
    @Test
    public void testMazeGenerateRandomWalk(){
        int height = 10;
        int width = 10;
        Maze maze = generator.generate(height, width);
        assertEquals(height,maze.getHeight());
        assertEquals(width, maze.getWidth());

    }

    /**
     * Тестирует количество проходов в сгенерированном лабиринте.
     * Проверяет, что в лабиринте есть проходы, и их количество находится в заданных пределах.
     */
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

    /**
     * Тестирует, что все клетки в сгенерированном лабиринте имеют допустимый тип.
     * Проверяет, что каждая клетка является либо проходом, либо стеной, либо содержит деньги.
     */
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
