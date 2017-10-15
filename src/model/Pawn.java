package model;

import view.ChessBoard;

import java.util.ArrayList;

public class Pawn extends Piece {

	public Pawn(int img, int row, int col) {
		super(img, row, col);

	}

	@Override
	public boolean checkCanMove(int toX, int toY) {
		return super.checkCanMove(toX, toY);
	}

	@Override
	public Pawn copyOfPiece() {
		Pawn pieceCopy = new Pawn(super.getImage(), super.getX(), super.getY());
		return pieceCopy;
	}

	@Override
	public boolean checkRules(int toX, int toY, boolean isPieceHere) {
		int x = super.getX();
		int y = super.getY();
		byte type = super.getType();
//		System.out.println("Vi tri den " + toX + " " + toY + " Player: " + type);
		if (type == 0) {
			if (toY >= 0 && toY <= 4) {
				if (toY == y + 1 && toX == x) {
					return true;
				}
			}
			if (toY > 4 && toY <= 9) {
				if ((toY == y + 1 && toX == x) || (toY == y && toX == x + 1) || (toY == y && toX == x - 1)) {
					if (toX >= 0 && toX <= 8) {
						return true;
					}
				}
			}
		}
		if (type == 1) {
			if (toY >= 5 && toY <= 9) {
				if (toY == y - 1 && toX == x) {
					return true;
				}
			}
			if (toY >= 0 && toY <= 4) {
				if ((toY == y - 1 && toX == x) || (toY == y && toX == x + 1) || (toY == y && toX == x - 1)) {
					if (toX >= 0 && toX <= 8) {
						return true;
					}
				}
			}
		}
		return false;
	}

	@Override
	public ArrayList<int[]> generateNextMoves(ChessBoard chessBoard){
		int[][] targets = new int[][]{{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
		return this.getMovable(targets);
	};
}
