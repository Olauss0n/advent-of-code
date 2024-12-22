package aoc.day.y2024;

import aoc.day.AdventOfCodeBaseTest;
import aoc.day.exceptions.NoExampleSolutionGivenException;
import aoc.util.AdventOfCodeSolver;

public class Day22Test extends AdventOfCodeBaseTest {
    @Override
    protected AdventOfCodeSolver getSolver() {
        return new Day22();
    }

    @Override
    protected Object getExampleSolutionPartOne() {
        return 37327623;
    }

    @Override
    protected Object getExampleSolutionPartTwo() {
        throw new NoExampleSolutionGivenException();
    }
}
