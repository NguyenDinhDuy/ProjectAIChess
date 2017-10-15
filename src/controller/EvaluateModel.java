package controller;

import model.*;
import view.ChessBoard;

/**
 * Created by quanpv on 25/10/2016.
 */

public class EvaluateModel {

    public static int evaluate(ChessBoard chessBoard, int player) {

        int valuePositionRed = 0;
        int valuePieceRed = 0;
        int valuePieceBlack = 0;
        int valuePositionBlack = 0;

        for (Piece piece : chessBoard.getManagePiece().getListPiece()) {
            if (piece.getType()==Player.RED_PLAYER){
                valuePieceRed += evaluatePieceValue(piece);
                valuePositionRed += evaluatePosition(piece, Player.RED_PLAYER);
            } else if(piece.getType()==Player.BLACK_PLAYER) {
                valuePieceBlack += evaluatePieceValue(piece);
                valuePositionBlack+=evaluatePosition(piece, Player.BLACK_PLAYER);
            }
        }

        int redEval = valuePieceRed + valuePositionRed;
        int blackEval = valuePieceBlack + valuePositionBlack;
        return (player == Player.RED_PLAYER) ? (redEval - blackEval): (blackEval - redEval);
    }

    /*
     *  evaluate for value of King, Advisor, Elephant, Chariot, Cannon, Horse, Pawn.
     *  @return pieceValue base on type.
     */

    private static int evaluatePieceValue(Piece piece) {

        int[] pieceValue = new int[]{6000, 120, 120, 600, 270, 285, 30};

        if (piece instanceof Pawn)
            return pieceValue[6];
        else if (piece instanceof Advisor)
            return pieceValue[1];
        if (piece instanceof Elephant)
            return pieceValue[2];
        if (piece instanceof Chariot)
            return pieceValue[3];
        if (piece instanceof Cannon)
            return pieceValue[4];
        if (piece instanceof Horse)
            return pieceValue[5];
        if(piece instanceof King)
            return pieceValue[0];
        return 0;
    }

    /*
     * Evaluate piece value based on location in Chess Boad for red player
     * if evaluate for black player, change positsion of back inverse
     */
    private static int evaluatePosition(Piece piece, int player) {

        // matrix contain value for each piece corresponding to position in the Chess Board
        int[][] chariotPosition = new int[][]{
                {14,  14,  12,   18,  16,   18,  12,  14,  14},
                {16,  20,  18,   24,  26,   24,  18,  20,  16},
                {12,  12,  12,   18,  18,   18,  12,  12,  12},
                {12,  18,  16,   22,  22,   22,  16,  18,  12},
                {12,  14,  12,   18,  18,   18,  12,  14,  12},
                {12,  16,  14,   20,  20,   20,  14,  16,  12},
                { 6,  10,   8,   14,  14,   14,   8,  10,   6},
                { 4,   8,   6,   14,  12,   14,   6,   8,   4},
                { 8,  4,    8,   16,   8,   16,   8,   4,   8},
                {-2,  10,   6,   14,  12,   14,   6,  10,  -2}
        };

        int[][] cannonPosition = new int[][]{
                { 6,  4,  0,  -10,  -12,   -10,    0,   4,   6},
                { 2,  2,  0,   -4,  -14,    -4,    0,   2,   2},
                { 2,  2,  0,  -10,   -8,   -10,    0,   2,   2},
                { 0,  0, -2,    4,   10,     4,   -2,   0,   0},
                { 0,  0,  0,    2,    8,     2,    0,   0,   0},
                {-2,  0,  4,    2,    6,     2,    4,   0,  -2},
                { 0,  0,  0,    2,    4,     2,    0,   0,   0},
                { 4,  0,  8,    6,   10,     6,    8,   0,   4},
                { 0,  2,  4,    6,    6,     6,    4,   2,   0},
                { 0,  0,  2,    6,    6,     6,    2,   0,   0}
        };

        int[][] horsePosition = new int[][]{
                {4,   8,  16,   12,   4,  12,  16,   8,  4},
                {4,  10,  28,   16,   8,  16,  28,  10,  4},
                {12, 14,  16,   20,  18,  20,  16,  14, 12},
                {8,  24,  18,   24,  20,  24,  18,  24,  8},
                {6,  16,  14,   18,  16,  18,  14,  16,  6},
                {4,  12,  16,   14,  12,  14,  16,  12,  4},
                {2,   6,   8,    6,  10,   6,   8,   6,  2},
                {4,   2,   8,    8,   4,   8,   8,   2,  4},
                {0,   2,   4,    4,  -2,   4,   4,   2,  0},
                {0,  -4,   0,    0,   0,   0,   0,  -4,  0}
        };

        int[][] pawnPosition = new int[][]{
                { 0,  3,   6,   9,  12,   9,  6,    3,   0},
                {18, 36,  56,  80, 120,  80, 56,   36,  18},
                {14, 26,  42,  60,  80,  60, 42,   26,  14},
                {10, 20,  30,  34,  40,  34, 30,   20,  10},
                { 6, 12,  18,  18,  20,  18, 18,   12,   6},
                { 2,  0,   8,   0,   8,   0,  8,    0,   2},
                { 0,  0,  -2,   0,   4,   0, -2,    0,   0},
                { 0,  0,   0,   0,   0,   0,  0,    0,   0},
                { 0,  0,   0,   0,   0,   0,  0,    0,   0},
                { 0,  0,   0,   0,   0,   0,  0,    0,   0}
        };
        // check for each name of piece and return value based on position
        if( player==Player.RED_PLAYER){
            if (piece instanceof Pawn){
                return pawnPosition[piece.getY()] [piece.getX()];
            } else if (piece instanceof Chariot){
                return chariotPosition[piece.getY()] [piece.getX()];
            } else if (piece instanceof Cannon) {
                return cannonPosition[piece.getY()] [piece.getX()];
            } else if (piece instanceof  Horse){
                return horsePosition[piece.getY()] [piece.getX()];
            }
        } else if(player==Player.BLACK_PLAYER) {
            // inverse location of chess for matrix  correspond, only change Y axis
            int pieceY = ChessBoard.MAX_ROW -1 - piece.getY();

            if (piece instanceof Pawn){
                return pawnPosition[pieceY] [piece.getX()];
            } else if (piece instanceof Chariot){
                return chariotPosition[pieceY] [piece.getX()];
            } else if (piece instanceof Cannon) {
                return cannonPosition[pieceY] [piece.getX()];
            } else if (piece instanceof  Horse){
                return horsePosition[pieceY] [piece.getX()];
            }
        }
        return 0;

    }

    private int evalPieceControl() {
        return 0;
    }

    private static int evalPieceFlexible(int p) {
        int[] pieceFlexible = new int[]{0, 1, 1, 13, 7, 7, 15};
        return 0;
    }

    private static int evalPieceProtect() {
        return 0;
    }

    private static int evalPieceFeature() {
        return 0;
    }

    public static boolean isGameOver(){
        return false;
    }
}
