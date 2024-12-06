package aoc.util;

public interface AdventOfCodeSolver {

    Object solvePartOne();

    Object solvePartTwo();

    default void setup() {
        String output = "Running test for " + getYearAndDay();
        System.out.println("-".repeat(output.length()));
        System.out.println(output);
        System.out.println("-".repeat(output.length()));
    }

    default void runPartOne() {
        System.out.println("Part one is: " + solvePartOne());
    }

    default void runPartTwo() {
        System.out.println("Part two is: " + solvePartTwo());
    }

    private String getYearAndDay() {
        return "year: " + getYear() + ", day: " + getDay();
    }

    private int getYear() {
        String packageName = this.getClass().getPackageName();
        String year = packageName.substring(packageName.lastIndexOf('.') + 1).substring(1);
        return Integer.parseInt(year);
    }

    private int getDay() {
        String className = this.getClass().getSimpleName();
        String day = className.replaceAll("[^0-9]", "");
        return Integer.parseInt(day);
    }
}
