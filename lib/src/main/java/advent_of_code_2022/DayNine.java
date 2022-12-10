package advent_of_code_2022;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.lang.Math.*;

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

        int line = 0;

        Position h = new Position();
        Position t = new Position();
        Set<String> visited = new HashSet<>();
        visited.add(t.coords());

        int maxX = 0;
        int minX = 0;
        int maxY = 0;
        int minY = 0;

        for (String move : input) {
            line++;
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
                    } else if (h.y - t.y < -1) {
                        t.move("D");
                        if (h.x != t.x) {
                            if (h.x > t.x) {
                                t.move("R");
                            } else {
                                t.move("L");
                            }
                        }
                    }

                    if (t.x > maxX) {
                        maxX = t.x;
                    }
                    if (t.x < minX) {
                        minX = t.x;
                    }
                    if (t.y > maxY) {
                        maxY = t.y;
                    }
                    if (t.y < minY) {
                        minY = t.y;
                    }
                    visited.add(t.coords());
                }
            }

            //printPath(maxX, minX, maxY, minY, h, t, visited, move);
        }


        return visited.size();


    }

    public int solvePart2() {

        Position a = new Position();
        Position b = new Position();
        Position c = new Position();
        Position d = new Position();
        Position e = new Position();
        Position f = new Position();
        Position g = new Position();
        Position h = new Position();
        Position i = new Position();
        Position j = new Position();

        Set<String> visited = new HashSet<>();
        visited.add(j.coords());

        for (String move : input) {
            String direction = move.split(" ")[0];
            int distance = Integer.parseInt(move.split(" ")[1]);

            for (int step = 0; step < distance; step++) {
                a.move(direction);
                ifMoveRequired(a, b, null);
                ifMoveRequired(b, c, null);
                ifMoveRequired(c, d, null);
                ifMoveRequired(d, e, null);
                ifMoveRequired(e, f, null);
                ifMoveRequired(f, g, null);
                ifMoveRequired(g, h, null);
                ifMoveRequired(h, i, null);
                ifMoveRequired(i, j, visited);
            }

            //printPath(maxX, minX, maxY, minY, h, t, visited, move);
        }


        return visited.size();
    }

    private void ifMoveRequired(Position a, Position b, Set<String> visited) {
        if (moveRequired(a, b)) {

            if (a.x - b.x > 1) {
                b.move("R");
                if (a.y != b.y) {
                    if (a.y > b.y) {
                        b.move("U");
                    } else {
                        b.move("D");
                    }
                }
            } else if (a.x - b.x < -1) {
                b.move("L");
                if (a.y != b.y) {
                    if (a.y > b.y) {
                        b.move("U");
                    } else {
                        b.move("D");
                    }
                }
            }

            if (a.y - b.y > 1) {
                b.move("U");
                if (a.x != b.x) {
                    if (a.x > b.x) {
                        b.move("R");
                    } else {
                        b.move("L");
                    }
                }
            } else if (a.y - b.y < -1) {
                b.move("D");
                if (a.x != b.x) {
                    if (a.x > b.x) {
                        b.move("R");
                    } else {
                        b.move("L");
                    }
                }
            }
            if (visited != null) {
                visited.add(b.coords());
            }
        }
    }

    public void printPath(int maxX, int minX, int maxY, int minY, Position h, Position t, Set<String> visited, String move) {
        System.out.println("");
        System.out.println(move);
        System.out.println("");

        for (int i = maxY+1; i >= minY-1; i--) {
            for (int j = minX-1; j <= maxX+1; j++) {

                if (h.x == j && h.y == i) {
                    System.out.print("H");
                } else if (t.x == j && t.y == i) {
                    System.out.print("T");
                } else if (j == 0 && i == 0) {
                    System.out.print("$");
                } else if (visited.contains(i+","+j)) {
                    System.out.print("#");
                } else {
                    System.out.print(".");
                }
            }
            System.out.println("");
        }
        System.out.println("");
        System.out.println("");
    }

    public boolean moveRequired(Position h, Position t) {
        return abs(h.x - t.x) > 1 || abs(h.y - t.y) > 1;
    }
}