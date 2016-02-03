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
public class Mancala {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException{
        // TODO code application logic here
        FileInputStream fileInputStream=null;
        InputStreamReader inputStreamReader=null;
        BufferedReader bufferedReader=null;
        
        try{
            fileInputStream=new FileInputStream("input.txt");
            inputStreamReader=new InputStreamReader(fileInputStream);
            bufferedReader=new BufferedReader(inputStreamReader);
            
            //Read input file
            int task=Integer.parseInt(bufferedReader.readLine().trim());
            int player=Integer.parseInt(bufferedReader.readLine().trim());
            int cuttingDepth=Integer.parseInt(bufferedReader.readLine().trim());
            
            String[] boardPlayer2=bufferedReader.readLine().trim().split(" ");
            String[] boardPlayer1=bufferedReader.readLine().trim().split(" ");
            
            int boardSize=boardPlayer2.length;
            int[] board2=new int[boardSize];
            int[] board1=new int[boardSize];
            
            for(int i=0;i<boardSize;i++){
                board2[i]=Integer.parseInt(boardPlayer2[i].trim());
                board1[i]=Integer.parseInt(boardPlayer1[i].trim());
            }
            
            int mancala2=Integer.parseInt(bufferedReader.readLine().trim());
            int mancala1=Integer.parseInt(bufferedReader.readLine().trim());
            
            Game g=new Game(player, board2, board1, mancala2, mancala1);
            Utility util=new Utility();
            
            
            //Call Greedy or Minimax or AlphaBeta method based on value specified in input
            if(!util.GameOver(g)){                
                switch(task){
                    case 1:
                        Greedy gr=new Greedy();
                        gr.greedy(g);
                        break;
                    case 2:
                        Minimax mm=new Minimax(cuttingDepth);
                        mm.minimax(g);
                        //mm.writerNextState();
                        //mm.writeTraverseLog();
                        break;
                    case 3:
                        AlphaBeta ab=new AlphaBeta(cuttingDepth);
                        ab.alphabeta(g);
                        break;
                    case 4:
                        //competition
                        break;
                }
            }
        }
        catch(Exception ex){
            System.out.println("Exception occured : "+ex.getMessage());
        }
        finally{      
            bufferedReader.close();
            inputStreamReader.close();
            fileInputStream.close();
        }
    }
}
