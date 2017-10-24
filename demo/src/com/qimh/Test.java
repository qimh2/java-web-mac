package com.qimh;

public class Test {
	
	
	static{
		System.out.println("static.....");
	}
	{
		
		System.out.println("block");
	}
	
	public Test(){
		
		System.out.println("construct....");
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		System.out.println("hello world");
//		System.out.println("good......");
//		
//		
//		String string = "AA1";
//		System.out.println(string.hashCode());
//		System.out.println(string.hashCode());
//		System.out.println(Test.class);
		
		
		
		Test test = new Test();
		
		
	}

}
