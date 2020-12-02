package aoc;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day1 {
    private File input;

    protected Day1(File input) {
        this.input = input;
    }

    public int solve() throws IOException {
        List<Integer> inputs;

        try (Stream<String> stream = Files.lines(input.toPath())) {
           inputs = stream
                   .map(x -> Integer.parseInt(x))
                   .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }

        for (var x : inputs) {
            for (var y: inputs.subList(1, inputs.size() - 1)) {
                if (x + y == 2020) {
                    return x * y;
                }
            }
        }

       throw new RuntimeException("No numbers added up to 2020");
    }
}
