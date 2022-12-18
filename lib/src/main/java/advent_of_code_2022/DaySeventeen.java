package advent_of_code_2022;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

record Coord17(long x, long y) {
}

class Piece {
    Set<Coord17> coords = new HashSet<>();
    long x, y;

    Piece(Set<Coord17> coords) {
        this.coords = coords;
    }

    boolean move(int x, int y) {
        Set<Coord17> mappedCoords = mapCoords(x, y);

        if (x != 0) {
            long newMinX = mappedCoords.stream().mapToLong(Coord17::x).min().getAsLong();
            long newMaxX = mappedCoords.stream().mapToLong(Coord17::x).max().getAsLong();
            if (newMinX >= 0 && newMaxX < 7 && Collections.disjoint(mappedCoords, DaySeventeen.deadPile)) {
                this.x += x;
                return true;
            }
        }

        if (y != 0) {
            long newMinY = mappedCoords.stream().mapToLong(Coord17::y).min().getAsLong();
            if (newMinY < 0) {
                return false;
            } else if (!Collections.disjoint(DaySeventeen.deadPile, mappedCoords)) {
                return false;
            } else {
                this.y += y;
                return true;
            }
        }
        return false;
    }

    Set<Coord17> mapCoords(int x, int y) {
        Set<Coord17> result = new HashSet<>();
        for (Coord17 coord : coords) {
            result.add(new Coord17(this.x + coord.x() + x, this.y + coord.y() + y));
        }
        return result;
    }

}

record State(long pieceIndex, long height) {
}

public class DaySeventeen {
    static final List<Piece> PIECES = new ArrayList<>();
    static Set<Coord17> deadPile = new HashSet<>();
    private List<String> input;
    private long highPoint = 0;

    static {
        PIECES.add(new Piece(Set.of(new Coord17(0, 0), new Coord17(1, 0), new Coord17(2, 0), new Coord17(3, 0))));
        PIECES.add(new Piece(
                Set.of(new Coord17(1, 0), new Coord17(0, 1), new Coord17(1, 1), new Coord17(2, 1), new Coord17(1, 2))));
        PIECES.add(new Piece(
                Set.of(new Coord17(0, 0), new Coord17(1, 0), new Coord17(2, 0), new Coord17(2, 1), new Coord17(2, 2))));
        PIECES.add(new Piece(Set.of(new Coord17(0, 0), new Coord17(0, 1), new Coord17(0, 2), new Coord17(0, 3))));
        PIECES.add(new Piece(Set.of(new Coord17(0, 0), new Coord17(1, 0), new Coord17(0, 1), new Coord17(1, 1))));
    }

    public DaySeventeen(List<String> input) {
        this.input = input;
    }

    public long solvePart1(long noOfPieces) {
        deadPile = new HashSet<>();
        int windIndex = 0;
        long pieceIndex = 0;
        String wind = input.get(0);
        Map<String, State> states = new HashMap<>();

        while (pieceIndex < noOfPieces) {
            String topOfTower = getTopOfTower();
            State preExistingState = states.get(topOfTower);
            if (preExistingState != null) {
                System.out.println(
                        pieceIndex + " " + (pieceIndex - preExistingState.pieceIndex()) + " " + (highPoint - preExistingState.height()));
            } else {
                states.put(topOfTower, new State(pieceIndex, highPoint));
            }

            Piece currentPiece = PIECES.get((int) (pieceIndex++ % PIECES.size()));
            currentPiece.x = 2;
            currentPiece.y = highPoint + 3;

            while (true) {

                // for (int row = currentPiece.y; row >= 0; row--) {
                // StringBuilder line = new StringBuilder(" " + LocalDateTime.now());
                // for( int column = 0; column < 7; column++){
                // if(currentPiece.mapCoords(0, 0).contains(new Coord17(column, row))){
                // line.append("@");
                // } else {
                // line.append(".");
                // }
                // }
                // System.out.println(line);
                // }
                // System.out.println();

                windIndex %= wind.length();
                switch (wind.charAt(windIndex++)) {
                    case '<' -> currentPiece.move(-1, 0);
                    case '>' -> currentPiece.move(1, 0);
                }
                if (currentPiece.move(0, -1)) {
                    // do nowt
                } else {
                    // update highpoint add to dead stack
                    Set<Coord17> currentPieceMappedCoords = currentPiece.mapCoords(0, 0);
                    highPoint = Long.max(highPoint,
                            currentPieceMappedCoords.stream().mapToLong(Coord17::y).max().getAsLong() + 1);
                    deadPile.addAll(currentPieceMappedCoords);
                    break;
                }
            }

        }
        return highPoint;
    }

    private String getTopOfTower() {
        StringBuilder sb = new StringBuilder();
        for (long y = highPoint; y > highPoint - 50; y--) {
            for (long x = 0; x < 7; x++) {
                if(deadPile.contains(new Coord17(x, y))){
                    sb.append("#");
                } else {
                    sb.append(".");
                }
            }
        }
        return sb.toString();
    }
}
