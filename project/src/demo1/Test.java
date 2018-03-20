package demo1;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Test extends extend2 {
	public static void main(String[] args){
		/*Test test=new Test();
		extend2.test(test);*/
		BlockingQueue<String> queue1=new LinkedBlockingQueue<String>(5);
		BlockingQueue<String> queue2=new LinkedBlockingQueue<String>(5);
		AtomicInteger count=new AtomicInteger(0);
		ExecutorService service=Executors.newCachedThreadPool();
		Model m1=new Model(queue1,count,service);
		Model2 m2=new Model2(queue1,queue2,count,service);
		//Model3 m3=new Model3(queue2,service);
		Model3 m3=new Model3();
		try {
			TimeUnit.SECONDS.sleep(15);
			System.out.println();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.err.println("主线程sleep出现错误");
			e.printStackTrace();
		}
		m1.stop();
		m2.stop();
		m3.stop();
		service.shutdownNow();
	}
	
}
