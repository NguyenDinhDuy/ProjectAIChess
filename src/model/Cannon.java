package model;

import view.ChessBoard;

import java.util.ArrayList;

public class Cannon extends Piece {

	public Cannon(int img, int row, int col) {
		super(img, row, col);

	}

	@Override
	public boolean checkCanMove(int toX, int toY) {
		// TODO Auto-generated method stub
		return super.checkCanMove(toX, toY);
	}

	@Override
	public Cannon copyOfPiece() {
		Cannon cannon = new Cannon(super.getImage(), super.getX(), super.getY());
		return cannon;
	}

	@Override
	public boolean checkRules(int toX, int toY, boolean isPieceHere) {
		// TODO Auto-generated method stub
		int x = super.getX();
		int y = super.getY();

		int count = 0;
		if (toX == x && toY == y) {
			return false;
		}
		if (toY < 0 || toY > 9 || toX < 0 || toX > 8) {
			return false;
		}

		if (toX >= 0 && toX <= 8 && toY >= 0 && toY <= 9 ) {
			if (toX == x) {
				if (toY > y) {
					for (int k = y + 1; k < toY; k++) {
						if (ManagePieces.searchPiece(toX, k) != null) {
							count++;
						}
					}
				} else if (toY < y) {
					for (int k = toY + 1; k <= y - 1; k++) {
						if (ManagePieces.searchPiece(toX, k) != null) {
							count++;
						}
					}
				}
//				 System.out.println(count);
				if (count == 0)
					if (isPieceHere == true)
						return false;
				if (count == 1)
					if (isPieceHere == false)
						return false;
				if (count >= 2) {
					return false;
				}
			}
			else
			if (toY == y) {
				if (toX > x) {
					for (int k = x + 1; k < toX; k++) {
						if (ManagePieces.searchPiece(k, toY) != null) {
							count++;
						}
					}
				}
				if (toX < x) {
					for (int k = toX + 1; k < x; k++) {
						if (ManagePieces.searchPiece(k, toY) != null) {
							count++;
						}
					}
				}
//				 System.out.println("Code trong" + count);
				if (count == 0)
					if (isPieceHere == true)
						return false;
				if (count == 1)
					if (isPieceHere == false)
						return false;
				if (count >= 2) {
					return false;
				}
			}
			else return false; 
		}
//		System.out.println(count);
		return true;
	}

	@Override
	public ArrayList<int[]> generateNextMoves(ChessBoard chessBoard){
		int[][] targets = new int[][]{
				{0, 1}, {0, 2}, {0, 3}, {0, 4},{0, 5}, {0, 6}, {0, 7}, {0, 8}, {0, 9},
				{0, -1}, {0, -2}, {0, -3}, {0, -4},{0, -5}, {0, -6}, {0, -7}, {0, -8}, {0, -9},
				{1, 0}, {2, 0}, {3, 0}, {4, 0},{5, 0}, {6, 0}, {7, 0}, {8, 0},
				{-1, 0}, {-2, 0}, {-3, 0}, {-4, 0},{-5, 0}, {-6, 0}, {-7, 0}, {-8, 0},
		};
		return this.getMovable(targets);
	};
}
