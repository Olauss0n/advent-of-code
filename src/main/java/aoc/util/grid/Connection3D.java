package aoc.util.grid;

public record Connection3D(Position3D start, Position3D stop, long distance) implements Comparable<Connection3D> {

    @Override
    public int compareTo(Connection3D other) {
        return Long.compare(this.distance, other.distance);
    }
}
