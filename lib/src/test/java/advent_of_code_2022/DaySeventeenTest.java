package advent_of_code_2022;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

class DaySeventeenTest{
    @Test
    void part1Example(){
        DaySeventeen daySeventeen = new DaySeventeen(
            List.of(">>><<><>><<<>><>>><<<>>><<<><<<>><>><<>>")
        );
        assertThat(daySeventeen.solvePart1()).isEqualTo(3068);
    }

    @Test
    void part1(){
        DaySeventeen daySeventeen = new DaySeventeen(Utils.readInputLinesFromFile("day_seventeen.txt"));
        assertThat(daySeventeen.solvePart1()).isEqualTo(3235);
    }
}