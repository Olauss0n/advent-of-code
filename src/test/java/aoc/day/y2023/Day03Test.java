package aoc.day.y2023;

import aoc.day.AdventOfCodeBaseTest;
import aoc.day.AdventOfCodeSolver;

public class Day03Test extends AdventOfCodeBaseTest {
    @Override
    protected AdventOfCodeSolver getSolver() {
        return new Day03();
    }

    @Override
    protected Object getExampleSolutionPartOne() {
        return 4361;
    }

    @Override
    protected Object getExampleSolutionPartTwo() {
        return 467835;
    }
}
