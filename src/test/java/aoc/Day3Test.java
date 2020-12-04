package aoc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    public void part1_withTestInput_encountersSevenTrees() {
        assertEquals(7, day3.part1(), "Solution was incorrect");
    }

    @Test
    public void part2_withTestInput_returnsExpectedValue() {
        assertEquals(BigInteger.valueOf(336), day3.part2(), "Solution was incorrect");
    }
}