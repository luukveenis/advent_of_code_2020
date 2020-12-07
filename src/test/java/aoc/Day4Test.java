package aoc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day4Test {
    private Day4 day4;

    @BeforeEach
    public void setUp() {
        var inputFile = new File(
                getClass()
                        .getClassLoader()
                        .getResource("day4.txt")
                        .getFile()
        );
        this.day4 = new Day4(inputFile);
    }

    @Test
    public void part1_withTestInput_returnsFakeValue() {
        assertEquals(-1, day4.part1(), "Solution was incorrect");
    }
}