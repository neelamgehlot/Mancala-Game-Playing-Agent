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

/* Get next best move based on Alpha Beta technique
 * And traverse log shows the exploration of game 
 * tree in order to select best next move.
 */
public class AlphaBeta {
    Utility util=new Utility();
    private final int depth;
    private static int count=0;
    
    public AlphaBeta(int depth){
        this.depth=depth;
    }
    
    
    //Game tree exploration
    public void alphabeta(Game g) throws IOException{
        try{
            FileOutputStream fileOutputStreamTraverseLog=new FileOutputStream("traverse_log.txt");
            OutputStreamWriter outputStreamWriterTraverseLog=new OutputStreamWriter(fileOutputStreamTraverseLog);
            BufferedWriter bufferedWriterTraverseLog=new BufferedWriter(outputStreamWriterTraverseLog);

            bufferedWriterTraverseLog.write("Node,Depth,Value,Alpha,Beta");
            bufferedWriterTraverseLog.newLine();
            
            Node root=new Node("root", g, Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, 0, true, null);

            getAllMoves(root,bufferedWriterTraverseLog);
            util.nextState(root);
            
            bufferedWriterTraverseLog.close();
            outputStreamWriterTraverseLog.close();
            fileOutputStreamTraverseLog.close();
        }
        catch(Exception ex){
            System.out.println("Exception in AlphaBeta : "+ex.getMessage());
        }
    }
    
    /* Get all the valid moves based on current state
     * and select next best move
    */
    public void getAllMoves(Node n, BufferedWriter bw) throws IOException{
        count=n.depth;
        //Leaf Node
        if(count>=depth && !n.game.getGetAnotherTurn()){
            n.eval=util.eval(n.game.getPlayer(), n.game.getMancalaPlayer1(), n.game.getMancalaPlayer2());
            bw.write(n.name+","+n.depth+","+util.evalToString(n.eval)+","+util.evalToString(n.alpha)+","+util.evalToString(n.beta));
            bw.newLine();            
            return;
        }
        
        boolean valid=false;
        if(count==depth && n.game.getGetAnotherTurn())
            valid=true;
        while((count<depth || valid) && !util.GameOver(n.game)){
            bw.write(n.name+","+n.depth+","+util.evalToString(n.eval)+","+util.evalToString(n.alpha)+","+util.evalToString(n.beta));
            bw.newLine();
            util.expansion(n);
            valid=false;
            ListIterator<Node> listIterator=n.children.listIterator();
            while(listIterator.hasNext()){
                Node temp=listIterator.next();
                temp.alpha=n.alpha;
                temp.beta=n.beta;
                getAllMoves(temp,bw);
                if(n.max){
                    if(temp.eval>n.eval)
                        n.eval=temp.eval;
                    if(!prun(n)){
                        if(temp.eval>n.alpha)
                            n.alpha=temp.eval;
                    }
                }
                else{
                    if(temp.eval<n.eval)
                        n.eval=temp.eval; 
                    if(!prun(n)){
                        if(temp.eval<n.beta)
                            n.beta=temp.eval;
                    }
                }
                bw.write(n.name+","+n.depth+","+util.evalToString(n.eval)+","+util.evalToString(n.alpha)+","+util.evalToString(n.beta));
                bw.newLine();
                if(prun(n))
                    break;
            }
            count++;
        }   
    }
    
    //Check pruning condition
    public boolean prun(Node n){
        if(n.max){
            if(n.beta<=n.eval){ //Pruning
                return true;
            }
            else{
                return false;
            }
        }
        else{
            if(n.alpha>=n.eval){ //Pruning
                return true;
            }
            else{
                return false;
            }
        }
    }
}

