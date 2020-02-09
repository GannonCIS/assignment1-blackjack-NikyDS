/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;

import java.util.Scanner;

/**
 *
 * @author gubotdev
 */
public class Dealer {
    private Deck myDeck = new Deck();
    private Player[] myPlayers;
    private Hand dealerHand = new Hand();
    private Scanner scan = new Scanner(System.in);
    
    public Dealer(int numOfPlayers){
        
        initMyPlayers(numOfPlayers);
        dealOutOpeningHand();
        takePlayerTurns();
        
    }
    
    public void dealOutOpeningHand(){
        for(int i = 0; i < 2; i ++){
            for(Player currPlayer : myPlayers){
                currPlayer.getMyHand().addCard(myDeck.dealCard());
            }
            dealerHand.addCard(myDeck.dealCard());
        }
    }
    
    public void takePlayerTurns(){
        for(Player currPlayer : myPlayers){
            while(currPlayer.getMyHand().getNumOfCards() < 5 &&
                    currPlayer.getMyHand().getScore() < 21){
                System.out.println(currPlayer.getName() + "'s Hand");
                currPlayer.getMyHand().printHand();
                System.out.println(currPlayer.getName() + "'s Score:");
                currPlayer.getMyHand().printScore();
                System.out.println("Wanna Hit? (y/n)");
                char opt = scan.next().charAt(0);
                if(opt == 'y'){
                    currPlayer.getMyHand().addCard(myDeck.dealCard());
                }else{
                    break;
                }
            }
            currPlayer.getMyHand().printHand();
            System.out.println(currPlayer.getName() + "'s Score:");
            currPlayer.getMyHand().printScore();
        }
        playOutDealerHand();
        declareWinners();
    }
    
    public void playOutDealerHand(){
        while(dealerHand.getScore() < 16){
            dealerHand.addCard(myDeck.dealCard());
        }
        System.out.println("Dealer's Hand:");
        dealerHand.printHand();
        System.out.println("Dealer's Score: ");
        dealerHand.printScore();
    }
    
    public void declareWinners(){
        for(Player currPlayer : myPlayers){
            if(dealerHand.getScore() > 21 && 
                    currPlayer.getMyHand().getScore() < 21){
                System.out.println(currPlayer.getName()+ " Wins- Dealer Busted");
            }else if(dealerHand.getScore() < currPlayer.getMyHand().getScore()
                    && currPlayer.getMyHand().getScore() < 21){
                System.out.println(currPlayer.getName() + " Wins- Beat the Dealer");
            }else if(currPlayer.getMyHand().getScore() == 21){
                System.out.println(currPlayer.getName() + " Wins- Blackjack");
            }else {
                System.out.println(currPlayer.getName() + " Loses- Better Luck"
                        + " Next Time");
            }
        }
    }
   
    private void initMyPlayers(int numPlayers) {
        myPlayers = new Player[numPlayers];
        for(int i = 0; i < myPlayers.length; i++){
            System.out.println("Player " + (i+1) + " what's your name or press"
                    + " n if you do not want a name:");
            String name = scan.next();
            if(name.equals("n")){
                myPlayers[i] = new Player(i+1);
            }else{
                myPlayers[i] = new Player(name);
            }
        }
    }
}
