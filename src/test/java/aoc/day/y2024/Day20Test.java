package aoc.day.y2024;

import aoc.day.AdventOfCodeBaseTest;
import aoc.day.exceptions.NoExampleSolutionGivenException;
import aoc.util.AdventOfCodeSolver;

public class Day20Test extends AdventOfCodeBaseTest {
    @Override
    protected AdventOfCodeSolver getSolver() {
        return new Day20();
    }

    @Override
    protected Object getExampleSolutionPartOne() {
        return 44;
    }

    @Override
    protected Object getExampleSolutionPartTwo() {
        throw new NoExampleSolutionGivenException();
    }
}
