package advent_of_code_2022;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DaySevenTest {

    @Test
    void exampleSimple() {
        DaySeven daySeven = new DaySeven(List.of(
                "$ cd /",
                "$ ls",
                "dir a",
                "14848514 b.txt",
                "8504156 c.dat",
                "dir d",
                "$ cd a",
                "$ ls",
                "dir e",
                "29116 f",
                "2557 g",
                "62596 h.lst",
                "$ cd e",
                "$ ls",
                "584 i",
                "$ cd ..",
                "$ cd ..",
                "$ cd d",
                "$ ls",
                "4060174 j",
                "8033020 d.log",
                "5626152 d.ext",
                "7214296 k"
                                                ));
        assertThat(daySeven.solvePart1()).isEqualTo(95437);
    }

    @Test
    void solvePart1() {
        DaySeven daySeven = new DaySeven(Utils.readInputLinesFromFile("day_seven.txt"));
        assertThat(daySeven.solvePart1()).isEqualTo(1447046);
    }

    @Test
    void solvePart2() {
        DaySeven daySeven = new DaySeven(Utils.readInputLinesFromFile("day_seven.txt"));
        assertThat(daySeven.solvePart2()).isEqualTo(578710);
    }

}
