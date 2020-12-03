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
        var policy = new SledPasswordPolicy();

        return getPasswords()
                .stream()
                .filter(policy::validate)
                .collect(Collectors.toList())
                .size();
    }

    public int part2() {
        var policy = new TobogganPasswordPolicy();

        return getPasswords()
                .stream()
                .filter(policy::validate)
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

    private abstract class PasswordPolicy {
        public boolean validate(String passwordLine) {
            var matcher = PASSWORD_PATTERN.matcher(passwordLine);

            if (matcher.find()) {
                var min = Integer.parseInt(matcher.group("min"));
                var max = Integer.parseInt(matcher.group("max"));
                var character = matcher.group("char").charAt(0);
                var password = matcher.group("password");

                return policy(password, min, max, character);
            }
            return false;
        }

        abstract boolean policy(String password, int min, int max, char character);
    }

    private class SledPasswordPolicy extends PasswordPolicy {
        @Override
        protected boolean policy(String password, int min, int max, char character) {
            var occurrences = password.chars().filter(c -> c == character).count();
            return occurrences >= min && occurrences <= max;
        }
    }

    private class TobogganPasswordPolicy extends PasswordPolicy {
        @Override
        protected boolean policy(String password, int min, int max, char character) {
            if (password.charAt(min - 1) == character) {
                return !(password.charAt(max - 1) == character);
            } else if (password.charAt(max - 1) == character) {
                return !(password.charAt(min - 1) == character);
            } else {
                return false;
            }
        }
    }
}
