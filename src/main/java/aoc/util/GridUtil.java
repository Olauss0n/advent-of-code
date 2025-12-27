package aoc.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class GridUtil {

    public static <T> void printMatrix(T[][] matrix, String formatter) {
        System.out.println();
        for (T[] lines : matrix) {
            for (T element : lines) {
                System.out.printf(formatter, element);
            }
            System.out.println();
        }
    }

    public static <T> void printMatrix(T[][] matrix) {
        printMatrix(matrix, "%1s");
    }

    public static String[][] createStringMatrix(int xWidth, int yHeight) {
        return new String[yHeight][xWidth];
    }

    public static Integer[][] createIntMatrix(int xWidth, int yHeight) {
        return new Integer[yHeight][xWidth];
    }

    public static String[][] fillMatrix(String[][] matrix, String fillerChar) {
        for (int y = 0; y < matrix.length; y++) {
            for (int x = 0; x < matrix[0].length; x++) {
                matrix[y][x] = fillerChar;
            }
        }
        return matrix;
    }

    public static void fillMatrix(Integer[][] matrix, int fillerChar) {
        for (int y = 0; y < matrix.length; y++) {
            for (int x = 0; x < matrix[0].length; x++) {
                matrix[y][x] = fillerChar;
            }
        }
    }

    public static boolean isWithinBounds(String[][] matrix, Position pos) {
        return pos.xPos >= 0 && pos.yPos >= 0 && pos.yPos < matrix.length && pos.xPos < matrix[0].length;
    }

    public static boolean isWithinBounds(String[][] matrix, Position position, Direction direction) {
        Position updatedPosition = position.move(direction);
        return isWithinBounds(matrix, updatedPosition);
    }

    public static Position findPosition(String[][] matrix, String searchExpression) {
        for (int y = 0; y < matrix.length; y++) {
            for (int x = 0; x < matrix[y].length; x++) {
                if (matrix[y][x].equals(searchExpression)) {
                    return new Position(x, y);
                }
            }
        }
        throw new IllegalStateException();
    }

    public static List<Position> findPositions(String[][] matrix, String searchExpression) {
        List<Position> positions = new ArrayList<>();
        for (int y = 0; y < matrix.length; y++) {
            for (int x = 0; x < matrix[y].length; x++) {
                if (matrix[y][x].equals(searchExpression)) {
                    positions.add(new Position(x, y));
                }
            }
        }
        return positions;
    }

    // Dijkstra's Algorithm with potential turning cost
    public static int calculateDistance(String[][] matrix, State startState, Position end, int turningCost) {
        PriorityQueue<State> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(new State(startState.position, startState.direction, startState.value));

        Set<Orientation> visited = new HashSet<>();

        while (!priorityQueue.isEmpty()) {
            State currentState = priorityQueue.poll();
            if (visited.contains(new Orientation(currentState.position, currentState.direction))) {
                continue;
            }
            visited.add(new Orientation(currentState.position, currentState.direction));
            if (currentState.position.equals(end)) {
                return currentState.value;
            }
            Position nextPosition = currentState.position.move(currentState.direction);
            if (GridUtil.isWithinBounds(matrix, nextPosition)
                    && !matrix[nextPosition.yPos][nextPosition.xPos].equals("#")) {
                priorityQueue.add(new State(nextPosition, currentState.direction, currentState.value + 1));
            }
            priorityQueue.add(new State(
                    currentState.position, currentState.direction.rotateClockwise(), currentState.value + turningCost));
            priorityQueue.add(new State(
                    currentState.position,
                    currentState.direction.rotateCounterClockwise(),
                    currentState.value + turningCost));
        }
        throw new IllegalStateException();
    }

    public record State(Position position, Direction direction, int value) implements Comparable<State> {

        @Override
        public int compareTo(State o) {
            return Integer.compare(this.value, o.value);
        }
    }

    public record Orientation(Position position, Direction direction) {}

    // xPos = column, yPos = row
    public record Position(int xPos, int yPos) implements Comparable<Position> {

        public Position(String[] elements) {
            this(Integer.parseInt(elements[0]), Integer.parseInt(elements[1]));
        }

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

        public Position move1Step(OctagonalDirection direction) {
            return switch (direction) {
                case NORTH -> new Position(xPos, yPos - 1);
                case NORTH_WEST -> new Position(xPos - 1, yPos - 1);
                case NORTH_EAST -> new Position(xPos + 1, yPos - 1);
                case WEST -> new Position(xPos - 1, yPos);
                case EAST -> new Position(xPos + 1, yPos);
                case SOUTH -> new Position(xPos, yPos + 1);
                case SOUTH_WEST -> new Position(xPos - 1, yPos + 1);
                case SOUTH_EAST -> new Position(xPos + 1, yPos + 1);
            };
        }

        public Position move2Steps(OctagonalDirection direction) {
            return switch (direction) {
                case NORTH -> new Position(xPos, yPos - 2);
                case NORTH_WEST -> new Position(xPos - 1, yPos - 1);
                case NORTH_EAST -> new Position(xPos + 1, yPos - 1);
                case WEST -> new Position(xPos - 2, yPos);
                case EAST -> new Position(xPos + 2, yPos);
                case SOUTH -> new Position(xPos, yPos + 2);
                case SOUTH_WEST -> new Position(xPos - 1, yPos + 1);
                case SOUTH_EAST -> new Position(xPos + 1, yPos + 1);
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

        public Direction rotateCounterClockwise() {
            return switch (this) {
                case UP -> LEFT;
                case LEFT -> DOWN;
                case DOWN -> RIGHT;
                case RIGHT -> UP;
            };
        }
    }

    public enum OctagonalDirection {
        NORTH,
        NORTH_WEST,
        NORTH_EAST,
        WEST,
        EAST,
        SOUTH_WEST,
        SOUTH,
        SOUTH_EAST;
    }
}
