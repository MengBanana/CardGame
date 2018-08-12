/**
 * Game.java
 * @author Xinmeng Zhang
 */
package edu.northeastern.cs_5006;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * This Game class has a static method play() that takes the number of players 
 * and the number of cards per player. 
 *
 */
public class Game {
	
	/**
	 * The play method creates a fixed thread pool ExecutorService (Executors.newFixedThreadPool()) 
	 * with enough threads to run all the players and the dealer. 
	 * It also creates a BlockingQueue of size one (1) where cards are dealt by the dealer and taken by the players.
	 * Then the method creates and submits the required number of Player instances and executes 
	 * a Dealer instance to the ExecutorService. Finally, it shuts down the ExecutorService and 
	 * returns the hands returned from submitting the Player instances in the form of Future<Card[]> 
	 * objects as an array.
	 * 
	 * @param players the number of players
	 * @param cardsPerPlayer the cards per player
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Future<Card[]>[] play(int players, int cardsPerPlayer) {
		// 5 threads, 1 for dealer, 4 players
		ExecutorService executor = Executors.newFixedThreadPool(5);
		BlockingQueue<Card> blockingQueue = new ArrayBlockingQueue<>(1);
		@SuppressWarnings("rawtypes")
		Future[] hands = new Future[players];
        for (int i = 0; i < players; i++) {  
	        Callable<Card[]> player = new Player(blockingQueue, cardsPerPlayer);  
	        Future<Card[]> future = executor.submit(player);
	        hands[i] = future;
        }
        Runnable dealer = new Dealer(blockingQueue, players, cardsPerPlayer);  
        executor.execute(dealer);
        executor.shutdown();
        return hands;
	}
}
