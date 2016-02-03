/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mancala;

import java.io.*;
/**
 *
 * @author Neelam
 */

//Get best move based on Greedy technique
public class Greedy {
    Utility util=new Utility();
    
    public void greedy(Game ga){
        Game move=getAllMoves(ga);
        try{
            util.writeNextState(move);
        }
        catch(Exception ex){
            System.out.println("Exception in Greedy : "+ex.getMessage());
        }
    }
    
    //Get all next valid moves and select the best one based on highest value of eval
    public Game getAllMoves(Game ga){
        double maxEval=Double.NEGATIVE_INFINITY;
        Game move=new Game(ga);
        int pit=-1;
        for(int i=0;i<ga.getBoardSize();i++){
            Game temp=new Game(ga);
            if(!util.GameOver(temp)){
                if(temp.getPlayer()==1){
                    int[] localBoard1=new int[temp.getBoardSize()];
                    System.arraycopy(temp.getBoardPlayer1(), 0, localBoard1, 0, temp.getBoardSize());
                    if(localBoard1[i]>0){
                        temp=new Game(util.moveStones(temp, temp.getPlayer(), i));
                        if(temp.getGetAnotherTurn()){
                            temp=new Game(getAllMoves(temp));
                        }
                        if(maxEval<util.eval(temp.getPlayer(), temp.getMancalaPlayer1(),temp.getMancalaPlayer2())){
                            maxEval=util.eval(temp.getPlayer(), temp.getMancalaPlayer1(),temp.getMancalaPlayer2());
                            pit=i;
                            move=new Game(temp);
                            if(util.GameOver(move))
                                break;
                        }
                    }
                }
                else{
                    int[] localBoard2=new int[temp.getBoardSize()];
                    System.arraycopy(temp.getBoardPlayer2(), 0, localBoard2, 0, temp.getBoardSize());
                    if(localBoard2[i]>0){
                        temp=new Game(util.moveStones(temp, temp.getPlayer(), i));
                        if(temp.getGetAnotherTurn()){
                            temp=new Game(getAllMoves(temp));
                        }
                        if(maxEval<util.eval(temp.getPlayer(), temp.getMancalaPlayer1(),temp.getMancalaPlayer2())){
                            maxEval=util.eval(temp.getPlayer(), temp.getMancalaPlayer1(),temp.getMancalaPlayer2());
                            pit=i;
                            move=new Game(temp);
                            if(util.GameOver(move))
                                break;
                        }
                    }
                }
                
            }
        }
        return move;
    }
}
