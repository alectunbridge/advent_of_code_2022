package advent_of_code_2022;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DayThree {
    private List<String> input;

    public DayThree(List<String> input) {
        this.input = input;
    }

    public int solvePart1() {
        int total = 0;
        for (String line : input) {
            List<String> compartment1 = Stream.of(line.substring(0, line.length() / 2).split("")).collect(Collectors.toCollection(ArrayList::new));
            List<String> compartment2 = List.of(line.substring(line.length() / 2).split(""));
            compartment1.retainAll(compartment2);
            total += compartment1.stream().distinct().mapToInt(s -> {
                int offset = -'a' + 1;
                char c = s.charAt(0);
                if (Character.isUpperCase(c)) {
                    offset = -'A' + 27;
                }
                return c + offset;
            }).sum();
        }
        return total;
    }
}
