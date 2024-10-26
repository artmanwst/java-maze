package backend.academy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * Класс для решения задачи поиска кратчайшего пути в лабиринте.
 */
@SuppressWarnings("RegexpSinglelineJava")
public class SearchSolv implements Solver {
    private List<Coordinate> shortestPath = null;
    private int moneyOnShortestPath = 0;

    /**
     * Решает лабиринт, используя поиск в ширину (BFS).
     * @param maze  Лабиринт, в котором нужно найти путь.
     * @param start Стартовая координата.
     * @param end   Конечная координата.
     */
    public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end) {
        Queue<PathNode> queue = new LinkedList<>();
        Set<Coordinate> visited = new HashSet<>();

        queue.add(new PathNode(start, new ArrayList<>(Collections.singletonList(start)), 0));
        visited.add(start);

        while (!queue.isEmpty()) {
            PathNode currentNode = queue.poll();
            Coordinate currentCoordinate = currentNode.coordinate;
            List<Coordinate> currentPath = currentNode.path;
            int currentMoney = currentNode.money;

            if (currentCoordinate.equals(end)) {
                if (shortestPath == null || currentPath.size() < shortestPath.size()
                    || (currentPath.size() == shortestPath.size() && currentMoney > moneyOnShortestPath)) {
                    moneyOnShortestPath = currentMoney;
                    shortestPath = new ArrayList<>(currentPath);
                    System.out.println("Found shorter path: " + currentPath.size() + " with money: " + currentMoney);
                }
                continue;
            }

            for (Coordinate neighbor : MazeUtils.getNeighbors(currentCoordinate, maze)) {
                if (visited.add(neighbor)
                    && (maze.getCell(neighbor.row(), neighbor.col()).getKind() == Cell.Kind.PASSAGE
                        || maze.getCell(neighbor.row(), neighbor.col()).getKind() == Cell.Kind.END
                        || maze.getCell(neighbor.row(), neighbor.col()).getKind() == Cell.Kind.MONEY)) {

                    List<Coordinate> newPath = new ArrayList<>(currentPath);
                    newPath.add(neighbor);
                    Cell.Kind cellKind = maze.getCell(neighbor.row(), neighbor.col()).getKind();
                    int newMoney = currentMoney + (cellKind == Cell.Kind.MONEY ? 1 : 0);

                    queue.add(new PathNode(neighbor, newPath, newMoney));
                }
            }
        }

        return shortestPath != null ? shortestPath : Collections.emptyList();
    }

    /**
     * Внутренний класс для представления узла пути в лабиринте.
     */
    private static class PathNode {
        Coordinate coordinate;
        List<Coordinate> path;
        int money;

        PathNode(Coordinate coordinate, List<Coordinate> path, int money) {
            this.coordinate = coordinate;
            this.path = path;
            this.money = money;
        }
    }
}
