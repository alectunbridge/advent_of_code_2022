package advent_of_code_2022;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DayTwoTest {

    @Test
    void drawRock() {
        DayTwo dayTwo = new DayTwo(List.of("A X"));
        assertThat(dayTwo.solvePart1()).isEqualTo(4);
    }

    @Test
    void drawPaper() {
        DayTwo dayTwo = new DayTwo(List.of("B Y"));
        assertThat(dayTwo.solvePart1()).isEqualTo(5);
    }

    @Test
    void drawScissors() {
        DayTwo dayTwo = new DayTwo(List.of("C Z"));
        assertThat(dayTwo.solvePart1()).isEqualTo(6);
    }

    @Test
    void part1And2Example() {
        DayTwo dayTwo = new DayTwo(List.of("A Y",
                "B X",
                "C Z"));
        assertThat(dayTwo.solvePart1()).isEqualTo(15);
        assertThat(dayTwo.solvePart2()).isEqualTo(12);
    }

    @Test
    void solvePart1AndTwo() {
        DayTwo dayTwo = new DayTwo(Utils.readInputLinesFromFile("day_two.txt"));
        assertThat(dayTwo.solvePart1()).isEqualTo(10718);
        assertThat(dayTwo.solvePart2()).isEqualTo(14652);
    }
}
