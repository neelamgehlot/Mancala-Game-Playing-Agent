/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mancala;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author Neelam
 * 
 */

//Node class to represent game state
public class Node{
    String name;
    Game game=null;
    double eval;
    double alpha;
    double beta;
    
    int depth;
    boolean max;
    Node parent;
    List<Node> children;
    
    //Default Constructor
    public Node(){
        this.alpha=Double.NEGATIVE_INFINITY;
        this.beta=Double.POSITIVE_INFINITY;
        this.children=new LinkedList<Node>();
    }
    
    //Constructor for Minimax method
    public Node(String name, Game game, double eval, int depth, boolean max, Node parent){
        this.name=name;
        this.game=new Game(game);
        this.eval=eval;
        this.alpha=Double.NEGATIVE_INFINITY;
        this.beta=Double.POSITIVE_INFINITY;
        this.depth=depth;
        this.max=max;
        this.parent=parent;
        this.children=new LinkedList<Node>();
    }
    
    //Constructor for alpha beta method
    public Node(String name, Game game, double eval, double alpha, double beta, int depth, boolean max, Node parent){
        this.name=name;
        this.game=new Game(game);
        this.eval=eval;
        this.alpha=alpha;
        this.beta=beta;
        this.depth=depth;
        this.max=max;
        this.parent=parent;
        this.children=new LinkedList<Node>();
    }
    
    //Copy Constructor
    public Node(Node x){
        name=x.name;
        game=new Game(x.game);
        eval=x.eval;
        alpha=x.alpha;
        beta=x.beta;
        depth=x.depth;
        max=x.max;
        parent=x.parent;
        children=new LinkedList<Node>();
        ListIterator<Node> listIterator=x.children.listIterator();
        while(listIterator.hasNext()){
            children.add(listIterator.next());
        }
    }
}



