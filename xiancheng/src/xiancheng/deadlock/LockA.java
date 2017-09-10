package xiancheng.deadlock;

public class LockA {
	private LockA(){}
	public static LockA lockA = new LockA();
}
