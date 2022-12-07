package advent_of_code_2022;

import java.util.List;

public class DaySeven {
    List<String> input;

    public DaySeven(List<String> input) {
        this.input = input;
    }

    public int solvePart1() {

        boolean adding = false;
        for (String line : input) {
            if (line.equals("$ ls")) {
                adding = true;
            }

        }

        return 0;
    }
}