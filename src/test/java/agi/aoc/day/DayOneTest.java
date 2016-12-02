package agi.aoc.day;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNotNull;

public class DayOneTest {

    final DayOne.Position startPostition = new DayOne.Position(DayOne.Direction.NORTH, 0, 0);

    @Test
    public void example1() {
        // Following R2, L3 leaves you 2 blocks East and 3 blocks North, or 5 blocks away.
        DayOne dayOne = new DayOne();
        String input = "R2, L3";
        List<DayOne.Move> moves = dayOne.getCoordinates(input);
        assertNotNull(moves);
        DayOne.Navigator navigator = new DayOne.Navigator(startPostition);
        assertThat(navigator.getManhattanDistance(moves), equalTo(5));
    }

    @Test
    public void example2() {
        // R2, R2, R2 leaves you 2 blocks due South of your starting position,
        // which is 2 blocks away
        DayOne dayOne = new DayOne();
        String input = "R2, R2, R2";
        List<DayOne.Move> moves = dayOne.getCoordinates(input);
        DayOne.Navigator navigator = new DayOne.Navigator(startPostition);
        assertThat(navigator.getManhattanDistance(moves), equalTo(2));
    }

    @Test
    public void example3() {
        // R5, L5, R5, R3 leaves you 12 blocks away
        DayOne dayOne = new DayOne();
        String input = "R5, L5, R5, R3";
        List<DayOne.Move> moves = dayOne.getCoordinates(input);
        DayOne.Navigator navigator = new DayOne.Navigator(startPostition);
        assertThat(navigator.getManhattanDistance(moves), equalTo(12));
    }

    @Test
    public void handleInput() {
        DayOne dayOne = new DayOne();
        String input = "L2, R1, L2";
        List<DayOne.Move> moves = dayOne.getCoordinates(input);
        assertNotNull(moves);
        assertThat(moves.size(), equalTo(3));
        assertThat(moves.get(0).rotation, equalTo(-90));
        assertThat(moves.get(0).distance, equalTo(2));
    }
}
