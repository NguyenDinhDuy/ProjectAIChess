package model;

import view.ChessBoard;

import java.util.ArrayList;

public class Chariot extends Piece{

	public Chariot(int img, int row, int col) {
		super(img,row,col);
		
	}

	@Override
	public boolean checkCanMove( int toX, int toY) {
		// TODO Auto-generated method stub
		return super.checkCanMove( toX, toY);
	}
	
	@Override
	public Chariot copyOfPiece() {
		Chariot pieceCopy=new Chariot(super.getImage(), super.getX(), super.getY());
		return pieceCopy;
	}

	@Override
	public boolean checkRules(int toX, int toY, boolean isPieceHere) {
		int x = super.getX();
		int y = super.getY();
		if (toX < 0 || toX > 8 || toY < 0 || toY > 9) {
            return false;
        }
        if (toX != x && toY != y) {
            return false;
        }
        if (toX == x && toY >= 0 && toY <= 9) {
            if (toY > y) {
                for (int k = y + 1; k < toY; k++) {
                    if (ManagePieces.searchPiece(toX, k) != null) {
                        return false;
                    }
                }
            }

            if (toY < y) {
                for (int k = toY + 1; k <= y - 1; k++) {
                    if (ManagePieces.searchPiece(toX,k) != null) {
                        return false;
                    }
                }
            }
        }
        if (toY == y && toX >= 0 && toX <= 8) {
            if (toX > x) {
                for (int k = x + 1; k < toX; k++) {
                    if (ManagePieces.searchPiece(k,toY) != null) {
                        return false;
                    }
                }
            }
            if (toX < x) {
                for (int k = toX + 1; k <= x - 1; k++) {
                    if (ManagePieces.searchPiece(k,toY)!= null) {
                        return false;
                    }
                }
            }
        }
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
