package advent_of_code_2022;

import org.junit.jupiter.api.Test;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class DaySevenTest {

    @Test
    void parseSingleDirectory() {
        DaySeven daySeven = new DaySeven(
                List.of(
                        "$ cd e",
                        "$ ls",
                        "584 i"
                )
        );
        assertThat(daySeven.solvePart1()).isEqualTo(584);
    }

    @Test
    void testMultipleSimpleDirectories() {
        DaySeven daySeven = new DaySeven(List.of(
                "$ cd e",
                "$ ls",
                "584 i",
                "$ cd f",
                "$ ls",
                "584 i"
        ));
        assertThat(daySeven.solvePart1()).isEqualTo(584+584);
    }
    
    @Test
    void testNestedDirectories() {
        DaySeven daySeven = new DaySeven(List.of(
                "$ cd e",
                "$ ls",
                "584 i",
                "dir f",
                "$ cd f",
                "$ ls",
                "1 i"
        ));
        assertThat(daySeven.solvePart1()).isEqualTo(584+1+1);
    }
    
    @Test
    void partOneExample(){
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
    void part1() {
        DaySeven daySeven = new DaySeven(Utils.readInputLinesFromFile("day_seven.txt"));
        assertThat(daySeven.solvePart1()).isEqualTo(0);
    }
}
