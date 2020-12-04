package aoc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class Day3Test {
    private Day3 day3;

    @BeforeEach
    public void setUp() {
        var inputFile = new File(
                getClass()
                        .getClassLoader()
                        .getResource("day3.txt")
                        .getFile()
        );
        this.day3 = new Day3(inputFile);
    }

    @Test
    public void part1_notImplemented_returnsNegativeOne() {
        assertEquals(-1, day3.part1(), "Solution was incorrect");
    }
}