package demo1;

public class extend2 {
	public void play(){
		System.out.println("test");
	};
	public static void test(extend2 e){//向上转型
		e.play();
	}
}
