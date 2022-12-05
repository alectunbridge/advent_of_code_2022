package advent_of_code_2022;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DayFiveTest {

    @Test
    void solvePart1And2Test() {
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
        //assertThat(dayFour.solvePart2()).isEqualTo(859);
    }

    @Test
    void solvePart1() {
        DayFive dayFive = new DayFive(Utils.readInputLinesFromFile("day_five.txt"));
        assertThat(dayFive.solvePart1()).isEqualTo("FCVRLMVQP");
        //assertThat(dayFour.solvePart2()).isEqualTo(859);
    }
}