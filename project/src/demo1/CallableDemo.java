package demo1;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableDemo {
	public static void main(String[] args) {
		ExecutorService service=Executors.newSingleThreadExecutor();
		ArrayList<Future<String>> list=new ArrayList<Future<String>>();
		for (int i = 0; i < 3; i++) {
			list.add(service.submit(new CallableDemo2(i)));//submit 方法返回一个Future对象
		}
		for(Future<String> s:list){
			try {
				System.err.println(s.get());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				service.shutdown();
			}
		}
	}
}
class CallableDemo2 implements Callable<String>{
	private int i;
	public CallableDemo2(int i){
		this.i=i;
	}
	public CallableDemo2(){
		
	}
	@Override
	public String call() throws Exception {
		// TODO Auto-generated method stub
		
		return "这个是参数"+i;
	}
	
}
