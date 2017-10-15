package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.*;

import model.ManageImagePiece;
import model.ManagePieces;
import model.Piece;
import view.ChessBoard;

public class ChessBoardController {
	
	private ChessBoard chessBoard;
	private ManageImagePiece manageImagePiece;
	private ManagePieces managePieces;
	private int currentX;
	private int currentY;
	
	public ChessBoardController(){
		manageImagePiece = new ManageImagePiece();
		managePieces = new ManagePieces();
		chessBoard = new ChessBoard(managePieces, manageImagePiece);
		displayChessBoard(chessBoard);
		setMouseEvent();
	}
	
	public void displayChessBoard(ChessBoard chessBoard){
		 JFrame frame=new JFrame();
		 frame.add(chessBoard);
		 frame.setSize(620, 705);
		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 frame.setVisible(true);
		 frame.setResizable(false);
	}
	
	public void setMouseEvent(){
				
		chessBoard.addMouseListener(new MouseListener() {
			int cursor_x;
			int cursor_y;
			@Override
			public void mouseReleased(MouseEvent event) {

			}
			
			@Override
			public void mousePressed(MouseEvent event) {
				cursor_x=event.getX();
				cursor_y=event.getY();
				int temp_x=cursor_x%60; 
				int temp_y=cursor_y%60;
				if(temp_x>35) currentX= cursor_x/60; else if(temp_x<25) currentX=cursor_x/60-1; else currentX=-1;
				if(temp_y>35) currentY= cursor_y/60; else if(temp_y<25) currentY=cursor_y/60-1; else currentY=-1;
				if(currentX>=0 && currentY>=0 && currentX <9 && currentY <10)
				    chessBoard.selectPiece(currentX, currentY);
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {

			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				chessBoard.setClickMouse();
				
			}
		});
	}

    public ChessBoard getChessBoard() {
        return chessBoard;
    }


    public void run() {

		while (true) {

			if (chessBoard.hasWin()==1) {
				JOptionPane.showConfirmDialog(null, "The black has lost!", "Information", JOptionPane.OK_OPTION);
				break;
			} else if(chessBoard.hasWin()==-1){
				JOptionPane.showConfirmDialog(null, "The red has lost!", "Information", JOptionPane.OK_OPTION);
				break;
			}

			if (Player.currentPlayer == Player.RED_PLAYER){
				try{
					Thread.sleep(1000);
					System.out.println("Wating for RED player move....");
				} catch (Exception e){
					System.out.println(e);
				}
			} else if(Player.currentPlayer == Player.BLACK_PLAYER){
				responseMoveChess();
			}
		}
	}

	public void responseMoveChess(){
		EvaluateModel.evaluate(chessBoard, Player.BLACK_PLAYER);
		SearchModel searchModel = new SearchModel();
		TreeNode result = searchModel.search(chessBoard);
		chessBoard.getManagePiece().movePiece(result.piece, result.to);
		chessBoard.repaint();
		System.out.println("System completed work.");
	}

}
