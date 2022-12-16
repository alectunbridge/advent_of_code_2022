package advent_of_code_2022;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
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
        // calculate values of nodes
        int timeRemaining = 30;
        int totalFlow = 0;
        int currentNodeIndex = getNodeIndex("AA");

        while(timeRemaining>0) {
            System.out.println(currentNodeIndex);
            for (int i = 0; i < distances.length; i++) {
                distances[i][currentNodeIndex] = null;
            }
            Integer[] availableFlows = new Integer[valves.size()];
            for (int toIndex = 0; toIndex < valves.size(); toIndex++) {
                Integer timeToGetThere = distances[currentNodeIndex][toIndex];
                if (timeToGetThere != null && timeRemaining >= timeToGetThere + 1) {
                    availableFlows[toIndex] = (timeRemaining - timeToGetThere - 1) * valves.get(toIndex).flowRate;
                }
            }

            int maxFlow = Arrays.stream(availableFlows).filter(Objects::nonNull).mapToInt(Integer::valueOf).max().orElse(0);
            int destinationNodeIndex;
            for (destinationNodeIndex = 0; destinationNodeIndex < availableFlows.length; destinationNodeIndex++) {
                if (availableFlows[destinationNodeIndex] != null && availableFlows[destinationNodeIndex] == maxFlow) {
                    break;
                }
            }
            totalFlow += maxFlow;
            timeRemaining -= distances[currentNodeIndex][destinationNodeIndex] + 1;
            currentNodeIndex = destinationNodeIndex;
        }
        return totalFlow;
    }
}
