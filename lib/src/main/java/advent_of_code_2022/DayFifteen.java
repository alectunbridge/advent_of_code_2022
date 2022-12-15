package advent_of_code_2022;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

record CoordDayFifteen(int x, int y) {
    CoordDayFifteen(String x, String y) {
        this(Integer.parseInt(x), Integer.parseInt(y));
    }
}

public class DayFifteen {

    private final List<String> input;
    private Set<CoordDayFifteen> blankCoords;
    private Set<CoordDayFifteen> sensors;
    private Set<CoordDayFifteen> beacons;

    public DayFifteen(List<String> input) {
        this.input = input;
        blankCoords = new HashSet<>();
        sensors = new HashSet<>();
        beacons = new HashSet<>();
    }

    public int solvePart1(int rowIndex) {
        Pattern inputPattern = Pattern.compile("Sensor at x=(-?\\d+), y=(-?\\d+): closest beacon is at x=(-?\\d+), y=(-?\\d+)");
        for (String line : input) {
            Matcher lineMatcher = inputPattern.matcher(line);
            if (lineMatcher.matches()) {
                CoordDayFifteen sensor = new CoordDayFifteen(lineMatcher.group(1), lineMatcher.group(2));
                sensors.add(sensor);
                CoordDayFifteen beacon = new CoordDayFifteen(lineMatcher.group(3), lineMatcher.group(4));
                beacons.add(beacon);
                blankCoords.addAll(coordsWithinDistance(sensor, beacon));
            } else {
                throw new IllegalArgumentException("input line doesn't match: " + line);
            }

        }
        blankCoords.removeAll(sensors);
        blankCoords.removeAll(beacons);
        return (int) blankCoords.stream().filter(coord -> coord.y() == 10).count();
    }

    public Set<CoordDayFifteen> coordsWithinDistance(CoordDayFifteen sensor, CoordDayFifteen beacon) {
        int distanceToBeacon = Math.abs(-sensor.x() + beacon.x()) + Math.abs(- sensor.y() + beacon.y());
        Set<CoordDayFifteen> result = new HashSet<>();
        for (int yOffset = -distanceToBeacon; yOffset <= distanceToBeacon; yOffset++) {
            int remainingOffset = distanceToBeacon - Math.abs(yOffset);
            for (int xOffset = -remainingOffset; xOffset <= remainingOffset ; xOffset++) {
                result.add(new CoordDayFifteen(sensor.x()+xOffset, sensor.y()+yOffset));
            }
        }
        return result;
    }
}
