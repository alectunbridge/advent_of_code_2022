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

    public int solvePart2() {
        int totalScore = 0;
        for (String line : input) {
            String playerOne = line.split(" ")[0];
            String strategy = line.split(" ")[1];
            switch (strategy) {
                case "X" -> totalScore += 0;
                case "Y" -> totalScore += 3;
                case "Z" -> totalScore += 6;

            }
            String playerTwo = findPlayer2Move(playerOne, strategy);
            switch (playerTwo) {
                case "A" -> totalScore += 1;
                case "B" -> totalScore += 2;
                case "C" -> totalScore += 3;
            }
        }
        return totalScore;
    }

    private String findPlayer2Move(String playerOne, String strategy) {
        String result = "";
        if (strategy.equals("Y")) {
            return playerOne;
        }
        if (strategy.equals("X")) {
            switch (playerOne) {
                case "A" -> result = "C";
                case "B" -> result = "A";
                case "C" -> result = "B";
            }
        } else {
            switch (playerOne) {
                case "A" -> result = "B";
                case "B" -> result = "C";
                case "C" -> result = "A";
            }
        }
        return result;
    }


}
