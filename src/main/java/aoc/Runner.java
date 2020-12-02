package aoc;

import java.io.File;
import java.io.IOException;

public class Runner {
    public static void main(String[] args) throws IOException {
        var day1input = new File(Runner.class.getClassLoader().getResource("day1.txt").getFile());
        var day1 = new Day1(day1input);

        System.out.println("Day 1, part 1: " + day1.part1());
        System.out.println("Day 1, part 2: " + day1.part2());
    }
}
