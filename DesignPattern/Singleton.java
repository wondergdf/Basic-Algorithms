
import java.io.Serializable;

class LazyMan {
	private static LazyMan instance;

	private LazyMan() {
		if (instance != null) {// avoid reflection attack
			throw new ExceptionInInitializerError("Instance already exist.");
		}
	}

	public static synchronized LazyMan getInstance() {
		if (instance == null) {
			instance = new LazyMan();
		}
		return instance;
	}
}

class HungryMan {
	private static HungryMan instance = new HungryMan();

	private HungryMan() {

	}

	public static HungryMan getInstance() {
		return instance;
	}
}

class StaticClass {
	private static class InstanceHolder {
		private static final StaticClass instance = new StaticClass();
	}

	private StaticClass() {

	}

	public static final StaticClass getInstance() {
		return InstanceHolder.instance;

	}
}

class DoubleCheck implements Serializable {
	private static DoubleCheck Instance;

	private DoubleCheck() {

	}

	public static DoubleCheck getInstance() {
		if (Instance == null) {
			synchronized (DoubleCheck.class) {
				if (Instance == null) {
					Instance = new DoubleCheck();
				}
			}
		}
		return Instance;
	}

	private Object readResolve() {// avoid serialization attack
		return Instance;
	}
}

enum EnumSingleton {
	Instance;

	public void doSomething() {

	}

}

public class Singleton {
	public static void main(String[] args) {
		
		EnumSingleton instanc = EnumSingleton.Instance;

	}
}
