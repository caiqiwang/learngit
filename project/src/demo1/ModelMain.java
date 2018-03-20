package demo1;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class ModelMain {
	public static void main(String[] args) {
		BlockingQueue<String> queue=new LinkedBlockingQueue<>(10);
		AtomicInteger count =new AtomicInteger(0);
		ExecutorService service=Executors.newCachedThreadPool();
		ModelTest test=new ModelTest (queue, service,count);
		Consumer consumer=new Consumer(queue,service);
		System.out.println();
	}
}
