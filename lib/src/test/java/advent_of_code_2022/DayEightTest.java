package advent_of_code_2022;

import org.junit.jupiter.api.Test;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;


class DayEightTest {

    @Test
    void part1Example() {
        DayEight dayEight = new DayEight(List.of(
                "30373",
                "25512",
                "65332",
                "33549",
                "35390"
        ));
        assertThat(dayEight.solvePart1()).isEqualTo(21);
    }

    @Test
    void solvePart1() {
        DayEight dayEight = new DayEight(Utils.readInputLinesFromFile("day_eight.txt"));
        assertThat(dayEight.solvePart1()).isEqualTo(1698);
    }

    @Test
    void solvePartExample2() {
        DayEight dayEight = new DayEight(List.of(
                "30373",
                "25512",
                "65332",
                "33549",
                "35390"
        ));
        assertThat(dayEight.solvePart2()).isEqualTo(8);
    }

    @Test
    void solvePart2() {
        DayEight dayEight = new DayEight(Utils.readInputLinesFromFile("day_eight.txt"));
        assertThat(dayEight.solvePart2()).isEqualTo(672280);
    }
}