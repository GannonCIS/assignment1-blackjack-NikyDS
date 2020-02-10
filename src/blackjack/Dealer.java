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
        
        
    }
    public void playGame(){
        dealOutOpeningHand();
        takePlayerTurns();
    }
    
    public void dealOutOpeningHand(){
        for(int i = 0; i < 2; i ++){
            for(Player currPlayer : myPlayers){
                currPlayer.getMyHand().addCard(myDeck.dealCard());
            }
            dealerHand.addCard(myDeck.dealCard());
            if( i < 1){
            System.out.println("Dealer's Hand");
            dealerHand.printHand();
                System.out.println("Dealer's Score: " + dealerHand.getScore() 
                + "\n");
            }
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
                    //currPlayer.getMyHand().changeAce(currPlayer);
                }else{
                    System.out.println("\n");
                    break;
                }
                
            }
            currPlayer.getMyHand().printHand();
            System.out.println(currPlayer.getName() + "'s Score: " + 
                    currPlayer.getMyHand().getScore() + "\n");
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
        System.out.println("Dealer's Score: " + dealerHand.getScore() +
                "\n");
    }
    
    
    public void declareWinners(){
        for(Player currPlayer : myPlayers){
            if(dealerHand.getScore()> 21 || 
                    currPlayer.getMyHand().getScore() > 21){
                if(currPlayer.getMyHand().getScore() > 21){
                    System.out.println(currPlayer.getName() + "'s Score = " + 
                        currPlayer.getMyHand().getScore() + " You Busted "
                            + "Loser");
                }else{
                    System.out.println(currPlayer.getName() +"'s Score = " + 
                        currPlayer.getMyHand().getScore()+ " Dealer Busted"
                            +  " You Win");
                }
            }else if(dealerHand.getScore()==21 || dealerHand.getNumOfCards()>4){
                System.out.println(currPlayer.getName() + "'s Score = " + 
                        currPlayer.getMyHand().getScore()+
                        "Dealer Wins!!! You Lose...");
            }else if(currPlayer.getMyHand().getNumOfCards()>4){
                System.out.println(currPlayer.getName() + "'s Score = " + 
                        currPlayer.getMyHand().getScore()+ " Nice Hand"
                        + " You Win");
            }else if(currPlayer.getMyHand().getScore() > dealerHand.getScore()){
                System.out.println(currPlayer.getName() + "'s Score = " + 
                        currPlayer.getMyHand().getScore() + " Beat the Dealer"
                        + " You Win");
            }else{
                System.out.println(currPlayer.getName() + "'s Score = " + 
                        currPlayer.getMyHand().getScore()+" Dealer Beat You");
            }
        }
    }
   
    private void initMyPlayers(int numPlayers) {
        myPlayers = new Player[numPlayers];
        for(int i = 0; i < myPlayers.length; i++){
            System.out.println("Player " + (i+1) + " what's your name: " );
            String name = scan.nextLine();
            if(name.equals("")){
                myPlayers[i] = new Player(i+1);
            }else{
                myPlayers[i] = new Player(name);
            }
        }
    }
}
