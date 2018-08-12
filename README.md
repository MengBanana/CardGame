# CardGame

Card. This class implements a playing card with a suit and a value. The class has a static method makeDeck() that returns a fresh 
deck in the form of a List. The deck is in order by suit and then by value, so the deck should be shuffled before dealing 
(see Collections.shuffle).

PlayGame. This class has a main program that plays a game and then prints the hands.

Dealer. This class takes blocking queue of cards, the number of players, and the number of cards per player through its constructor. 
The class should implement Runnable. Its run() method makes a new deck and shuffles it. Then it deals the number of cards per player 
for each player to the blocking queue from the deck, and then returns. Print each card to stderr just before putting it to the queue.

Player. This class takes a blocking queue of cards and the number of cards per player through its constructor. The class should 
implement Callable. Its call() method should create an array of Card, take the required number of cards from the queue, and then return 
the array. Print the player's thread id and the card to stderr just after taking each card, and then sleep a random time between 100ms 
and 1000ms before taking the next card (see java.util.Random).

Game. This class has a static method play() that takes the number of players and the number of cards per player. The method creates a 
fixed thread pool ExecutorService (Executors.newFixedThreadPool()) with enough threads to run all the players and the dealer. It also 
creates a BlockingQueue of size one (1) where cards are dealt by the dealer and taken by the players. Then the method creates and submits 
the required number of Player instances and executes a Dealer instance to the ExecutorService. Finally, it shuts down the ExecutorService 
and returns the hands returned from submitting the Player instances in the form of Future<Card[]> objects as an array.
