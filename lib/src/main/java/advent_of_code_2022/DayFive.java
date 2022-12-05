package advent_of_code_2022;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DayFive {

    private final List<Deque<String>> stacks;
    private List<String> input;

    public DayFive(List<String> input) {
        this.input = input;
        stacks = new ArrayList<>();
    }

    public String solvePart1() {
        boolean firstLine = true;
        for (String line : input) {
            if (firstLine) {
                int noOfStacks = (line.length() + 1) / 4;
                for (int i = 0; i < noOfStacks; i++) {
                    stacks.add(new ArrayDeque<>());
                }
                firstLine = false;
            }
            if (line.contains("[")) {
                Pattern cratePattern = Pattern.compile(".(.)..?");
                Matcher matcher = cratePattern.matcher(line);
                int stackIndex = 0;
                while (matcher.find()) {
                    String crateLetter = matcher.group(1);
                    if (!crateLetter.isBlank()) {
                        stacks.get(stackIndex).addLast(crateLetter);
                    }
                    stackIndex++;
                }

            } else if (line.matches(".*[a-z].*")) {

                String[] parts = line.split(" ");

                int quantity = Integer.parseInt(parts[1]);
                int from = Integer.parseInt(parts[3]);
                int to = Integer.parseInt(parts[5]);

                for (int i = 0; i < quantity; i++) {
                    stacks.get(to - 1).push(stacks.get(from - 1).pop());
                }
            } else {
                //do nowt
            }
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (Deque<String> stack : stacks) {
            if (!stack.isEmpty()) {
                stringBuilder.append(stack.pop());
            }
        }
        return stringBuilder.toString();
    }

    public String solvePart2() {
        boolean firstLine = true;
        for (String line : input) {
            if (firstLine) {
                int noOfStacks = (line.length() + 1) / 4;
                for (int i = 0; i < noOfStacks; i++) {
                    stacks.add(new ArrayDeque<>());
                }
                firstLine = false;
            }
            if (line.contains("[")) {
                Pattern cratePattern = Pattern.compile(".(.)..?");
                Matcher matcher = cratePattern.matcher(line);
                int stackIndex = 0;
                while (matcher.find()) {
                    String crateLetter = matcher.group(1);
                    if (!crateLetter.isBlank()) {
                        stacks.get(stackIndex).addLast(crateLetter);
                    }
                    stackIndex++;
                }

            } else if (line.matches(".*[a-z].*")) {

                String[] parts = line.split(" ");

                int quantity = Integer.parseInt(parts[1]);
                int from = Integer.parseInt(parts[3]);
                int to = Integer.parseInt(parts[5]);

                Deque<String> intermediateStack = new ArrayDeque<>();
                for (int i = 0; i < quantity; i++) {
                    String pop = stacks.get(from - 1).pop();
                    intermediateStack.push(pop);
                }
                for(String crate:intermediateStack) {
                    stacks.get(to - 1).push(crate);
                }
            } else {
                //do nowt
            }
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (Deque<String> stack : stacks) {
            if (!stack.isEmpty()) {
                stringBuilder.append(stack.pop());
            }
        }
        return stringBuilder.toString();
    }
}

