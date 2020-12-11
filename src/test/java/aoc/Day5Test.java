package aoc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day5Test {
    private Day5 day5;

    @BeforeEach
    public void setUp() {
        var inputFile = new File(
                getClass()
                        .getClassLoader()
                        .getResource("day5.txt")
                        .getFile()
        );
        this.day5 = new Day5(inputFile);
    }

    @Test
    public void part1_withTestInput_returnsFakeValue() {
        assertEquals(-1, day5.part1(), "Solution was incorrect");
    }

    @Test
    public void part2_withTestInput_returnsFakeValue() {
        assertEquals(-1, day5.part2(), "Solution was incorrect");
    }
}