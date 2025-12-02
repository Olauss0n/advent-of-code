package aoc.day.y2025;

import aoc.day.AdventOfCodeBaseTest;
import aoc.util.AdventOfCodeSolver;

public class Day02Test extends AdventOfCodeBaseTest {
    @Override
    protected AdventOfCodeSolver getSolver() {
        return new Day02();
    }

    @Override
    protected Object getExampleSolutionPartOne() {
        return 1227775554;
    }

    @Override
    protected Object getExampleSolutionPartTwo() {
        return 4174379265L;
    }
}
