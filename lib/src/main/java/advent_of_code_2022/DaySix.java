package advent_of_code_2022;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DaySix {
    private List<String> input;

    public  DaySix(List<String> input) {
        this.input = input;
    }

    public int solvePart1() {
        String string = input.get(0);
        for (int index = 0; index < string.length() -3 ; index++) {
            char c1 = string.charAt(index);
            char c2 = string.charAt(index+1);
            char c3 = string.charAt(index+2);
            char c4 = string.charAt(index+3);
            
            Set<Character> window = new HashSet<>();
            window.add(c1);
            window.add(c2);
            window.add(c3);
            window.add(c4);
            if (window.size() == 4) {
                return index + 4;
            }
            
        }
        return 0;
    }
}
