package Mini1.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.tools.Tool;

import Mini1.canvas.ActionCanvas;

public class Background extends Entity {

	private Image img;
	private Image[] twinkles = new Image[3];
	private Image twinkle1;
	private Image twinkle2;
	private Image twinkle3;

	// 모든 이미지의 시작,끝 위치
//	private int dx1;
//	private int dy1;
//	private int dx2;
//	private int dy2;

	// 거지짤
//	private static int sWidth = 255;
//	private static int sHeight = 255;

	// 깔끔짤 겸 dx,dy
//	private static int fWidth = 700;// 331;//피니쉬 사진의 너비이자 모든 dx2 좌표
//	private static int fHeight = 700;// 510;//피니쉬 사진의 높이이자 모든 dy2 좌표
	// 700,700 으로 dx,dy 잡고 이미지는 좌표없이 오리지널로 가져다썼는데 왜 짤리지?

	private Boolean finish = false;

	public Boolean getFinish() {
		return finish;
	}

	public void setFinish(Boolean finish) {
		this.finish = finish;
	}

	public Background() {
		this(0, 0,0,0, false);
	}

	public Background(double x, double y,double w, double h, Boolean finish) {
		this.finish = finish;
		setX(x);
		setY(y);
		setWidth(w);
		setHeight(h);
		// 시작이미지
		try {
			img = ImageIO.read(new File("res/startBackground.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 반짝이 이미지
		for (int i = 0; i < 3; i++)
			try {
				twinkles[i] = ImageIO.read(new File("res/Twinkle.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	public void paint(Graphics g) {

		// 두 이미지의 좌표가 같네? 엔티티의 x,y를 이용하자
		// 이미지들의 좌표
		int dx =(int) getX();// 0
		int dy = (int)getY();// 0
		
		int w = (int)getWidth();
		int h = (int)getHeight();
		
		if (isActive()) {
			if (finish) {
				changeImage();
				// 이미지. x좌표,y좌표, 너비,높이
				g.drawImage(img, dx, dy, w,h,ActionCanvas.instance);
				g.drawImage(twinkles[0], 50, 100, 200, 200, ActionCanvas.instance);
				g.drawImage(twinkles[1], 400, 50, 300, 300, ActionCanvas.instance);
				g.drawImage(twinkles[2], 250, 300, 400, 400, ActionCanvas.instance);
			} else
				g.drawImage(img, dx, dy, w,h,ActionCanvas.instance);
		}
	}

	public void changeImage() {
		for (int i = 0; i < 3; i++)
			try {
				twinkles[i] =  ImageIO.read(new File("res/Twinkle.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		try {
			img = ImageIO.read(new File("res/finishBackground.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}
}
