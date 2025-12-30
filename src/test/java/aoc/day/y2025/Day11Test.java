package aoc.day.y2025;

import aoc.day.AdventOfCodeBaseTest;
import aoc.day.AdventOfCodeSolver;
import aoc.util.Reader;

public class Day11Test extends AdventOfCodeBaseTest {
    @Override
    protected AdventOfCodeSolver getSolver() {
        return new Day11();
    }

    @Override
    protected String getExampleInputPartTwo() {
        return Reader.readExampleInputPartTwo(this.getClass());
    }

    @Override
    protected Object getExampleSolutionPartOne() {
        return 5;
    }

    @Override
    protected Object getExampleSolutionPartTwo() {
        return 2;
    }
}
