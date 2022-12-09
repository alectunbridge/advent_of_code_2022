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
        assertThat(dayNine.solvePart1()).isEqualTo(1698);
    }
}