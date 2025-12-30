package aoc.day.y2023;

import aoc.day.AdventOfCodeBaseTest;
import aoc.day.AdventOfCodeSolver;
import aoc.util.Reader;

public class Day01Test extends AdventOfCodeBaseTest {
    @Override
    protected AdventOfCodeSolver getSolver() {
        return new Day01();
    }

    @Override
    protected String getExampleInputPartTwo() {
        return Reader.readExampleInputPartTwo(this.getClass());
    }

    @Override
    protected Object getExampleSolutionPartOne() {
        return 142;
    }

    @Override
    protected Object getExampleSolutionPartTwo() {
        return 281;
    }
}
