package controller;

import java.util.ArrayList;

import model.Piece;
import view.ChessBoard;

/**
 * Created by quanpv on 25/10/2016.
 * min, max alphabet pruning search model
 */

public class SearchModel {

    private static int DEPTH = 2;
    private static ChessBoard chessBoard;

    public TreeNode search(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
        TreeNode bestNode = null;

        ArrayList<TreeNode> allMovable = generateMovesForAll(true);

        for (TreeNode node : allMovable) {
            Piece eaten = chessBoard.getManagePiece().getPiece(node.to);
           //Move piece to the new position, update location and remove other if exist in that location
            if (chessBoard.getManagePiece().getPiece(node.to)!=null){
                eaten = chessBoard.getManagePiece().getPiece(node.to);
            }
            chessBoard.getManagePiece().movePiece(node.piece, node.to);
            node.value = alphaBeta(DEPTH, Integer.MIN_VALUE, Integer.MAX_VALUE, false);

            // Select a best move during searching to save time
            if (bestNode == null || node.value >= bestNode.value) {
                bestNode = node;
            }

            // virtual move back to start location that before we visit it
            chessBoard.getManagePiece().movePiece(node.piece, node.from);

            if (eaten != null) {
                chessBoard.getManagePiece().getListPiece().add(eaten);
            }
        }
        return bestNode;
    }

    /**
     *
     * @param depth the limited dept of tree that we search for wanted value
     * @param alpha contain the value of the maximum of the current node
     * @param beta contain the value of the miniimum of the current node
     * @param isMax the next node search is max or not
     * @return  maximum value for the current node
     */
    private int alphaBeta(int depth, int alpha, int beta, boolean isMax) {
        /* Return evaluation if reaching leaf node or any side won.*/
        if (depth == 0 || chessBoard.hasWin()!=0)
            return new EvaluateModel().evaluate(chessBoard, Player.BLACK_PLAYER);

        ArrayList<TreeNode> moves = generateMovesForAll(isMax);

        synchronized (this) {
            for (final TreeNode node : moves) {
                Piece eaten = null;
                if(chessBoard.getManagePiece().getPiece(node.to)!=null){
                    eaten = chessBoard.getManagePiece().getPiece(node.to);
                };
                chessBoard.getManagePiece().movePiece(node.piece, node.to[0], node.to[1]);

                final int finalBeta = beta;
                final int finalAlpha = alpha;
                final int finalDepth = depth;
                final int[] temp = new int[1];

                if (depth == 2) {
                    if (isMax) {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                temp[0] = Math.max(finalAlpha, alphaBeta(finalDepth - 1, finalAlpha, finalBeta, false));
                            }
                        }).run();
                        alpha = temp[0];
                    } else {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                temp[0] = Math.min(finalBeta, alphaBeta(finalDepth - 1, finalAlpha, finalBeta, true));
                            }
                        }).run();
                        beta = temp[0];
                    }
                }
                else {
                    if (isMax) alpha = Math.max(alpha, alphaBeta(depth - 1, alpha, beta, false));
                    else beta = Math.min(beta, alphaBeta(depth - 1, alpha, beta, true));
                }
                chessBoard.getManagePiece().movePiece(node.piece, node.from);

                if (eaten != null) {
                    chessBoard.getManagePiece().getListPiece().add(eaten);
                }
            /* Cut-off */
                if (beta <= alpha) break;
            }
        }
        return isMax ? alpha : beta;
    }

    /**
     * Generate all move that player can do in the next step.
     * @param isMax determine generate moves for player that is maximum player
     * @return
     */
    private ArrayList<TreeNode> generateMovesForAll(boolean isMax) {

        ArrayList<TreeNode> moves = new ArrayList<>();

        for (Piece piece : chessBoard.getManagePiece().getListPiece()) {

            if (isMax && piece.getType() == Player.RED_PLAYER)
                continue;
            if (!isMax && piece.getType() == Player.BLACK_PLAYER)
                continue;

            int[] currentPosition;
            for (int[] nextPosition : piece.generateNextMoves(chessBoard)) {
                currentPosition = new int[]{ piece.getX(), piece.getY() };
                moves.add(new TreeNode(piece, currentPosition, nextPosition));
            }
        }
        return moves;
    }
}
