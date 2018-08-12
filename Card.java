/*
 * Card.java
 */

package edu.northeastern.cs_5006;

import java.util.ArrayList;
import java.util.List;

/**
 * This class implements a playing card.
 * 
 * @author philip gust
 */
class Card {
	/** enumeration of suits */
	static public enum Suit {HEARTS, DIAMONDS, CLUBS, SPADES};
	/** Suit of card */
	final public Suit suit;
	
	/** Value of card */
	final public int value;
	
	/** Card factory */
	private static List<Card> deck = null;

	/**
	 * Create a new card.
	 * 
	 * @param value the card value
	 * @param suit the card suit
	 */
	private Card(int value, Suit suit) {
		this.suit = suit;
		this.value = value;
	}

	/**
	 * Return a string representation of a card.
	 * @return string representation of a card
	 */
	public String toString() {
		final String[] values = {"","A","2","3","4","5","6","7","8","9","X","J","Q","K"};
		final String[] suits = {"\u2661","\u2662","\u2667", "\u2664"}; // H, D, C, S
		return values[value] + "(" + suits[suit.ordinal()] + ")";
	}
	
	/**
	 * Create a deck 52 cards. The cards are
	 * in order by suit and then by value, just
	 * as a real new deck of cards would be. 
	 * Use Collections.shuffle() to shuffle it.
	 * 
	 * @return a deck of 52 cards
	 */
	static List<Card> makeDeck() {
		if (deck == null) {
			// create cards to ensure object quality comparison
			deck = new ArrayList<Card>(52);
			for (Suit suit : Suit.values()) {
				for (int value = 1; value <= 13; value++) {
					deck.add(new Card(value, suit));
				}
			}
		}
		return new ArrayList<Card>(deck);
	}
}