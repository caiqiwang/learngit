package demo1;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class mains {
	public static void main(String[] args) throws InterruptedException{
		AtomicInteger count = new AtomicInteger(0);
		ExecutorService service=Executors.newFixedThreadPool(5);//创建线程池
		BlockingQueue<String> queue=new LinkedBlockingQueue<String>(3);
		for(int i =0;i<3;i++){
		new Producers(queue,count,service);}
	//	new consumers(queue,service);
		System.out.println();
	}
}
