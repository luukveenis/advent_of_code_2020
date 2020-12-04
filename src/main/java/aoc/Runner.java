package aoc;

import java.io.File;
import java.io.IOException;

public class Runner {
    public static void main(String[] args) throws IOException {
        var day1input = new File(Runner.class.getClassLoader().getResource("day1.txt").getFile());
        var day1 = new Day1(day1input);

        System.out.println("Day 1, part 1: " + day1.part1());
        System.out.println("Day 1, part 2: " + day1.part2());

        var day2input = new File(Runner.class.getClassLoader().getResource("day2.txt").getFile());
        var day2 = new Day2(day2input);

        System.out.println("Day 2, part 1: " + day2.part1());
        System.out.println("Day 2, part 2: " + day2.part2());

        var day3input = new File(Runner.class.getClassLoader().getResource("day3.txt").getFile());
        var day3 = new Day3(day3input);

        System.out.println("Day 3, part 1: " + day3.part1());
        System.out.println("Day 3, part 2: " + day3.part2());
    }
}
