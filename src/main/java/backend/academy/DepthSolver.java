package backend.academy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Класс, реализующий решение лабиринта с помощью алгоритма глубинного поиска.
 * Этот класс находит кратчайший путь от стартовой до конечной координаты,
 * а также максимальное количество денег на этом пути.
 */
@SuppressWarnings("RegexpSinglelineJava")
public class DepthSolver implements Solver {
    private List<Coordinate> shortestPath = null;
    private Set<Coordinate> visited = new HashSet<>();
    private int moneyOnShortestPath = 0;

    public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end) {
        List<Coordinate> currentPath = new ArrayList<>();
        visited.add(start);
        currentPath.add(start);
        int currentMoney = (maze.getCell(start.row(), start.col()).getKind() == Cell.Kind.MONEY) ? 1 : 0;
        findPaths(maze, start, end, currentPath, currentMoney);
        return shortestPath != null ? shortestPath : Collections.emptyList();
    }

    /**
     * Рекурсивный метод для поиска всех возможных путей в лабиринте.
     *
     * @param maze Лабиринт, по которому происходит поиск.
     * @param now Текущая координата.
     * @param end Конечная точкаи.
     * @param currentPath Список координат текущего пути.
     * @param currentMoney кол-во денег на текущем пути.
     */
    private void findPaths(Maze maze, Coordinate now, Coordinate end, List<Coordinate> currentPath, int currentMoney) {
        if (now.equals(end)) {
            if (shortestPath == null || currentPath.size() < shortestPath.size()
                || (currentPath.size() == shortestPath.size() && currentMoney > moneyOnShortestPath)) {
                moneyOnShortestPath = currentMoney;
                shortestPath = new ArrayList<>(currentPath);
            }
            return;
        }

        for (Coordinate neighbor : MazeUtils.getNeighbors(now, maze)) {
            if (visited.add(neighbor)
                && (maze.getCell(neighbor.row(), neighbor.col()).getKind() == Cell.Kind.PASSAGE
                || maze.getCell(neighbor.row(), neighbor.col()).getKind() == Cell.Kind.END
                || maze.getCell(neighbor.row(), neighbor.col()).getKind() == Cell.Kind.MONEY)) {

                currentPath.add(neighbor);

                int newMoney = currentMoney
                    + (maze.getCell(neighbor.row(), neighbor.col()).getKind() == Cell.Kind.MONEY ? 1 : 0);

                findPaths(maze, neighbor, end, currentPath, newMoney);

                currentPath.remove(currentPath.size() - 1);
                visited.remove(neighbor);
            }
        }

    }

}

