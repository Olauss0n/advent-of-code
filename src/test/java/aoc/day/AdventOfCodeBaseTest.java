package aoc.day;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import aoc.util.AdventOfCodeSolver;

public abstract class AdventOfCodeBaseTest {

    protected abstract AdventOfCodeSolver getSolver();

    @BeforeEach
    public void setup() {
        getSolver().setup();
    }

    @Test
    public void testPartOne() {
        getSolver().runPartOne();
    }

    @Test
    public void testPartTwo() {
        getSolver().runPartTwo();
    }
}
