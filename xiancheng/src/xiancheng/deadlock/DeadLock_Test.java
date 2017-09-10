package xiancheng.deadlock;

public class DeadLock_Test {
	public static void main(String[] args) {
		DeadLock deadLock = new DeadLock();
		Thread t1 = new Thread(deadLock);
		Thread t2 = new Thread(deadLock);
		t1.start();
		t2.start();
	}
}
