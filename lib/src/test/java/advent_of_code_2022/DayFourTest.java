package advent_of_code_2022;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class DayFourTest {
    @Test
    void identifySingleFullyContainedRange() {
        DayFour dayFour = new DayFour(List.of("2-8,3-7"));
        assertThat(dayFour.solvePart1()).isEqualTo(1);
    }

    @Test
    void part1Example() {
        DayFour dayFour = new DayFour(List.of(
                "2-4,6-8",
                "2-3,4-5",
                "5-7,7-9",
                "2-8,3-7",
                "6-6,4-6",
                "2-6,4-8"));

                assertThat(dayFour.solvePart1()).isEqualTo(2);
    }

    @Test
    void solvePart1(){
        DayFour dayFour = new DayFour(Utils.readInputLinesFromFile("day_four.txt"));
        assertThat(dayFour.solvePart1()).isEqualTo(498);
    }
}