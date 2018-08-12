/**
 * Dealer.java
 * @author Xinmeng Zhang
 */
package edu.northeastern.cs_5006;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.BlockingQueue;

/**
 * This class takes blocking queue of cards, the number of players, 
 * and the number of cards per player through its constructor. 
 * The class implements Runnable.
 */
public class Dealer implements Runnable {
	
	BlockingQueue<Card> blockingQueue;
	int players;
	int cardsPerPlayer;
	
	/**
	 * Constructor of Dealer
	 * 
	 * @param blockingQueue the blockingQueue to enqueue card
	 * @param players the number of players in the game
	 * @param cardsPerPlayer the number of cards per player
	 */
	public Dealer(BlockingQueue<Card> blockingQueue, int players, int cardsPerPlayer) {
		this.blockingQueue = blockingQueue;
		this.players = players;
		this.cardsPerPlayer = cardsPerPlayer;
	}
	
	/**
	 * Override of run function
	 * The run() method makes a new deck and shuffles it. 
	 * Then it deals the number of cards per player for each player to the blocking queue from the deck,
	 * and then returns. Print each card to stderr just before putting it to the queue.
	 */
	@Override
	public void run() {
		// make a new deck and shuffle;
		List<Card> deck = Card.makeDeck();
		Collections.shuffle(deck);
		int total = players * cardsPerPlayer;
		// enque n cards in the blockingqueue, n = total
		for (int i=0; i<total; i++) {
			Card cur = deck.get(i);
			System.err.printf("Dealer: putting %s\n", cur.toString());
			try {
				blockingQueue.put(cur);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
