package advent_of_code_2022;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DayFiveTest {

    @Test
    void solvePart1Example() {
        DayFive dayFive = new DayFive(List.of(
                "    [D]    ",
                "[N] [C]    ",
                "[Z] [M] [P]",
                " 1   2   3",
                "",
                "move 1 from 2 to 1",
                "move 3 from 1 to 3",
                "move 2 from 2 to 1",
                "move 1 from 1 to 2"
        ));
        assertThat(dayFive.solvePart1()).isEqualTo("CMZ");
    }
    @Test
    void solvePart2Example() {
        DayFive dayFive = new DayFive(List.of(
                "    [D]    ",
                "[N] [C]    ",
                "[Z] [M] [P]",
                " 1   2   3",
                "",
                "move 1 from 2 to 1",
                "move 3 from 1 to 3",
                "move 2 from 2 to 1",
                "move 1 from 1 to 2"
        ));
        assertThat(dayFive.solvePart2()).isEqualTo("MCD");
    }

    @Test
    void solvePart1() {
        DayFive dayFive = new DayFive(Utils.readInputLinesFromFile("day_five.txt"));
        assertThat(dayFive.solvePart1()).isEqualTo("FCVRLMVQP");
    }
    @Test
    void solvePart2() {
        DayFive dayFive = new DayFive(Utils.readInputLinesFromFile("day_five.txt"));
        assertThat(dayFive.solvePart2()).isEqualTo("RWLWGJGFD");
    }
}