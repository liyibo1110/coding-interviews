package com.github.liyibo1110.coding.interviews.cp2;

/**
 * 题目2 单例模式
 * @author liyibo
 *
 */
public class SingletonPattern2 {

	/**
	 * 饿汉型（类加载同时已实例化，所以线程安全，问题在于单例基本都是要延迟生成的）
	 */
	static class HugerSingleton1 {
		
		private static final HugerSingleton1 INSTANCE = new HugerSingleton1();
		
		private HugerSingleton1() {
			
		}
		
		public static HugerSingleton1 getInstance() {
			return INSTANCE;
		}
	}
	
	/**
	 * 饿汉型 + 静态块加载 （同上）
	 */
	static class HugerSingleton2 {
		
		private static HugerSingleton2 INSTANCE;
		
		static {
			INSTANCE = new HugerSingleton2();
		}
		
		private HugerSingleton2() {
			
		}
		
		public static HugerSingleton2 getInstance() {
			return INSTANCE;
		}
	}
	
	/**
	 * 懒汉型（线程不安全版本）
	 */
	static class Singleton1 {
		
		private static Singleton1 INSTANCE;
		
		private Singleton1() {
			
		}
		
		public static Singleton1 getInstance() {
			
			if (INSTANCE == null) {
				INSTANCE = new Singleton1();
			}
			return INSTANCE;
		}
	}
	
	/**
	 * 懒汉型 + get方法加锁（性能差，单例就算创建好了，以后只能有1个线程进去）
	 */
	static class Singleton2 {
		
		private static Singleton2 INSTANCE;
		
		private Singleton2() {
			
		}
		
		public synchronized static Singleton2 getInstance() {
			
			if (INSTANCE == null) {
				INSTANCE = new Singleton2();
			}
			return INSTANCE;
		}
	}
	
	/**
	 * 懒汉型 + get方法内部加锁 + 双重检查（改善性能问题，但是逻辑复杂）
	 */
	static class Singleton3 {
		
		private static Singleton3 INSTANCE;
		
		private Singleton3() {
			
		}
		
		public static Singleton3 getInstance() {
			
			if (INSTANCE == null) {
				synchronized (Singleton3.class) {
					if (INSTANCE == null) {
						INSTANCE = new Singleton3();
					}
				}
			}
			return INSTANCE;
		}
	}
	
	/**
	 * 懒汉型 + 静态内部类（线程安全，还比较简洁，除非用人为反射）
	 */
	static class Singleton4 {
		
		private static class SingletonHolder {
			private static Singleton4 INSTANCE = new Singleton4();
		}
		
		private Singleton4() {
			
		}
		
		public static Singleton4 getInstance() {
			return SingletonHolder.INSTANCE;
		}
	}
	
	/**
	 * 枚举（并不常用）
	 */
	static enum Singleton5 {
		INSTANCE;
	}
}
