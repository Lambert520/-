package day09;

import java.awt.image.BufferedImage;

/**
 * �ӵ�����
 * @author asus
 *
 */
public class Bullet extends FlyingObject{
	
	//����
	private int speed = 3;	//�ӵ��������Ϸɵ��ٶ�
	protected BufferedImage[] images;
	//������
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
		y -= speed; //���Ϸɣ�������ֵ��С
		
	}
	@Override
	public boolean outOfBounds() {
		// TODO Auto-generated method stub
		return y<-height;
	}
}
