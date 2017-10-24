package com.qimh.inneranonymous;

public class Outer4 {

	int x = 3;

	public void function() {
		AbsDemo d = new AbsDemo() {
			int num = 9;

			@Override
			void show() {
				System.out.println("num===" + num);
			}

			void abc() {
				System.out.println("haha");
			}
		};

		d.show();
	}
	
	
	public static void main(String[] args) {
		Outer4 outer4 = new Outer4();
		outer4.function();
	}

}
