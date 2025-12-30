package aoc.day.y2025;

import java.awt.Polygon;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import aoc.day.AdventOfCodeSolver;
import aoc.util.Converter;
import aoc.util.grid.Position;

public class Day09 implements AdventOfCodeSolver {
    @Override
    public Object solvePartOne(String input, boolean isExample) {
        List<Position> positions = Converter.convertInputToList(input).stream()
                .map(line -> line.split(","))
                .map(Position::new)
                .toList();
        long max = 0;
        for (int i = 0; i < positions.size(); i++) {
            Position first = positions.get(i);
            for (int j = i + 1; j < positions.size(); j++) {
                Position second = positions.get(j);
                long dx = Math.abs(first.xPos() - second.xPos()) + 1L;
                long dy = Math.abs(first.yPos() - second.yPos()) + 1L;
                long area = dx * dy;
                if (area > max) {
                    max = area;
                }
            }
        }
        return max;
    }

    @Override
    public Object solvePartTwo(String input, boolean isExample) {
        List<Position> positions = Converter.convertInputToList(input).stream()
                .map(line -> line.split(","))
                .map(Position::new)
                .toList();

        // Collect unique coordinates using the "Set then Sort" pattern
        Set<Integer> uniqueXSet = new HashSet<>();
        Set<Integer> uniqueYSet = new HashSet<>();
        for (Position position : positions) {
            uniqueXSet.add(position.xPos());
            uniqueYSet.add(position.yPos());
        }

        List<Integer> sortedXLines = new ArrayList<>(uniqueXSet);
        List<Integer> sortedYLines = new ArrayList<>(uniqueYSet);
        Collections.sort(sortedXLines);
        Collections.sort(sortedYLines);

        // Build the Polygon for containment checks
        Polygon polygon = new Polygon();
        for (Position position : positions) {
            polygon.addPoint(position.xPos(), position.yPos());
        }

        // Create a compressed grid of "Green" status
        int gridWidth = sortedXLines.size();
        int gridHeight = sortedYLines.size();
        boolean[][] isGreenTile = new boolean[gridWidth][gridHeight];

        for (int xIndex = 0; xIndex < gridWidth; xIndex++) {
            for (int yIndex = 0; yIndex < gridHeight; yIndex++) {
                int theaterX = sortedXLines.get(xIndex);
                int theaterY = sortedYLines.get(yIndex);

                // A tile is valid if it's inside the loop or exactly on the red boundary
                if (polygon.contains(theaterX, theaterY) || isPointOnEdge(theaterX, theaterY, positions)) {
                    isGreenTile[xIndex][yIndex] = true;
                }
            }
        }

        // Build 2D Prefix Sum on the compressed grid
        // This allows O(1) checking of any rectangle
        int[][] summedAreaTable = new int[gridWidth + 1][gridHeight + 1];
        for (int x = 0; x < gridWidth; x++) {
            for (int y = 0; y < gridHeight; y++) {
                summedAreaTable[x + 1][y + 1] = (isGreenTile[x][y] ? 1 : 0)
                        + summedAreaTable[x][y + 1]
                        + summedAreaTable[x + 1][y]
                        - summedAreaTable[x][y];
            }
        }

        // Evaluate all pairs of Red Tiles as opposite corners
        long maxArea = 0;
        for (int i = 0; i < positions.size(); i++) {
            for (int j = i + 1; j < positions.size(); j++) {
                Position p1 = positions.get(i);
                Position p2 = positions.get(j);

                int minX = Math.min(p1.xPos(), p2.xPos());
                int maxX = Math.max(p1.xPos(), p2.xPos());
                int minY = Math.min(p1.yPos(), p2.yPos());
                int maxY = Math.max(p1.yPos(), p2.yPos());

                // Find where these theater coordinates exist on our "Compressed Ruler"
                int xIndexStart = Collections.binarySearch(sortedXLines, minX);
                int xIndexEnd = Collections.binarySearch(sortedXLines, maxX);
                int yIndexStart = Collections.binarySearch(sortedYLines, minY);
                int yIndexEnd = Collections.binarySearch(sortedYLines, maxY);

                // Calculate how many green/red tiles are actually in this range
                int expectedTiles = (xIndexEnd - xIndexStart + 1) * (yIndexEnd - yIndexStart + 1);
                int actualTiles = summedAreaTable[xIndexEnd + 1][yIndexEnd + 1]
                        - summedAreaTable[xIndexStart][yIndexEnd + 1]
                        - summedAreaTable[xIndexEnd + 1][yIndexStart]
                        + summedAreaTable[xIndexStart][yIndexStart];
                // If the count matches the area of the compressed rectangle, the theater rectangle is valid
                if (actualTiles == expectedTiles) {
                    long theaterWidth = (long) maxX - minX + 1;
                    long theaterHeight = (long) maxY - minY + 1;
                    maxArea = Math.max(maxArea, theaterWidth * theaterHeight);
                }
            }
        }
        return maxArea;
    }

    private boolean isPointOnEdge(int x, int y, List<Position> points) {
        for (int i = 0; i < points.size(); i++) {
            Position p1 = points.get(i);
            Position p2 = points.get((i + 1) % points.size());
            // Vertical segment check
            if (p1.xPos() == p2.xPos() && x == p1.xPos()) {
                if (y >= Math.min(p1.yPos(), p2.yPos()) && y <= Math.max(p1.yPos(), p2.yPos())) {
                    return true;
                }
            }
            // Horizontal segment check
            if (p1.yPos() == p2.yPos() && y == p1.yPos()) {
                if (x >= Math.min(p1.xPos(), p2.xPos()) && x <= Math.max(p1.xPos(), p2.xPos())) {
                    return true;
                }
            }
        }
        return false;
    }
}
