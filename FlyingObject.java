package day09;

import java.awt.image.BufferedImage;

/**
 * 飞行物
 * 这个类是界面中所有飞行物体的描述
 * 是英雄、敌机、蜜蜂、子弹的父类
 * 将这个物体的共有属性或共通功能，抽象提取到当前父类
 * @author asus
 *abstract抽象的
 *抽象的类不能直接变为对象，即 不能使用new关键字
 *抽象类一般当作父类，给别的类继承
 */
public abstract class FlyingObject extends Object{
	//属性
	//一般情况下，父类的属性课设置为保护的
	//比如：房产、车子、田地等会继承给子类
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	protected BufferedImage image;
	
	//构造器
	//无构造器，抽象不能生成一个具体对象
	
	//功能方法
	//所有飞行物，动一下
	//对于不同的飞行物，动一下的方式不一样！
	//蜜蜂除了向下动一下，还向右动一下
	//子弹是向上动一下。。。。
	public abstract void step();
	//封装一个get函数，用于返回当前类中的保护/私有属性
	public int getX(){
		return x;
	}
	//封装一个set函数，用于给类中的属性设置值
	public void setX(int x){
		this.x = x;
	}
	public int getY(){
		return y;
	}
	public void setY(int y){
		this.y = y;
	}
	public int getWidth(){
		return width;
	}
	public void setWidth(int width){
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public BufferedImage getImage() {
		return image;
	}
	public void setImage(BufferedImage image) {
		this.image = image;
	}
	public boolean shootBy(Bullet bullet){
		int x = bullet.x;
		int y = bullet.y;
		return this.x<x && x<this.x + width && this.y<y && y<this.y + height;
	}
	//检查是否出边界
	public abstract boolean outOfBounds();
}
