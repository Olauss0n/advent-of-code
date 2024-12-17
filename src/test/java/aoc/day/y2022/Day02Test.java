package aoc.day.y2022;

import aoc.day.AdventOfCodeBaseTest;
import aoc.util.AdventOfCodeSolver;

public class Day02Test extends AdventOfCodeBaseTest {
    @Override
    protected AdventOfCodeSolver getSolver() {
        return new Day02();
    }

    @Override
    protected Object getExampleSolutionPartOne() {
        return 15;
    }

    @Override
    protected Object getExampleSolutionPartTwo() {
        return 12;
    }
}
