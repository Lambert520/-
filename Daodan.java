package day09;

import java.util.Random;

public class Daodan extends FlyingObject{

	private int ySpeed = 1;//纵坐标上移动的速度
	//获得蜜蜂奖励类型
	
	//构造器
	//当new创建对象时，使用构造器给属性赋值初始化
	public  Daodan(int x , int y){
		this.image = ShootPanel.daodan;
		//直接取图片对象的宽高，作为蜜蜂的宽高
		this.width = image.getWidth();
		this.height = image.getHeight();
		//this.x = 200;
		//this.y = 837 + height;
		this.x = x;
		this.y = y;
		//使用一个负的高度作为y坐标值
		//因为是取图片的左上角顶点，作为对象的坐标值
		
	}
		//蜜蜂是界面的上边缘随机出现
		//使用随机数工具产生一个随机数作为横坐标值
		
		//蜜蜂的奖励类型
		//奖励类型有两种，0表示20次双倍火力，1表示一条生命

	
	
	public void step() {
		y -= ySpeed;
		
	}
	public boolean outOfBounds(){
		return y<0;
	}

	
}
