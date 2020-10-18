package com.team.app.entity;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import com.team.app.canvas.ActionCanvas;

public class Hand extends Entity {
	private static final int LEFT = 1006;
	private static final int RIGHT = 1007;
	
	private Image img; //손이미지
	private Clip clip; //사운드 클립
	private int keyTempo;
	private int key1;
	private int key2;
	
	boolean direction = true; //손움직임반향
	private double per;
	private HandMoveListener handMoveListener;
	
	
	//인터페이스
	public void setMoveListener(HandMoveListener handMoveListener) {
		this.handMoveListener = handMoveListener;
	}
		
	//생성자(초기화)
	public Hand() {
		this(250, 300);
		
	}
	
	//생성자2(초기화)
	public Hand(int x, int y) {
		
		
		this.setX(x);
		this.setY(y);
		
		try {
			img = ImageIO.read(new File("res/hand.png"));//동기식 이미지 파일읽기
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setWidth(img.getWidth(ActionCanvas.instance));
		this.setHeight(img.getHeight(ActionCanvas.instance));
		
		this.setActive(true);//객체 생성;
	}
	
	
	//효과음
	public void playSound(String pathName, boolean isLoop) {
		try {
			clip = AudioSystem.getClip();
			File audioFile = new File(pathName);
			AudioInputStream ais = AudioSystem.getAudioInputStream(audioFile);
			clip.open(ais);
			clip.start();
			if(isLoop)
				clip.loop(Clip.LOOP_CONTINUOUSLY); //무한반복
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	

	//키누르기
	public void moveKey(int key) {
		if(key == LEFT && key1==0) {
			key1 = 1;
			keyTempo++;
		}else if(key == RIGHT && key2==0) {
			key2 = 1;
			keyTempo++;
		}
			
		if(keyTempo>=2 && key1==1 && key2==1) {
			moveHand();
		}
	}
	
	//조건맞을시 움직임
	public void moveHand() {
		if(this.getActive()) {
			if(direction == true) {
				setX(getX()-11);
				setY(getY()+10);
	
				if(getX()< 45) {
					direction = false;
				}
			}
			
			if(direction == false) {
				setX(getX()+11);
				setY(getY()-10);
		
				if(getX() > 200) {
					direction = true;
				}
			}
			
			keyTempo = 0;
			key1=0;
			key2=0;
			
			per = (per+0.5);
			handMoveListener.onMove(per);
			playSound("audio/handSound.wav", false); //오디오 파일
		}
		if(per>=100) {
			this.setActive(false); //객체 안보이게 하기
		}
		 
	}
	
	
	public void update() {
	}
	
	public void paint(Graphics g) {
		g.drawImage(img, (int)getX(), (int)getY(), ActionCanvas.instance);
	}
	
}
