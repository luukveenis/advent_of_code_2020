package aoc;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class Day1Test {
    @Test
    public void solve_withTestInput_isCorrect() throws Exception {
        File file = new File(
                getClass()
                        .getClassLoader()
                        .getResource("day1/test.txt")
                        .getFile()
        );
        var day1 = new Day1(file);

        assertEquals(514579, day1.solve(), "Solution was incorrect");
    }
}