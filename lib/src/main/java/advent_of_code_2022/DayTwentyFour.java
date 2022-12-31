package advent_of_code_2022;

import java.util.*;

record Coord24(int x, int y) {
    @Override
    public String toString() {
        return x + "," + y;
    }
}

public class DayTwentyFour {

    private Comparator<Coord24> distanceComparator;
    private Coord24 start = null;
    private Coord24 end = null;
    private boolean[][] leftWind;
    private boolean[][] rightWind;
    private boolean[][] upWind;
    private boolean[][] downWind;
    private int width;
    private int height;
    private int bestTime;
    private int[][][] timeMap;
    private float count;

    public DayTwentyFour(List<String> input) {
        leftWind = new boolean[input.size() - 2][input.get(0).length() - 2];
        rightWind = new boolean[input.size() - 2][input.get(0).length() - 2];
        upWind = new boolean[input.size() - 2][input.get(0).length() - 2];
        downWind = new boolean[input.size() - 2][input.get(0).length() - 2];
        start = new Coord24(0, -1);
        end = new Coord24(leftWind[0].length - 1, leftWind.length);
        for (int y = 1; y < input.size() - 1; y++) {
            for (int x = 1; x < input.get(y).length() - 1; x++) {
                switch (input.get(y).charAt(x)) {
                    case '<' -> leftWind[y - 1][x - 1] = true;
                    case '>' -> rightWind[y - 1][x - 1] = true;
                    case '^' -> upWind[y - 1][x - 1] = true;
                    case 'v' -> downWind[y - 1][x - 1] = true;
                }
            }
        }
        width = leftWind[0].length;
        height = leftWind.length;
        bestTime = Integer.MAX_VALUE;
        timeMap = new int[height][width][width];
        for (int tmY = 0; tmY < height; tmY++) {
            for (int tmX = 0; tmX < width; tmX++) {
                Arrays.fill(timeMap[tmY][tmX], Integer.MAX_VALUE);
            }
        }
        count = 0;
        distanceComparator = Comparator.comparingInt((Coord24 c) -> c.x() + c.y()).reversed();
    }

    public int solvePart1(Coord24 position, int time) {
        if (time > bestTime) {
            return bestTime;
        }
        if (position.equals(end)) {
            System.out.println("end: " + time + ' ' + count / (width*height*width));
            if (time < bestTime) {
                bestTime = time;
            }
            return bestTime;
        }

        if (!start.equals(position)) {
            if (timeMap[position.y()][position.x()][time % (width)] <= time) {
                return bestTime;
            } else {
                timeMap[position.y()][position.x()][time % (width)] = time;
                count++;
            }
        }

        List<Coord24> possibleMoves = getPossibleMoves(position, time);
        for (Coord24 move : possibleMoves) {
            solvePart1(move, time + 1);
        }
        return bestTime - 1;
    }

    private List<Coord24> getPossibleMoves(Coord24 position, int time) {
        List<Coord24> result = new ArrayList<>();
        result.add(new Coord24(position.x(), position.y() - 1));
        result.add(new Coord24(position.x(), position.y() + 1));
        result.add(new Coord24(position.x() - 1, position.y()));
        result.add(new Coord24(position.x() + 1, position.y()));
        result.add(position);

        for (Iterator<Coord24> it = result.iterator(); it.hasNext();) {
            Coord24 coord = it.next();
            if ((coord.equals(start) && coord.equals(position)) || coord.equals(end)) {
                continue;
            }
            if (coord.x() < 0 || coord.x() >= width || coord.y() < 0 || coord.y() >= height) {
                it.remove();
                continue;
            }

            int leftWindIndex = (coord.x() + time) % width;
            int rightWindIndex = (coord.x() - time) % width;
            if (rightWindIndex < 0) {
                rightWindIndex += width;
            }
            int upWindIndex = (coord.y() + time) % height;
            int downWindIndex = (coord.y() - time) % height;
            if (downWindIndex < 0) {
                downWindIndex += height;
            }

            if (leftWind[coord.y()][leftWindIndex] ||
                    rightWind[coord.y()][rightWindIndex] ||
                    upWind[upWindIndex][coord.x()] ||
                    downWind[downWindIndex][coord.x()]) {
                it.remove();
            }

        }
        result.sort(distanceComparator);
        return result;
    }

    public void reverse() {
        Coord24 tmp = start;
        start = end;
        end = tmp;
        distanceComparator = distanceComparator.reversed();
        bestTime = Integer.MAX_VALUE;
        timeMap = new int[height][width][width];
        for (int tmY = 0; tmY < height; tmY++) {
            for (int tmX = 0; tmX < width; tmX++) {
                Arrays.fill(timeMap[tmY][tmX], Integer.MAX_VALUE);
            }
        }
        count = 0;
    }
}
