package advent_of_code_2022;
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
        assertThat(dayTwentyFour.solvePart1(new Coord24(0,-1),1)).isEqualTo(0);
    }
}
