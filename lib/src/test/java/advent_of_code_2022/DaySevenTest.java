package advent_of_code_2022;

import org.junit.jupiter.api.Test;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class DaySevenTest {

    @Test
    void parseSingleDirectory() {
        DaySeven daySeven = new DaySeven(
                List.of(
                        "$ cd d",
                        "$ ls",
                        "4060174 j",
                        "8033020 d.log",
                        "5626152 d.ext",
                        "7214296 k"
                )
        );
        assertThat(daySeven.solvePart1()).isEqualTo(4060174+8033020+5626152+7214296);
    }
}
