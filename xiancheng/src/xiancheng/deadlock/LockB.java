package xiancheng.deadlock;

public class LockB {
	private LockB(){};
	public static LockB lockB = new LockB();
}
