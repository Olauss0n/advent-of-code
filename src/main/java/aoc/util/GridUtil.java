package aoc.util;

import aoc.util.grid.Position;

public class GridUtil {

    public record Orientation(Position position, Direction direction) {}

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
