package aoc.day;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import aoc.util.AdventOfCodeSolver;
import static org.junit.jupiter.api.Assertions.assertEquals;

public abstract class AdventOfCodeBaseTest {

    protected abstract AdventOfCodeSolver getSolver();

    protected abstract String getInput();

    protected abstract String getExampleInput();

    protected abstract Object getExampleSolutionPartOne();

    protected abstract Object getExampleSolutionPartTwo();

    @BeforeEach
    public void setup() {
        getSolver().setup();
    }

    @Test
    public void testPartOne() {
        getSolver().runPartOne(getInput());
    }

    @Test
    public void testPartTwo() {
        getSolver().runPartTwo(getInput());
    }

    @Test
    public void verifyExamples() {
        Object examplePartOne = getSolver().solvePartOne(getExampleInput());
        Object examplePartTwo = getSolver().solvePartTwo(getExampleInput());
        System.out.printf("Example part one: %s\nExample part two: %s\n", examplePartOne, examplePartTwo);
        assertEquals(getExampleSolutionPartOne(), examplePartOne, "Part one is wrong");
        assertEquals(getExampleSolutionPartTwo(), examplePartTwo, "Part two is wrong");
        System.out.println("Examples are verified and correct.");
    }
}
