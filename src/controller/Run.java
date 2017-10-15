package controller;

import javax.swing.JFrame;

import model.ManageImagePiece;
import model.ManagePieces;
import view.ChessBoard;

public class Run {

	public static void main(String[] args){

		ChessBoardController chessBoardController = new ChessBoardController();
		chessBoardController.run();
	}
}
