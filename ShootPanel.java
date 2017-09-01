package day09;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ShootPanel extends JPanel {
	//常量
	private int nbm=0;
	private int nam=0;
	private int wenziX = 0;
	private int wenziY = 55;
	private boolean W = false;
	//面板的 宽 和 高
	private Timer timer; // 定时器
	private int intervel = 1000 / 100; 
	public static final int WIDTH = 495;
	public static final int HEIGHT = 900;
	private int score = 0;
	private int state;
	public static final int START = 0; 
	public static final int RUNNING = 1; 
	public static final int PAUSE = 2; 
	public static final int GAME_OVER = 3; 
	//属性
	//背景图片，buffer缓冲区，带有缓冲区的图片
	public static BufferedImage bgImage;
	public static BufferedImage airplaneImage;
	public static BufferedImage airplaneImage1;
	public static BufferedImage airplaneImage2;
	public static BufferedImage beeImage;
	public static BufferedImage bullet0Image;
	public static BufferedImage bullet1Image;
	public static BufferedImage bullet2Image;
	public static BufferedImage gameoverImage;
	public static BufferedImage hero0Image;
	public static BufferedImage hero1Image;
	public static BufferedImage pauseImage;
	public static BufferedImage startImage;
	public static BufferedImage boomImage;
	public static BufferedImage daodan;
	
	static{
		//静态代码块，当 当前类文件被从硬盘加载到内存时，该代码块中的代码会被执行，且执行这一次！
		//加载图片的工作只需要执行一次！提前加载到内存！
		//从硬盘中读取文件，比内存中操作慢
		try{
			System.out.println("加载图片文件...");
			bgImage = ImageIO.read(ShootPanel.class.getResource("8.jpg"));
			airplaneImage = ImageIO.read(ShootPanel.class.getResource("diji.png"));
			airplaneImage1 = ImageIO.read(ShootPanel.class.getResource("1.png"));
			airplaneImage2 = ImageIO.read(ShootPanel.class.getResource("3.png"));
			beeImage = ImageIO.read(ShootPanel.class.getResource("bee.png"));
			bullet0Image = ImageIO.read(ShootPanel.class.getResource("6.png"));
			bullet1Image = ImageIO.read(ShootPanel.class.getResource("10.png"));
			bullet2Image = ImageIO.read(ShootPanel.class.getResource("11.png"));
			gameoverImage = ImageIO.read(ShootPanel.class.getResource("jiesu.png"));
			hero0Image = ImageIO.read(ShootPanel.class.getResource("4.png"));
			hero1Image = ImageIO.read(ShootPanel.class.getResource("5.png"));
			pauseImage = ImageIO.read(ShootPanel.class.getResource("pause1.png"));
			startImage = ImageIO.read(ShootPanel.class.getResource("start0.jpg"));
			boomImage = ImageIO.read(ShootPanel.class.getResource("9.png"));
			daodan = ImageIO.read(ShootPanel.class.getResource("daodan.png"));
		}catch(IOException e){
			e.printStackTrace();
			}
	}
	
	
	//属性
	//定义飞行物数组，用于存放敌机和蜜蜂
	private FlyingObject[] flyings;
	private Bullet[] bullets;
	private Hero hero;
	//private Daodan [] daodans;
	//构造器
	//给属性，进行赋值初始化
	/*public void stepAction1()
	{
		for(int i = 0;i < daodans.length;i++)
	   {
		  Daodan d = daodans[i];
		   if(W)
		  {
			 W = false;
			 d.step();
		  }
	   }
	}*/
	private LinkedList<Bullet> bulletList;
	private MediaPlayer player;
	public ShootPanel(){
		player = new MediaPlayer("music.wav");
		new Thread(player).start();
		flyings = new FlyingObject[2];
		flyings[0] = new Airplane();
		flyings[1] = new Bee();
		
		
		bullets = new Bullet[3];
		bullets[0] = new Bullet(180,360,0);
		bullets[1] = new Bullet(180,350,1);
		bullets[2] = new Bullet(180,340,2);
		
		//daodans = new Daodan[2];
		//daodans[0] = new Daodan(180,250);
		//daodans[1] = new Daodan(180,50);
		hero = new Hero();
	}
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(bgImage, 0, 0, null);
		paintHero0(g);
		paintBullets(g);
		paintFlyingObjects(g);
		paintScore(g);
		paintState(g);
		//paintDaodan(g);
		g.setColor(Color.BLUE);
	}
	private void paintFlyingObjects(Graphics g) {
		g.drawImage(hero.getImage(), hero.getX(), hero.getY(), null);
		
	}
	private void paintBullets(Graphics g) {
		for (int i = 0; i < flyings.length; i++) {
			FlyingObject fo = flyings[i];
			g.drawImage(fo.getImage(), fo.getX(),fo.getY(), null);
		}
		
	}
	private void paintHero0(Graphics g) {
		for (int i = 0; i < bullets.length; i++) {
			Bullet b = bullets[i];
			g.drawImage(b.getImage(), b.getX(), b.getY(), null);
		}
		
	}
	/*private void paintDaodan(Graphics g)
	{
		for(int i = 0;i < daodans.length;i++)
		{
			Daodan d = daodans[i];
		    g.drawImage(d.getImage(), d.getX(), d.getY(),null );
		}
	}*/
	
	//让所有飞行物动一下step（）
	public void stepAction() {
		for (int i = 0; i < flyings.length; i++) {
			FlyingObject f = flyings[i];
			f.step();
		}
		for (int i = 0; i < bullets.length; i++) {
			Bullet b = bullets[i];
			b.step();
		}
		hero.step();
	}
	
	
	//随机生成一个飞行物：敌机  和  蜜蜂
	//敌机可以生成多一些，蜜蜂少一些
	public FlyingObject nextOne(){
		Random rd = new Random();
		int num = rd.nextInt(24);
		if(num == 0){
			//5%的几率生成蜜蜂奖励
			
			return new Bee();
		}else if(num == 1){
			//5%的几率生成炸弹
			return new Boom();
		}else if(num>1&&num<10){
			//90%的几率生成敌机
			return new Airplane();
		}else if(num>=8&&num<16){
			return new Airplane1();
		}else{
			return new Airplane2();
		}
		
	}
	//每隔400ms，生成一个飞行物，并进入游戏界面
	//400ms：400ms/10ms=40次才生成一个飞行物
	//进入游戏：将生成的飞行物，保存到flyings数组中
	private int flyingEnteredIndex = 0;
	public void enterAction(){
		flyingEnteredIndex++;
		if(flyingEnteredIndex%40 ==0){
			//调用nextOne，随机生成一个飞行物
			FlyingObject f = nextOne();
			//将生成的飞行物，保存到flyings数组中
			flyings = Arrays.copyOf(flyings,flyings.length+1);
			//copyOf(原数组，新数组的长度）
			//将flyings数组，拷贝出一个新的数组
			//新数组长度比旧数组多一个位置
			//同时将原数组的内容，拷贝到新数组空间中
			//通过 = 赋值运算符，让flyings指向新数组空间
			//将新的飞行物，保存到新数组的最后一个位置
			flyings[flyings.length - 1] = f;
			//将原数组扩容，多增加一个空位置
			//重新创建一个新的数组空间
			//int[] ary = new int[10];
			int length = flyings.length + 1;
			FlyingObject[] ary =new FlyingObject[length];
			//将原数组的内容拷贝到新数组中去
			//flyings[] --> 目标数组/新数组ary[]
			for (int i = 0; i < flyings.length; i++) {
				ary[i] = flyings[i];
			}
			//将生成的飞行物，存放到最后这个空位置
			ary[ary.length-1] = f;
			//让flyings数组变量，指向新的数组空间
			flyings = ary;
			//将ary变量中的数组地址，拷贝到flyings中
			if(nbm-nam>0){
				FlyingObject[] array =new FlyingObject[0];
				flyings = array;
				nam=nbm;
			}
		}
	}
	int  shootIndex = 0;
	public void shootAction(){
		shootIndex++;
		if(shootIndex % 16 ==0){
			//100ms发一颗子弹
			Bullet[] bs = hero.shoot();
			bullets = Arrays.copyOf(bullets, bullets.length+bs.length);
			System.arraycopy(bs, 0, bullets, bullets.length-bs.length,bs.length);
		}
	}
	public void bangAction(){
		for (int i = 0; i < bullets.length; i++) {
			//遍历所有子弹
			Bullet b = bullets[i];
			bang(b);
		}
	}
	public void bang(Bullet bullet){
		int index = -1;
		for (int i = 0; i < flyings.length; i++) {
			FlyingObject obj = flyings[i];
			if(obj.shootBy(bullet)){
				index =i;  //记录被击中的飞行物的索引
				bullet.x = -100;
				bullet.y = -100;
				break;
			}
		}
		if(index != -1){
			//有击中的飞行物
			FlyingObject one = flyings[index];
			//记录被击中的飞行物
			FlyingObject temp = flyings[index];
			//被击中的飞行物于最后一个飞行物交换
			flyings[index] = flyings[flyings.length-1];
			flyings[flyings.length - 1] = temp;
			//删除最后一个飞行物（即被击中的）
			flyings = Arrays.copyOf(flyings, flyings.length-1);
			//检查one的类型，如果是敌人，就算分
			if(one instanceof Enemy){
				//检查类型，是敌人，则加分
				Enemy e = (Enemy) one;
				score += e.getScore();
			}
			if(one instanceof Award){
				//若为奖励，设置奖励类型
				Award a = (Award) one;
				int type = a.getType();
				//获取奖励类型
				switch(type){
				case Award.DOUBLE_FIRE:
					hero.addDoubleFire();
					break;
				case Award.LIFE:
					hero.addLife();
					break;
				case Award.TNT:
					hero.Tnt();
					break;
				case Boom.BOOM:
					score -= 10;
					break;
				}
			}
		}
	}
	public void paintScore(Graphics g){
		int x = 10;
		int y = 25;
		Font font = new Font(Font.SANS_SERIF, Font.BOLD, 24);
		g.setColor(Color.RED);
		g.setFont(font);
		g.drawString("SCOER:"+score, 0, 25);
		y += 20;
		g.drawString("LIFE:"+hero.getLife(), 0, 45);
		g.drawString("TNT:"+ hero.getTnt(), 0,65);
	}
	public void paintState(Graphics g){
		switch(state){
		case START:
			g.drawImage(startImage, 0, 0, null);
			break;
		case PAUSE:
			g.drawImage(pauseImage, 0, 0, null);
			break;
		case GAME_OVER:
			g.drawImage(gameoverImage, 0, 0, null);
			break;
		}
	}
	public void action(){
		this.requestFocus();
		//2.给面板，添加事件处理对象
		this.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				
				//可以从KeyEvent对象中，获得被按下键的编码值
				//左；上；下；右
				int key = e.getKeyCode();
				System.out.println(key);
				switch(key){
				case KeyEvent.VK_F1:
					if(hero.TNT>0){
						nbm++;
						hero.TNT--;
						W=true;
						
					}
					break;
				}
			}
		});
		
		
		
		
		
		MouseAdapter l = new MouseAdapter(){
			public void mouseMoved(MouseEvent e) 
			{ // 鼠标移动
				if(state == RUNNING){
					int x = e.getX(); 
					int y = e.getY();
					hero.moveTo(x, y);
				}
				
			}
		//处理鼠标滑动操作
		public void mouseEntered(MouseEvent e){
			//鼠标进入
			if(state == PAUSE){
				state = RUNNING;
			}
		}
		public void mouseExited(MouseEvent e){
			if(state != GAME_OVER){
				state = PAUSE;
			}
		}
		public void mouseClicked(MouseEvent e){
			switch(state){
			case START:
				state = RUNNING;
				break;
			case GAME_OVER:
				flyings = new FlyingObject[0];
				bullets = new Bullet[0];
				hero = new Hero();
				score = 0;
				state =START;
				
				break;
			}
		}
	};
		this.addMouseListener(l); 
		this.addMouseMotionListener(l);
		timer = new Timer(); // 主流程控制 
		timer.schedule(new TimerTask() { 
			public void run() { 
			if (state == RUNNING) { 
			enterAction();//让飞行物进场
			stepAction();
			shootAction();//射击
			bangAction();
			outOfBoundAction();
			checkGameOverAction();
			}
			
			repaint();
			}
			
		},intervel,intervel);
		
	}
	
	public void outOfBoundAction(){
		int index = 0;
		//存储活着的飞行物
		FlyingObject[] flyingLives = new FlyingObject[flyings.length];
		for (int i = 0; i < flyings.length; i++) {
			FlyingObject f = flyings[i];
			if(!f.outOfBounds()){
				flyingLives[index++] = f;
			}
		}
		flyings = Arrays.copyOf(flyingLives, index);
		//将不越界的飞行物都留着
		index = 0;
		Bullet[] bulletLives = new Bullet[bullets.length];
		for (int i = 0; i < bullets.length; i++) {
			Bullet b = bullets[i];
			if(!b.outOfBounds()){
				bulletLives[index++] = b;
			}
		}
		bullets = Arrays.copyOf(bulletLives, index);
		//将不越界的子弹留着
	}
	public boolean isGameOver(){
		for (int i = 0; i < flyings.length; i++) {
			int index = -1;
			FlyingObject obj = flyings[i];
			if(hero.hit(obj)){
				hero.subtractLife();
				hero.setDoubleFire(0);
				index = i;
			}
			if(index != -1){
				FlyingObject t = flyings[index];
				flyings[index] = flyings[flyings.length - 1];
				flyings[flyings.length - 1] =t;
				flyings = Arrays.copyOf(flyings, flyings.length - 1);
			}
		}
		return hero.getLife() <= 0;
	}
	public void checkGameOverAction(){
		if(isGameOver()){
			state = GAME_OVER;//改变状态
		}
	}
	
	
	
}
