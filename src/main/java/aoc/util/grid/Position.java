package aoc.util.grid;

// xPos = column, yPos = row
public record Position(int xPos, int yPos) implements Comparable<Position> {

    public Position(String[] elements) {
        this(Integer.parseInt(elements[0]), Integer.parseInt(elements[1]));
    }

    public Position move(Direction direction) {
        return move(direction, 1);
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

    public int manhattanDistance(Position other) {
        return Math.abs(this.xPos - other.xPos) + Math.abs(this.yPos - other.yPos);
    }

    @Override
    public String toString() {
        return xPos + "," + yPos;
    }

    @Override
    public int compareTo(Position o) {
        if (this.xPos == o.xPos) {
            return this.yPos - o.yPos;
        }
        return this.xPos - o.xPos;
    }
}
