package day09;

import javax.swing.JFrame;

public class ShootFrame extends JFrame{
	
	//属性
	private ShootPanel shootPanel;
	//构造器
	public ShootFrame(){
		this.setTitle("飞机大战1.0");
		this.setSize(470, 837);
		//this.setLocations(200,20);//设置位置
		this.setLocationRelativeTo(null);//居中
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
