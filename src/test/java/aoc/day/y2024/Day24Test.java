package aoc.day.y2024;

import aoc.day.AdventOfCodeBaseTest;
import aoc.day.AdventOfCodeSolver;
import aoc.day.exceptions.NoExampleSolutionGivenException;

public class Day24Test extends AdventOfCodeBaseTest {
    @Override
    protected AdventOfCodeSolver getSolver() {
        return new Day24();
    }

    @Override
    protected Object getExampleSolutionPartOne() {
        return 4;
    }

    @Override
    protected Object getExampleSolutionPartTwo() {
        throw new NoExampleSolutionGivenException();
    }
}
