package day09;
/**
 * 奖励类型，接口
 * 在该接口中，规定了奖励的类型，只要是奖励，必须明确实现getType方法
 * 蜜蜂Bee类型，就会实现该接口，实现该接口的，实现了获得奖励类型的方法
 *
 */
public interface Award {
	//常量表示奖励类型
	int DOUBLE_FIRE = 0;	//双倍火力
	int LIFE = 1;			//增加一条命
	int TNT = 2;			//炸弹	
	int BOOM = 3;
	//获得奖励类型
	int getType();
	
}
