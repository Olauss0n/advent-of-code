package aoc.util;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import aoc.util.GridUtil.Orientation;
import aoc.util.SearchUtil.Edge;
import aoc.util.grid.Position;

public class Matrix<T> {
    private final T[][] grid;

    private final int xWidth;

    private final int yHeight;

    // Columns = Horizontal (X)
    public int columns() {
        return this.xWidth;
    }

    // Rows = Vertical (Y)
    public int rows() {
        return this.yHeight;
    }

    public List<T> getColumn(int x) {
        List<T> column = new ArrayList<>(this.yHeight);
        for (int y = 0; y < this.yHeight; y++) {
            column.add(this.grid[y][x]);
        }
        return column;
    }

    public List<T> getRow(int y) {
        return Arrays.asList(this.grid[y]);
    }

    /**
     * Returns the grid as a list of rows.
     */
    public List<List<T>> getRows() {
        List<List<T>> rows = new ArrayList<>();
        for (T[] row : grid) {
            rows.add(Arrays.asList(row));
        }
        return rows;
    }

    /**
     * Returns the grid as a list of columns.
     */
    public List<List<T>> getColumns() {
        List<List<T>> columns = new ArrayList<>();
        for (int x = 0; x < xWidth; x++) {
            List<T> col = new ArrayList<>();
            for (int y = 0; y < yHeight; y++) {
                col.add(grid[y][x]);
            }
            columns.add(col);
        }
        return columns;
    }

    public Matrix(T[][] grid) {
        this.grid = grid;
        this.yHeight = grid.length;
        this.xWidth = grid[0].length;
    }

    public static Matrix<Integer> createIntMatrix(Matrix<?> matrix) {
        return new Matrix<>(new Integer[matrix.rows()][matrix.columns()]);
    }

    public static Matrix<Integer> createIntMatrix(int xWidth, int yHeight) {
        return new Matrix<>(new Integer[yHeight][xWidth]);
    }

    public static Matrix<String> createStringMatrix(Matrix<?> matrix) {
        return new Matrix<>(new String[matrix.rows()][matrix.columns()]);
    }

    public static Matrix<String> createStringMatrix(int xWidth, int yHeight) {
        return new Matrix<>(new String[yHeight][xWidth]);
    }

    public static Matrix<String> createStringMatrix(int xWidth, int yHeight, String fillerChar) {
        return createStringMatrix(xWidth, yHeight).fill(fillerChar);
    }

    public T get(Position position) {
        return this.grid[position.yPos()][position.xPos()];
    }

    public T get(int xPos, int yPos) {
        return this.grid[yPos][xPos];
    }

    public void set(Position pos, T value) {
        this.grid[pos.yPos()][pos.xPos()] = value;
    }

    public void set(int xPos, int yPos, T value) {
        this.grid[yPos][xPos] = value;
    }

    public List<Edge<Orientation>> getGridEdges(Orientation current, int turnCost) {
        return getGridEdges(current, turnCost, cell -> !cell.equals("#"));
    }

    public List<Edge<Orientation>> getGridEdges(Orientation current, int turnCost, Predicate<T> isPassable) {
        List<Edge<Orientation>> edges = new ArrayList<>();

        // Move Forward
        Position next = current.position().move(current.direction());
        if (isWithinBounds(next) && isPassable.test(this.get(next))) {
            edges.add(new SearchUtil.Edge<>(new Orientation(next, current.direction()), 1));
        }

        // Turns
        edges.add(new Edge<>(
                new Orientation(current.position(), current.direction().rotateClockwise()), turnCost));
        edges.add(new Edge<>(
                new Orientation(current.position(), current.direction().rotateCounterClockwise()), turnCost));

        return edges;
    }

    public boolean isWithinBounds(Position position) {
        return position.xPos() >= 0
                && position.yPos() >= 0
                && position.xPos() < this.xWidth
                && position.yPos() < this.yHeight;
    }

    public Position findPosition(T value) {
        for (int y = 0; y < this.yHeight; y++) {
            for (int x = 0; x < this.xWidth; x++) {
                if (this.grid[y][x].equals(value)) {
                    return new Position(x, y);
                }
            }
        }
        throw new IllegalStateException();
    }

    public List<Position> findPositions(T value) {
        List<Position> positions = new ArrayList<>();
        for (int y = 0; y < this.yHeight; y++) {
            for (int x = 0; x < this.xWidth; x++) {
                if (this.grid[y][x].equals(value)) {
                    positions.add(new Position(x, y));
                }
            }
        }
        return positions;
    }

    public Matrix<T> fill(T fillerChar) {
        for (int y = 0; y < this.yHeight; y++) {
            for (int x = 0; x < this.xWidth; x++) {
                this.grid[y][x] = fillerChar;
            }
        }
        return this;
    }

    public void print(String formatter) {
        System.out.println();
        for (T[] row : this.grid) {
            for (T element : row) {
                System.out.printf(formatter, element);
            }
            System.out.println();
        }
    }

    public void print() {
        print("%1s");
    }

    public Matrix<T> copy() {
        @SuppressWarnings("unchecked")
        T[][] newGrid = (T[][]) Array.newInstance(
                this.grid.getClass().getComponentType().getComponentType(), this.yHeight, this.xWidth);
        for (int i = 0; i < this.yHeight; i++) {
            newGrid[i] = this.grid[i].clone();
        }
        return new Matrix<>(newGrid);
    }

    public <R> Matrix<R> map(Function<T, R> mapper) {
        @SuppressWarnings("unchecked")
        R[][] newGrid = (R[][]) new Object[yHeight][xWidth];
        for (int y = 0; y < yHeight; y++) {
            for (int x = 0; x < xWidth; x++) {
                T value = grid[y][x];
                newGrid[y][x] = (value == null) ? null : mapper.apply(value);
            }
        }
        return new Matrix<>(newGrid);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }

        Matrix<?> that = (Matrix<?>) other;

        return Arrays.deepEquals(this.grid, that.grid);
    }

    @Override
    public int hashCode() {
        return java.util.Arrays.deepHashCode(grid);
    }
}
