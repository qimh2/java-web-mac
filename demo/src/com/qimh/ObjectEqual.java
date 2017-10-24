package com.qimh;


/**
 * 
 * @author qiminhui
 * 判断java中两个对象是否相等:
 * 
 * java中的基本数据类型判断是否相等，直接使用"=="就行了，相等返回true，否则，返回false。
 * 但是java中的引用类型的对象比较变态，假设有两个引用对象obj1,obj2,
	obj1==obj2 判断是obj1,obj2这两个引用变量是否相等，即它们所指向的对象是否为同一个对象。言外之意就是要求两个变量所指内存地址相等的时候，才能返回true，每个对象都有自己的一块内存，因此必须指向同一个对象才返回ture。
	
	
	如果想要自定义两个对象（不是一个对象，即这两个对象分别有自己的一块内存）是否相等的规则，那么必须在对象的类定义中重写equals()方法，如果不重写equals()方法的话，默认的比较方式是比较两个对象是否为同一个对象。
	在Java API中，有些类重写了equals()方法，它们的比较规则是：当且仅当该equals方法参数不是 null，两个变量的类型、内容都相同，则比较结果为true。这些类包括：String、Double、Float、Long、Integer、Short、Byte、、Boolean、BigDecimal、BigInteger等等，太多太多了，但是常见的就这些了，具体可以查看API中类的equals()方法，就知道了。
	
	重写equals()方法的步骤一般如下：
	1、先用“==”判断是否相等。
	
	2、判断equals()方法的参数是否为null，如果为null，则返回false；因为当前对象不可能为null，如果为null，则不能调用其equals()方法，否则抛java.lang.NullPointerException异常。
	
	3、当参数不为null，则如果两个对象的运行时类（通过getClass()获取）不相等，返回false，否则继续判断。
	
	4、判断类的成员是否对应相等。往下就随意发挥了。
	
	总结：java ，equel默认比较是否相等是地址空间的相等（hashcode是否相等），如果我们想要两个对象只要他们值相等，两个对象就相等，那么就需要重写equal方法
 */
public class ObjectEqual {
	public int id;
	public String name;
	
	public ObjectEqual(){
		
	}

	public ObjectEqual(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	
	@Override
	public boolean equals(Object obj) {
		if(obj==null){
            return false;
        }
        if(this==obj){
            return true;
        }
        if(obj instanceof ObjectEqual){
        	ObjectEqual oEqual=(ObjectEqual)obj;
            if(oEqual.id==this.id&&oEqual.name.equals(this.name)){
                return true;
            }else{
                return false;
            }
        }
        return false;
	}
	
	
	public static void main(String[] args) {
		ObjectEqual objectEqual1 = new ObjectEqual(1, "aa");
		ObjectEqual objectEqual2 = new ObjectEqual(1, "aa");
		
		System.out.println(objectEqual1.equals(objectEqual2));
		
	}
	
	
}
