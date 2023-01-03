package advent_of_code_2022;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
public class DayTwentyFiveTest {
    
    @Test
    void snafuToDecimal(){
        DayTwentyFive dayTwentyFive = new DayTwentyFive();
        assertThat(dayTwentyFive.toDecimal("1=-0-2")).isEqualTo(1747);
        assertThat(dayTwentyFive.toDecimal("12111")).isEqualTo(906);
        assertThat(dayTwentyFive.toDecimal("2=0=")).isEqualTo(198);
        assertThat(dayTwentyFive.toDecimal("21")).isEqualTo(11);
        assertThat(dayTwentyFive.toDecimal("2=01")).isEqualTo(201);
        assertThat(dayTwentyFive.toDecimal("111")).isEqualTo(31);
        assertThat(dayTwentyFive.toDecimal("20012")).isEqualTo(1257);
        assertThat(dayTwentyFive.toDecimal("112")).isEqualTo(32);
        assertThat(dayTwentyFive.toDecimal("1=-1=")).isEqualTo(353);
        assertThat(dayTwentyFive.toDecimal("1-12")).isEqualTo(107);
        assertThat(dayTwentyFive.toDecimal("12")).isEqualTo(7);
        assertThat(dayTwentyFive.toDecimal("1=")).isEqualTo(3);
        assertThat(dayTwentyFive.toDecimal("122")).isEqualTo(37);
    }

    @Test
    void decimalToSnafu() {
        DayTwentyFive dayTwentyFive = new DayTwentyFive();
        assertThat(dayTwentyFive.toSnafu(4890)).isEqualTo("2=-1=0");
    }


    @Test
    void part1Example(){
        DayTwentyFive dayTwentyFive = new DayTwentyFive();
        String result = dayTwentyFive.sum(
                List.of(
                        "1=-0-2",
                        "12111",
                        "2=0=",
                        "21",
                        "2=01",
                        "111",
                        "20012",
                        "112",
                        "1=-1=",
                        "1-12",
                        "12",
                        "1=",
                        "122"
                )
        );
        assertThat(result).isEqualTo("2=-1=0");
    }

    @Test
    void solvePart1() {
        DayTwentyFive dayTwentyFive = new DayTwentyFive();
        assertThat(dayTwentyFive.sum(Utils.readInputLinesFromFile("day_twenty_five.txt"))).isEqualTo("2=0-2-1-0=20-01-2-20");
        
    }
}
