package com.team.app.canvas;

import java.awt.Canvas;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Image;

import com.team.app.entity.Background;
import com.team.app.entity.CountDown;
import com.team.app.entity.CountListener;
import com.team.app.entity.Effect;
import com.team.app.entity.Entity;
import com.team.app.entity.Gauge;
import com.team.app.entity.GaugeFillListener;
import com.team.app.entity.Hand;
import com.team.app.entity.HandMoveListener;
import com.team.app.entity.SpaceBar;
import com.team.app.entity.Timer;
import com.team.app.entity.Twinkle;
import com.team.app.entity.edgeBorder;

public class ActionCanvas extends Canvas {

	public static Canvas instance;
	private Hand hand;
	private CountDown countDown;
	private Timer timer;
	private Gauge gauge;

	private Background background;
	private Twinkle[] twinkle = new Twinkle[4];
	private Effect effect;
	private SpaceBar spaceBar;
	private edgeBorder border;
	private Entity[] entitys = new Entity[8];
//	private Player[][] entitys = new Entity[8][];
	private int entitySize;

	// hand, countDown, time, gauge, background,
	// twinkle, effect, spaceBar, border
	// 9개

	// bg, border, time, hand, gauge,
	// twinkle, spaceBar, effect, countDown

	public ActionCanvas() {
		instance = this;

		hand = new Hand();
		timer = new Timer(60);
		countDown = new CountDown(3);
		gauge = new Gauge(400);
		background = new Background();

		hand.setMoveListener(new HandMoveListener() {

			@Override
			public void onMove(double per) {
				System.out.println(per);
			}
		});

		countDown.setCountListener(new CountListener() {

			@Override
			public void onEnd() {
				timer.setActive(true);
				gauge.setActive(true);
				hand.setActive(true);
			}
		});

		gauge.setFillListener(new GaugeFillListener() {

			@Override
			public void onFilld() {
				background.setFinish(true);
				effect.show();
				spaceBar.show();
				spaceBar.isActive();
				timer.stop();

			}
		});


		spaceBar.setPressListener(new SpaceBarListener()){
			
			@Override
			public void onPress() {
				
			}
			
		});
		
		entitys[0] = background;
		entitys[1] = border;
		entitys[2] = timer;
		entitys[3] = hand;
		entitys[4] = gauge;
		entitys[5] = twinkle[0];
		entitys[6] = twinkle[1];
		entitys[7] = twinkle[2];
		entitys[8] = twinkle[3];
		entitys[9] = spaceBar;
		entitys[10] = effect;
		entitys[11] = countDown;

		entitySize = 12;
	}

	// 쓰레드
	public void start() {

		// 게임시작
		countDown.show();
		background.show();
		timer.show();
		gauge.show();
		hand.show();
		
		background.setActive(true);

//		countScreen();
		countDown.update();
		repaint();

		// 게임 중
		for (int i = 0; i < entitySize; i++) {
			if(entitys[i].isActive())
				entitys[i].update();
		}
		repaint();

		//

		// 게임 시작

//		Runnable sub = new Runnable() {
//			@Override
//			public void run() {
//				while(true) {
//					hand.update();
//					repaint();
//				}
//			}
//		};
//		
//		Thread th = new Thread(sub);
//		th.start();
	}

	@Override
	public boolean keyDown(Event evt, int key) {
		hand.moveKey(key);
		return super.keyDown(evt, key);
	}

	@Override
	public void update(Graphics g) {
		paint(g);
	}

	@Override
	public void paint(Graphics g) {
		Image buf = this.createImage(this.getWidth(), this.getHeight());
		Graphics bg = buf.getGraphics();

//		hand.paint(bg);
//		time.paint(bg);
//		countDown.paint(bg);
//		gauge.paint(bg);
//		background.paint(bg);

		for (int i = 0; i < entitySize; i++) {
			if(entitys[i].isVisible())
				entitys[i].update();
		}
		
		
		g.drawImage(buf, 0, 0, this);
	}
}
