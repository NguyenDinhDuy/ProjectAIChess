package model;

import view.ChessBoard;

import java.util.ArrayList;

public class Elephant extends Piece{

	public Elephant(int img, int row, int col) {
		super(img,row,col);
	
	}
	
	@Override
	public boolean checkCanMove( int toX, int toY) {
		// TODO Auto-generated method stub
		return super.checkCanMove( toX, toY);
	}
	
	@Override
	public Elephant copyOfPiece() {
		Elephant pieceCopy=new Elephant(super.getImage(), super.getX(), super.getY());
		return pieceCopy;
	}

	@Override
	public boolean checkRules(int toX, int toY, boolean isPieceHere) {
		int x = super.getX();
		int y = super.getY();
		byte type = super.getType();
		if (toX >= 0 && toX <= 8) {
            if (toY >= 0 && toY <= 4 && type == 0) {
                if (toX == x + 2 && toY == y - 2 && ManagePieces.searchPiece(x+1, y-1)== null) {
                    return true;
                }
                if (toX == x + 2 && toY == y + 2 && ManagePieces.searchPiece(x + 1,y + 1) == null) {
                    return true;
                }
                if (toX == x - 2 && toY == y - 2 &&  ManagePieces.searchPiece(x - 1,y - 1) == null) {
                    return true;
                }
                if (toX == x - 2 && toY == y + 2 &&  ManagePieces.searchPiece(x - 1,y + 1) == null) {
                    return true;
                }
            }
            if (toY >= 5 && toY <= 9 && type == 1) {
            	if (toX == x + 2 && toY == y - 2 && ManagePieces.searchPiece(x+1, y-1)== null) {
                    return true;
                }
                if (toX == x + 2 && toY == y + 2 && ManagePieces.searchPiece(x + 1,y + 1) == null) {
                    return true;
                }
                if (toX == x - 2 && toY == y - 2 &&  ManagePieces.searchPiece(x - 1,y - 1) == null) {
                    return true;
                }
                if (toX == x - 2 && toY == y + 2 &&  ManagePieces.searchPiece(x - 1,y + 1) == null) {
                    return true;
                }
            }

        }
        return false;
	}

    @Override
    public ArrayList<int[]> generateNextMoves(ChessBoard chessBoard){
        int[][] targets = new int[][]{{2, 2}, {2, -2}, {-2, -2 }, {-2, 2}};
        return this.getMovable(targets);
    };

}
