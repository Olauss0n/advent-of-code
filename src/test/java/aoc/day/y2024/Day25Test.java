package aoc.day.y2024;

import aoc.day.AdventOfCodeBaseTest;
import aoc.day.exceptions.NoExampleSolutionGivenException;
import aoc.util.AdventOfCodeSolver;

public class Day25Test extends AdventOfCodeBaseTest {
    @Override
    protected AdventOfCodeSolver getSolver() {
        return new Day25();
    }

    @Override
    protected Object getExampleSolutionPartOne() {
        return 3;
    }

    @Override
    protected Object getExampleSolutionPartTwo() {
        throw new NoExampleSolutionGivenException();
    }
}
