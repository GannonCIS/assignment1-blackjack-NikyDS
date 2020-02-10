/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;

/**
 *
 * @author gubotdev
 */
public class Deck {
    private Card[] myCards = new Card[52];
    private String[] ranks = {"Ace", "2", "3", "4", "5", "6", "7", "8","9","10",
        "Jack","Queen", "King"};
    private String[] suits = {"Clubs", "Diamonds", "Hearts", "Spades"};
    private int nextCard = 0;
    
    public Deck(){
        initDeck();
        shuffle();
    }
    
    private void initDeck(){
        int index = 0;
        for(int s = 0; s< suits.length; s++){
            for(int r = 0; r< ranks.length; r++){
               myCards[index] = new Card(ranks[r], suits[s]);
               index++;
            }
        }
    }
    
    private void shuffle(){
        for(int i = 0; i < myCards.length; i++){
            Card temp = myCards[i];
            int rand = (int)(Math.random()*52);
            myCards[i] = myCards[rand];
            myCards[rand] = temp;
        }
    }
    
    //printDeck is for testing only --delete me
    public void printDeck(){
        for(int i = 0; i < myCards.length; i++){
            System.out.println(myCards[i].RANK + " of " + myCards[i].SUIT);
        }
    }
    
    public Card dealCard(){
        Card next = myCards[nextCard];
        nextCard++;
        if(nextCard > 51){
            shuffle();
            nextCard = 0;
        }
        return next; 
    }

}
