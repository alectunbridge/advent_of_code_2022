package advent_of_code_2022;

import java.util.*;

class CoordDayNine {
    int x, y;

    CoordDayNine(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        CoordDayNine that = (CoordDayNine) o;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}

class DayNine {
    private List<String> input;
    private Set<String> tailPositions;
    private List<CoordDayNine> knots;

    DayNine(List<String> input) {
        this.input = input;
        this.tailPositions = new HashSet<>();
        this.knots = new ArrayList<>();
    }

    public int solvePart1() {
        return solveForNumberOfKnots(2);
    }

    public int solvePart2() {
        return solveForNumberOfKnots(10);
    }

    public int solveForNumberOfKnots(int noOfKnots) {
        for (int i = 0; i < noOfKnots; i++) {
            knots.add(new CoordDayNine(0, 0));
        }

        tailPositions.add("0,0");

        for (String line : input) {
            String[] tokens = line.split(" ");
            int xDelta = 0;
            int yDelta = 0;

            switch (tokens[0]) {
                case "R":
                    xDelta = 1;
                    yDelta = 0;
                    break;
                case "L":
                    xDelta = -1;
                    yDelta = 0;
                    break;
                case "U":
                    xDelta = 0;
                    yDelta = 1;
                    break;
                case "D":
                    xDelta = 0;
                    yDelta = -1;
                    break;
            }

            for (int step = 0; step < Integer.parseInt(tokens[1]); step++) {

                knots.get(0).x += xDelta;
                knots.get(0).y += yDelta;

                for (int index = 0; index < knots.size() - 1; index++) {
                    moveTail(knots.get(index), knots.get(index + 1));
                }

                // for (int y = 5; y >= 0 ; y--) {
                // for (int x = 0; x < 6; x++) {
                // CoordDayNine currentPosition = new CoordDayNine(x, y);
                // if(knots.contains(currentPosition)){
                // System.out.print(knots.indexOf(currentPosition));
                // } else {
                // System.out.print(".");
                // }
                // }
                // System.out.println();
                // }
                // System.out.println();

                tailPositions.add("" + knots.get(knots.size() - 1).x + "," + knots.get(knots.size() - 1).y);

            }

        }

        return tailPositions.size();
    }

    private void moveTail(CoordDayNine head, CoordDayNine tail) {
        int tailXDelta = (-tail.x + head.x) / 2;
        int tailYDelta = (-tail.y + head.y) / 2;
        if (Math.abs(tailXDelta) == 1 && tailYDelta == 0) {
            tail.y = head.y;
            tail.x += tailXDelta;
        } else if (Math.abs(tailYDelta) == 1 && tailXDelta == 0) {
            tail.x = head.x;
            tail.y += tailYDelta;
        } else {
            tail.x += tailXDelta;
            tail.y += tailYDelta;
        }
    }

    public Set<String> getTailPositions() {
        return tailPositions;
    }
}