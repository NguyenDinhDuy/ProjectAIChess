package controller;

/**
 * Created by quanpv on 26/10/2016.
 */

public class Player {

    public static final int BLACK_PLAYER = 0;
    public static final int RED_PLAYER = 1;
    public static int currentPlayer = Player.RED_PLAYER;

    public static void inverseCurrentPlayer(){
        if (currentPlayer==RED_PLAYER) {
            currentPlayer = BLACK_PLAYER;
        } else if (currentPlayer==BLACK_PLAYER){
            currentPlayer = RED_PLAYER;
        }
    }
}
