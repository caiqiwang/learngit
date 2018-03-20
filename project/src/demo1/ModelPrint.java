package demo1;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class ModelPrint implements Runnable{
	private int count=30;
	private String str;
	private BlockingQueue<String> queue;
	public ModelPrint(BlockingQueue<String> queue,ExecutorService service,int count){
		this.queue=queue;
		service.execute(this);
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			while(count>0){
				switch (count%3) {
				case 0:
					str="A";
					queue.put(str);
					break;
				case 2:
					str="B";
					queue.put(str);
					break;
				default:
					str="C";
					queue.put(str);
					break;
				}
				count--;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public static void main(String[] args) {
		BlockingQueue<String> queue=new LinkedBlockingQueue<String>(1);
		ExecutorService service=Executors.newCachedThreadPool();
		new ModelPrint(queue, service,30);
		new ModelPrint2(queue,service,30);
		System.out.println();
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		service.shutdown();
	}
}
class ModelPrint2 implements Runnable{
	private BlockingQueue<String> queue;
	private ExecutorService service;
	private int count=30;
	public ModelPrint2(BlockingQueue<String> queue,ExecutorService service,int count){
		this.queue=queue;
		service.execute(this);
	}
	@Override
	public void run() {
		try {
			while(true){
				String str=queue.take();
				System.out.print(str);
				count--;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
