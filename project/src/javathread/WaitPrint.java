package javathread;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class WaitPrint {
	public static void main(String[] args) {
		ExecutorService service = Executors.newCachedThreadPool();
		ArrayList<String> arrayList = new ArrayList<String>();
		Object obj = new Object();
		service.execute(new ProduceWatiPrint(obj, arrayList));
		service.execute(new CustomerWaitPrint(obj, arrayList));
		System.out.println();
 		service.shutdownNow();
	}
}

class ProduceWatiPrint implements Runnable { // 生产者
	private final int maxcount = 5;
	private Object obj;
	private ArrayList<String> arrayList;

	public ProduceWatiPrint(Object obj, ArrayList<String> arrayLis) {
		this.obj = obj;
		this.arrayList = arrayLis;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		String str = "aaa";
		int a = 0;
		synchronized (WaitPrint.class) {
			try {
				while (a < 10) {
					while (arrayList.size() > maxcount) {
						WaitPrint.class.wait();
					}
					arrayList.add(str + a);
					WaitPrint.class.notifyAll();
					a++;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}

class CustomerWaitPrint implements Runnable {// 消费者
	private ArrayList<String> arrayList;
	private Object obj;

	public CustomerWaitPrint(Object obj, ArrayList<String> arrayList) {
		this.obj = obj;
		this.arrayList = arrayList;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		synchronized (WaitPrint.class) {
			try {
				while (true) {
					if (arrayList.size() > 0) {
						String str = arrayList.remove(0);
						System.out.println(str);
						WaitPrint.class.notifyAll();
					}
					while (arrayList.size() == 0) {
						WaitPrint.class.wait();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}