package advent_of_code_2022;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class DayFifteenTest {

    @Test
    void part1Example() {
        DayFifteen dayFifteen = new DayFifteen(List.of(
                "Sensor at x=2, y=18: closest beacon is at x=-2, y=15",
                "Sensor at x=9, y=16: closest beacon is at x=10, y=16",
                "Sensor at x=13, y=2: closest beacon is at x=15, y=3",
                "Sensor at x=12, y=14: closest beacon is at x=10, y=16",
                "Sensor at x=10, y=20: closest beacon is at x=10, y=16",
                "Sensor at x=14, y=17: closest beacon is at x=10, y=16",
                "Sensor at x=8, y=7: closest beacon is at x=2, y=10",
                "Sensor at x=2, y=0: closest beacon is at x=2, y=10",
                "Sensor at x=0, y=11: closest beacon is at x=2, y=10",
                "Sensor at x=20, y=14: closest beacon is at x=25, y=17",
                "Sensor at x=17, y=20: closest beacon is at x=21, y=22",
                "Sensor at x=16, y=7: closest beacon is at x=15, y=3",
                "Sensor at x=14, y=3: closest beacon is at x=15, y=3",
                "Sensor at x=20, y=1: closest beacon is at x=15, y=3"
        ));

        assertThat(dayFifteen.solvePart1(10)).isEqualTo(26);
    }

    @Test
    void part2Example() {
        DayFifteen dayFifteen = new DayFifteen(List.of(
                "Sensor at x=2, y=18: closest beacon is at x=-2, y=15",
                "Sensor at x=9, y=16: closest beacon is at x=10, y=16",
                "Sensor at x=13, y=2: closest beacon is at x=15, y=3",
                "Sensor at x=12, y=14: closest beacon is at x=10, y=16",
                "Sensor at x=10, y=20: closest beacon is at x=10, y=16",
                "Sensor at x=14, y=17: closest beacon is at x=10, y=16",
                "Sensor at x=8, y=7: closest beacon is at x=2, y=10",
                "Sensor at x=2, y=0: closest beacon is at x=2, y=10",
                "Sensor at x=0, y=11: closest beacon is at x=2, y=10",
                "Sensor at x=20, y=14: closest beacon is at x=25, y=17",
                "Sensor at x=17, y=20: closest beacon is at x=21, y=22",
                "Sensor at x=16, y=7: closest beacon is at x=15, y=3",
                "Sensor at x=14, y=3: closest beacon is at x=15, y=3",
                "Sensor at x=20, y=1: closest beacon is at x=15, y=3"
        ));

        assertThat(dayFifteen.solvePart2WithRanges(20)).isEqualTo(56000011);
    }

    @Test
    void manhattanDistance() {
        DayFifteen dayFifteen = new DayFifteen(List.of(
                "Sensor at x=8, y=7: closest beacon is at x=2, y=10"
        ));

        Set<CoordDayFifteen> blankCoords = dayFifteen.coordsWithinDistance(new CoordDayFifteen(8, 7, 0), new CoordDayFifteen(2, 10, 0));

        for (int y = -2; y < 23; y++) {
            StringBuilder sb = new StringBuilder();
            for (int x = -2; x < 26; x++) {
                if (blankCoords.contains(new CoordDayFifteen(x, y, 0))) {
                    sb.append("#");
                } else {
                    sb.append(".");
                }
            }
            System.out.println(sb);
        }
    }

    @Test
    void solvePart1() {
        DayFifteen dayFifteen = new DayFifteen(Utils.readInputLinesFromFile("day_fifteen.txt"));
        assertThat(dayFifteen.solvePart1(2000000)).isEqualTo(5688618);
    }

    @Test
    void solvePart2() {
        DayFifteen dayFifteen = new DayFifteen(Utils.readInputLinesFromFile("day_fifteen.txt"));
        assertThat(dayFifteen.solvePart2WithRanges(4000000)).isEqualTo(12625383204261L);
    }

    @Test
    void calculatePerimeterDistance0() {
        DayFifteen dayFifteen = new DayFifteen(Collections.emptyList());
        CoordDayFifteen sensor = new CoordDayFifteen(0, 0, 0);
        assertThat(dayFifteen.calculatePerimeter(sensor)).containsOnly(
                new CoordDayFifteen(0, -1),

                new CoordDayFifteen(-1, 0),
                new CoordDayFifteen(1, 0),

                new CoordDayFifteen(0, 1)
        );
    }

    @Test
    void calculatePerimeterDistance1() {
        DayFifteen dayFifteen = new DayFifteen(Collections.emptyList());
        CoordDayFifteen sensor = new CoordDayFifteen(0, 0, 1);
        assertThat(dayFifteen.calculatePerimeter(sensor))
                .hasSize(8)
                .containsOnly(
                        new CoordDayFifteen(0, -2),
                        new CoordDayFifteen(-1, -1),
                        new CoordDayFifteen(1, -1),
                        new CoordDayFifteen(-2, 0),
                        new CoordDayFifteen(2, 0),
                        new CoordDayFifteen(-1, 1),
                        new CoordDayFifteen(1, 1),
                        new CoordDayFifteen(0, 2)
                );
    }
}
