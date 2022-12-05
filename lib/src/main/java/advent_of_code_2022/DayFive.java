package advent_of_code_2022;

import java.util.List;
import java.util.Stack;

public class DayFive {

    private List<String> input;

    public DayFive(List<String> input) {
        this.input = input;
    }

    public List<Stack> getData() {
        Stack<String> stack1 = new Stack<>();
        Stack<String> stack2 = new Stack<>();
        Stack<String> stack3 = new Stack<>();
        Stack<String> stack4 = new Stack<>();
        Stack<String> stack5 = new Stack<>();
        Stack<String> stack6 = new Stack<>();
        Stack<String> stack7 = new Stack<>();
        Stack<String> stack8 = new Stack<>();
        Stack<String> stack9 = new Stack<>();

        stack1.push("R");
        stack1.push("N");
        stack1.push("F");
        stack1.push("V");
        stack1.push("L");
        stack1.push("J");
        stack1.push("S");
        stack1.push("M");

        stack2.push("P");
        stack2.push("N");
        stack2.push("D");
        stack2.push("Z");
        stack2.push("F");
        stack2.push("J");
        stack2.push("W");
        stack2.push("H");

        stack3.push("W");
        stack3.push("R");
        stack3.push("C");
        stack3.push("D");
        stack3.push("G");

        stack4.push("N");
        stack4.push("B");
        stack4.push("S");

        stack5.push("M");
        stack5.push("Z");
        stack5.push("W");
        stack5.push("P");
        stack5.push("C");
        stack5.push("B");
        stack5.push("F");
        stack5.push("N");

        stack6.push("P");
        stack6.push("R");
        stack6.push("M");
        stack6.push("W");

        stack7.push("R");
        stack7.push("T");
        stack7.push("N");
        stack7.push("G");
        stack7.push("L");
        stack7.push("S");
        stack7.push("W");

        stack8.push("Q");
        stack8.push("T");
        stack8.push("H");
        stack8.push("F");
        stack8.push("N");
        stack8.push("B");
        stack8.push("V");

        stack9.push("L");
        stack9.push("M");
        stack9.push("H");
        stack9.push("Z");
        stack9.push("N");
        stack9.push("F");

        return List.of(stack1, stack2, stack3, stack4, stack5, stack6, stack7, stack8, stack9);
    }

    public List<Stack> getTestData() {
        Stack<String> stack1 = new Stack<>();
        Stack<String> stack2 = new Stack<>();
        Stack<String> stack3 = new Stack<>();
        stack1.push("Z");
        stack1.push("N");
        stack2.push("M");
        stack2.push("C");
        stack2.push("D");
        stack3.push("P");
        return List.of(stack1, stack2, stack3);
    }

    public String solvePart1() {

        List<Stack> stacks = getData();

        for (String instruction : input) {
            String[] parts = instruction.split(" ");

            int quantity = Integer.parseInt(parts[1]);
            int from = Integer.parseInt(parts[3]);
            int to = Integer.parseInt(parts[5]);

            for (int i = 0; i < quantity; i++) {
                stacks.get(to - 1).push(stacks.get(from - 1).pop());
            }
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (Stack<String> stack : stacks) {
            if (!stack.isEmpty()) {
                stringBuilder.append(stack.pop());
            }
        }

        System.out.println(stringBuilder.toString());

        return stringBuilder.toString();
    }

    public String solvePart2() {

        return "";
    }

}