package model;

import view.ChessBoard;

import java.util.ArrayList;

public class Advisor extends Piece {

	public Advisor(int img, int row, int col) {
		super(img, row, col);
	}

	@Override
	public boolean checkCanMove(int toX, int toY) {
		return super.checkCanMove(toX, toY);
	}

	@Override
	public Advisor copyOfPiece() {
		Advisor advisor = new Advisor(super.getImage(), super.getX(), super.getY());
		return advisor;
	}

	@Override
	public boolean checkRules(int toX, int toY, boolean isPieceHere) {
		int x = super.getX();
		int y = super.getY();
		if (toX < 3 || toX > 5) {
            return false;
        }
        if ((toX == x + 1 && toY == y + 1) || (toX == x + 1 && toY == y - 1) ||
				(toX == x - 1 && toY == y - 1) || (toX == x - 1 && toY == y + 1)) {
            if ((toY >= 0 && toY <= 2 && toX >= 3 && toX <= 5) || (toY >= 7 && toY <= 9 && toX >= 3 && toX <= 5))    
                    return true;
        }
        return false;
	}

	@Override
    public ArrayList<int[]> generateNextMoves(ChessBoard chessBoard){
        int[][] targets = new int[][]{{1, 1}, {1, -1}, {-1, -1}, {-1, 1}};
        return this.getMovable(targets);
    };
}
