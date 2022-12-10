package advent_of_code_2022;

import org.junit.jupiter.api.Test;

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
        assertThat(dayNine.solvePart1()).isEqualTo(6236);
    }

    @Test
    void solvePart2Example() {
        DayNine dayNine = new DayNine(List.of(
                "R 5",
                "U 8",
                "L 8",
                "D 3",
                "R 17",
                "D 10",
                "L 25",
                "U 20"
        ));
        assertThat(dayNine.solvePart2()).isEqualTo(36);
    }

    @Test
    void solvePart2() {
        DayNine dayNine = new DayNine(Utils.readInputLinesFromFile("day_nine.txt"));
        assertThat(dayNine.solvePart2()).isEqualTo(2449);
    }
}