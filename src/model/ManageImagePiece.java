package model;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ManageImagePiece {
	private BufferedImage[] listImg;
	
	public ManageImagePiece(){
		listImg=new BufferedImage[15];
		try {
			listImg[0]=ImageIO.read(getClass().getResource("/source/selected_piece.GIF"));
			
			listImg[1]=ImageIO.read(getClass().getResource("/source/red_king.GIF"));
			listImg[2]=ImageIO.read(getClass().getResource("/source/red_advisor.GIF"));
			listImg[3]=ImageIO.read(getClass().getResource("/source/red_elephant.GIF"));
			listImg[4]=ImageIO.read(getClass().getResource("/source/red_chariot.GIF"));
			listImg[5]=ImageIO.read(getClass().getResource("/source/red_cannon.GIF"));
			listImg[6]=ImageIO.read(getClass().getResource("/source/red_horse.GIF"));
			listImg[7]=ImageIO.read(getClass().getResource("/source/red_pawn.GIF"));
			
			listImg[8]=ImageIO.read(getClass().getResource("/source/black_king.GIF"));
			listImg[9]=ImageIO.read(getClass().getResource("/source/black_advisor.GIF"));
			listImg[10]=ImageIO.read(getClass().getResource("/source/black_elephant.GIF"));
			listImg[11]=ImageIO.read(getClass().getResource("/source/black_chariot.GIF"));
			listImg[12]=ImageIO.read(getClass().getResource("/source/black_cannon.GIF"));
			listImg[13]=ImageIO.read(getClass().getResource("/source/black_horse.GIF"));
			listImg[14]=ImageIO.read(getClass().getResource("/source/black_pawn.GIF"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public BufferedImage[] getListImg(){
		return this.listImg;
	}
	
	public BufferedImage getImage(int piece){
		return listImg[piece];
	}
}
