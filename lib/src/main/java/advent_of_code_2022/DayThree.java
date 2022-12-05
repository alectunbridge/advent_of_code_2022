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
            total += compartment1.stream().distinct().mapToInt(this::mapItemToPriority).sum();
        }
        return total;
    }

    public int solvePart2() {
        int total = 0;
        for (int i = 0; i <= input.size() - 3; i += 3) {
            List<String> elf1 = Stream.of(input.get(i).split("")).collect(Collectors.toCollection(ArrayList::new));
            List<String> elf2 = List.of(input.get(i + 1).split(""));
            List<String> elf3 = List.of(input.get(i + 2).split(""));
            elf1.retainAll(elf2);
            elf1.retainAll(elf3);
            total += elf1.stream().distinct().mapToInt(this::mapItemToPriority).sum();
        }
        return total;
    }

    private int mapItemToPriority(String item) {
        int offset = -'a' + 1;
        char itemAsChar = item.charAt(0);
        if (Character.isUpperCase(itemAsChar)) {
            offset = -'A' + 27;
        }
        return itemAsChar + offset;

    }
}
