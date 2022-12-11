package advent_of_code_2022;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


class DayNineTest {

    @Test
    void solvePart1Example() {
        DayNine dayNine = new DayNine(List.of(
                "R 4",
                "U 4",
                "L 3",
                "D 1",
                "R 4",
                "D 1",
                "L 5",
                "R 2"
        ));
        assertThat(dayNine.solvePart1()).isEqualTo(13);
    }

    @Test
    void solvePart1() {
        DayNine dayNine = new DayNine(Utils.readInputLinesFromFile("day_nine.txt"));
        assertThat(dayNine.solvePart1()).isEqualTo(1698);
    }

    @Test
    void part2Example() {
        DayNine part2Example = new DayNine(Arrays.asList(
                "R 5",
                "U 8",
                "L 8",
                "D 3",
                "R 17",
                "D 10",
                "L 25",
                "U 20"
        ));
        int result = part2Example.solvePart2();
        System.out.println(part2Example.getTailPositions());
        assertThat(result).isEqualTo(36);
    }
        

    public static void main(String[] args) {
        DayNine dayNine = new DayNine(Arrays.asList(
                "R 4",
                "U 4",
                "L 3",
                "D 1",
                "R 4",
                "D 1",
                "L 5",
                "R 2"
        ));
        int result = dayNine.solvePart1();

        System.out.println(dayNine.getTailPositions());
        assert result == 13 : "part one example expected 13 but was " + result;

        DayNine part1 = new DayNine(Utils.readInputLinesFromFile("day_nine.txt"));
        result = part1.solvePart1();
        assert result == 6522 : "part one expected ? but was " + result;


        DayNine part2 = new DayNine(Utils.readInputLinesFromFile("day_nine.txt"));
        result = part2.solvePart2();
        assert result == 0 : "part two expected ? but was " + result;

    }
}