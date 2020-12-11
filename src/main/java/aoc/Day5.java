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
        var seatIds = getBoardingPasses()
                .stream()
                .map(this::getSeatId)
                .sorted()
                .collect(Collectors.toList());

        /* Seat IDs are an arithmetic sequence. We can get the missing seat ID by
           calculating the sum of the sequence, then subtracting off all the seat
           IDs that we know to leave the missing one. This relies on the guarantee
           from the problem statement that the seat isn't the first or last.
         */
        var lowestSeat = seatIds.get(0);
        var highestSeat = seatIds.get(seatIds.size() - 1);
        var sum = ((seatIds.size() + 1) * (lowestSeat + highestSeat)) / 2;

        return seatIds.stream().reduce(sum, (result, seatId) -> result - seatId);
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