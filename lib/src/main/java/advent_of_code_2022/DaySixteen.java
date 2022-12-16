package advent_of_code_2022;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


record Edge(String from, String to) {
}

class Valve {
    String name;
    int flowRate;
    boolean on;

    Valve(String name, int flowRate, boolean on) {
        this.name = name;
        this.flowRate = flowRate;
        this.on = on;
    }

    Valve(String name, String flowRate) {
        this(name, Integer.parseInt(flowRate), false);
    }
}

public class DaySixteen {
    private final List<Valve> valves = new ArrayList<>();
    private final List<Edge> edges = new ArrayList<>();
    private final Integer[][] distances;
    private int maxFlow = 0;

    public DaySixteen(List<String> input) {
        Pattern inputPattern = Pattern.compile("Valve (..) has flow rate=(\\d+); tunnels? leads? to valves? (.*)");

        for (String line : input) {
            Matcher lineMatcher = inputPattern.matcher(line);
            if (lineMatcher.matches()) {
                valves.add(new Valve(lineMatcher.group(1), lineMatcher.group(2)));
                String[] destinationValues = lineMatcher.group(3).split(", ");
                for (String valve : destinationValues) {
                    edges.add(new Edge(lineMatcher.group(1), valve));
                }
            } else {
                throw new IllegalArgumentException("Unparseable input: " + line);
            }
        }

        this.distances = floydWarshall(valves.size());
    }

    private Integer[][] floydWarshall(int noOfNodes) {
        Integer[][] distances = new Integer[noOfNodes][noOfNodes];
        for (Edge edge : edges) {
            int fromIndex = getNodeIndex(edge.from());
            int toIndex = getNodeIndex(edge.to());
            distances[fromIndex][toIndex] = 1;
        }
        for (int i = 0; i < noOfNodes; i++) {
            distances[i][i] = 0;
        }
        for (int k = 0; k < noOfNodes; k++) {
            for (int i = 0; i < noOfNodes; i++) {
                for (int j = 0; j < noOfNodes; j++) {
                    if (distances[i][k] != null &&
                            distances[k][j] != null &&
                            (distances[i][j] == null || distances[i][j] > distances[i][k] + distances[k][j])) {
                        distances[i][j] = distances[i][k] + distances[k][j];
                    }
                }
            }
        }
        return distances;
    }

    private int getNodeIndex(String name) {
        for (int i = 0; i < valves.size(); i++) {
            if (valves.get(i).name.equals(name)) {
                return i;
            }
        }
        throw new IllegalArgumentException("can't find node: " + name);
    }

    public int solvePart1() {

        //eliminate zero flow valves
        for (int i = 0; i < valves.size(); i++) {
            if (valves.get(i).flowRate == 0) {
                for (int j = 0; j < distances.length; j++) {
                    distances[j][i] = null;
                }
            }
        }

        int timeRemaining = 30;
        int totalFlow = 0;
        int currentNodeIndex = getNodeIndex("AA");
        walkGraph(currentNodeIndex, timeRemaining, totalFlow, new HashSet<>());

        return maxFlow;
    }

    private void walkGraph(int currentNodeIndex, int timeRemaining, int totalFlow, HashSet<Integer> visitedIndexes) {
        maxFlow = Integer.max(totalFlow, maxFlow);
        for (int childNodeIndex = 0; childNodeIndex < distances.length; childNodeIndex++) {
            if (distances[currentNodeIndex][childNodeIndex] != null
                    && !visitedIndexes.contains(childNodeIndex)) {
                int timeToGetToAndOpenValve = distances[currentNodeIndex][childNodeIndex] + 1;
                if (timeRemaining >= timeToGetToAndOpenValve) {
                    HashSet<Integer> newVisitedIndexes = new HashSet<>(visitedIndexes);
                    newVisitedIndexes.add(childNodeIndex);
                    walkGraph(childNodeIndex,
                            timeRemaining - timeToGetToAndOpenValve,
                            totalFlow + (timeRemaining - timeToGetToAndOpenValve) * valves.get(childNodeIndex).flowRate,
                            newVisitedIndexes
                    );
                }
            }
        }
    }
}
