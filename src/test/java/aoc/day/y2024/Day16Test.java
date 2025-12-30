package aoc.day.y2024;

import aoc.day.AdventOfCodeBaseTest;
import aoc.day.AdventOfCodeSolver;

public class Day16Test extends AdventOfCodeBaseTest {
    @Override
    protected AdventOfCodeSolver getSolver() {
        return new Day16();
    }

    @Override
    protected Object getExampleSolutionPartOne() {
        return 7036;
    }

    @Override
    protected Object getExampleSolutionPartTwo() {
        return 45;
    }
}
