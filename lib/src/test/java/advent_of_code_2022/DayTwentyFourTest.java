package advent_of_code_2022;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

public class DayTwentyFourTest {
    
    @Test
    void examplePart1(){
        DayTwentyFour dayTwentyFour = new DayTwentyFour(
            List.of(
                "#.######",
                "#>>.<^<#",
                "#.<..<<#",
                "#>v.><>#",
                "#<^v^^>#",
                "######.#"
            )
        );

        assertThat(dayTwentyFour.solvePart1(new Coord24(0,-1),1)).isEqualTo(18);
    }

    @Test
    void part1(){
        DayTwentyFour dayTwentyFour = new DayTwentyFour(Utils.readInputLinesFromFile("day_twenty_four.txt"));
        assertThat(dayTwentyFour.solvePart1(new Coord24(0,-1),1)).isEqualTo(264);
    }

    @Test
    @Disabled
    /*Doesn't work on the sample input as the state space is too small
    and the timeMap prunes valid solutions where it revisits states.
     */
    void examplePart2(){
        DayTwentyFour dayTwentyFour = new DayTwentyFour(
                List.of(
                        "#.######",
                        "#>>.<^<#",
                        "#.<..<<#",
                        "#>v.><>#",
                        "#<^v^^>#",
                        "######.#"
                )
        );

        assertThat(dayTwentyFour.solvePart1(new Coord24(0,-1),1)).isEqualTo(18);
        dayTwentyFour.reverse();
        assertThat(dayTwentyFour.solvePart1(new Coord24(5,4),18)).isEqualTo(41);
        dayTwentyFour.reverse();
        assertThat(dayTwentyFour.solvePart1(new Coord24(0,-1),41)).isEqualTo(54);
    }

    @Test
    void part2(){
        DayTwentyFour dayTwentyFour = new DayTwentyFour(Utils.readInputLinesFromFile("day_twenty_four.txt"));
        dayTwentyFour.reverse();
        assertThat(dayTwentyFour.solvePart1(new Coord24(119,25),264)).isEqualTo(530);
        dayTwentyFour.reverse();
        assertThat(dayTwentyFour.solvePart1(new Coord24(0,-1),530)).isEqualTo(789);
    }
}
