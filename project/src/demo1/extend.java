package demo1;

public class extend {
	private String name;
	private extend ex=new extend();
	private extend(String s){//有参构造器
		this.name=s;
	}
	private extend(){//无参构造器
		
	}
	public void farword(int number){
		ex.farword(number);
	}
	public static void main(String[] args){
		
	}
}
