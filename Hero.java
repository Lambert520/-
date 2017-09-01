package day09;

import java.awt.image.BufferedImage;

public class Hero extends FlyingObject{
	
	//属性
	protected BufferedImage[] images;	//两张图片
	protected int index = 0;			//来回切图片的计数
	private int doubleFire;				//还有多少次双倍火力
	private int life;					//生命的条数
	protected int TNT;
	//构造器
	public Hero(){
		this.image = ShootPanel.hero0Image;
		this.width = image.getWidth();
		this.height = image.getHeight();
		this.x = 150;
		this.y = 400;
		
		images = new BufferedImage[]{
				ShootPanel.hero0Image,
				ShootPanel.hero1Image
		};
		this.doubleFire = 0;
		this.life = 3;
	}

	
	public void step() {
		image = images[index++/10%images.length];
	
	}
	public Bullet[] shoot(){
		int xStep = width/4;
		int yStep = 20;
		if(doubleFire>0){
			Bullet[] bullets = new Bullet[5];
			bullets[0] = new Bullet(x + xStep-10,y - 2*yStep,1);
			bullets[1] = new Bullet(x + 3 * xStep+10,y -2* yStep,2);
			bullets[2] = new Bullet(x + 2*xStep, y-3* yStep,0);
			bullets[3] = new Bullet(x - xStep-10,y - 1*yStep,2);
			bullets[4] = new Bullet(x + 4 * xStep+20,y -1* yStep,1);
			doubleFire -= 2;
			return bullets;
		}else{
			Bullet[] bullets = new Bullet[1];//y-yStep(子弹到飞机的位置）
			bullets[0] = new Bullet(x + 2*xStep, y- yStep,0);
			return bullets;
			
		}
	}
	public void moveTo(int x, int y){
		this.x = x - width / 2;
		this.y = y -height / 2;
		
	}
	public void addDoubleFire(){
		doubleFire += 80;
	}
	public void addLife(){
		life++;
	}
	public int getLife(){
		if(life>=0)return life;
		else return 0;
	}
	public void subtractLife(){
		life--;
	}
	public void setDoubleFire(int doubleFire){
		this.doubleFire = doubleFire;
	}
	public boolean hit(FlyingObject other){
		int x1 = other.x - this.width / 2;
		int x2 = other.x + other.width + this.width / 2;
		int y1 = other.y - this.height /2;
		int y2 = other.y + other.height + this.height / 2;
		return this.x + this.width / 2 > x1  && this.x + this.width / 2 < x2&& this.y + this.height / 2 > y1&& this.y + this.width / 2 < y2;
  
	}
	public void Tnt(){
		TNT++;
	}
	public int getTnt(){
		if(TNT>=0)return TNT;
		else return 0;
	}
	
}