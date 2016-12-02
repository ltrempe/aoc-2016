package agi.aoc.day;

import agi.aoc.Challenge;
import agi.aoc.util.ResourceLoader;

import java.util.ArrayList;
import java.util.List;

public class DayOne implements Challenge {

    enum Direction {
        NORTH,
        EAST,
        SOUTH,
        WEST
    }

    @Override
    public void start() {
        String input = new ResourceLoader().load("day_one.txt");
        Navigator navigator = new Navigator(new Position(Direction.NORTH, 0, 0));
        final int answer = navigator.getManhattanDistance(getCoordinates(input));
        System.out.println("Day 1 snswer = " + answer);
    }

    ArrayList<Move> getCoordinates(String input) {
        final ArrayList<Move> moves = new ArrayList<>();
        String[] split = input.split(",");
        for (String s : split) {
            s = s.trim();
            String direction = s.substring(0, 1);
            String distance = s.substring(1);
            moves.add(new Move(direction, Integer.parseInt(distance)));
        }
        return moves;
    }

    static class Move {

        public final int rotation;
        public final int distance;

        public Move(String direction, int distance) {
            if ("L".equalsIgnoreCase(direction)) {
                rotation = -90;
            } else {
                rotation = 90;
            }
            this.distance = distance;
        }
    }

    static class Navigator {
        final Position startPosition;
        Position currentPosition;

        public Navigator(Position startPosition) {
            this.startPosition = startPosition;
            currentPosition = startPosition;
        }

        public int getManhattanDistance(List<Move> moves) {
            for (int i = 0; i < moves.size(); i++) {
                final Move move = moves.get(i);
                final int rotation = move.rotation;
                final int distance = move.distance;
                currentPosition = walk(rotation, distance);
            }
            return Math.abs(startPosition.x - currentPosition.x)
                    + Math.abs(startPosition.y - currentPosition.y);
        }

        private Position walk(int rotation, int distance) {
            Direction[] compass = Direction.values();
            int currentIndex = currentPosition.direction.ordinal();

            if (rotation > 0) {
                currentIndex++;
            } else {
                currentIndex--;
            }
            if (currentIndex >= compass.length) {
                currentIndex = 0;
            } else if (currentIndex < 0) {
                currentIndex = compass.length - 1;
            }

            final Direction direction = compass[currentIndex];
            int x = currentPosition.x;
            int y = currentPosition.y;

            if (direction == Direction.NORTH) {
                y += distance;
            } else if (direction == Direction.EAST) {
                x += distance;
            } else if (direction == Direction.SOUTH) {
                y -= distance;
            } else {
                x -= distance;
            }
            return new Position(direction, x, y);
        }
    }

    public static class Position {
        public final Direction direction;
        public final int x;
        public final int y;

        public Position(Direction direction, int x, int y) {
            this.direction = direction;
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return direction + " " + x + " " + y;
        }
    }
}
