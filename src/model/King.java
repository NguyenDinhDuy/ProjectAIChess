package model;

import view.ChessBoard;

import java.util.ArrayList;

public class King  extends Piece{

	public King(int img, int row, int col) {
		super(img,row,col);
		
	}
	
	@Override
	public boolean checkCanMove( int toX, int toY) {
		// TODO Auto-generated method stub
		return super.checkCanMove( toX, toY);
	}

	@Override
	public King copyOfPiece() {
		King pieceCopy=new King(super.getImage(), super.getX(), super.getY());
		return pieceCopy;
	}

	@Override
	public boolean checkRules(int toX, int toY, boolean isPieceHere) {
		int x = super.getX();
		int y = super.getY();
		
		if (toX == x && toY == y) {
            return false;
        }
        if (toX >= 3 && toX <= 5) {
            if ((toY >= 0 && toY <= 2) || (toY >= 7 && toY <= 9)) {
                if ((toY == y + 1 && toX == x)
                        || (toY == y - 1 && toX == x)
                        || (toY == y && toX == x + 1)
                        || (toY == y && toX == x - 1)) {
                    
                        return true;
                   
                }
            }
        }
        return false;
	}

	@Override
	public ArrayList<int[]> generateNextMoves(ChessBoard chessBoard){
		// relative positsion from current locate.
		int[][] targets = new int[][]{{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
		return this.getMovable(targets);
	};
}
