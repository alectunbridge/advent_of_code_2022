package advent_of_code_2022;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

record Range(int start, int end) {
    public Range merge(Range otherRange) {
        if (otherRange.start() <= end + 1) {
            return new Range(start, Integer.max(end, otherRange.end()));
        }
        return null;
    }
}

record CoordDayFifteen(int x, int y, int distance) {
    CoordDayFifteen(String x, String y, int distance) {
        this(Integer.parseInt(x), Integer.parseInt(y), distance);
    }

    CoordDayFifteen(int x, int y) {
        this(x, y, 0);
    }
}

public class DayFifteen {
    private Set<CoordDayFifteen> blankCoords;
    private List<CoordDayFifteen> sensors;
    private List<CoordDayFifteen> beacons;
    private int maxDistance;
    private int minSensorX;
    private int maxSensorX;
    private int minSensorY;
    private int maxSensorY;

    public DayFifteen(List<String> input) {
        blankCoords = new HashSet<>();
        sensors = new ArrayList<>();
        beacons = new ArrayList<>();
        maxDistance = Integer.MIN_VALUE;
        minSensorX = Integer.MAX_VALUE;
        maxSensorX = Integer.MIN_VALUE;

        Pattern inputPattern = Pattern.compile("Sensor at x=(-?\\d+), y=(-?\\d+): closest beacon is at x=(-?\\d+), y=(-?\\d+)");
        for (String line : input) {
            Matcher lineMatcher = inputPattern.matcher(line);
            if (lineMatcher.matches()) {

                CoordDayFifteen beacon = new CoordDayFifteen(lineMatcher.group(3), lineMatcher.group(4), 0);
                beacons.add(beacon);
                CoordDayFifteen sensor = new CoordDayFifteen(lineMatcher.group(1), lineMatcher.group(2),
                        manhattanDistance(new CoordDayFifteen(lineMatcher.group(1), lineMatcher.group(2), 0), beacon));
                sensors.add(sensor);
                int distance = manhattanDistance(sensor, beacon);
                if (distance > maxDistance) {
                    maxDistance = distance;
                }
                if (sensor.x() < minSensorX) {
                    minSensorX = sensor.x();
                }
                if (sensor.x() > maxSensorX) {
                    maxSensorX = sensor.x();
                }
                if (sensor.y() < minSensorY) {
                    minSensorY = sensor.y();
                }
                if (sensor.y() > maxSensorY) {
                    maxSensorY = sensor.y();
                }

            } else {
                throw new IllegalArgumentException("input line doesn't match: " + line);
            }

        }
    }

    public int solvePart1(int rowIndex) {
        for (int x = minSensorX - maxDistance; x <= maxSensorX + maxDistance; x++) {
            for (int i = 0; i < sensors.size(); i++) {
                CoordDayFifteen inspectionCoord = new CoordDayFifteen(x, rowIndex, 0);
                if (manhattanDistance(sensors.get(i), beacons.get(i)) >= manhattanDistance(sensors.get(i), inspectionCoord)) {
                    blankCoords.add(inspectionCoord);
                }
            }
        }

        blankCoords.removeAll(sensors);
        blankCoords.removeAll(beacons);

        return blankCoords.size();
    }

    public long solvePart2(int maxCoord) {
        Set<CoordDayFifteen> perimeters = new HashSet<>();
        for (CoordDayFifteen sensor : sensors) {
            perimeters.addAll(calculatePerimeter(sensor));
        }

        for (CoordDayFifteen coord : perimeters) {
            if (coord.x() >= 0 && coord.x() <= maxCoord && coord.y() >= 0 && coord.y() <= maxCoord) {
                int sensorCount = 0;
                for (CoordDayFifteen otherSensor : sensors) {
                    if (manhattanDistance(otherSensor, coord) > otherSensor.distance()) {
                        sensorCount++;
                    }
                }
                if (sensorCount == sensors.size()) {
                    return coord.x() * 4000000L + coord.y();
                }
            }
        }
        return 0;
    }

    public long solvePart2WithRanges(int maxCoord) {
        for (int y = 0; y <= maxCoord; y++) {
            List<Range> ranges = new ArrayList<>();
            for (CoordDayFifteen sensor : sensors) {
                int remainingDistance = sensor.distance() - Math.abs(-sensor.y() + y);
                if (remainingDistance < 0) {
                    continue;
                }
                int xMin = sensor.x() - remainingDistance;
                if (xMin < 0) {
                    xMin = 0;
                }
                int xMax = sensor.x() + remainingDistance;
                if (xMax > maxCoord) {
                    xMax = maxCoord;
                }
                ranges.add(new Range(xMin, xMax));
            }
            ranges.sort(Comparator.comparing(Range::start).thenComparing(Range::end));
            Range mergedRange = new Range(0, 0);
            for (int rangeIndex = 0; rangeIndex < ranges.size(); rangeIndex++) {
                mergedRange = mergedRange.merge(ranges.get(rangeIndex));
                if (mergedRange == null) {
                    return (ranges.get(rangeIndex).start() - 1) * 4000000L + y;
                }
            }
        }
        throw new IllegalArgumentException("We should never get here");
    }

    public Set<CoordDayFifteen> calculatePerimeter(CoordDayFifteen sensor) {
        Set<CoordDayFifteen> result = new HashSet<>();
        for (int yOffset = -sensor.distance() - 1; yOffset <= sensor.distance() + 1; yOffset++) {
            int remainingOffset = sensor.distance() - Math.abs(yOffset) + 1;
            result.add(new CoordDayFifteen(sensor.x() - remainingOffset, sensor.y() + yOffset));
            result.add(new CoordDayFifteen(sensor.x() + remainingOffset, sensor.y() + yOffset));
        }
        return result;
    }

    public Set<CoordDayFifteen> coordsWithinDistance(CoordDayFifteen sensor, CoordDayFifteen beacon) {
        int distanceToBeacon = manhattanDistance(sensor, beacon);
        Set<CoordDayFifteen> result = new HashSet<>();
        for (int yOffset = -distanceToBeacon; yOffset <= distanceToBeacon; yOffset++) {
            int remainingOffset = distanceToBeacon - Math.abs(yOffset);
            for (int xOffset = -remainingOffset; xOffset <= remainingOffset; xOffset++) {
                result.add(new CoordDayFifteen(sensor.x() + xOffset, sensor.y() + yOffset, 0));
            }
        }
        return result;
    }

    private static int manhattanDistance(CoordDayFifteen coord1, CoordDayFifteen coord2) {
        return Math.abs(-coord1.x() + coord2.x()) + Math.abs(-coord1.y() + coord2.y());
    }
}
