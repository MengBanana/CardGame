/**
 * Player.java
 * @author Xinmeng Zhang
 */
package edu.northeastern.cs_5006;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;

/**
 * This class takes a blocking queue of cards and the number of cards per player through its constructor. 
 * The class should implement Callable. 
 */
public class Player implements Callable<Card[]> {
	
	BlockingQueue<Card> blockingQueue;
	int cardsPerPlayer;
	
	/**
	 * Constructor for player
	 * @param blockingQueue the blockingQueue to get the car from
	 * @param cardsPerPlayer the number of cards per player
	 */
	public Player(BlockingQueue<Card> blockingQueue, int cardsPerPlayer) {
		this.blockingQueue = blockingQueue;
		this.cardsPerPlayer = cardsPerPlayer;
	}
	
	/**
	 * Override of call function
	 * The call() method should create an array of Card, 
	 * take the required number of cards from the queue, and then return the array. 
	 * Print the player's thread id and the card to stderr just after taking each card, 
	 * and then sleep a random time between 100ms and 1000ms before taking the next card.
	 */
	@Override
	public Card[] call() throws InterruptedException {
		Card[] cards = new Card[cardsPerPlayer];
		// each thread takes at mots i cards. i = cardsPerPlayer
		for (int i=0; i<cardsPerPlayer; i++) {
			// black method take
			Card cur = blockingQueue.take();
			System.err.printf("Player %s: took %s\n", Thread.currentThread().getName(), cur.toString());
			cards[i] = cur;
			Random rand = new Random();
			// 0-901, + 100, 100-1001
			int time = rand.nextInt(901) + 100;
			// sleep a random time
			Thread.sleep(time);
		}
		return cards;
	}
	
}
