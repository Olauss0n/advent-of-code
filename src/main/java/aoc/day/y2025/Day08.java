package aoc.day.y2025;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import aoc.util.AdventOfCodeSolver;
import aoc.util.Converter;
import aoc.util.DisjointSet;
import aoc.util.SpaceUtil.Connection;
import aoc.util.SpaceUtil.Position3D;

public class Day08 implements AdventOfCodeSolver {
    @Override
    public Object solvePartOne(String input, boolean isExample) {
        List<Position3D> positions = parsePositions(input);
        DisjointSet<Position3D> circuits = new DisjointSet<>(positions);
        List<Connection> connections = buildConnections(positions);

        int limit = isExample ? 10 : 1000;
        connections.stream().sorted().limit(limit).forEach(c -> circuits.union(c.start(), c.stop()));

        return circuits.getComponentSizes().stream()
                .sorted(Comparator.reverseOrder())
                .limit(3)
                .mapToLong(Integer::longValue)
                .reduce(1, (a, b) -> a * b);
    }

    @Override
    public Object solvePartTwo(String input, boolean isExample) {
        List<Position3D> positions = parsePositions(input);
        DisjointSet<Position3D> circuits = new DisjointSet<>(positions);
        List<Connection> connections = buildConnections(positions);
        Collections.sort(connections);

        for (Connection connection : connections) {
            if (circuits.union(connection.start(), connection.stop())) {
                // Check if this merger resulted in the final single circuit
                if (circuits.getComponentCount() == 1) {
                    return connection.start().x() * connection.stop().x();
                }
            }
        }

        throw new IllegalStateException();
    }

    private static List<Position3D> parsePositions(String input) {
        return Converter.convertInputToList(input).stream()
                .map(line -> line.split(","))
                .map(Position3D::new)
                .toList();
    }

    private static List<Connection> buildConnections(List<Position3D> positions) {
        List<Connection> connections = new ArrayList<>();
        for (int i = 0; i < positions.size(); i++) {
            for (int j = i + 1; j < positions.size(); j++) {
                Position3D p1 = positions.get(i);
                Position3D p2 = positions.get(j);
                connections.add(new Connection(p1, p2, p1.squaredDistanceTo(p2)));
            }
        }
        return connections;
    }
}
