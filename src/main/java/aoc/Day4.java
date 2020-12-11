package aoc;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.regex.Pattern;

public class Day4 {
    private File input;
    private static final String[] REQUIRED_FIELDS = {"byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid"};
    private static final Set<String> REQUIRED_FIELD_SET = new HashSet<>(Arrays.asList(REQUIRED_FIELDS));
    private static final List<String> EYE_COLOURS = List.of("amb", "blu", "brn", "gry", "grn", "hzl", "oth");

    protected Day4(File input) {
        this.input = input;
    }

    public long part1() {
        return getPassports()
                .stream()
                .filter(passport -> passport.keySet().containsAll(REQUIRED_FIELD_SET))
                .count();
    }

    public long part2() {
        return getPassports()
                .stream()
                .filter(passport -> passport.keySet().containsAll(REQUIRED_FIELD_SET))
                .filter(this::isValid)
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

    private boolean isValid(HashMap<String, String> passport) {
        for (var entry : passport.entrySet()) {
            switch (entry.getKey()) {
                case "byr":
                    if (!isValidYear(entry.getValue(), 1920, 2002)) {
                        return false;
                    }
                    break;
                case "iyr":
                    if (!isValidYear(entry.getValue(), 2010, 2020)) {
                        return false;
                    }
                    break;
                case "eyr":
                    if (!isValidYear(entry.getValue(), 2020, 2030)) {
                        return false;
                    }
                    break;
                case "hgt":
                    if (!isValidHeight(entry.getValue())) {
                        return false;
                    }
                    break;
                case "hcl":
                    if (!entry.getValue().matches("^#[0-9a-f]{6}$")) {
                        return false;
                    }
                    break;
                case "ecl":
                    if (!EYE_COLOURS.contains(entry.getValue())) {
                        return false;
                    }
                    break;
                case "pid":
                    if (!entry.getValue().matches("\\d{9}")) {
                        return false;
                    }
                    break;
                default:
                    break;
            }
        }

        return true;
    }

    private boolean isValidYear(String yearText, int lowerBound, int upperBound) {
        if (!Pattern.matches("\\d{4}", yearText)) {
            return false;
        }

        var year = Integer.parseInt(yearText);
        return year >= lowerBound && year <= upperBound;
    }

    private boolean isValidHeight(String height) {
        var matcher = Pattern.compile("(?<value>\\d+)(?<units>cm|in)").matcher(height);

        if (matcher.matches()) {
            var value = Integer.parseInt(matcher.group("value"));

            if (matcher.group("units").equals("cm")) {
                return value >= 150 && value <= 193;
            } else {
                return value >= 59 && value <= 76;
            }
        } else {
            return false;
        }
    }
}