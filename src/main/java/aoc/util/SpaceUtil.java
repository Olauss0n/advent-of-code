package aoc.util;

public class SpaceUtil {

    public record Position3D(int x, int y, int z) {

        public Position3D(String[] elements) {
            this(Integer.parseInt(elements[0]), Integer.parseInt(elements[1]), Integer.parseInt(elements[2]));
        }

        public long squaredDistanceTo(Position3D other) {
            long dx = (long) this.x - other.x;
            long dy = (long) this.y - other.y;
            long dz = (long) this.z - other.z;
            return dx * dx + dy * dy + dz * dz;
        }

        public double distanceTo(Position3D other) {
            return Math.sqrt(squaredDistanceTo(other));
        }

        public int manhattanDistance(Position3D other) {
            return Math.abs(x - other.x) + Math.abs(y - other.y) + Math.abs(z - other.z);
        }
    }

    public record Connection(Position3D start, Position3D stop, long distance) implements Comparable<Connection> {

        @Override
        public int compareTo(Connection other) {
            return Long.compare(this.distance, other.distance);
        }
    }
}
