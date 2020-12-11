package aoc;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day5 {
    private File input;

    protected Day5(File input) {
        this.input = input;
    }

    public int part1() {
        return getBoardingPasses()
                .stream()
                .map(this::getSeatId)
                .max(Integer::compareTo)
                .get();
    }

    public long part2() {
        return -1;
    }

    private int getSeatId(String boardingPass) {
        var rowIdentifier = boardingPass.substring(0, 7);
        var columnIdentifier = boardingPass.substring(7, 10);

        return getRowOrColumn(rowIdentifier, 0, 127) * 8 + getRowOrColumn(columnIdentifier, 0, 7);
    }

    private int getRowOrColumn(String input, int low, int high) {
        if (input.length() == 1) {
            return input.equals("F") || input.equals("L") ? low : high;
        }

        var midpoint = (int) Math.floor((low + high) / 2);

        if (input.charAt(0) == 'F' || input.charAt(0) == 'L') {
            return getRowOrColumn(input.substring(1), low, midpoint);
        } else {
            return getRowOrColumn(input.substring(1), midpoint + 1, high);
        }
    }

    private List<String> getBoardingPasses() {
        try (var passes = Files.lines(input.toPath())) {
           return passes.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to read input file");
        }
    }
}