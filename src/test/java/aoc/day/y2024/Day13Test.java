package aoc.day.y2024;

import aoc.day.AdventOfCodeBaseTest;
import aoc.day.AdventOfCodeSolver;
import aoc.day.exceptions.NoExampleGivenException;
import aoc.day.exceptions.NoExampleSolutionGivenException;

public class Day13Test extends AdventOfCodeBaseTest {
    @Override
    protected AdventOfCodeSolver getSolver() {
        return new Day13();
    }

    @Override
    protected String getExampleInputPartTwo() {
        throw new NoExampleGivenException();
    }

    @Override
    protected Object getExampleSolutionPartOne() {
        return 480;
    }

    @Override
    protected Object getExampleSolutionPartTwo() {
        throw new NoExampleSolutionGivenException();
    }
}
