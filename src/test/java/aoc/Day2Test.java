package aoc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class Day2Test {
    private Day2 day2;

    @BeforeEach
    public void setUp() {
        var inputFile = new File(
                getClass()
                        .getClassLoader()
                        .getResource("day2.txt")
                        .getFile()
        );
        this.day2 = new Day2(inputFile);
    }

    @Test
    public void part1_withTestInput_has2ValidPasswords() {
        assertEquals(3, day2.part1(), "Solution was incorrect");
    }

    @Test
    public void part2_withTestInput_has1ValidPassword() {
        assertEquals(2, day2.part2(), "Solution was incorrect");
    }
}