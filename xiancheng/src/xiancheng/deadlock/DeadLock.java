package xiancheng.deadlock;

public class DeadLock implements Runnable {
	public int i = 0;
	@Override
	public void run() {
		while(true){
			if(i%2==0){
				synchronized (LockA.lockA) {
					System.out.println("if....lockA");
					synchronized (LockB.lockB) {
						System.out.println("if....lockB");
					}
				}
			}else{
				synchronized (LockB.lockB) {
					System.out.println("else....lockB");
					synchronized (LockA.lockA) {
						System.out.println("else....lockA");
					}
				}
			}
			i++;
		}
		
	}

}
