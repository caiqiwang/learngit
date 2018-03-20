package demo1;

import java.util.List;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Producers implements Runnable{
	public BlockingQueue<String> strList;
	private AtomicInteger count ;
	public Producers(BlockingQueue<String> list,AtomicInteger count,ExecutorService service){
		this.strList=list;
		this.count = count;
		service.execute(this);
	}
	 
	@Override
	public void run() {
		 String a ="aaa";
		 int num = count.incrementAndGet();
		 try {
			strList.put(a+num);
			System.out.println();
		} catch (InterruptedException e) {
			System.out.println();
			e.printStackTrace();
		}
	}
	 
}
