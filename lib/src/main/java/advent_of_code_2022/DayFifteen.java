package advent_of_code_2022;

import java.util.ArrayList;
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
    private List<CoordDayFifteen> sensors;
    private List<CoordDayFifteen> beacons;
    private int maxDistance;
    private int minSensorX;
    private int maxSensorX;

    public DayFifteen(List<String> input) {
        this.input = input;
        blankCoords = new HashSet<>();
        sensors = new ArrayList<>();
        beacons = new ArrayList<>();
        maxDistance = Integer.MIN_VALUE;
        minSensorX = Integer.MAX_VALUE;
        maxSensorX = Integer.MIN_VALUE;
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
//                blankCoords.addAll(coordsWithinDistance(sensor, beacon));
                int distance = manhattanDistance(sensor, beacon);
                if (distance > maxDistance) {
                    maxDistance = distance;
                }
                if (sensor.x()<minSensorX){
                    minSensorX = sensor.x();
                }
                if (sensor.x()>maxSensorX){
                    maxSensorX = sensor.x();
                }
            } else {
                throw new IllegalArgumentException("input line doesn't match: " + line);
            }

        }


        for (int x = minSensorX - maxDistance; x <= maxSensorX + maxDistance ; x++) {
            for (int i = 0; i < sensors.size(); i++) {
                CoordDayFifteen inspectionCoord = new CoordDayFifteen(x, rowIndex);
                if(manhattanDistance(sensors.get(i),beacons.get(i)) >= manhattanDistance(sensors.get(i), inspectionCoord)){
                    blankCoords.add(inspectionCoord);
                }
            }
        }

        blankCoords.removeAll(sensors);
        blankCoords.removeAll(beacons);
        
        return (int) blankCoords.size();
    }

    public Set<CoordDayFifteen> coordsWithinDistance(CoordDayFifteen sensor, CoordDayFifteen beacon) {
        int distanceToBeacon = manhattanDistance(sensor, beacon);
        Set<CoordDayFifteen> result = new HashSet<>();
        for (int yOffset = -distanceToBeacon; yOffset <= distanceToBeacon; yOffset++) {
            int remainingOffset = distanceToBeacon - Math.abs(yOffset);
            for (int xOffset = -remainingOffset; xOffset <= remainingOffset; xOffset++) {
                result.add(new CoordDayFifteen(sensor.x() + xOffset, sensor.y() + yOffset));
            }
        }
        return result;
    }

    private static int manhattanDistance(CoordDayFifteen coord1, CoordDayFifteen coord2) {
        return Math.abs(-coord1.x() + coord2.x()) + Math.abs(-coord1.y() + coord2.y());
    }
}
