package aoc.util;

import aoc.util.grid.Position3D;

public class SpaceUtil {

    public record Connection(Position3D start, Position3D stop, long distance) implements Comparable<Connection> {

        @Override
        public int compareTo(Connection other) {
            return Long.compare(this.distance, other.distance);
        }
    }
}
