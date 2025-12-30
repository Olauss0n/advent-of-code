package aoc.util.path;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Getter;

/**
 * A generic Disjoint Set Union (DSU) structure.
 * Useful for tracking connectivity and grouping elements.
 */
public class DisjointSet<T> {

    @Getter
    private int componentCount;

    public DisjointSet(Collection<T> items) {
        // Initialize the map with a new Node for every item
        items.forEach(item -> nodes.put(item, new Node<>()));
        this.componentCount = items.size();
    }

    private static class Node<T> {
        Node<T> parent = this;
        int size = 1;
    }

    private final Map<T, Node<T>> nodes = new HashMap<>();

    public boolean union(T a, T b) {
        Node<T> rootA = find(nodes.get(a));
        Node<T> rootB = find(nodes.get(b));

        if (rootA != rootB) {
            // Merge the smaller group into the larger group
            if (rootA.size < rootB.size) {
                rootA.parent = rootB;
                rootB.size += rootA.size;
            } else {
                rootB.parent = rootA;
                rootA.size += rootB.size;
            }
            componentCount -= 1;
            return true;
        }
        return false;
    }

    private Node<T> find(Node<T> node) {
        if (node.parent == node) return node;
        // Path Compression
        return node.parent = find(node.parent);
    }

    public List<Integer> getComponentSizes() {
        return nodes.values().stream()
                .filter(node -> node.parent == node) // Roots only
                .map(node -> node.size)
                .toList();
    }
}
