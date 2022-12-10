package advent_of_code_2022;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.lang.Math.abs;

class Position {

    int x = 0;
    int y = 0;

    void move(String direction) {
        if ("U".equals(direction)) {
            y++;
        } else if ("D".equals(direction)) {
            y--;
        } else if ("R".equals(direction)) {
            x++;
        } else {
            x--;
        }
    }

    String coords() {
        return y + "," + x;
    }
}

public class DayNine {

    private List<String> input;

    public DayNine(List<String> input) {
        this.input = input;
    }

    public int solvePart1() {

        Position h = new Position();
        Position t = new Position();
        Set<String> visited = new HashSet<>();
        visited.add(t.coords());

        for (String move : input) {
            String direction = move.split(" ")[0];
            int distance = Integer.parseInt(move.split(" ")[1]);

            for (int i = 0; i < distance; i++) {
                h.move(direction);

                if (moveRequired(h,t)) {

                    if (h.x - t.x > 1) {
                        t.move("R");
                        if (h.y != t.y) {
                            if (h.y > t.y) {
                                t.move("U");
                            } else {
                                t.move("D");
                            }
                        }
                    } else if (h.x - t.x < -1) {
                        t.move("L");
                        if (h.y != t.y) {
                            if (h.y > t.y) {
                                t.move("U");
                            } else {
                                t.move("D");
                            }
                        }
                    }

                    if (h.y - t.y > 1) {
                        t.move("U");
                        if (h.x != t.x) {
                            if (h.x > t.x) {
                                t.move("R");
                            } else {
                                t.move("L");
                            }
                        }
                    } else if (h.x - t.x < -1) {
                        t.move("D");
                        if (h.x != t.x) {
                            if (h.x > t.x) {
                                t.move("R");
                            } else {
                                t.move("L");
                            }
                        }
                    }
                    visited.add(t.coords());
                }
            }
        }

        return visited.size();
    }

    public boolean moveRequired(Position h, Position t) {
        return abs(h.x - t.x) > 1 || abs(h.y - t.y) > 1;
    }

    public int solvePart2() {

        return 0;
    }
}