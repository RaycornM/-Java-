package bird;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * �ڽ�һϵ�����͹���Ϊһ�������ʱ����ѭ��ԭ��
 * 
 * 1���������๲�е�һ�����Զ��鵽���࣬������������е�����
 * 2����������ľ�̬���Զ��ϲ������࣬����������е�
 * 3�����췽���Ǹ����Ը�ֵ�ģ�ÿ����������Ե�ֵ��������ȫһ����
 * ���ԣ���������ӵ���Լ������Ĺ��췽��
 * 
 */
public abstract class FlyingObject {

	protected  int x;
	protected int y;
	protected int width;
	protected int height;
	
	protected static Image img_wall;
	protected static Image img_box;
	protected static Image img_stone;
	
	protected int status;
	
	static{
		try {
			img_wall=ImageIO.read(new File("wall.jpg"));
			img_box=ImageIO.read(new File("food_box.png"));
			img_stone=ImageIO.read(new File("food_stone.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public abstract void paintSelf(Graphics g);
	public abstract boolean isOut();
	public abstract void move();
	public abstract boolean isClosed(FlyingObject w);

	public int getX() {
		return x;
	}


	public void setX(int x) {
		this.x = x;
	}


	public int getY() {
		return y;
	}


	public void setY(int y) {
		this.y = y;
	}


	public int getWidth() {
		return width;
	}


	public void setWidth(int width) {
		this.width = width;
	}


	public int getHeight() {
		return height;
	}


	public void setHeight(int height) {
		this.height = height;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
	
}
