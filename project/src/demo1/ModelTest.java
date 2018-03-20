package demo1;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicInteger;

public class ModelTest implements Runnable{//生产者
	private BlockingQueue<String> queue;
	private AtomicInteger count;
	public ModelTest(BlockingQueue<String> queue,ExecutorService service,AtomicInteger count){
		this.queue=queue;
		this.count=count;
		service.execute(this);
	}
	@Override
	public void run() {
		try {
			String str1="abc";
			int number=count.getAndIncrement();
			queue.put((str1+number));
			System.out.println();
		} catch (Exception e) {
			// TODO: handle exception
		}
		// TODO Auto-generated method stub
		
	}

}
class Consumer implements Runnable{    //消费者
	private BlockingQueue<String> queue;
	public Consumer(BlockingQueue<String> queue,ExecutorService service){
		this.queue=queue;
		service.execute(this);
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			String str=queue.take();
			System.out.println(str);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
}
