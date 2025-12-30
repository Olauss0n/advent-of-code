package aoc.day.y2024;

import aoc.day.AdventOfCodeBaseTest;
import aoc.day.AdventOfCodeSolver;

public class Day23Test extends AdventOfCodeBaseTest {
    @Override
    protected AdventOfCodeSolver getSolver() {
        return new Day23();
    }

    @Override
    protected Object getExampleSolutionPartOne() {
        return 7;
    }

    @Override
    protected Object getExampleSolutionPartTwo() {
        return "co,de,ka,ta";
    }
}
