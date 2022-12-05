package advent_of_code_2022;

import java.util.List;
import java.util.function.IntPredicate;

public class DayFour {

    private List<String> input;

    public DayFour(List<String> input) {
        this.input = input;
    }

    public int solvePart1() {
        int countOfTotallyContainedRanges = 0;
        for (String line : input) {
            String[] ranges = line.split(",");
            int range1Start = Integer.parseInt(ranges[0].split("-")[0]);
            int range1End = Integer.parseInt(ranges[0].split("-")[1]);
            int range2Start = Integer.parseInt(ranges[1].split("-")[0]);
            int range2End = Integer.parseInt(ranges[1].split("-")[1]);

            if ((range1Start >= range2Start && range1End <= range2End) ||
                    (range2Start >= range1Start && range2End <= range1End)) {
                countOfTotallyContainedRanges++;
            }

        }
        return countOfTotallyContainedRanges;
    }

    public int solvePart2() {
        int countOfOverlappingRanges = 0;
        for (String line : input) {
            String[] ranges = line.split(",");
            int range1Start = Integer.parseInt(ranges[0].split("-")[0]);
            int range1End = Integer.parseInt(ranges[0].split("-")[1]);
            int range2Start = Integer.parseInt(ranges[1].split("-")[0]);
            int range2End = Integer.parseInt(ranges[1].split("-")[1]);

            if( (range1Start < range2Start && range1End < range2Start) ||
            (range1Start > range2End && range1End > range2End)){
                countOfOverlappingRanges += 0;
            } else {
                countOfOverlappingRanges++;
            }

        }
        return countOfOverlappingRanges;
    }

}
