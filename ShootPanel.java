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
	//����
	private int nbm=0;
	private int nam=0;
	private int wenziX = 0;
	private int wenziY = 55;
	private boolean W = false;
	//���� �� �� ��
	private Timer timer; // ��ʱ��
	private int intervel = 1000 / 100; 
	public static final int WIDTH = 495;
	public static final int HEIGHT = 900;
	private int score = 0;
	private int state;
	public static final int START = 0; 
	public static final int RUNNING = 1; 
	public static final int PAUSE = 2; 
	public static final int GAME_OVER = 3; 
	//����
	//����ͼƬ��buffer�����������л�������ͼƬ
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
		//��̬����飬�� ��ǰ���ļ�����Ӳ�̼��ص��ڴ�ʱ���ô�����еĴ���ᱻִ�У���ִ����һ�Σ�
		//����ͼƬ�Ĺ���ֻ��Ҫִ��һ�Σ���ǰ���ص��ڴ棡
		//��Ӳ���ж�ȡ�ļ������ڴ��в�����
		try{
			System.out.println("����ͼƬ�ļ�...");
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
	
	
	//����
	//������������飬���ڴ�ŵл����۷�
	private FlyingObject[] flyings;
	private Bullet[] bullets;
	private Hero hero;
	//private Daodan [] daodans;
	//������
	//�����ԣ����и�ֵ��ʼ��
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
	
	//�����з����ﶯһ��step����
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
	
	
	//�������һ��������л�  ��  �۷�
	//�л��������ɶ�һЩ���۷���һЩ
	public FlyingObject nextOne(){
		Random rd = new Random();
		int num = rd.nextInt(24);
		if(num == 0){
			//5%�ļ��������۷佱��
			
			return new Bee();
		}else if(num == 1){
			//5%�ļ�������ը��
			return new Boom();
		}else if(num>1&&num<10){
			//90%�ļ������ɵл�
			return new Airplane();
		}else if(num>=8&&num<16){
			return new Airplane1();
		}else{
			return new Airplane2();
		}
		
	}
	//ÿ��400ms������һ���������������Ϸ����
	//400ms��400ms/10ms=40�β�����һ��������
	//������Ϸ�������ɵķ�������浽flyings������
	private int flyingEnteredIndex = 0;
	public void enterAction(){
		flyingEnteredIndex++;
		if(flyingEnteredIndex%40 ==0){
			//����nextOne���������һ��������
			FlyingObject f = nextOne();
			//�����ɵķ�������浽flyings������
			flyings = Arrays.copyOf(flyings,flyings.length+1);
			//copyOf(ԭ���飬������ĳ��ȣ�
			//��flyings���飬������һ���µ�����
			//�����鳤�ȱȾ������һ��λ��
			//ͬʱ��ԭ��������ݣ�������������ռ���
			//ͨ�� = ��ֵ���������flyingsָ��������ռ�
			//���µķ�������浽����������һ��λ��
			flyings[flyings.length - 1] = f;
			//��ԭ�������ݣ�������һ����λ��
			//���´���һ���µ�����ռ�
			//int[] ary = new int[10];
			int length = flyings.length + 1;
			FlyingObject[] ary =new FlyingObject[length];
			//��ԭ��������ݿ�������������ȥ
			//flyings[] --> Ŀ������/������ary[]
			for (int i = 0; i < flyings.length; i++) {
				ary[i] = flyings[i];
			}
			//�����ɵķ������ŵ���������λ��
			ary[ary.length-1] = f;
			//��flyings���������ָ���µ�����ռ�
			flyings = ary;
			//��ary�����е������ַ��������flyings��
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
			//100ms��һ���ӵ�
			Bullet[] bs = hero.shoot();
			bullets = Arrays.copyOf(bullets, bullets.length+bs.length);
			System.arraycopy(bs, 0, bullets, bullets.length-bs.length,bs.length);
		}
	}
	public void bangAction(){
		for (int i = 0; i < bullets.length; i++) {
			//���������ӵ�
			Bullet b = bullets[i];
			bang(b);
		}
	}
	public void bang(Bullet bullet){
		int index = -1;
		for (int i = 0; i < flyings.length; i++) {
			FlyingObject obj = flyings[i];
			if(obj.shootBy(bullet)){
				index =i;  //��¼�����еķ����������
				bullet.x = -100;
				bullet.y = -100;
				break;
			}
		}
		if(index != -1){
			//�л��еķ�����
			FlyingObject one = flyings[index];
			//��¼�����еķ�����
			FlyingObject temp = flyings[index];
			//�����еķ����������һ�������ｻ��
			flyings[index] = flyings[flyings.length-1];
			flyings[flyings.length - 1] = temp;
			//ɾ�����һ��������������еģ�
			flyings = Arrays.copyOf(flyings, flyings.length-1);
			//���one�����ͣ�����ǵ��ˣ������
			if(one instanceof Enemy){
				//������ͣ��ǵ��ˣ���ӷ�
				Enemy e = (Enemy) one;
				score += e.getScore();
			}
			if(one instanceof Award){
				//��Ϊ���������ý�������
				Award a = (Award) one;
				int type = a.getType();
				//��ȡ��������
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
		//2.����壬����¼��������
		this.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				
				//���Դ�KeyEvent�����У���ñ����¼��ı���ֵ
				//���ϣ��£���
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
			{ // ����ƶ�
				if(state == RUNNING){
					int x = e.getX(); 
					int y = e.getY();
					hero.moveTo(x, y);
				}
				
			}
		//������껬������
		public void mouseEntered(MouseEvent e){
			//������
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
		timer = new Timer(); // �����̿��� 
		timer.schedule(new TimerTask() { 
			public void run() { 
			if (state == RUNNING) { 
			enterAction();//�÷��������
			stepAction();
			shootAction();//���
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
		//�洢���ŵķ�����
		FlyingObject[] flyingLives = new FlyingObject[flyings.length];
		for (int i = 0; i < flyings.length; i++) {
			FlyingObject f = flyings[i];
			if(!f.outOfBounds()){
				flyingLives[index++] = f;
			}
		}
		flyings = Arrays.copyOf(flyingLives, index);
		//����Խ��ķ����ﶼ����
		index = 0;
		Bullet[] bulletLives = new Bullet[bullets.length];
		for (int i = 0; i < bullets.length; i++) {
			Bullet b = bullets[i];
			if(!b.outOfBounds()){
				bulletLives[index++] = b;
			}
		}
		bullets = Arrays.copyOf(bulletLives, index);
		//����Խ����ӵ�����
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
			state = GAME_OVER;//�ı�״̬
		}
	}
	
	
	
}
