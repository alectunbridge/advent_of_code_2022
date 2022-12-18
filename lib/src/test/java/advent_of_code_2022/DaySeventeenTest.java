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
        assertThat(daySeventeen.solvePart1(2022)).isEqualTo(3068);
    }

    @Test
    void part1(){
        DaySeventeen daySeventeen = new DaySeventeen(Utils.readInputLinesFromFile("day_seventeen.txt"));
        assertThat(daySeventeen.solvePart1(2022)).isEqualTo(3235);
    }

    @Test
    void part2Example(){
        DaySeventeen daySeventeen = new DaySeventeen(
            List.of(">>><<><>><<<>><>>><<<>>><<<><<<>><>><<>>")
        );
        // assertThat(daySeventeen.solvePart1(82)).isEqualTo(0);
        //82 35 53
        long trillion = 1000000000000L;
        int startOfCycle = 82;
        int piecesInCycle = 35;
        long cycles = (trillion - startOfCycle)/piecesInCycle;
        int heightOfCycle = 53;
        long heightOfCycles = cycles * heightOfCycle;
        long piecesRemaining = trillion - startOfCycle - (cycles * piecesInCycle);
        long height = daySeventeen.solvePart1(piecesRemaining+startOfCycle);
        long totalHeight = height + heightOfCycles;
        assertThat(totalHeight).isEqualTo(1514285714288L);
    }

    @Test
    void part2(){
        DaySeventeen daySeventeen = new DaySeventeen(
            Utils.readInputLinesFromFile("day_seventeen.txt"));
        
        //assertThat(daySeventeen.solvePart1(2022)).isEqualTo(0);
        //1984 1720 2738
        long trillion = 1000000000000L;
        int startOfCycle = 1984;
        int piecesInCycle = 1720;
        long cycles = (trillion - startOfCycle)/piecesInCycle;
        int heightOfCycle = 2738;
        long heightOfCycles = cycles * heightOfCycle;
        long piecesRemaining = trillion - startOfCycle - (cycles * piecesInCycle);
        long height = daySeventeen.solvePart1(piecesRemaining+startOfCycle);
        long totalHeight = height + heightOfCycles;
        assertThat(totalHeight).isEqualTo(1591860465110L);
    }
}