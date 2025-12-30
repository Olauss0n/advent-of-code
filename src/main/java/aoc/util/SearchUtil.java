package aoc.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.function.Function;
import java.util.function.Predicate;

import aoc.util.grid.Matrix;
import aoc.util.grid.Orientation;
import aoc.util.grid.Position;

public class SearchUtil {

    /**
     * Breadth-First Search (BFS):
     * Finds the shortest path in an unweighted graph (all edges cost 1).
     * Best for: Finding the minimum number of steps in a grid or state-machine.
     * Can reconstruct the path.
     */
    public static <T> SearchResult<T> bfs(T start, Predicate<T> isTarget, Function<T, Iterable<T>> getNeighbors) {
        Queue<T> queue = new LinkedList<>();
        Map<T, T> cameFrom = new HashMap<>();
        Map<T, Integer> distance = new HashMap<>();

        queue.add(start);
        distance.put(start, 0);
        cameFrom.put(start, null);

        while (!queue.isEmpty()) {
            T current = queue.poll();
            int currentDist = distance.get(current);

            if (isTarget.test(current)) {
                return new SearchResult<>(current, currentDist, cameFrom);
            }

            for (T next : getNeighbors.apply(current)) {
                if (!distance.containsKey(next)) {
                    distance.put(next, currentDist + 1);
                    cameFrom.put(next, current);
                    queue.add(next);
                }
            }
        }
        throw new IllegalStateException("No path found from start state.");
    }

    /**
     * Dijkstra's Algorithm:
     * Finds the shortest path in a weighted graph (edges have different costs).
     * Best for: Puzzles with turn costs, terrain difficulty, or fuel consumption.
     */
    public static <T> SearchResult<T> dijkstra(
            T start, Predicate<T> isTarget, Function<T, Iterable<Edge<T>>> getEdges) {
        PriorityQueue<Node<T>> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(node -> node.cost));
        Map<T, Integer> minCosts = new HashMap<>();
        Map<T, T> cameFrom = new HashMap<>();

        priorityQueue.add(new Node<>(start, 0));
        minCosts.put(start, 0);
        cameFrom.put(start, null);

        while (!priorityQueue.isEmpty()) {
            Node<T> current = priorityQueue.poll();

            if (current.cost > minCosts.getOrDefault(current.state, Integer.MAX_VALUE)) {
                continue;
            }
            if (isTarget.test(current.state)) {
                return new SearchResult<>(current.state, current.cost, cameFrom);
            }

            for (Edge<T> edge : getEdges.apply(current.state)) {
                int newCost = current.cost + edge.cost;
                if (newCost < minCosts.getOrDefault(edge.destination, Integer.MAX_VALUE)) {
                    minCosts.put(edge.destination, newCost);
                    cameFrom.put(edge.destination, current.state);
                    priorityQueue.add(new Node<>(edge.destination, newCost));
                }
            }
        }
        throw new IllegalStateException("No path found to a target matching the condition.");
    }

    public static <T> List<Edge<Orientation>> getGridEdges(Matrix<T> matrix, Orientation current, int turnCost) {
        return getGridEdges(matrix, current, turnCost, 1, cell -> !cell.equals("#"));
    }

    /**
     * Grid Edge Factory:
     * Generates standard transitions for orientation-based movement (forward + turns).
     * Connects a Matrix state to Dijkstra's weighted search.
     */
    public static <T> List<Edge<Orientation>> getGridEdges(
            Matrix<T> matrix, Orientation current, int turnCost, int moveCost, Predicate<T> isPassable) {
        List<Edge<Orientation>> edges = new ArrayList<>();

        // Move Forward
        Position next = current.position().move(current.direction());
        if (matrix.isWithinBounds(next) && isPassable.test(matrix.get(next))) {
            edges.add(new Edge<>(new Orientation(next, current.direction()), moveCost));
        }

        // Turns
        edges.add(new Edge<>(
                new Orientation(current.position(), current.direction().rotateClockwise()), turnCost));
        edges.add(new Edge<>(
                new Orientation(current.position(), current.direction().rotateCounterClockwise()), turnCost));

        return edges;
    }

    private record Node<T>(T state, int cost) {}

    public record Edge<T>(T destination, int cost) {}

    public record SearchResult<T>(T endState, int distance, Map<T, T> cameFrom) {
        public List<T> getPath() {
            List<T> path = new ArrayList<>();
            for (T curr = endState; curr != null; curr = cameFrom.get(curr)) {
                path.add(curr);
            }
            Collections.reverse(path);
            return path;
        }
    }
}
