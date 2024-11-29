package aoc.runner;

import aoc.day.y2023.Day01;
import aoc.day.y2023.Day02;
import aoc.day.y2023.Day03;
import aoc.day.y2023.Day04;
import aoc.day.y2023.Day05;
import aoc.day.y2023.Day06;
import aoc.day.y2023.Day07;
import aoc.day.y2023.Day08;
import aoc.day.y2023.Day09;
import aoc.day.y2023.Day11;
import aoc.day.y2023.Day13;
import aoc.day.y2023.Day14;

public class Run2023 {

    public static void runDay(int day) {
        switch (day) {
            case 1 -> Day01.run();
            case 2 -> Day02.run();
            case 3 -> Day03.run();
            case 4 -> Day04.run();
            case 5 -> Day05.run();
            case 6 -> Day06.run();
            case 7 -> Day07.run();
            case 8 -> Day08.run();
            case 9 -> Day09.run();
            case 11 -> Day11.run();
            case 13 -> Day13.run();
            case 14 -> Day14.run();
        }
    }
}
