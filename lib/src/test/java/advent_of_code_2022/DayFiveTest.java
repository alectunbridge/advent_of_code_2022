package advent_of_code_2022;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DayFiveTest {

    @Test
    void solvePart1And2Test(){
        DayFive dayFive = new DayFive(Utils.readInputLinesFromFile("testinput.txt"));
        assertThat(dayFive.solvePart1()).isEqualTo("CMZ");
        //assertThat(dayFour.solvePart2()).isEqualTo(859);
    }

    @Test
    void solvePart1(){
        DayFive dayFive = new DayFive(Utils.readInputLinesFromFile("day_five.txt"));
        assertThat(dayFive.solvePart1()).isEqualTo("CMZ");
        //assertThat(dayFour.solvePart2()).isEqualTo(859);
    }
}