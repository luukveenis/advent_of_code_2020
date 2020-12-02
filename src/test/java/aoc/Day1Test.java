package aoc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day1Test {
    private Day1 day1;

    @BeforeEach
    public void setUp() {
        var inputFile = new File(
                getClass()
                        .getClassLoader()
                        .getResource("day1/test.txt")
                        .getFile()
        );
        day1 = new Day1(inputFile);
    }

    @Test
    public void part1_withTestInput_isCorrect() {
        assertEquals(514579, day1.part1(), "Solution was incorrect");
    }

    @Test
    public void part2_withTestInput_isCorrect() {
        assertEquals(241861950, day1.part2(), "Solution was incorrect");
    }
}