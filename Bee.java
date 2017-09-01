package day09;

import java.util.Random;

/**
 * 蜜蜂类型
 * 蜜蜂是一个飞行物，还是一个奖励
 *
 */
public class Bee extends FlyingObject implements Award{
	//属性
	private int xSpeed = 1;//横坐标上移动的速度
	private int ySpeed = 2;//纵坐标上移动的速度
	private int awardType;//蜜蜂奖励的类型
	//获得蜜蜂奖励类型
	
	//构造器
	//当new创建对象时，使用构造器给属性赋值初始化
	public Bee(){
		this.image = ShootPanel.beeImage;
		//直接取图片对象的宽高，作为蜜蜂的宽高
		this.width = image.getWidth();
		this.height = image.getHeight();
		
		//使用一个负的高度作为y坐标值
		//因为是取图片的左上角顶点，作为对象的坐标值
		this.y = -height;
		
		//蜜蜂是界面的上边缘随机出现
		//使用随机数工具产生一个随机数作为横坐标值
		Random rd = new Random();
		int bound = ShootPanel.WIDTH - width;
		this.x = rd.nextInt(bound);
		
		//蜜蜂的奖励类型
		//奖励类型有两种，0表示20次双倍火力，1表示一条生命
		awardType = rd.nextInt(4);//[0,2)
	}
	public int getType() {
		// TODO Auto-generated method stub
		return awardType;
	}
	
	public void step() {
		x += xSpeed;
		y += ySpeed;
		if(x > ShootPanel.WIDTH-width){
			xSpeed = -1;//向左飞
		}
		if(x<0){
			xSpeed = 1;//向右飞
		}
	}
	public boolean outOfBounds(){
		return y>ShootPanel.HEIGHT;
	}
	
}
