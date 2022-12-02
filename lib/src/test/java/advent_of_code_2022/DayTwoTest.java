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
    void part1Example() {
        DayTwo dayTwo = new DayTwo(List.of("A Y",
                "B X",
                "C Z"));
        assertThat(dayTwo.solvePart1()).isEqualTo(15);
    }

    @Test
    void solvePart1() {
        DayTwo dayTwo = new DayTwo(Utils.readInputLinesFromFile("day_two.txt"));
        assertThat(dayTwo.solvePart1()).isEqualTo(0);
    }
}
