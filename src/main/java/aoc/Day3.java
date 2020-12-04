package aoc;

import com.google.common.primitives.Chars;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day3 {
    private static final char TREE = '#';
    private File input;

    protected Day3(File input) {
        this.input = input;
    }

    public int part1() {
        return traverse(3, 1);
    }

    private int traverse(int right, int down) {
        List<List<Character>> grid = getGrid();
        int gridWidth = grid.get(0).size();
        int gridHeight = grid.size();
        int currentX = 0;
        int currentY = 0;
        int treeCounter = 0;

        for (; currentY <= gridHeight - 1; currentX += right, currentY += down) {
           if (grid.get(currentY % gridHeight).get(currentX % gridWidth) == TREE) {
               treeCounter++;
           }
        }

        return treeCounter;
    }

    private List<List<Character>> getGrid() {
        try (Stream<String> lines = Files.lines(input.toPath())) {
           return lines
                   .map(line -> Chars.asList(line.toCharArray()))
                   .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Could not read input file");
        }
    }
}
