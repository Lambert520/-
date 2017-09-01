package day09;

import java.util.Random;

/**
 * �۷�����
 * �۷���һ�����������һ������
 *
 */
public class Bee extends FlyingObject implements Award{
	//����
	private int xSpeed = 1;//���������ƶ����ٶ�
	private int ySpeed = 2;//���������ƶ����ٶ�
	private int awardType;//�۷佱��������
	//����۷佱������
	
	//������
	//��new��������ʱ��ʹ�ù����������Ը�ֵ��ʼ��
	public Bee(){
		this.image = ShootPanel.beeImage;
		//ֱ��ȡͼƬ����Ŀ�ߣ���Ϊ�۷�Ŀ��
		this.width = image.getWidth();
		this.height = image.getHeight();
		
		//ʹ��һ�����ĸ߶���Ϊy����ֵ
		//��Ϊ��ȡͼƬ�����ϽǶ��㣬��Ϊ���������ֵ
		this.y = -height;
		
		//�۷��ǽ�����ϱ�Ե�������
		//ʹ����������߲���һ���������Ϊ������ֵ
		Random rd = new Random();
		int bound = ShootPanel.WIDTH - width;
		this.x = rd.nextInt(bound);
		
		//�۷�Ľ�������
		//�������������֣�0��ʾ20��˫��������1��ʾһ������
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
			xSpeed = -1;//�����
		}
		if(x<0){
			xSpeed = 1;//���ҷ�
		}
	}
	public boolean outOfBounds(){
		return y>ShootPanel.HEIGHT;
	}
	
}
