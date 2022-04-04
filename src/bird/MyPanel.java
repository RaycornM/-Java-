package bird;

import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Arrays;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MyPanel extends JPanel{
	
	BackGround back=new BackGround(0,0);
	Bird bird=new Bird(600,200);

	FlyingObject[] objs=new FlyingObject[0];
	
	public MyPanel() {
		
	}
	
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		back.paintSelf(g);
		bird.paintSelf(g);
		for(FlyingObject w:objs){
			w.paintSelf(g); 
			// �������Ƿ����ȡ���� ���������͡������������ȡ�������ö��� �ڲ�����д�ķ����塣���������̬
			//��̬�������ģ� ��1�����ø������õķ�����ʱ��ָ�����������д�����塣
			//��2������ͬһ��������ط�����Ҳ�ж�̬��
			//��3�� �����һ�����ã�������һ��������� FlyingObject a=new GoldBox();
			// ����������ɶɶ��˼�� �������͵�һ���������������
			// ���� ����������ɶ��˼�� ���˻�������������������ͣ�ȫ���������͡�
		}
	}
	
	/*�Զ���һ���������ƶ�����*/
	public void moveAction(){
		new Thread(){
			public void run() {
				for(;;){
					//���ǽ�Ƿ�Խ�� ��� �����Ƿ�Խ��
					FlyingObject[] tmp=new FlyingObject[0];//������ŵ�ǽ
					for(FlyingObject w:objs){
						if(!w.isOut()){
							tmp=Arrays.copyOf(tmp, tmp.length+1);
							tmp[tmp.length-1]=w;
						}
					}
					objs=tmp;
					//����Ƿ�С������
					if(bird.getStatus()==Config.BIRD_DEAD){
						JOptionPane.showMessageDialog(null, "С������");
						return;
					}
					//���С��ײ��
					if(bird.hitGround()){
						bird.setStatus(Config.BIRD_DEAD);//
					}
					//���С��ײǽ������
					for(FlyingObject w:objs){
						if(bird.hit(w)){
							if(w instanceof Enemy){ // �ж϶���ľ�������
								bird.setStatus(Config.BIRD_DYING);
							}else if(w instanceof Award){
								w.setStatus(Config.FLYINGOBJECT_DEAD);
							}
						}
					}
					//С���ƶ��������ƶ���ǽ�ƶ�
					bird.move();
					back.move();
					for(FlyingObject w:objs){
						if(w!=null){
							w.move();
						}
					}
					repaint();// ����MyPanel��repaint
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			};
		}.start();
	}

	
	// �Զ��巽�����ֶ���������
	public void moveHandle(){
		//�����˼��̼����߳�
		KeyAdapter l=new KeyAdapter(){
			// ���м��̰������µ�ʱ�򣬾ͻ��Զ�����
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_UP && bird.getStatus()==Config.BIRD_LIVING){// ����
						bird.moveUp();
						repaint();
				}
				if(e.getKeyCode()==KeyEvent.VK_DOWN && bird.getStatus()==Config.BIRD_LIVING){// ����
					bird.moveDown();
					repaint();
				}
			}
		};
		this.setFocusable(true);// ���۽�
		this.addKeyListener(l);// ���̼����߳�������ֱ�ӼӸ�panel
	}
	
	/*�����ϰ���ǽ������*/
	public void createWallAction(){
		
		new Thread(){
			public void run() {
				for(;;){
					//System.out.println("Ŀǰǽ������"+walls.length);
					
					// ��������ǰ���ж������ɵ�ǽ�����е�ǽ���бȽ��Ƿ����̫����
					Random r=new Random();
					int n=r.nextInt(3);
					
					FlyingObject w=null;
					
					switch(n){
						case 0:w=new Wall();break;
						case 1: w=new GoldBox();break;
						case 2: w=new Stone();break;
					}
					boolean isOk=true;
					for(FlyingObject tmp:objs){
						if(tmp.isClosed(w)){
							isOk=false;//��w���ϸ�
							break;//ֻҪ��һ��Ԫ������ǽw�ܽ������������ѭ����
						}
					}
					if(!isOk){
						continue;
					}
					objs=Arrays.copyOf(objs,objs.length+1);//
					objs[objs.length-1]=w;
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			};
		}.start();
		
	}
	
	// ��������ƶ�����
	public void mouseMoveAction(){
		MouseMotionAdapter m=new MouseMotionAdapter() {
			public void mouseMoved(MouseEvent e) {
				int x=e.getX();
				int y=e.getY();
				bird.move(x,y);
				repaint();
			}
		};
		this.setFocusable(true);
		this.addMouseMotionListener(m);
	}
	

}
