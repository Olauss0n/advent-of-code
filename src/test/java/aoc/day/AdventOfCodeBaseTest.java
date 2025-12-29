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
            getSolver().runPartOne(getInput(), false);
        } catch (NotImplementedException e) {
            System.out.println("Part one: Not implemented.");
        }
    }

    @Test
    public void testPartTwo() {
        try {
            getSolver().runPartTwo(getInput(), false);
        } catch (NotImplementedException e) {
            System.out.println("Part two: Not implemented.");
        }
    }

    @Test
    public void verifyExamples() {
        boolean noExceptions = true;
        noExceptions &= runWithExceptionHandling(() -> {
            System.out.print("Example part one: \n");
            Object examplePartOne = getSolver().solvePartOne(getExampleInput(), true);
            System.out.printf("%s\n", examplePartOne);
            assertSolutionMatches(getExampleSolutionPartOne(), examplePartOne, "Part one is wrong.");
        });
        noExceptions &= runWithExceptionHandling(() -> {
            System.out.print("Example part two: \n");
            Object examplePartTwo = getSolver().solvePartTwo(getExampleInputPartTwo(), true);
            System.out.printf("%s\n", examplePartTwo);
            assertSolutionMatches(getExampleSolutionPartTwo(), examplePartTwo, "Part two is wrong.");
        });
        if (noExceptions) {
            System.out.println("Examples are verified and correct.");
        }
    }

    private boolean runWithExceptionHandling(Runnable runnable) {
        try {
            runnable.run();
            return true;
        } catch (NoExampleGivenException e) {
            System.out.println("No example was given.");
        } catch (NoExampleSolutionGivenException e) {
            System.out.println("No example solution was given.");
        } catch (NotImplementedException e) {
            System.out.println("Not implemented.");
        } catch (FileNotFoundException e) {
            System.out.println("File was not found: " + e.getMessage());
        }
        return false;
    }

    private void assertSolutionMatches(Object expected, Object actual, String errorMessage) {
        if (expected instanceof Number expectedNumber && actual instanceof Number actualNumber) {
            assertEquals(expectedNumber.longValue(), actualNumber.longValue(), errorMessage);
        } else {
            assertEquals(expected, actual, errorMessage);
        }
    }
}
