package com.qimh;

public class TestClass {
	public static void main(String[] args) {

		try {
			// 测试.class

			Class testTypeClass = TestClassType.class;

			System.out.println("testTypeClass---" + testTypeClass);

			// 测试Class.forName()

			Class testTypeForName = Class.forName("com.qimh.TestClassType");

			System.out.println("testTypeForName---" + testTypeForName);

			// 测试Object.getClass()

			TestClassType testTypeGetClass = new TestClassType();

			System.out.println("testTypeGetClass---"+ testTypeGetClass.getClass());

		} catch (ClassNotFoundException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}
	}

}

class TestClassType {

	// 构造函数

	public TestClassType() {

		System.out.println("----构造函数---");

	}

	// 静态的参数初始化

	static {

		System.out.println("---静态的参数初始化---");

	}

	// 非静态的参数初始化

	{

		System.out.println("----非静态的参数初始化---");

	}

}