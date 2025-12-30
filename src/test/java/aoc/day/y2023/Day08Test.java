package aoc.day.y2023;

import aoc.day.AdventOfCodeBaseTest;
import aoc.day.AdventOfCodeSolver;
import aoc.util.Reader;

public class Day08Test extends AdventOfCodeBaseTest {
    @Override
    protected AdventOfCodeSolver getSolver() {
        return new Day08();
    }

    @Override
    protected String getExampleInputPartTwo() {
        return Reader.readExampleInputPartTwo(this.getClass());
    }

    @Override
    protected Object getExampleSolutionPartOne() {
        return 2;
    }

    @Override
    protected Object getExampleSolutionPartTwo() {
        return 6;
    }
}
