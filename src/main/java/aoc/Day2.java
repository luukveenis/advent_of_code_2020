package aoc;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day2 {
    private final Pattern PASSWORD_PATTERN = Pattern.compile(
            "^(?<min>\\d+)-(?<max>\\d+) (?<char>\\w): (?<password>.*)$"
    );
    private File input;

    protected Day2(File input) {
        this.input = input;
    }

    public int part1() {
        return getPasswords()
                .stream()
                .filter(this::isValidPolicy1)
                .collect(Collectors.toList())
                .size();
    }

    public int part2() {
        return getPasswords()
                .stream()
                .filter(this::isValidPolicy2)
                .collect(Collectors.toList())
                .size();
    }

    private List<String> getPasswords() {
        try (Stream<String> passwords = Files.lines(input.toPath())) {
            return passwords.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Input file does not exist");
        }
    }

    private boolean isValidPolicy1(String passwordLine) {
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

    private boolean isValidPolicy2(String passwordLine) {
        var matcher = PASSWORD_PATTERN.matcher(passwordLine);

        if (matcher.find()) {
            var min = Integer.parseInt(matcher.group("min")) - 1;
            var max = Integer.parseInt(matcher.group("max")) - 1;
            var character = matcher.group("char").charAt(0);
            var password = matcher.group("password");

            if (password.charAt(min) == character) {
               return !(password.charAt(max) == character);
            } else if (password.charAt(max) == character) {
               return !(password.charAt(min) == character);
            } else {
                return false;
            }
        }

        return false;
    }
}
