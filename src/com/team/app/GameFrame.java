package com.team.app;

import java.awt.Frame;

import com.team.app.canvas.ActionCanvas;

public class GameFrame extends Frame {
	
//	public Player[] players;
	
	public GameFrame() {
		
		
		
		ActionCanvas canvas = new ActionCanvas();
		setSize(1200, 900);
		setVisible(true);	
		add(canvas);
		canvas.start();
	}
}
