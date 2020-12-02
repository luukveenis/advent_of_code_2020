package aoc;

import com.google.common.collect.Sets;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day1 {
    private File input;

    protected Day1(File input) {
        this.input = input;
    }

    public int solve() {
        var inputs = getInputs();
        var subset = findSubsetBySum(inputs, 2, 2020);

        return subset.stream().reduce(1, Math::multiplyExact);
    }

    private Set<Integer> getInputs() {
        try (Stream<String> stream = Files.lines(input.toPath())) {
            return stream
                    .map(x -> Integer.parseInt(x))
                    .collect(Collectors.toSet());
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("File does not exist");
        }
    }

    private Set<Integer> findSubsetBySum(Set<Integer> set, int setSize, int sumTo) {
       return Sets.combinations(set, setSize)
               .stream()
               .filter(subset -> subset.stream().mapToInt(Integer::intValue).sum() == sumTo)
               .findFirst()
               .orElseThrow();
    }
}
