package advent_of_code_2022;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Directory {

    private String name;

    public Directory(String name) {

        this.name = name;
    }
}

public class DaySeven {
    private static final String CD_PATTERN = "\\$ cd (<?name>\\w*)";
    private List<String> input;

    
    public DaySeven(List<String> input) {

        this.input = input;
    }

    public int solvePart1() {
        for (int i = 0; i < input.size(); i++) {
            String line = input.get(i);
            Matcher cdMatcher = Pattern.compile(CD_PATTERN).matcher(line);
            if(cdMatcher.matches()){
                Directory directory = new Directory(cdMatcher.group("name"));   
            }
            
        }
        return 0;
    }
}
