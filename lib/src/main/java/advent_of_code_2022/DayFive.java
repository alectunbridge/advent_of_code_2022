package advent_of_code_2022;

import java.util.*;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

record Move(int quantity, Deque<String> from, Deque<String> to) {
}

public class DayFive {

    private final List<Deque<String>> stacks;
    private List<String> input;

    public DayFive(List<String> input) {
        this.input = input;
        stacks = new ArrayList<>();
    }

    public String solve(Consumer<Move> moveConsumer) { //
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
                Move move = new Move(quantity, stacks.get(from - 1), stacks.get(to - 1));

                moveConsumer.accept(move);
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

    public String solvePart1() {
        return solve(move -> {
            for (int i = 0; i < move.quantity(); i++) {
                move.to().push(move.from().pop());
            }
        });
    }

    public String solvePart2() {
       return solve(move -> {
           Deque<String> intermediateStack = new ArrayDeque<>();
           for (int i = 0; i < move.quantity(); i++) {
               String pop = move.from().pop();
               intermediateStack.push(pop);
           }
           for (String crate : intermediateStack) {
               move.to().push(crate);
           }
       });
    }
}

