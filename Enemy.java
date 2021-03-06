package day09;
/**
 * 敌机类型
 * @author asus
 *java中使用接口，来变向的实现类似于多继承的现象
 *将abstract的修改符，修改为interface接口
 *interface接口是一种特殊的抽象类
 *其中所有的函数/方法，都是抽象方法
 *其中所有的属性都是常量，没有变量
 *类似于C++中的，纯虚抽象类
 *
 *因此，作为接口中的子类/实现类，必须实现编写接口中所有的方法（因为所有方法都是抽象的）
 *
 *可理解为，要认干爹，必须实现干爹的梦想或想法
 *通常在接口中编写方法给出对子类的约定！
 *如：计算机中的接口USB接口
 *
 */
public /*abstract class*/ interface Enemy { 
	//对于不同的敌人，消灭后返回的分值不一样
	//小飞机打掉后得5分，打boss后得10分
	//因此，无法直接给出具体的代码
	//那么不写
	//同时，在方法声明上加上abstract，表示抽象的
	//在语法上，要求抽象函数所在的类，必须是抽象类
	/*public abstract*/ int getScore();
	//接口中的方法，默认是公开抽象的，可以不明确写出！
}
