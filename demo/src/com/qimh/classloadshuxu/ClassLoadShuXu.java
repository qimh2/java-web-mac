package com.qimh.classloadshuxu;

public class ClassLoadShuXu {
	
	//普通属性
	private String str = getString("ordinary properties...");
	//静态代码块
	static {
		System.out.println("static block.....");
	}
	
	//类静态属性
	public static String staticStr = getStaticString("static properties");
//	public static ClassLoadShuXu instance = new ClassLoadShuXu();
	
	
//	{
//		System.out.println("ordinary block....");
//	}

	
	private String getString(String str){
		System.out.println(str);
		return str;
	}
	private static String getStaticString(String str){
		System.out.println(str);
		return str;
	}
	
    private ClassLoadShuXu(){
	   System.out.println("static properties....");
    }

    private ClassLoadShuXu(String name){
    	System.out.println("contruction ....."+name);
    }
    
	public static void main(String[] args) {
		ClassLoadShuXu classLoadShuXu = new ClassLoadShuXu("Singleton");
	}

}
