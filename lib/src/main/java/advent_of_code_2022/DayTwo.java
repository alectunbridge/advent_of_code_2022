package advent_of_code_2022;

import java.util.List;

public class DayTwo {
    private List<String> input;

    public DayTwo(List<String> input) {
        this.input = input;
    }

    public int solvePart1() {
        int totalScore = 0;
        for (String line : input) {
            String playerOne = line.split(" ")[0];
            String playerTwo = line.split(" ")[1];
            if (playerOne.equals("A") && playerTwo.equals("X") ||
                    playerOne.equals("B") && playerTwo.equals("Y") ||
                    playerOne.equals("C") && playerTwo.equals("Z")) {
                totalScore += 3;
            } else if (playerOne.equals("A") && playerTwo.equals("Y") ||
                    playerOne.equals("B") && playerTwo.equals("Z") ||
                    playerOne.equals("C") && playerTwo.equals("X")
            ) {
                totalScore += 6;
            } else {
                totalScore += 0;
            }
            switch (playerTwo) {
                case "X" -> totalScore += 1;
                case "Y" -> totalScore += 2;
                case "Z" -> totalScore += 3;
            }
        }
        return totalScore;
    }
}
