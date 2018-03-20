package demo1;

import java.util.ArrayList;
import java.util.List;

/*
 * wait学习
 * 三线程打印ABC
 */
public class ProducterConsumer {
	 public static void main(String[] args) {
		
	}
}

class ProducterProducterConsumer implements  Runnable{
	
	private List<String> bottleList ;
	private Object obj;
	public ProducterProducterConsumer(List<String> bottleList,Object obj ){
		this.bottleList = bottleList;
		this.obj = obj;
	}
	
	public void run() {
		while(true){
			
		}
	}
	
}

class ConsumerProducterConsumer implements  Runnable{
	private List<String> bottleList ;
	private Object obj;
	public ConsumerProducterConsumer(List<String> bottleList,Object obj ){
		this.bottleList = bottleList;
		this.obj = obj;
	}
	
	public void run() {
		while(true){
			
		}
	}
	
}