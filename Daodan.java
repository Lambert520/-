package day09;

import java.util.Random;

public class Daodan extends FlyingObject{

	private int ySpeed = 1;//���������ƶ����ٶ�
	//����۷佱������
	
	//������
	//��new��������ʱ��ʹ�ù����������Ը�ֵ��ʼ��
	public  Daodan(int x , int y){
		this.image = ShootPanel.daodan;
		//ֱ��ȡͼƬ����Ŀ�ߣ���Ϊ�۷�Ŀ��
		this.width = image.getWidth();
		this.height = image.getHeight();
		//this.x = 200;
		//this.y = 837 + height;
		this.x = x;
		this.y = y;
		//ʹ��һ�����ĸ߶���Ϊy����ֵ
		//��Ϊ��ȡͼƬ�����ϽǶ��㣬��Ϊ���������ֵ
		
	}
		//�۷��ǽ�����ϱ�Ե�������
		//ʹ����������߲���һ���������Ϊ������ֵ
		
		//�۷�Ľ�������
		//�������������֣�0��ʾ20��˫��������1��ʾһ������

	
	
	public void step() {
		y -= ySpeed;
		
	}
	public boolean outOfBounds(){
		return y<0;
	}

	
}
