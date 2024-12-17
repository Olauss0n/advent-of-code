package aoc.util;

public interface AdventOfCodeSolver {

    Object solvePartOne(String input);

    Object solvePartTwo(String input);

    default void setup() {
        try {
            InputFetcher.fetchInput(getYear(), getDay());
        } catch (Exception e) {
            System.out.println("Exception while fetching input: " + e);
        }
        String output = "Running test for " + getYearAndDay();
        System.out.println("-".repeat(output.length()));
        System.out.println(output);
        System.out.println("-".repeat(output.length()));
    }

    default void runPartOne(String input) {
        System.out.println("Part one is: " + solvePartOne(input));
    }

    default void runPartTwo(String input) {
        System.out.println("Part two is: " + solvePartTwo(input));
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
