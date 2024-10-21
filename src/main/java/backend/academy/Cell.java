package backend.academy;

public class Cell {
    private Kind kind;

    public enum Kind {
        WALL, PASSAGE, START, END, MONEY
    }

    public Cell(int row, int col, Kind kind) {
        this.kind = kind;


    }

    public Kind getKind() {
        return kind;
     }

     public void setKind(Kind kind) {
        this.kind = kind;
     }


}
