
package logic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


public class GameEngineTest {

    @Test
    public void determineHandStrengthPairTest() {

        int[] inputHand = {1, 1, 3, 4, 5};
        int[] expected = {1, 1, 5, 4, 3};
        int[] actual = GameEngine.determineHandStrength(inputHand);
        Assertions.assertArrayEquals(expected, actual);

    }

    @Test
    public void determineHandStrengthTest2Pair() {

        int [] inputHand = new int[]{5, 5, 4, 4, 6};
        int [] expected = new int[]{2, 5, 4, 6, 0};
        int [] actual = GameEngine.determineHandStrength(inputHand);
        Assertions.assertArrayEquals(expected, actual);

    }

    @Test
    public void determineHandStrengthTest2ofKind() {

        int [] inputHand = new int[]{1, 1, 1, 3, 2};
        int [] expected = new int[]{3, 1, 3, 2, 0};
        int [] actual = GameEngine.determineHandStrength(inputHand);
        Assertions.assertArrayEquals(expected, actual);

    }

    @Test
    public void determineHandStrengthTestStraight() {

        int [] inputHand = new int[]{1, 2, 3, 4, 5};
        int [] expected = new int[]{4, 5, 0, 0, 0};
        int [] actual = GameEngine.determineHandStrength(inputHand);
        Assertions.assertArrayEquals(expected, actual);

    }

    @Test
    public void determineHandStrengthTestFullHouse() {

        int [] inputHand = new int[]{2, 2, 2, 5, 5};
        int [] expected = new int[]{5, 2, 5, 0, 0};
        int [] actual = GameEngine.determineHandStrength(inputHand);
        Assertions.assertArrayEquals(expected, actual);

    }

    @Test
    public void determineHandStrengthTest4ofKind() {

        int [] inputHand = new int[]{6, 6, 6, 6, 1};
        int [] expected = new int[]{6, 6, 1, 0, 0};
        int [] actual = GameEngine.determineHandStrength(inputHand);
        Assertions.assertArrayEquals(expected, actual);

    }

    @Test
    public void determineHandStrengthTest5ofKind() {

        int [] inputHand = new int[]{1, 1, 1, 1, 1};
        int [] expected = new int[]{7, 1, 0, 0, 0};
        int [] actual = GameEngine.determineHandStrength(inputHand);
        Assertions.assertArrayEquals(expected, actual);

    }

    @Test
    public void determineHandStrengthTestNada() {

        int [] inputHand = new int[]{1, 2, 3, 5, 6};
        int [] expected = new int[]{0, 4, 0, 0, 0};
        int [] actual = GameEngine.determineHandStrength(inputHand);
        Assertions.assertArrayEquals(expected, actual);

    }


    @Test
    public void compareHandStrengthTestExpectTrue() {

        int[] hand1 = {7, 6, 0, 0, 0};
        int[] hand2 = {0, 3, 0, 0, 0};
        Boolean result = GameEngine.compareHandStrength(hand1, hand2);
        Assertions.assertTrue(result);

    }

    @Test
    public void compareHandStrengthTestExpectFalse() {

        int[] hand2 = {7, 6, 0, 0, 0};
        int[] hand1 = {0, 3, 0, 0, 0};
        Boolean result = GameEngine.compareHandStrength(hand1, hand2);
        Assertions.assertFalse(result);

    }

    @Test
    public void compareHandStrengthTestTieExpectFalse(){

        int[] hand1 = {7,6,0,0,0};
        int[] hand2= {7,6,0,0,0};
        Boolean result = GameEngine.compareHandStrength(hand1,hand2);
        Assertions.assertFalse(result);

    }

    @Test
    public void sortHandStrengthTest() {

        int[] hand1 = {0,5,0,0,0}; // Rolled nothing, missing a 5.
        int[] hand2 = {1,2,6,5,4}; // Rolled a pair of ones, 6,5,4 kickers.
        int[] hand3 = {2,4,5,2,0}; // Rolled two pairs of 5s and 4s, with a 2 kicker.
        int[] hand4 = {5,5,1,0,0}; // Rolled a full house 5s and 1s.

        int[][] testHand = {hand1,hand2,hand3,hand4};
        int[][] expectedHand = {hand4, hand3,hand2, hand1};
        int[][] sortedHand = GameEngine.sortHandStrength(testHand);
        Assertions.assertArrayEquals(expectedHand, sortedHand);
    }

    @Test
    public void sortHandStrengthTieTest() {

        int[] hand1 = {0,5,0,0,0}; // Rolled nothing, missing a 5.
        int[] hand2 = {5,5,1,0,0}; // Rolled a full house 5s and 1s.
        int[] hand3 = {2,4,5,2,0}; // Rolled two pairs of 5s and 4s, with a 2 kicker.
        int[] hand4 = {5,5,1,0,0}; // Rolled a full house 5s and 1s.

        int[][] testHand = {hand1,hand2,hand3,hand4};
        int[][] expectedHand = {hand4, hand2,hand3, hand1};
        int[][] sortedHand = GameEngine.sortHandStrength(testHand);
        Assertions.assertArrayEquals(expectedHand, sortedHand);
    }

    @Test
    public void GameEngineConstructorHandlesAPlayerBeingNull() {
        List<Player> listOfFakePlayers = new ArrayList<>();
        listOfFakePlayers.add(null);
        listOfFakePlayers.add(new FakePlayer());
        listOfFakePlayers.add(new FakePlayer());

        GameEngine testEngine = new GameEngine(listOfFakePlayers);
        Assertions.assertEquals(2,testEngine.getCurrentPlayerNumber());

    }

    @Test
    public void currentPlayerAnteTrueTest(){

        List<Player> listOfFakePlayers = new ArrayList<>();
        listOfFakePlayers.add(new FakePlayer());

        listOfFakePlayers.get(0).setMarker(15);

        GameEngine testEngine = new GameEngine(listOfFakePlayers);

        Assertions.assertTrue(testEngine.currentPlayerPayAnte());
        Assertions.assertEquals(10,testEngine.getCurrentPot());

    }

    @Test
    public void currentPlayerAnteFalseTest(){

        List<Player> listOfFakePlayers = new ArrayList<>();
        listOfFakePlayers.add(new FakePlayer());

        listOfFakePlayers.get(0).setMarker(5);

        GameEngine testEngine = new GameEngine(listOfFakePlayers);
        Assertions.assertFalse(testEngine.currentPlayerPayAnte());

    }

    @Test
    public void getPlayerTest_expect2(){

        List<Player> listOfPlayers = new ArrayList<>();
        listOfPlayers.add(new RealPlayer("a", 15, true));
        listOfPlayers.add(new RealPlayer("b", 20, true));
        listOfPlayers.add(new RealPlayer("c", 15, true));
        listOfPlayers.add(new RealPlayer("d", 31, true));

        GameEngine testEngine = new GameEngine(listOfPlayers);

        Assertions.assertEquals("b", testEngine.getPlayer(2).getName());

    }

    @Test
    public void isPlayerWinnerTest_expectsTrue() {
        List<Player> listOfPlayers = new ArrayList<>();
        listOfPlayers.add(new RealPlayer("a", 15, true));

        GameEngine testEngine = new GameEngine(listOfPlayers);

        Assertions.assertTrue(testEngine.isPlayerWinner());
    }

    @Test
    public void isPlayerWinnerTest_expectsFalse() {
        List<Player> listOfPlayers = new ArrayList<>();
        listOfPlayers.add(new RealPlayer("a", 15, true));
        listOfPlayers.add(new RealPlayer("b", 20, true));

        GameEngine testEngine = new GameEngine(listOfPlayers);

        Assertions.assertFalse(testEngine.isPlayerWinner());
    }

    @Test
    public void eliminateCurrentPlayerTest_expectsStringb() {
        List<Player> listOfPlayers = new ArrayList<>();
        listOfPlayers.add(new RealPlayer("a", 15, true));
        listOfPlayers.add(new RealPlayer("b", 20, true));

        GameEngine testEngine = new GameEngine(listOfPlayers);
        testEngine.eliminateCurrentPlayer();

        Assertions.assertEquals("b",testEngine.getCurrentPlayer().getName());
    }

    @Test
    public void roundWinnerTest_expectsListOfSingle2() {
        List<Player> listOfPlayers = new ArrayList<>();
        listOfPlayers.add(new FakePlayer());
        listOfPlayers.add(new FakePlayer());

        List<Integer> expected = new ArrayList<>();
        expected.add(2);

        GameEngine testEngine = new GameEngine(listOfPlayers);
        testEngine.eliminateCurrentPlayer();
        Assertions.assertEquals(expected, testEngine.roundWinner());

    }

    @Test
    public void roundWinnerTieTest_expectsListOf1and2() {
        List<Player> listOfPlayers = new ArrayList<>();
        listOfPlayers.add(new FakePlayer());
        listOfPlayers.add(new FakePlayer());

        List<Integer> expected = new ArrayList<>();
        expected.add(1);
        expected.add(2);

        GameEngine testEngine = new GameEngine(listOfPlayers);
        Assertions.assertEquals(expected, testEngine.roundWinner());

    }

    @Test
    public void getCurrentPlayerBetTest_expects0() {
        List<Player> listOfPlayers = new ArrayList<>();
        listOfPlayers.add(new FakePlayer());

        GameEngine testEngine = new GameEngine(listOfPlayers);
        Assertions.assertEquals(0, testEngine.getCurrentPlayerBet());
    }

    @Test
    public void rollCurrentPlayerTest_expectArrayOf5Ones() {
        List<Player> listOfPlayers = new ArrayList<>();
        listOfPlayers.add(new FakePlayer());

        GameEngine testEngine = new GameEngine(listOfPlayers);
        Assertions.assertArrayEquals(new int[] {1, 1, 1, 1, 1}, testEngine.rollCurrentPlayer());
    }

    @Test
    public void getCurrentRoundTest_expects1() {
        List<Player> listOfPlayers = new ArrayList<>();
        listOfPlayers.add(new FakePlayer());

        GameEngine testEngine = new GameEngine(listOfPlayers);
        Assertions.assertEquals(1,testEngine.getCurrentRound());
    }

    @Test
    public void nextRoundTest_expectsRound5() {
        List<Player> listOfPlayers = new ArrayList<>();
        listOfPlayers.add(new FakePlayer());

        GameEngine testEngine = new GameEngine(listOfPlayers);
        for (int i = 1; i < 5; i++) {
            testEngine.newRound();
        }
        Assertions.assertEquals(5,testEngine.getCurrentRound());
    }

    @Test
    public void nextPlayerTest_expects2() {
        List<Player> listOfPlayers = new ArrayList<>();
        listOfPlayers.add(new FakePlayer());
        listOfPlayers.add(new FakePlayer());

        GameEngine testEngine = new GameEngine(listOfPlayers);
        testEngine.nextPlayer();
        Assertions.assertEquals(2,testEngine.getCurrentPlayerNumber());
    }

    @Test
    public void nextPlayerTest_expects1() {
        List<Player> listOfPlayers = new ArrayList<>();
        listOfPlayers.add(new FakePlayer());
        listOfPlayers.add(new FakePlayer());

        GameEngine testEngine = new GameEngine(listOfPlayers);
        testEngine.nextPlayer();
        testEngine.nextPlayer();
        Assertions.assertEquals(1,testEngine.getCurrentPlayerNumber());
    }

    @Test
    public void isCurrentPlayerFirstPlayerTest_expectsTrue() {
        List<Player> listOfPlayers = new ArrayList<>();
        listOfPlayers.add(new FakePlayer());
        listOfPlayers.add(new FakePlayer());

        GameEngine testEngine = new GameEngine(listOfPlayers);

        Assertions.assertTrue(testEngine.isCurrentPlayerFirstPlayer());
    }

    @Test
    public void isCurrentPlayerFirstPlayerTest_expectsFalse() {
        List<Player> listOfPlayers = new ArrayList<>();
        listOfPlayers.add(new FakePlayer());
        listOfPlayers.add(new FakePlayer());

        GameEngine testEngine = new GameEngine(listOfPlayers);
        testEngine.nextPlayer();

        Assertions.assertFalse(testEngine.isCurrentPlayerFirstPlayer());
    }

    @Test
    public void currentPlayerFoldTest() {
        List<Player> listOfPlayers = new ArrayList<>();
        listOfPlayers.add(new FakePlayer());
        listOfPlayers.add(new FakePlayer());

        GameEngine testEngine = new GameEngine(listOfPlayers);
        testEngine.nextPlayer();
        testEngine.currentPlayerFold();

        List<Integer> expected = new ArrayList<>();
        expected.add(1);

        Assertions.assertEquals(expected, testEngine.roundWinner());
    }






private String handStrengthOutput(int[] expected, int [] actual) {
        StringBuilder string1 = new StringBuilder();
        StringBuilder string2 = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            string1.append(expected[i]);
            string1.append(" ");
            string2.append(actual[i]);
            string2.append(" ");
        }

        return ("Expected: " + string1.toString() + " Actual: " +string2.toString());
    }

}