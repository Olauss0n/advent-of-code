package aoc.day;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import aoc.day.exceptions.NoExampleGivenException;
import aoc.day.exceptions.NoExampleSolutionGivenException;
import aoc.util.AdventOfCodeSolver;
import aoc.util.Reader;
import aoc.util.exceptions.FileNotFoundException;
import aoc.util.exceptions.NotImplementedException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public abstract class AdventOfCodeBaseTest {

    protected abstract AdventOfCodeSolver getSolver();

    protected abstract Object getExampleSolutionPartOne();

    protected abstract Object getExampleSolutionPartTwo();

    protected String getInput() {
        return Reader.readInput(getSolver().getClass());
    }

    protected String getExampleInput() {
        return Reader.readExampleInput(getSolver().getClass());
    }

    protected String getExampleInputPartTwo() {
        return getExampleInput();
    }

    @BeforeEach
    public void setup() {
        getSolver().setup();
    }

    @Test
    public void testPartOne() {
        try {
            getSolver().runPartOne(getInput());
        } catch (NotImplementedException e) {
            System.out.println("Part one: Not implemented.");
        }
    }

    @Test
    public void testPartTwo() {
        try {
            getSolver().runPartTwo(getInput());
        } catch (NotImplementedException e) {
            System.out.println("Part two: Not implemented.");
        }
    }

    @Test
    public void verifyExamples() {
        try {
            System.out.print("Example part one: ");
            Object examplePartOne = getSolver().solvePartOne(getExampleInput());
            System.out.printf("%s\n", examplePartOne);
            assertSolutionMatches(getExampleSolutionPartOne(), examplePartOne, "Part one is wrong.");

            System.out.print("Example part two: ");
            Object examplePartTwo = getSolver().solvePartTwo(getExampleInputPartTwo());
            System.out.printf("%s\n", examplePartTwo);
            assertSolutionMatches(getExampleSolutionPartTwo(), examplePartTwo, "Part two is wrong.");

            System.out.println("Examples are verified and correct.");
        } catch (NoExampleGivenException e) {
            System.out.println("No example was given.");
        } catch (NoExampleSolutionGivenException e) {
            System.out.println("No example solution was given.");
        } catch (NotImplementedException e) {
            System.out.println("Not implemented.");
        } catch (FileNotFoundException e) {
            System.out.println("File was not found: " + e.getMessage());
        }
    }

    private void assertSolutionMatches(Object expected, Object actual, String errorMessage) {
        if (expected instanceof Number && actual instanceof Number) {
            assertEquals(((Number) expected).longValue(), ((Number) actual).longValue(), errorMessage);
        } else {
            assertEquals(expected, actual, errorMessage);
        }
    }
}
