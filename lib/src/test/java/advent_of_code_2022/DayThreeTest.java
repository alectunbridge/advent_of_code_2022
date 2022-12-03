package advent_of_code_2022;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DayThreeTest {

    @Test
    void part1ExampleLine1() {
        DayThree dayThree = new DayThree(List.of(
                "vJrwpWtwJgWrhcsFMMfFFhFp"
        ));
        assertThat(dayThree.solvePart1()).isEqualTo(16);

    }

    @Test
    void part1ExampleLine2() {
        DayThree dayThree = new DayThree(List.of(
                "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL"
        ));
        assertThat(dayThree.solvePart1()).isEqualTo(38);
    }

    @Test
    void part1ExampleAllLines() {
        DayThree dayThree = new DayThree(List.of(
                "vJrwpWtwJgWrhcsFMMfFFhFp",
                "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL",
                "PmmdzqPrVvPwwTWBwg",
                "wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn",
                "ttgJtRGJQctTZtZT",
                "CrZsJsPPZsGzwwsLwLmpwMDw"
        ));
        assertThat(dayThree.solvePart1()).isEqualTo(157);
    }

    @Test
    void part1() {
        DayThree dayThree = new DayThree(Utils.readInputLinesFromFile("day_three.txt"));
        assertThat(dayThree.solvePart1()).isEqualTo(8088);
    }
}
