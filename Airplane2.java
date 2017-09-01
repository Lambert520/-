package day09;

/**
 * 敌机类型
 * 继承于FlingObject类
 * 会将父类的公开的保护属性和函数都继承下来
 * @author asus
 *java中的继承关系，只允许继承一个父类，单继承的！
 *如果想实现多继承的现象，可以使用接口的概念，变向的实现
 */
public  class Airplane2 extends FlyingObject implements Enemy{
		
	//属性
	//不需要再定义x，y，width，height，image
	//以上这些属性，会直接从父类中继承下来
	
	//敌机的特有属性
	//向下飞的速度，2倍速
	private int speed = 2;
	
	//构造器
	public Airplane2(){
		//public static double Math.PI=3.1415926;
		this.image = ShootPanel.airplaneImage2;
		this.width = this.image.getWidth();
		this.height = image.getHeight();
		this.y = -height;
		int bound = ShootPanel.WIDTH - width;
		this.x = (int)(Math.random()*bound-10);
						//[0.0,1.0)
		}
	//功能方法
	//实现Enemy接口中的方法4
	public int getScore(){
		return 5;
	}
	@Override
	public void step() {
		y += speed;
		
	}
	@Override
	public boolean outOfBounds() {
		// 越界处理
		return y>ShootPanel.HEIGHT;
	}
}