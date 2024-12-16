package aoc.util;

public class MatrixUtil {

    public static String[][] createStringMatrix(int xWidth, int yHeight) {
        return new String[yHeight][xWidth];
    }

    public static boolean isWithinBounds(String[][] matrix, Position pos) {
        return pos.xPos >= 0 && pos.yPos >= 0 && pos.yPos < matrix.length && pos.xPos < matrix[0].length;
    }

    public static boolean isWithinBounds(String[][] matrix, Position position, Direction direction) {
        Position updatedPosition = position.move(direction);
        return isWithinBounds(matrix, updatedPosition);
    }

    public record Position(int xPos, int yPos) implements Comparable<Position> {

        public Position move(Direction direction) {
            return switch (direction) {
                case UP -> new Position(xPos, yPos - 1);
                case RIGHT -> new Position(xPos + 1, yPos);
                case DOWN -> new Position(xPos, yPos + 1);
                case LEFT -> new Position(xPos - 1, yPos);
            };
        }

        public Position move(int xPos, int yPos) {
            return new Position(this.xPos + xPos, this.yPos + yPos);
        }

        public Position move(Direction direction, int steps) {
            return switch (direction) {
                case UP -> new Position(xPos, yPos - steps);
                case RIGHT -> new Position(xPos + steps, yPos);
                case DOWN -> new Position(xPos, yPos + steps);
                case LEFT -> new Position(xPos - steps, yPos);
            };
        }

        @Override
        public int compareTo(Position o) {
            if (this.xPos == o.xPos) {
                return this.yPos - o.yPos;
            }
            return this.xPos - o.xPos;
        }
    }

    public enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT;

        public Direction rotateClockwise() {
            return switch (this) {
                case UP -> RIGHT;
                case RIGHT -> DOWN;
                case DOWN -> LEFT;
                case LEFT -> UP;
            };
        }
    }
}
