package aoc.day.y2022;

import aoc.day.AdventOfCodeBaseTest;
import aoc.util.AdventOfCodeSolver;
import aoc.util.Reader;

public class Day03Test extends AdventOfCodeBaseTest {
    @Override
    protected AdventOfCodeSolver getSolver() {
        return new Day03();
    }

    @Override
    protected String getExampleInputPartTwo() {
        return Reader.readExampleInputAsString(this.getClass());
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
