package day09;

/**
 * �л�����
 * �̳���FlingObject��
 * �Ὣ����Ĺ����ı������Ժͺ������̳�����
 * @author asus
 *java�еļ̳й�ϵ��ֻ����̳�һ�����࣬���̳еģ�
 *�����ʵ�ֶ�̳е����󣬿���ʹ�ýӿڵĸ�������ʵ��
 */
public  class Airplane2 extends FlyingObject implements Enemy{
		
	//����
	//����Ҫ�ٶ���x��y��width��height��image
	//������Щ���ԣ���ֱ�ӴӸ����м̳�����
	
	//�л�����������
	//���·ɵ��ٶȣ�2����
	private int speed = 2;
	
	//������
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
	//���ܷ���
	//ʵ��Enemy�ӿ��еķ���4
	public int getScore(){
		return 5;
	}
	@Override
	public void step() {
		y += speed;
		
	}
	@Override
	public boolean outOfBounds() {
		// Խ�紦��
		return y>ShootPanel.HEIGHT;
	}
}