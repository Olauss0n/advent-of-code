package aoc.day;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import aoc.day.exceptions.NoExampleGivenException;
import aoc.day.exceptions.NoExampleSolutionGivenException;
import aoc.util.exception.FileNotFoundException;
import aoc.util.exception.NoPuzzleAvailableException;
import aoc.util.exception.NotImplementedException;
import aoc.util.io.Reader;
import lombok.RequiredArgsConstructor;

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
        } catch (NoPuzzleAvailableException e) {
            System.out.println("Part two: No puzzle is available for this part.");
        }
    }

    @Test
    public void verifyExamplePartOne() {
        verifyExample(Part.ONE);
    }

    @Test
    public void verifyExamplePartTwo() {
        verifyExample(Part.TWO);
    }

    private void verifyExample(Part part) {
        try {
            String input =
                    switch (part) {
                        case ONE -> getExampleInput();
                        case TWO -> getExampleInputPartTwo();
                    };
            Object expected =
                    switch (part) {
                        case ONE -> getExampleSolutionPartOne();
                        case TWO -> getExampleSolutionPartTwo();
                    };
            if (expected == null) {
                System.out.printf("Example part %d: No solution provided to verify against.", part.number);
                return;
            }
            Object actual =
                    switch (part) {
                        case ONE -> getSolver().solvePartOne(input, true);
                        case TWO -> getSolver().solvePartTwo(input, true);
                    };

            System.out.printf("Example part %d: %s\n", part.number, actual);
            assertSolutionMatches(expected, actual, "Part " + part + " example solution mismatch.");
        } catch (NoExampleGivenException e) {
            System.out.printf("No example was given for part %d. \n", part.number);
        } catch (NoExampleSolutionGivenException e) {
            System.out.printf("No example solution was given for part %d.\n", part.number);
        } catch (NotImplementedException e) {
            System.out.printf("Part %d is not implemented.\n", part.number);
        } catch (NoPuzzleAvailableException e) {
            System.out.printf("No puzzle is available for part %d.\n", part.number);
        } catch (FileNotFoundException e) {
            System.out.printf("File was not found: %s.\n", e.getMessage());
        }
    }

    private void assertSolutionMatches(Object expected, Object actual, String errorMessage) {
        if (expected instanceof Number expectedNumber && actual instanceof Number actualNumber) {
            assertEquals(expectedNumber.longValue(), actualNumber.longValue(), errorMessage);
        } else {
            assertEquals(expected, actual, errorMessage);
        }
    }

    @RequiredArgsConstructor
    private enum Part {
        ONE(1),
        TWO(2);

        private final int number;
    }
}
