/**
 * PlayGame.java
 */
package edu.northeastern.cs_5006;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * This class tests the Game.
 * 
 * @author philip gust
 *
 */
public class PlayGame {
	/**
	 * Test the Game with 4 players and 5 cards per player.
	 * 
	 * @param args unused
	 */
	public static void main(String[] args) {
		// create a fixed pool of 5 threads and return an
		// array of nPlayer hands of nCardsPerPlayer cards
	    final int nPlayers = 4;
	    final int nCardsPerPlayer = 5;
	    Future<Card[]> hands[] = Game.play(nPlayers, nCardsPerPlayer);
	    
	    // for each player's hand of cards
	    for (Future<Card[]> hand : hands) {
	    		try {
	    			// print a line with the cards in the hand
	    			Card[] cards = hand.get();
    				System.out.printf("Hand");

    				String separator = ": ";
				for (Card card : cards) {
					System.out.print(separator);
					System.out.print(card);
					separator = ", ";
				}
	    			System.out.println();
			} catch (InterruptedException | ExecutionException e) {
				System.out.println(": unavailable");
			}
	    }
	}	    
}