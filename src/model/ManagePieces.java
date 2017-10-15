package model;

import controller.Player;

import java.util.ArrayList;


public class ManagePieces {

	private static ArrayList<Piece> listPiece;

    static {
		listPiece=new ArrayList<>();
		//king red=1, king black=8
		listPiece.add(new King(8, 4, 0));
		listPiece.add(new King(1, 4, 9));
		//advisor red=2, advisor black=9 
		listPiece.add(new Advisor(2, 3, 9));
		listPiece.add(new Advisor(2, 5, 9));
		listPiece.add(new Advisor(9, 3, 0));
		listPiece.add(new Advisor(9, 5, 0));
		//elephant red=3, elephant=10
		listPiece.add(new Elephant(3, 2, 9));
		listPiece.add(new Elephant(3, 6, 9));
		listPiece.add(new Elephant(10, 2, 0));
		listPiece.add(new Elephant(10, 6, 0));
		//chariot red=4, chariot black =11
		listPiece.add(new Chariot(4, 0, 9));
		listPiece.add(new Chariot(4, 8, 9));
		listPiece.add(new Chariot(11, 0, 0));
		listPiece.add(new Chariot(11, 8, 0));
		//cannon red=5, cannon black=12
		listPiece.add(new Cannon(5, 1, 7));
		listPiece.add(new Cannon(5, 7, 7));
		listPiece.add(new Cannon(12, 1, 2));
		listPiece.add(new Cannon(12, 7, 2));
		//horse red=6, horse black=13
		listPiece.add(new Horse(6, 1, 9));
		listPiece.add(new Horse(6, 7, 9));
		listPiece.add(new Horse(13, 1, 0));
		listPiece.add(new Horse(13, 7, 0));
		//pawn red=7, paw black =14
		listPiece.add(new Pawn(14, 0, 3));
		listPiece.add(new Pawn(14, 2, 3));
		listPiece.add(new Pawn(14, 4, 3));
		listPiece.add(new Pawn(14, 6, 3));
		listPiece.add(new Pawn(14, 8, 3));
		listPiece.add(new Pawn(7, 0, 6));
		listPiece.add(new Pawn(7, 2, 6));
		listPiece.add(new Pawn(7, 4, 6));
		listPiece.add(new Pawn(7, 6, 6));
		listPiece.add(new Pawn(7, 8, 6));
	}
	
	public static ArrayList<Piece> getListPiece(){
		return listPiece;
	}
	
	public static Piece searchPiece(int x, int y){
		for(Piece piece: listPiece)
			if(piece.getX()==x && piece.getY()==y)
				return piece;
		return null;
	}

	public static void movePiece(Piece piece, int toX, int toY){
		Player.inverseCurrentPlayer();
		Piece nextPiece=searchPiece(toX, toY);
		if(nextPiece==null){
			piece.setX(toX);
			piece.setY(toY);
		}
		else{
			listPiece.remove(nextPiece);
			piece.setX(toX);
			piece.setY(toY);
		}
	}


	public static void movePiece(Piece piece, int[] to){
		Player.inverseCurrentPlayer();
		Piece nextPiece=searchPiece(to[0], to[1]);
		if(nextPiece==null){
			piece.setX(to[0]);
			piece.setY(to[1]);
		}
		else{
			listPiece.remove(nextPiece);
			piece.setX(to[0]);
			piece.setY(to[1]);
		}
	}


	public static Piece getPiece(int [] location){
		for(Piece piece: listPiece){
			if(piece.getX()==location[0]&&piece.getY()==location[1]){
				return piece;
			}
		}
		return null;
	}

	public static Piece getPiece(int x, int y){
		for(Piece piece: listPiece){
			if(piece.getX()==x&&piece.getY()==y){
				return piece;
			}
		}
		return null;
	}

	public static void addPiece(Piece piece){
		listPiece.add(piece);
	}

	public static boolean isKingAlive(int player) {
		for (Piece piece : listPiece) {
			if (piece.getType() == player && piece instanceof King)
				return true;
		}
		return false;
	}
}
