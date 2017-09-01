package day09;

import javax.swing.JFrame;

public class ShootFrame extends JFrame{
	
	//����
	private ShootPanel shootPanel;
	//������
	public ShootFrame(){
		this.setTitle("�ɻ���ս1.0");
		this.setSize(470, 837);
		//this.setLocations(200,20);//����λ��
		this.setLocationRelativeTo(null);//����
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		shootPanel = new ShootPanel();
		this.add(shootPanel);
	}
	public void showMe(){
		this.setVisible(true);
		shootPanel.action();
	}
	public static void main(String[] args) {
		ShootFrame shootFrame = new ShootFrame();
		shootFrame.showMe();
	}
}
