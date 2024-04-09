package src.World;

import java.util.Objects;

public class Coordinate {
    private int x;
    private int y;

    public Coordinate(int x1, int y1) {
        x = x1;
        y = y1;
    }


    // Getters
    public int getRow() {return x;}
    public int getCol() {return y;}


    public Coordinate downCoord(int row, int col) {
        if (x + 1 >= row) { return null; }
        return new Coordinate(x + 1, y);
    }
    public Coordinate upCoord(int row, int col) {
        if (x - 1 < 0) { return null; }
        return new Coordinate(x - 1, y);
    }
    public Coordinate leftCoord(int row, int col) {
        if (y - 1 < 0) { return null; }
        return new Coordinate(x, y - 1);
    }
    public Coordinate rightCoord(int row, int col) {
        if (y + 1 >= col) { return null; }
        return new Coordinate(x, y + 1);
    }



    @Override
    public String toString() {
        return  "(" + Integer.toString(x) + ", " + Integer.toString(y) + ")";
    }

    @Override
    public boolean equals(Object other) {
        return x == ((Coordinate)other).getRow() && y == ((Coordinate)other).getCol();
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
