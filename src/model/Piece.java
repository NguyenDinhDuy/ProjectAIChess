package model;

import view.ChessBoard;

import java.util.ArrayList;

public abstract class Piece {

	private byte type; // 0 is black player, 1 is red player
	private int img;
	private int x;
	private int y;
	private int[][] blackValues;
    private int[][] redValues;

	public Piece(int img, int x, int y){
		this.img=img;
		this.x=x;
		this.y=y;
		if(this.img>7)
			type=0;
		else
			type=1;
	}
	
	public int[][] getBlackValues() {
		return blackValues;
	}

	public void setBlackValues(int[][] blackValues) {
		this.blackValues = blackValues;
	}

	public int[][] getRedValues() {
		return redValues;
	}

	public void setRedValues(int[][] redValues) {
		this.redValues = redValues;
	}

	public int getImage(){
		return this.img;
	}
	
	public int getX(){
		return this.x;
	}
	
	public byte getType() {
		return type;
	}

	public void setType(byte type) {
		this.type = type;
	}

	public int getY(){
		return this.y;
	}
	
	public void setX(int x){
		this.x=x;
	}
	
	public void setY(int y){
		this.y=y;
	}
	
	public boolean checkCanMove( int toX, int toY){
		Piece nextPiece=ManagePieces.searchPiece(toX, toY);
		if(nextPiece==null) {
			return checkRules(toX, toY,false);
		}
		else{
			if(this.type!=nextPiece.type)
				return checkRules(toX, toY,true); // Nếu khác phe thì true -> Cho phép di chuyển
		}
		return false;
	}
	public abstract boolean checkRules(int toX, int toY,boolean isPieceHere);
	public abstract Piece copyOfPiece();

	public abstract ArrayList<int[]> generateNextMoves(ChessBoard chessBoard);

	public ArrayList<int []> getMovable(int [][] targets){

		ArrayList<int[]> moves = new ArrayList<int[]>();
		int [] absoluteLocation;
		for (int[] target : targets) {
			int toX = this.getX() + target[0];
			int toY = this.getY() + target[1];
			if (this.checkCanMove(toX, toY)){
				absoluteLocation = new int []{toX, toY};
				moves.add(absoluteLocation);
			}
		}
		return moves;
	}

	public void printInfo() {
		try {
			System.out.println( this.getClass()  + "\t" + this.getType() + "\t(" + this.getX() + ", "  + this.getY() + " )");
		} catch (NullPointerException e) {
		}
	}
}
