package Exam1.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Exam1.canvas.ActionCanvas;

public class Gauge extends Entity {

	// ������ ���� ��� �������� �׷�����  x, y�� 0, 0 ������ �׵θ��� ©�� ���� �� ����
//	private double x;
//	private double y;

	// ���� ���� �ʺ�, ���� ä���� ����
//	private double width;
//	private double height;
	private final double MAX_HEIGHT;

//	private int imgW;
//	private int imgH;
	private static Image img;

	private GaugeFillListener fillListener;
	
	public void setFillListener(GaugeFillListener fillListener) {
		this.fillListener = fillListener;
	}

	public Gauge() {
		this(100, 100, 20, 0);
	}
	
	public Gauge(double MAX_HEIGHT) {
		this(100, 100, 20, 0);
		this.MAX_HEIGHT = MAX_HEIGHT;
	}
	
	public Gauge(double x, double y, double width, double height) {

		this.MAX_HEIGHT = 500;
		
		try {
			img = ImageIO.read(new File("res/red.png"));
			// �̹����� w, h �ҷ�����
//			imgW = img.getWidth(ActionCanvas.instance);
//			imgH = img.getHeight(ActionCanvas.instance);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
//	public void move(double x, double y) {
//		this.x = x;
//		this.y = y;
//	}
//	
//	public void setSize(double width, double height) {
//		this.width = width;
//		this.height = height;
//	}
	
	public void per(double setPer) {
		double per = MAX_HEIGHT / 100;
		setSize( getWidth(), setPer * per );
	}
	
	@Override
	public void setSize(double width, double height) {
		super.setSize(width, height);
	}
	
	@Override
	public void update() {

//		double width = getWidth();
		double height = getHeight();
		
		if (height < 0)
			setHeight(0);
		else if (MAX_HEIGHT < height)
			setHeight(MAX_HEIGHT);
		
		if( height == MAX_HEIGHT )
			if( fillListener != null )
				fillListener.filld();
	}
	
	@Override
	public void paint(Graphics g) {
		
		double height = getHeight();
		double width = getWidth();
		double x = getX();
		double y = getY();
		double dx1 = x;
		double dy1 = Math.ceil(y + MAX_HEIGHT - height);
		
//		double dy1 = y + MAX_HEIGHT - height;
		
		g.setColor(Color.red);
		g.fillRect((int)dx1, (int)dy1, (int)width, (int)height);
		
		double rectX = x - width / 2;
		double rectY = y - width / 2;
		double rectW = width * 2;
		double rectH = MAX_HEIGHT + width;
		
		g.setColor(Color.black);
		g.drawRect((int)rectX, (int)rectY, (int)rectW, (int)rectH);
		

//		double dx1 = x;
//		// �������� �Ʒ��κ� - ���簪 ( �ؿ������� ���� ������� �������� ĥ�ϰڴ� )
//		double dy1 = y + MAX_HEIGHT - height;
//		// ���������� �ʺ�
//		double dx2 = x + width;
//		// �������� �Ʒ�
//		double dy2 = y + MAX_HEIGHT;
//
//		g.drawImage(img, (int)dx1, (int)dy1, (int)dx2, (int)dy2,
//								0, 		  0, 		1,		  1,
//																ActionCanvas.instance);
	}
	
}
