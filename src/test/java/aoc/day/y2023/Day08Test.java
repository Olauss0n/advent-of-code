package aoc.day.y2023;

import aoc.day.AdventOfCodeBaseTest;
import aoc.day.exceptions.NoExampleSolutionGivenException;
import aoc.util.AdventOfCodeSolver;

public class Day08Test extends AdventOfCodeBaseTest {
    @Override
    protected AdventOfCodeSolver getSolver() {
        return new Day08();
    }

    @Override
    protected Object getExampleSolutionPartOne() {
        return 2;
    }

    @Override
    protected Object getExampleSolutionPartTwo() {
        throw new NoExampleSolutionGivenException();
    }
}
