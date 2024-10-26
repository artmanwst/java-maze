package backend.academy;

import java.util.List;


/**
 * Класс для рендеринга лабиринта и путей.
 */
public class Render {
    private static final String YELLOW_CIRCLE_EMOJI = "\uD83D\uDFE1";

    public enum PointType {
        START, END
    }

    /**
     * Отрисовывает лабиринт.
     * @param maze Лабиринт для рендеринга.
     */
    public String render(Maze maze) {
        StringBuilder stb = new StringBuilder();
        String yellowSquare = "🟨";

        for (int j = 0; j < maze.getWidth() + 2; j++) {
            stb.append(yellowSquare);
        }
        stb.append('\n');

        for (int i = 0; i < maze.getHeight(); i++) {
            stb.append(yellowSquare);

            for (int j = 0; j < maze.getWidth(); j++) {
                Cell cell = maze.getCell(i, j);
                if (cell.getKind() == Cell.Kind.START) {
                    stb.append("\uD83C\uDFC1");  // Флаг старта
                } else if (cell.getKind() == Cell.Kind.PASSAGE) {
                    stb.append('⬜');  // Проход
                } else if (cell.getKind() == Cell.Kind.END) {
                    stb.append('E');  // Конечная точка
                } else if (cell.getKind() == Cell.Kind.MONEY) {
                    stb.append("🟡"); // Монета (желтый круг)
                } else {
                    stb.append('⬛');  // Стена
                }
            }
            stb.append(yellowSquare);
            stb.append('\n');
        }

        for (int j = 0; j < maze.getWidth() + 2; j++) {
            stb.append(yellowSquare);
        }
        stb.append('\n');

        return stb.toString();
    }

    /**
     * Устанавливает начальную или конечную точку в лабиринте.
     * @param maze Лабиринт, в котором устанавливается точка.
     * @param start Координаты точки.
     * @param pointType Тип точки (START или END).
     */
    public boolean startMaze(Maze maze, Coordinate start, PointType pointType) {
        Cell cell = maze.getCell(start.row(), start.col());
        if (cell.getKind() == Cell.Kind.PASSAGE || cell.getKind() == Cell.Kind.MONEY) {
            if (pointType == PointType.START) {
                cell.setKind(Cell.Kind.START);
                return true;
            } else {
                cell.setKind(Cell.Kind.END);
                return true;
            }
        }
        return false;
    }

    /**
     * Отрисовывает путь в лабиринте.
     * @param maze Лабиринт, в котором отображается путь.
     * @param path Список координат, представляющий путь.
     */
    public String renderPath(Maze maze, List<Coordinate> path) {
        StringBuilder stb = new StringBuilder();
        String yellowSquare = "🟨";
        for (int j = 0; j < maze.getWidth() + 2; j++) {
            stb.append(yellowSquare);
        }
        stb.append('\n');

        for (int i = 0; i < maze.getHeight(); i++) {
            stb.append(yellowSquare);
            for (int j = 0; j < maze.getWidth(); j++) {
                Coordinate now = new Coordinate(i, j);
                if (path.contains(now)) {
                    stb.append("\uD83D\uDFE9");
                } else {
                    Cell cell = maze.getCell(i, j);
                    if (cell.getKind() == Cell.Kind.PASSAGE) {
                        stb.append('⬜');
                    } else if (cell.getKind() == Cell.Kind.MONEY) {
                        stb.append(YELLOW_CIRCLE_EMOJI);
                    } else if (cell.getKind() == Cell.Kind.END) {
                        stb.append('E');
                    } else {
                        stb.append('⬛');
                    }
                }
            }
            stb.append(yellowSquare);
            stb.append('\n');
        }
        for (int j = 0; j < maze.getWidth() + 2; j++) {
            stb.append(yellowSquare);
        }
        stb.append('\n');
        return stb.toString();
    }
}
