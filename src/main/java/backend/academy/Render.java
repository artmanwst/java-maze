package backend.academy;

import java.util.List;

public class Render {
    private static final String YELLOW_CIRCLE_EMOJI = "\uD83D\uDFE1";

    public enum PointType {
        START, END
    }

    public String render(Maze maze) {
        StringBuilder stb = new StringBuilder();
        for (int i = 0; i < maze.getHeight(); i++) {
            for (int j = 0; j < maze.getWidth(); j++) {
                Cell cell = maze.getCell(i, j);
                if (cell.getKind() == Cell.Kind.START) {
                    stb.append("\uD83C\uDFC1");
                } else if (cell.getKind() == Cell.Kind.PASSAGE) {
                    stb.append('⬜');
                } else if (cell.getKind() == Cell.Kind.END) {
                    stb.append('E');
                } else if (cell.getKind() == Cell.Kind.MONEY) {
                    stb.append(YELLOW_CIRCLE_EMOJI);
                } else {
                    stb.append('⬛');
                }
            }
            stb.append('\n');
        }
        return stb.toString();
    }

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

    public String renderPath(Maze maze, List<Coordinate> path) {
        StringBuilder stb = new StringBuilder();
        for (int i = 0; i < maze.getHeight(); i++) {
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
            stb.append('\n');
        }
        return stb.toString();
    }
}
