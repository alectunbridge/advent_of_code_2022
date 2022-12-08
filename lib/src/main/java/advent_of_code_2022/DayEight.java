package advent_of_code_2022;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DayEight {
    private final int[][] trees;
    private List<String> input;

    public DayEight(List<String> input) {
        this.input = input;
        trees = new int[input.size()][input.get(0).length()];
        for (int y = 0; y < input.size(); y++) {
            String line = input.get(y);
            for (int x = 0; x < line.length(); x++) {
                trees[y][x] = Integer.parseInt("" + line.charAt(x));
            }
        }
    }

    public int solvePart1() {
        Set<String> highTrees = new HashSet<>();


        for (int y = 0; y < trees.length; y++) {
            int highPoint = -1;
            for (int x = 0; x < trees[y].length; x++) {
                if (trees[y][x] > highPoint) {
                    highTrees.add(y + "," + x);
                    highPoint = trees[y][x];
                }
            }
            highPoint = -1;
            for (int x = trees[y].length - 1; x >= 0; x--) {
                if (trees[y][x] > highPoint) {
                    highTrees.add(y + "," + x);
                    highPoint = trees[y][x];
                }
            }
        }

        for (int x = 0; x < trees[0].length; x++) {
            int highPoint = -1;
            for (int y = 0; y < trees.length; y++) {
                if (trees[y][x] > highPoint) {
                    highTrees.add(y + "," + x);
                    highPoint = trees[y][x];
                }
            }
            highPoint = -1;
            for (int y = trees.length - 1; y >= 0; y--) {
                if (trees[y][x] > highPoint) {
                    highTrees.add(y + "," + x);
                    highPoint = trees[y][x];
                }
            }
        }


        return highTrees.size();
    }

    public int solvePart2() {
        int maxView = 0;
        for (int y = 0; y < trees.length; y++) {
            for (int x = 0; x < trees[0].length; x++) {
                int currentTreeHeight = trees[y][x];
                int upwardsCount = 0;
                for (int treeY = y - 1; treeY >= 0; treeY--) {
                    if (trees[treeY][x] < currentTreeHeight) {
                        upwardsCount++;
                    } else if (trees[treeY][x] >= currentTreeHeight){
                        upwardsCount++;
                        break;
                    }
                }
                int downwardsCount = 0;
                for (int treeY = y + 1; treeY < trees.length; treeY++) {
                    if (trees[treeY][x] < currentTreeHeight) {
                        downwardsCount++;
                    } else if (trees[treeY][x] >= currentTreeHeight){
                        downwardsCount++;
                        break;
                    }
                }
                int leftwardsCount = 0;
                for (int treeX = x - 1; treeX >= 0; treeX--) {
                    if (trees[y][treeX] < currentTreeHeight) {
                        leftwardsCount++;
                    } else if (trees[y][treeX] >= currentTreeHeight){
                        leftwardsCount++;
                        break;
                    }
                }
                int rightwardsCount = 0;
                for (int treeX = x + 1; treeX < trees[y].length; treeX++) {
                    if (trees[y][treeX] < currentTreeHeight) {
                        rightwardsCount++;
                    } else if (trees[y][treeX] >= currentTreeHeight){
                        rightwardsCount++;
                        break;
                    }
                }
                int currentView = upwardsCount * downwardsCount * leftwardsCount * rightwardsCount;
                if (currentView > maxView) {
                    maxView = currentView;
                }
            }
        }
        return maxView;
    }
}
