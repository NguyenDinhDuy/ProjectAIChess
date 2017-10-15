package controller;

/**
 * Created by quanpv on 25/10/2016.
 */

import model.Piece;

public class TreeNode {
    public Piece piece;
    public int[] from;
    public int[] to;
    public int value;

    public TreeNode(){

    }

    public TreeNode(Piece piece, int[] from, int[] to) {
        this.piece = piece;
        this.from = from;
        this.to = to;
    }

    public void printInfo(){
        System.out.print( piece.getClass() + "\tfrom : ( " +from[0] +  ", " + from[1]);
        System.out.println(") to ( " +to[0] +  ", " + to[1] + ")");
    }

}
