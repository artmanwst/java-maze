package backend.academy;

/**
 * Класс, представляющий виды ячеек лабиринта.
 * - WALL: стена
 * - PASSAGE: проход
 * - START: стартовая точка
 * - END: конечная точка
 * - MONEY: ячейка с деньгами
 */
public class Cell {
    private Kind kind;

    public enum Kind {
        WALL, PASSAGE, START, END, MONEY
    }

    public Cell(int row, int col, Kind kind) {
        this.kind = kind;


    }

    /** Получает тип ячейки */
    public Kind getKind() {
        return kind;
     }

    /** Устанавливает тип ячейки */
    public void setKind(Kind kind) {
        this.kind = kind;
     }


}
