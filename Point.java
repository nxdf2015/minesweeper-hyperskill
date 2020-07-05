package minesweeper;

public class Point {
    int row,col;

    public Point(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public static Point of(int row, int col){
        return new Point(row,col);
    }

    @Override
    public String toString() {
        return "Point{" +
                "row=" + row +
                ", col=" + col +
                '}';
    }
}
