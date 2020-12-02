package aoc;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day2 {
    private final Pattern PASSWORD_PATTERN = Pattern.compile(
            "^(?<min>\\d)-(?<max>\\d) (?<char>\\w): (?<password>.*)$"
    );
    private File input;

    protected Day2(File input) {
        this.input = input;
    }

    public int part1() {
       try (Stream<String> passwords = Files.lines(input.toPath())) {
           return passwords
                   .filter(this::isValid)
                   .collect(Collectors.toList())
                   .size();
       } catch (IOException e) {
           e.printStackTrace();
           throw new RuntimeException("Input file does not exist");
       }
    }

    private boolean isValid(String passwordLine) {
        var matcher = PASSWORD_PATTERN.matcher(passwordLine);

        if (matcher.find()) {
            var min = Integer.parseInt(matcher.group("min"));
            var max = Integer.parseInt(matcher.group("max"));
            var character = matcher.group("char").charAt(0);
            var password = matcher.group("password");
            var occurrences = password.chars().filter(c -> c == character).count();

            return occurrences >= min && occurrences <= max;
        }

        return false;
    }
}
