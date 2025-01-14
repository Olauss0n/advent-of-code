package aoc.day.y2022;

import aoc.day.AdventOfCodeBaseTest;
import aoc.util.AdventOfCodeSolver;

public class Day03Test extends AdventOfCodeBaseTest {
    @Override
    protected AdventOfCodeSolver getSolver() {
        return new Day03();
    }

    @Override
    protected Object getExampleSolutionPartOne() {
        return 157;
    }

    @Override
    protected Object getExampleSolutionPartTwo() {
        return 70;
    }
}
