package advent_of_code_2022;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

public class DaySixteenTest {


    @Test
    void part1Example() {
        DaySixteen daySixteen = new DaySixteen(List.of(
                "Valve AA has flow rate=0; tunnels lead to valves DD, II, BB",
                "Valve BB has flow rate=13; tunnels lead to valves CC, AA",
                "Valve CC has flow rate=2; tunnels lead to valves DD, BB",
                "Valve DD has flow rate=20; tunnels lead to valves CC, AA, EE",
                "Valve EE has flow rate=3; tunnels lead to valves FF, DD",
                "Valve FF has flow rate=0; tunnels lead to valves EE, GG",
                "Valve GG has flow rate=0; tunnels lead to valves FF, HH",
                "Valve HH has flow rate=22; tunnel leads to valve GG",
                "Valve II has flow rate=0; tunnels lead to valves AA, JJ",
                "Valve JJ has flow rate=21; tunnel leads to valve II"
        ));
        
        assertThat(daySixteen.solvePart1()).isEqualTo(1651);
    }

    @Test
    void part1() {
        DaySixteen daySixteen = new DaySixteen(Utils.readInputLinesFromFile("day_sixteen.txt"));
        assertThat(daySixteen.solvePart1()).isEqualTo(2056);
    }

    @Test
    void part2Example() {
        DaySixteen daySixteen = new DaySixteen(List.of(
                "Valve AA has flow rate=0; tunnels lead to valves DD, II, BB",
                "Valve BB has flow rate=13; tunnels lead to valves CC, AA",
                "Valve CC has flow rate=2; tunnels lead to valves DD, BB",
                "Valve DD has flow rate=20; tunnels lead to valves CC, AA, EE",
                "Valve EE has flow rate=3; tunnels lead to valves FF, DD",
                "Valve FF has flow rate=0; tunnels lead to valves EE, GG",
                "Valve GG has flow rate=0; tunnels lead to valves FF, HH",
                "Valve HH has flow rate=22; tunnel leads to valve GG",
                "Valve II has flow rate=0; tunnels lead to valves AA, JJ",
                "Valve JJ has flow rate=21; tunnel leads to valve II"
        ));

        int result = daySixteen.solvePart2();
        assertThat(result).isEqualTo(1707);
    }

    @Test
    void part2() {
        DaySixteen daySixteen = new DaySixteen(Utils.readInputLinesFromFile("day_sixteen.txt"));
        assertThat(daySixteen.solvePart2()).isEqualTo(2513);
    }
}
