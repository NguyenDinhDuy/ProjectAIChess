package model;

import view.ChessBoard;

import java.util.ArrayList;

public class Horse extends Piece{

	public Horse(int img, int x, int y) {
		super(img,x,y);
		
		// Matrix values
		setBlackValues(new int[][]{
            {4, 8, 16, 12, 4, 12, 16, 8, 4},
            {4, 10, 28, 16, 8, 16, 28, 10, 4},
            {12, 14, 16, 20, 18, 20, 16, 14, 12},
            {8, 24, 18, 24, 20, 24, 18, 24, 8},
            {6, 16, 14, 18, 16, 18, 14, 16, 6},
            {4, 12, 16, 14, 12, 14, 16, 12, 4},
            {2, 6, 8, 6, 10, 6, 8, 6, 2},
            {4, 2, 8, 8, 4, 8, 8, 2, 4},
            {0, 2, 4, 4, -2, 4, 4, 2, 0},
            {0, -4, 0, 0, 0, 0, 0, -4, 0}
        });
        
    
    
		setRedValues(new int[][]{
            {0, -4, 0, 0, 0, 0, 0, -4, 0},
            {0, 2, 4, 4, -2, 4, 4, 2, 0},
            {4, 2, 8, 8, 4, 8, 8, 2, 4},
            {2, 6, 8, 6, 10, 6, 8, 6, 2},
            {4, 12, 16, 14, 12, 14, 16, 12, 4},
            {6, 16, 14, 18, 16, 18, 14, 16, 6},
            {8, 24, 18, 24, 20, 24, 18, 24, 8},
            {12, 14, 16, 20, 18, 20, 16, 14, 12},
            {4, 10, 28, 16, 8, 16, 28, 10, 4},
            {4, 8, 16, 12, 4, 12, 16, 8, 4}
        });
	}


    // Kiểm tra có thể di chuyển tới ?
	@Override
	public boolean checkCanMove( int toX, int toY) {
		return super.checkCanMove( toX, toY);
	}
	
	@Override
	public Horse copyOfPiece() {
		Horse pieceCopy = new Horse(super.getImage(), super.getX(), super.getY());
		return pieceCopy;
	}

	@Override
	public boolean checkRules(int toX, int toY, boolean isPieceHere ) {

		int x = super.getX();
		int y = super.getY();
		if (toX < 0 || toX > 8 || toY < 0 || toY > 9) {
            return false;
        }
        if (toX == x && toY == y) {
            return false;
        }
        // Truong hop sang trai
        if (x - 2 >= 0 && x - 2 <= 8 && ((y - 1 >= 0 && y - 1 <= 9) || (y + 1 >= 0 && y + 1 <= 9))) {
            if (toX == x - 2 && (toY == y - 1 || toY == y + 1)) {
                if (ManagePieces.searchPiece(x - 1,y) == null) {
                        return true;//                    
                    
                }
            }
        }
//        // Truong hop di sang phải
        if (x + 2 >= 0 && x + 2 <= 8 && ((y - 1 >= 0 && y - 1 <= 9) || (y + 1 >= 0 && y + 1 <= 9))) {
            if (toX == x + 2 && (toY == y - 1 || toY == y + 1)) {
                if (ManagePieces.searchPiece(x + 1,y) == null) {
                        return true;
                }
            }
        }
//        // Truong hop di len
        if (y - 2 >= 0 && y - 2 <= 9 && ((x - 1 >= 0 && x - 1 <= 8) || (x + 1 >= 0 && x + 1 <= 8))) {
            if (toY == y - 2 && (toX == x - 1 || toX == x + 1)) {
                if (ManagePieces.searchPiece(x,y - 1) == null) {
                        return true;
                }
            }
        }
//        // TH sang di xuong
        if (y + 2 >= 0 && y + 2 <= 9 && ((x - 1 >= 0 && x - 1 <= 8) || (x + 1 >= 0 && x + 1 <= 8))) {
            if (toY == y + 2 && (toX == x - 1 || toX == x + 1)) {
                if (ManagePieces.searchPiece(x,y + 1)== null) {
                        return true;

                }
            }

        }
		return false;
	}

    @Override
    public ArrayList<int[]> generateNextMoves(ChessBoard chessBoard){
        int[][] targets = new int[][]{{1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}, {-2, -1}, {-2, 1 }, {-1, 2}};
        return this.getMovable(targets);
    };
}
