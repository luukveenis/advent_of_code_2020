package aoc;

import com.google.common.collect.Sets;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day1 {
    private File input;

    protected Day1(File input) {
        this.input = input;
    }

    public int solve() throws IOException {
        Set<Integer> inputs;

        try (Stream<String> stream = Files.lines(input.toPath())) {
           inputs = stream
                   .map(x -> Integer.parseInt(x))
                   .collect(Collectors.toSet());
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }

        var match = Sets.combinations(inputs, 2)
                .stream()
                .filter(set -> set.stream().mapToInt(Integer::intValue).sum() == 2020)
                .findFirst()
                .orElseThrow();

        return match.stream().reduce(1, (x, y) -> x * y);
    }
}
