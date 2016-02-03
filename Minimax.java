/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mancala;

import java.io.*;
import java.util.ListIterator;

/**
 *
 * @author Neelam
 */
/* Get next best move based on Minimax technique
 * And traverse log shows the exploration of game 
 * tree in order to select best next move.
 */
public class Minimax {
    Utility util=new Utility();
    private final int depth;
    private static int count=0;
    
    public Minimax(int depth){
        this.depth=depth;
    }
    
    //Game tree exploration
    public void minimax(Game g){
        try{
            FileOutputStream fileOutputStreamTraverseLog=new FileOutputStream("traverse_log.txt");
            OutputStreamWriter outputStreamWriterTraverseLog=new OutputStreamWriter(fileOutputStreamTraverseLog);
            BufferedWriter bufferedWriterTraverseLog=new BufferedWriter(outputStreamWriterTraverseLog);

            bufferedWriterTraverseLog.write("Node,Depth,Value");
            bufferedWriterTraverseLog.newLine();
            
            Node root=new Node("root", g, Double.NEGATIVE_INFINITY, 0, true, null);
            
            getAllMoves(root, bufferedWriterTraverseLog);
            //setValuesAndTraverse(root, bufferedWriterTraverseLog);
            util.nextState(root);
            
            bufferedWriterTraverseLog.close();
            outputStreamWriterTraverseLog.close();
            fileOutputStreamTraverseLog.close();  
        }
        catch(Exception ex){
            System.out.println("Exception in Minimax : "+ex.getMessage());
        }
    }
    
    /* Get all the valid moves based on current state
     * and select next best move
    */
    public void getAllMoves(Node n, BufferedWriter bw) throws IOException{
        count=n.depth;
        if(count>=depth && !n.game.getGetAnotherTurn()){
            n.eval=util.eval(n.game.getPlayer(), n.game.getMancalaPlayer1(), n.game.getMancalaPlayer2());
            bw.write(n.name+","+n.depth+","+util.evalToString(n.eval));
            bw.newLine();            
            return;
        }
        boolean valid=false;
        if(count==depth && n.game.getGetAnotherTurn())
            valid=true;
        while((count<depth || valid) && !util.GameOver(n.game)){
            bw.write(n.name+","+n.depth+","+util.evalToString(n.eval));
            bw.newLine();
            util.expansion(n);
            valid=false;
            ListIterator<Node> listIterator=n.children.listIterator();
            while(listIterator.hasNext()){
                Node temp=listIterator.next();
                getAllMoves(temp, bw);
                if(n.max){  
                    if(n.eval < temp.eval)
                        n.eval=temp.eval;
                }
                else{
                    if(n.eval > temp.eval)
                        n.eval=temp.eval;
                }
                bw.write(n.name+","+n.depth+","+util.evalToString(n.eval));
                bw.newLine();
            }
            count++;
        }
    }      
}
