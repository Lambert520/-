package day09;

import java.awt.image.BufferedImage;

/**
 * ������
 * ������ǽ��������з������������
 * ��Ӣ�ۡ��л����۷䡢�ӵ��ĸ���
 * ���������Ĺ������Ի�ͨ���ܣ�������ȡ����ǰ����
 * @author asus
 *abstract�����
 *������಻��ֱ�ӱ�Ϊ���󣬼� ����ʹ��new�ؼ���
 *������һ�㵱�����࣬�������̳�
 */
public abstract class FlyingObject extends Object{
	//����
	//һ������£���������Կ�����Ϊ������
	//���磺���������ӡ���صȻ�̳и�����
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	protected BufferedImage image;
	
	//������
	//�޹�����������������һ���������
	
	//���ܷ���
	//���з������һ��
	//���ڲ�ͬ�ķ������һ�µķ�ʽ��һ����
	//�۷�������¶�һ�£������Ҷ�һ��
	//�ӵ������϶�һ�¡�������
	public abstract void step();
	//��װһ��get���������ڷ��ص�ǰ���еı���/˽������
	public int getX(){
		return x;
	}
	//��װһ��set���������ڸ����е���������ֵ
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
	//����Ƿ���߽�
	public abstract boolean outOfBounds();
}
