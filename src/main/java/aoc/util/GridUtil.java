package aoc.util;

import aoc.util.grid.Direction;
import aoc.util.grid.Position;

public class GridUtil {

    public record Orientation(Position position, Direction direction) {}
}
