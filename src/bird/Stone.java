package bird;

import java.awt.Graphics;
import java.util.Random;

public class Stone extends FlyingObject implements Award{

	
	public Stone() {
		Random ran=new Random();
		this.x=0;// �����ʼλ���ڴ������
		this.y=ran.nextInt(Config.JFRAME_HEIGHT);
		this.width=60;
		this.height=60;
	}
	
	public void paintSelf(Graphics g){
		if(status== Config.FLYINGOBJECT_LIVING){
			g.drawImage(img_stone, x, y, width, height, null);
		}
	}
	
	public void move(){
		this.x+=2;
	}
	
	/* �õ�ǰǽ�͸���ǽ���бȽϣ��Ƿ����̫�����õ�ǰǽ��Ϊ�����
	 * ����������߻�����150�������ұ߻�����150�����Լ���������Χ
	 * ��ǰ��������Ĳ���������һ��ǽ��������Ͳ����￿�����򷵻��档
	 * **/
	public boolean isClosed(FlyingObject w){
		if(this.x-350 < w.x && w.x <this.x+this.width+350){
			return true;
		}
		return false;
	}
	
	
	
	public boolean isOut(){
		if(this.x+this.width >Config.JFRAME_WIDTH){
			return true;
		}
		return false;
	}
	
	

}
