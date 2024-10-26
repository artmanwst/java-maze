package backend.academy;

/**
 * Класс, представляющий лабиринт.
 * Он содержит высоту, ширину и сетку ячеек лабиринта, а также методы для манипуляции ячейками.
 */
public final class Maze {
    private final int height;
    private final int width;
    private final Cell[][] grid;

    /**
     * Конструктор для инициализации лабиринта заданной высоты и ширины.
     * @param height Высота лабиринта.
     * @param width  Ширина лабиринта.
     */
    public Maze(int height, int width) {
        this.height = height;
        this.width = width;
        this.grid = new Cell[height][width];
        initializeGrid();
    }

    /**
     * Метод для инициализации сетки лабиринта.
     * Все ячейки по умолчанию инициализируются как стены.
     */
    private void initializeGrid() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                grid[i][j] = new Cell(i, j, Cell.Kind.WALL);
            }
        }
    }

    /**
     * Получение высоты лабиринта.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Получение ширины лабиринта.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Получение ячейки по указанным координатам.
     *
     * @param row Номер строки.
     * @param col Номер столбца.
     */
    public Cell getCell(int row, int col) {
        return grid[row][col];
    }

    /**
     * Получение ячейки по указанным координатам.
     *
     * @param row Номер строки.
     * @param col Номер столбца.
     * @param kind тип ячейки
     */
    public void setClass(int row, int col, Cell.Kind kind) {
        grid[row][col].setKind(kind);
    }
}
