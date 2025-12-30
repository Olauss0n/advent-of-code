package aoc.day.y2024;

import aoc.day.AdventOfCodeBaseTest;
import aoc.day.AdventOfCodeSolver;
import aoc.day.exceptions.NoExampleSolutionGivenException;

public class Day15Test extends AdventOfCodeBaseTest {
    @Override
    protected AdventOfCodeSolver getSolver() {
        return new Day15();
    }

    @Override
    protected Object getExampleSolutionPartOne() {
        return 10092;
    }

    @Override
    protected Object getExampleSolutionPartTwo() {
        throw new NoExampleSolutionGivenException();
    }
}
