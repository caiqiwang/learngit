package demo1;

/*
 * 多线程学习
 */
public class Thread1 extends Thread{
	private  String name;
	
	
	public Thread1(String name){
		super(name);
		this.name=name;
	}
	public void run(){ //测试join方法
		System.out.println(Thread.currentThread().getName()+"线程运行开始");
		for(int i=0;i<5;i++){
			System.out.println("子线程"+name+"运行："+i+"次");
			try {
				sleep((int)Math.random()*10);
			} catch (Exception e) {
				// TODO: handle exception
			}
		
		}
		System.out.println(Thread.currentThread().getName()+"线程运行结束");
	}
}
