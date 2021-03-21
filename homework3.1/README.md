# 飞机大    继承窗体类，复写窗体刷新

## 制作思路
- **继承窗体类，复写窗体刷新 **

- **添加按键监听**

- **游戏结束**

- **图类 移动轨迹计算**

- **判断碰撞 音乐播放**

- **不断刷新窗体 处理细节**
## 编码
### 1.图片
1)百度找到图片
2)需要加载图片的工具类，可以使用getResource得到图片的UEL路径，然后使用Java自带的工具类imageIO来读取图片

```java
public class GameUtil {

	public static Image getImage(String path){
		URL url=GameUtil.class.getClassLoader().getResource(path);
		BufferedImage img=null;
		try {
			img = ImageIO.read(url);
		} catch (IOException e) {
			e.printStackTrace();
		}	
		return img;
	}
}
```
### 2.窗体
写一个类来继承窗体，为其设置宽高，位置，关闭设置，可否缩放大小
复写它的更新和绘画方法，以后要在里面写绘画图像和碰撞判断逻辑
为其添加按键监听事件，在这里处理按键的点击和释放，以此来实现图像的移动
``` java
    	public void launchFrame(){
    		setSize(width,height);
    		setResizable(false);
    		setLocation(200,20);
    		setVisible(true);
    		addKeyListener(new KeyMoniter());
    		addWindowListener(new WindowAdapter(){
    			@Override
    			public void windowClosing(WindowEvent e) {
    				System.exit(0);
    			}
    		});
    	}
     
            @Override
    	public void paint(Graphics graphics) {}
    	@Override
    	public void update(Graphics g){}
     
    	class KeyMoniter extends KeyAdapter{
    		@Override
    		public void keyPressed(KeyEvent e) {
    	            //按键按下
    		}
    		@Override
    		public void keyReleased(KeyEvent e) {
    		    //按键抬起
    		}	
    	}
```
### 3.游戏角色与飞机类
首先要有一个位置，还要能移动，其实也就是不断的计算位置和把图片画上去的过程
定义一些图像的属性，并在构造方法中初始化这些属性
``` java
    	boolean left,right,down,up;
    	
    	public int x,y,width,height;
    	
    	Image img ;
     
    	public Plane(String img_path,int x, int y){
    		this.img = GameUtil.getImage(img_path);
    		this.x = x;
    		this.y = y;
    		width = img.getWidth(null);
    		height = img.getWidth(null);
    		live = true;
    	}
```
把思聪画上去
```java
    	public void draw(Graphics g){
    		if(live){
    			g.drawImage(img, x, y, null);
    			move();
    		}
    	}
```
接下来最重要的就是计算游戏角色的位置了，为什么要把位置计算写到画思聪的方法里呢？主要是考虑这样的一种情况，如果你把方法计算写到按键按下或者释放里面，那么你想移动思聪的位置就得疯狂按键盘，要想长按按键来控制移动就要吧移动方法写到画思聪的方法里面，然后用按键的按下和释放来控制是否移动。

移动的时候还要考虑到边界的情况
```java
    	public void move(){
    		if(left&&x>=10){
    			x -= 10;
    		}		
    		if(up&&y>=30){
    			y -= 10;
    		}
    		if(right&&x<=FeiJiGame.width-60){
    			x += 10;
    		}
    		if(down&&y<=FeiJiGame.height -60){
    			y += 10;
    		}
    	}
     
    	public void KeyPressedControlDirection(KeyEvent e){
    		int key_code = e.getKeyCode();
    		if(key_code == 37){
    			left = true;
    		}
    		if(key_code == 38){
    			up = true;
    		}
    		if(key_code == 39){
    			right = true;
    		}
    		if(key_code == 40){
    			down = true;
    		}
    	}
     
    	public void KeyRelasedControlDirection(KeyEvent e){
    		int key_code = e.getKeyCode();
    		if(key_code == 37){
    			left = false;
    		}
    		if(key_code == 38){
    			up =false;
    		}
    		if(key_code == 39){
    			right = false;
    		}
    		if(key_code == 40){
    			down = false;
    		}
    	}
```
最后提供两个方法来改写的生存
对于飞机类就简单多了，就是让它不断的跑，不需要响应按键事件
定义一些属性和在构造方法中初始化

```java
    	double speed=15;
    	double degree;
     
    	public double x,y;
    	public int width,height;
    	Image img;
     
    	public Bullet(String img_path){
    		img=GameUtil.getImage(img_path);
    		degree = Math.random()*Math.PI*2;
    		x=FeiJiGame.width/2;
    		y=FeiJiGame.height/2;
    		width = 10;
    		height = 10;
    	}
```
随机的degree的用途是让它们的初始方向不同，使用sin和cos函数来控制热狗的移动，同时也要注意边界
``` java
    	public void draw(Graphics g){
    		g.drawImage(img, (int)x, (int)y, null);
    		x += speed*Math.cos(degree);
    		y += speed*Math.sin(degree);
     
    		if(x>FeiJiGame.width-width||x<width){
    			degree=Math.PI-degree;
    		}
    		if(y>FeiJiGame.height-height||y<height){
    			degree=-degree;
    		}
    	}
```
### 4.碰撞检测和音乐播放
在java的awt里面有一个矩形类可以判断两个矩形是否有重叠部分，有重叠说明就碰撞上了
```java
    Rectangle bulletRectangle = new Rectangle((int)bullet.x,(int)bullet.y,bullet.width,bullet.height);
    Rectangle planeRectangle = new Rectangle(plane.x,plane.y,plane.width,plane.height);
    boolean collide= bulletRectangle.intersects(planeRectangle);
```
音乐播放可以使用第三方的库，jl-1.0.1.jar,因为可能连续达成，所以需要多线程播放音乐
``` java
    	class MusicPlayer implements Runnable{
    		@Override
    		public void run() {
    			try {
    				new Player(new FileInputStream(FeiJiGame.class.getClassLoader().getResource("raw/music.mp3").getPath().substring(1))).play();
    			} catch (Exception e) {
    				e.printStackTrace();
    			}
    		}
    	}
```
5.完成

剩下的就是窗体的刷新，这个可以开一个线程来做
``` java
    	class PaintThread extends Thread {
    		@Override
    		public void run() {
    			while(!Thread.currentThread().isInterrupted()){
    				repaint();
    				try {
    					Thread.sleep(40);
    				} catch (InterruptedException e) {
    					Thread.currentThread().interrupt();
    				}
    			}
    		}	
    	}
```
游戏右上方要显示现在的游戏时间，结束也要显示一些文字信息，写了一个工具类
使用font控制字体，graphics.setFont设置字体，drawString画上去
``` java
    	public void printInfo(Graphics g,String message,int size,int x,int y){
    		g.setColor(Color.white);
    		Font f = new Font("宋体",Font.BOLD,size);
    		g.setFont(f);
    		g.drawString(message, x,y);
    	}
```
游戏结束时显示一些信息，根据时间判断给出等级
``` java
    	private void gameOver(Graphics graphics) {
    		printInfo(graphics,"GAME OVER",80,270,300);
    		int survivalTime = (int)(endTime.getTime()-starTime.getTime())/1000;
    		printInfo(graphics,"吃热狗时间："+survivalTime+"秒",40,300,400);
     
    		switch(survivalTime/10){
    			case 1:
    				printInfo(graphics,"独孤求败",50,350,500);
    				break;
    			case 2:
    				printInfo(graphics,"登堂入室",50,350,500);
    				break;
    			case 3:
    				printInfo(graphics,"小有成就",50,350,500);
    				break;
    			default:
    				printInfo(graphics,"初入江湖",50,350,500);
    				break;
    		}
    		paintThread.interrupt();
    	}

在初始化窗体时要添加热狗和开始重绘线程，开始计时

    		for(int i=0;i<15;i++){
    			Bullet bullet = new Bullet("images/hotdog.png");
    			bulletList.add(bullet);
     		}
    		starTime = new Date();
    		endTime = new Date();
    		paintThread=new PaintThread();
    		paintThread.start();
```
复写绘画和更新
``` java
            @Override
    	public void paint(Graphics graphics) {
    		graphics.drawImage(bg, 0, 0, null);
    		plane.draw(graphics);
    		endTime = new Date();
    		if(gameState){
    			for(int i=0;i<bulletList.size();i++){
    				Bullet bullet=bulletList.get(i);
    				bullet.draw(graphics);
     
    				Rectangle bulletRectangle = new Rectangle((int)bullet.x,(int)bullet.y,bullet.width,bullet.height);
    				Rectangle planeRectangle = new Rectangle(plane.x,plane.y,plane.width,plane.height);
    				boolean collide= bulletRectangle.intersects(planeRectangle);
     
    				if(collide){
    					if (bulletList.size()!=0){
    						executorService.execute(musicPlayer);
    						bulletList.remove(i);
    						if (bulletList.size()==0){
    							gameState = false;
    						}
    					}
    				}
    			}
    		}else {
    			endTime = new Date();
    			gameOver(graphics);
    			paintThread.interrupt();
    		}
     
    		int count_time = (int)(endTime.getTime()-starTime.getTime())/1000;
    		printInfo(graphics,"你已经吃了"+count_time+"秒",20,750,50);
    	}
     
    	Image ImageBuffer = null;  
    	Graphics GraImage = null;
    	@Override
    	public void update(Graphics g){
    	    ImageBuffer = createImage(this.getWidth(), this.getHeight());
    	    GraImage = ImageBuffer.getGraphics();
    	    paint(GraImage);
    	    GraImage.dispose();
    	    g.drawImage(ImageBuffer, 0, 0, this);
    	}  
```
在按键监听里面调用游戏角色的按键按下和释放方法
``` java
    	class KeyMoniter extends KeyAdapter{
    		@Override
    		public void keyPressed(KeyEvent e) {
    			plane.KeyPressedControlDirection(e);
    		}
    		@Override
    		public void keyReleased(KeyEvent e) {
    			plane.KeyRelasedControlDirection(e);
    		}	
    	}
```
最后启动游戏
``` java
    	public static void main(String[] args) {
    		FeiJiGame game = new FeiJiGame();
    		game.loadGame();
    	}
```