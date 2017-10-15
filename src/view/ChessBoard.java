package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

import controller.Player;
import model.King;
import model.ManageImagePiece;
import model.ManagePieces;
import model.Piece;


public class ChessBoard extends JPanel{

	public static final int MAX_ROW=10;
	public static final int MAX_COL=9;
	private int startX;
	private int startY;
	private int sizeRow;
	private int sizeCol;
	private int sizeCell;
	private ManagePieces managePieces;
	private ManageImagePiece listImagePiece;
	private Piece currentPiece;
	private Piece lastPiece;	
	private boolean check;	
	private int cursorX;
	private int cursorY;


	public ChessBoard(ManagePieces managePieces, ManageImagePiece listImagePiece){
		init();
		this.managePieces =managePieces ;
		this.listImagePiece=listImagePiece;
		setBackground(Color.WHITE);
	}
	
	public void init(){
		sizeCell=60;
		startX=sizeCell;
		startY=sizeCell;
		sizeRow=10*sizeCell;
		sizeCol=9*sizeCell;
		currentPiece=null;
		lastPiece=null;
		check=false;
	}

	public ManagePieces getManagePiece(){
		return managePieces;
	}
	
	@Override
	public void paint(Graphics g) {

		super.paint(g);
		g.setColor(Color.black);
		//draw cols
		for(int i=0;i<MAX_COL;i++)
			drawALine(g, i, 0, i, 9);
		//draw rows
		for(int i=0;i<MAX_ROW;i++)
			drawALine(g, 0, i, 8, i);
		drawALine(g, 3, 0, 5, 2);
		drawALine(g, 3, 2, 5, 0);
		drawALine(g, 5, 7, 3, 9);
		drawALine(g, 3, 7, 5, 9);
		
		g.setColor(Color.WHITE);
		for(int i=1;i<8;i++)
			drawALine(g, i, 4, i, 5);
		//draw selectedPiece
		if(check){
			drawImage(g, listImagePiece.getImage(0), currentPiece.getX(), currentPiece.getY());
			check=false;
		}
		//draw piece
		for(Piece piece: managePieces.getListPiece()){
			drawImage(g, listImagePiece.getImage(piece.getImage()), piece.getX(), piece.getY());
		}
	}
	
	public void drawALine(Graphics g, int i1, int j1, int i2, int j2){
		g.drawLine(sizeCell*(i1+1), sizeCell*(j1+1), sizeCell*(i2+1), sizeCell*(j2+1));
	}
	
	public void drawImage(Graphics g, BufferedImage img, int i, int j){
		g.drawImage(img, sizeCell*i+ sizeCell/2, sizeCell*j+sizeCell/2, null);
	}
	
	public void selectPiece(int x, int y){
		lastPiece=currentPiece;
		currentPiece= ManagePieces.searchPiece(x, y);
		if( lastPiece!=null){
			if(lastPiece.checkCanMove( x, y)){
				ManagePieces.movePiece(lastPiece, x, y);
				repaint();
				currentPiece=null;
			} else if(!lastPiece.checkCanMove(x, y) && ManagePieces.searchPiece(x, y) ==null) {
				currentPiece = lastPiece;
			}
		}
		
	}
	
	public void setClickMouse(){
		if(currentPiece!=null){
			check=true;
			repaint();
		}
	}

	public int hasWin() {
		int winPlayer = 0;
		if (ManagePieces.isKingAlive(Player.RED_PLAYER)&& !ManagePieces.isKingAlive(Player.BLACK_PLAYER)){
			return 1;
		} else if (!ManagePieces.isKingAlive(Player.RED_PLAYER)&& ManagePieces.isKingAlive(Player.BLACK_PLAYER)){
			return -1;
		}
		return 0;
	}
}
