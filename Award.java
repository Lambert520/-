package day09;
/**
 * �������ͣ��ӿ�
 * �ڸýӿ��У��涨�˽��������ͣ�ֻҪ�ǽ�����������ȷʵ��getType����
 * �۷�Bee���ͣ��ͻ�ʵ�ָýӿڣ�ʵ�ָýӿڵģ�ʵ���˻�ý������͵ķ���
 *
 */
public interface Award {
	//������ʾ��������
	int DOUBLE_FIRE = 0;	//˫������
	int LIFE = 1;			//����һ����
	int TNT = 2;			//ը��	
	int BOOM = 3;
	//��ý�������
	int getType();
	
}
