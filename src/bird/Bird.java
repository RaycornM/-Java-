package bird;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/***
 * �Զ���һ�����࣬
 *
 *һ��������һ�ֶ����ģ��
 *
 *һ������߱����Ժ���Ϊ���ֶ�����
 *���Զ���ö������ʱ��
 *
 *һ�������ԣ�ȫ�ֱ���������Ϊ��������
 *
 *�����Ը�ֵ�������뵽���ǹ��췽��
 *
 *
 *
 *��̬���Ժ;�̬�����ִֻ��һ�Σ�����������ִ��

�Ǿ�̬���ԡ��Ǿ�̬����顢���췽����ÿ����һ�������ִ��һ�Ρ������Ǻ��ھ�̬��ִ��

�Ǿ�̬�����͹��췽��������һ���������������Ը�ֵ��

 */
public class Bird {

	private int x;
	private int y;
	private int width;
	private int height;
	private static Image[] imgs=new Image[7];
	
	private int status;// 0 ���ţ�1 ������  2 ����
	
	static{
		try {// 7����Ƭ��ǰ5���Ƿ�������ӣ��������Ƿ���ģ������ź���
			for(int i=0;i<imgs.length;i++){
				imgs[i]=ImageIO.read(new File("bee"+i+".png"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	{
		//�Ǿ�̬����飬�͹��췽��һ�����У������Ǹ����Ը�ֵ �͹��췽������һ������ˣ�������
	}
	
	public Bird(int x,int y){// ���췽����ʲô�ã� 1�������Ը�ֵ  2��ͨ��new�ؼ��ִ�������
		this.x=x;
		this.y=y;
		this.width=Config.BIRD_WIDTH;
		this.height=Config.BIRD_HEIGHT;
	}
	
	// ��һ���Զ���ķ�������panel��paint����
	int index=0;
	public void paintSelf(Graphics g){
		if(this.status==Config.BIRD_LIVING){
			g.drawImage(imgs[index++], x,y, width, height, null);
			if(index>=5){
				index=0;
			}
		}else if(this.status==Config.BIRD_DYING){
			g.drawImage(imgs[5], x,y, width, height, null);
		}else{
			g.drawImage(imgs[6], x,y, width, height, null);
		}
	}
	
	public void move(){
		if(status==Config.BIRD_LIVING){
			this.y++;
		}else if(status==Config.BIRD_DYING){
			this.y+=30;
		}else if(status ==Config.BIRD_DEAD){
			this.x=0;
			this.y=0;//С���������ã���Ļ���Ͻ�
		}
	}
	
	public void move(int x,int y){
		if(status==Config.BIRD_LIVING){
			this.x=x;
			this.y=y;
		}else if(status==Config.BIRD_DYING){
			this.y+=30;
		}else if(status ==Config.BIRD_DEAD){
			this.x=0;
			this.y=0;//С���������ã���Ļ���Ͻ�
		}
	}
	
	
	public void moveUp(){
		this.y-=15;
	}
	
	public void moveDown(){
		this.y+=15;
	}
	
	public boolean hit(FlyingObject w){
		if(this.x<w.getX()+w.getWidth() && this.x+this.width>w.getX()){
			if(this.y+this.height>w.getY() && this.y<w.getY()+w.getHeight()){
				return true;
			}
		}
		return false;
	}
	
	public boolean hitGround(){
		if(this.y+this.height>Config.JFRAME_HEIGHT){
			return true;
		}
		return false;
	}

	
	public int getStatus() {
		return status;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}
	
	
}
