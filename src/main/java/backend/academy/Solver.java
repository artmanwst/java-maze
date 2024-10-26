package backend.academy;

import java.util.List;

/**
 * Интерфейс для типа решения задач по поиску пути в лабиринте.
 */
public interface Solver {
    List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end);
}
