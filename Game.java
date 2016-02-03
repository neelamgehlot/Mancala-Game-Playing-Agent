/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mancala;

/**
 *
 * @author Neelam
 */

//Represent Game state
public class Game {
    private int player;
    private int[] board1,board2;
    private int mancala1, mancala2;
    private int boardSize;
    private boolean getAnotherTurn;
    
    //Default Constructor
    public Game(){
        
    }
    
    //Constructor for board creation
    public Game(int player, int[] board2, int[] board1, int mancala2, int mancala1){
        this.player=player;
        this.boardSize=board2.length;
        this.board2=new int[boardSize];
        this.board1=new int[boardSize];
        for(int i=0;i<boardSize;i++){
            this.board2[i]=board2[i];
            this.board1[i]=board1[i];
        }
        this.mancala2=mancala2;
        this.mancala1=mancala1;
    }
    
    //Copy Constructor
    public Game(Game x){
        player=x.getPlayer();
        boardSize=x.getBoardSize();
        this.board2=new int[boardSize];
        this.board1=new int[boardSize];
        System.arraycopy(x.getBoardPlayer2(), 0, this.board2, 0, boardSize);
        System.arraycopy(x.getBoardPlayer1(), 0, this.board1, 0, boardSize);
        mancala2=x.getMancalaPlayer2();
        mancala1=x.getMancalaPlayer1();
        getAnotherTurn=x.getGetAnotherTurn();
    }  
    
    //Getters and Setters
    public int getBoardSize(){
        return boardSize;
    }
    
    public void setBoardSize(int boardSize){
        this.boardSize=boardSize;
    }
    
    public int getPlayer(){
        return player;
    }
    
    public void setPlayer(int player){
        this.player=player;
    }
    
    public int[] getBoardPlayer1(){
        return board1;
    }
    
    public void setBoardPlayer1(int[] board1){
        System.arraycopy(board1, 0, this.board1, 0, boardSize);
    }
    
    public int[] getBoardPlayer2(){
        return board2;
    }
    
    public void setBoardPlayer2(int[] board2){
        System.arraycopy(board2, 0, this.board2, 0, boardSize);
    }
    
    public int getMancalaPlayer1(){
        return mancala1;
    }
    
    public void setMancalaPlayer1(int mancala1){
        this.mancala1=mancala1;
    }    
    
    public int getMancalaPlayer2(){
        return mancala2; 
    }
    
    public void setMancalaPlayer2(int mancala2){
        this.mancala2=mancala2;
    }
    
    public boolean getGetAnotherTurn(){
        return getAnotherTurn;
    }
    
    public void setGetAnotherTurn(boolean getAnotherTurn){
        this.getAnotherTurn=getAnotherTurn;
    }
}
