package aoc;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.*;

public class Day4 {
    private File input;
    private static final String[] REQUIRED_FIELDS = {"byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid"};
    private static final Set<String> REQUIRED_FIELD_SET = new HashSet<>(Arrays.asList(REQUIRED_FIELDS));

    protected Day4(File input) {
        this.input = input;
    }

    public long part1() {
        return getPassports()
                .stream()
                .filter(passport -> passport.keySet().containsAll(REQUIRED_FIELD_SET))
                .count();
    }

    private List<HashMap<String, String>> getPassports() {
        List<HashMap<String, String>> passports = new ArrayList<>();
        passports.add(new HashMap<>());

        try (var lines = Files.lines(input.toPath())) {
            lines.forEach(line -> {
                if (line.isBlank()) {
                    passports.add(new HashMap<>());
                } else {
                    for (var field : line.split("\\p{javaWhitespace}")) {
                        var currentPassport = passports.get(passports.size() - 1);
                        var fieldParts = field.split(":");

                        currentPassport.put(fieldParts[0], fieldParts[fieldParts.length - 1]);
                    }
                }
            });

            return passports;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Could not read input file");
        }
    }
}