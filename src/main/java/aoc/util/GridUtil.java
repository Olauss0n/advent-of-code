package aoc.util;

import aoc.util.grid.Direction;
import aoc.util.grid.Position;

public class GridUtil {

    public record Orientation(Position position, Direction direction) {}

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
