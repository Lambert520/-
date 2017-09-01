package day09;

import java.awt.image.BufferedImage;

/**
 * 子弹类型
 * @author asus
 *
 */
public class Bullet extends FlyingObject{
	
	//属性
	private int speed = 3;	//子弹从下往上飞的速度
	protected BufferedImage[] images;
	//构造器
	public Bullet(int x, int y,int z){
		if(z==0)this.image = ShootPanel.bullet0Image;
		else if(z==1)this.image = ShootPanel.bullet1Image;
		else if(z==2) this.image = ShootPanel.bullet2Image;
		this.width = image.getWidth();
		this.height = image.getHeight();
		
		this.x = x-20;
		this.y = y;
		
	}
	
	@Override
	public void step() {
		y -= speed; //向上飞，纵坐标值变小
		
	}
	@Override
	public boolean outOfBounds() {
		// TODO Auto-generated method stub
		return y<-height;
	}
}
