package aoc.day.y2025;

import aoc.day.AdventOfCodeBaseTest;
import aoc.day.AdventOfCodeSolver;
import aoc.util.exceptions.NotImplementedException;

public class Day12Test extends AdventOfCodeBaseTest {
    @Override
    protected AdventOfCodeSolver getSolver() {
        return new Day12();
    }

    @Override
    protected Object getExampleSolutionPartOne() {
        return 2;
    }

    @Override
    protected Object getExampleSolutionPartTwo() {
        throw new NotImplementedException();
    }
}
