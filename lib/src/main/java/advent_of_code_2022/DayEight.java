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
                trees[y][x] = Integer.parseInt(""+line.charAt(x));
            }
        }
    }

    public int solvePart1() {
        Set<String> highTrees = new HashSet<>();

        
        for (int y = 0; y < trees.length; y++) {
            int highPoint = -1;
            for (int x = 0; x < trees[y].length; x++){
                if(trees[y][x]>highPoint){
                    highTrees.add(y + "," + x);
                    highPoint = trees[y][x];
                }
            }
            highPoint = -1;
            for (int x = trees[y].length-1; x >= 0; x--){
                if(trees[y][x]>highPoint){
                    highTrees.add(y + "," + x);
                    highPoint = trees[y][x];
                }
            }
        }

        for (int x = 0; x < trees[0].length; x++) {
            int highPoint = -1;
            for (int y = 0; y < trees.length; y++) {
                if(trees[y][x]>highPoint){
                    highTrees.add(y + "," + x);
                    highPoint = trees[y][x];
                }
            }
            highPoint = -1;
            for (int y = trees.length-1; y >= 0; y--) {
                if(trees[y][x]>highPoint){
                    highTrees.add(y + "," + x);
                    highPoint = trees[y][x];
                }
            }
        }
        

        return highTrees.size();
    }

    public int solvePart2() {
        for (int y = 0; y < trees.length; y++) {
            for (int x = 0; x < trees[0].length; x++) {
                
                for (int treeY = y-1; treeY >= 0; treeY--) {
                    
                }   
                
                
            }
        }
        return 0;
    }
}
