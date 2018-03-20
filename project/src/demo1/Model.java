package demo1;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Model implements Runnable{ //生产者1
	private BlockingQueue<String> queue;
	private AtomicInteger count;
	private boolean isRunning=true;
	public Model(BlockingQueue<String> queue,AtomicInteger count,ExecutorService service){
		this.queue=queue;
		this.count=count;
		service.execute(this);
	}
	@Override
	public void run() {
		try {
			while(isRunning){
				String str="a";
				int number=count.incrementAndGet();
				queue.put(str+number);
				TimeUnit.SECONDS.sleep(5);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		// TODO Auto-generated method stub
		
	}
	public void stop(){
		isRunning=false;
	}
}
 class Model2 implements Runnable{//生产者2
	 private BlockingQueue<String> queue1;
	 private BlockingQueue<String> queue2;
	 private AtomicInteger count;
	 private ExecutorService service;
	 private boolean isRunning=true;
	 public Model2(BlockingQueue<String> queue1,BlockingQueue<String> queue2,AtomicInteger count,ExecutorService service){
		 this.queue1=queue1;
		 this.queue2=queue2;
		 this.count=count;
		 this.service=service;
		 service.execute(this);
	 }
	@Override
	public void run() {
		try {
			while(isRunning){
			TimeUnit.SECONDS.sleep(10);
			String str=queue1.take();
			int number=count.incrementAndGet();
			queue2.put(str+number);
			Model3 m3=new Model3(queue2,service);
			//启动消费者线程
			
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		// TODO Auto-generated method stub
		
	}
	public void stop(){
		isRunning=false;
	}
 }
 class Model3 implements Runnable{//消费者
	 private BlockingQueue<String> queue;
	// private AtomicInteger count;
	 private boolean isRunning=true;
	 public Model3(BlockingQueue<String> queue,ExecutorService service){
		 //this.count=count;
		 this.queue=queue;
		 service.execute(this);
	 }
	 public Model3(){
		 
	 }
	@Override
	public void run() {
		try {
			while(isRunning){
			TimeUnit.SECONDS.sleep(10);
			String str=queue.take();
			System.out.println(str);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		// TODO Auto-generated method stub
		
	}
	public void stop(){
		isRunning=false;
	}
 }
