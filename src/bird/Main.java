package bird;

import javax.swing.JFrame; // ctrl shift o
import javax.swing.JPanel;

public class Main {

	//  alt /
	public static void main(String[] args) {
		JFrame frame=new JFrame();
		frame.setSize(Config.JFRAME_WIDTH, Config.JFRAME_HEIGHT);
		frame.setLocationRelativeTo(null);// ��Ļ����
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// �ٷ��ṩ��������ӷ�����ֻ�����������˵�����ť�����ȡ����ܻ�һֻ��
		//JPanel panel=new JPanel();
		
		MyPanel p=new MyPanel();
		
		frame.add(p);// �����������壬��ѭ��������panel��paint����
		
		p.createWallAction();//��������ǽ������
		p.moveAction();// �����ƶ�����
		p.moveHandle();//�����ֶ���������
		p.mouseMoveAction();//��������������
		frame.setVisible(true);
	}
	
}
