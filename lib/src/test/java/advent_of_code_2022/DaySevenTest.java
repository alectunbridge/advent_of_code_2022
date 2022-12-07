package advent_of_code_2022;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DaySevenTest {

    @Test
    void examplePart1() {
        DaySeven daySeven = new DaySeven(Utils.readInputLinesFromFile("day_seven.txt"));
        assertThat(daySeven.solvePart1()).isEqualTo(95437);
    }

    @Test
    void exampleSimple() {
        DaySeven daySeven = new DaySeven(List.of(
                "$ ls",
                "dir a",
                "14848514 b.txt",
                "8504156 c.dat"
                                                ));
        assertThat(daySeven.solvePart1()).isEqualTo(14848514+8504156);
    }
}