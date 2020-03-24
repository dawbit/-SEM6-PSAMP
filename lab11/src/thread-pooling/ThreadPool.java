// ThreadPool.java
import java.util.*;

public class ThreadPool {
    List _waitingThread = new Vector();

    public void put(DelegatingThread thread) {
	System.out.println("Putting back: " + thread);
	_waitingThread.add(thread);
    }

    public DelegatingThread get() {
	if (_waitingThread.size() != 0) {
	    DelegatingThread availableThread 
		= (DelegatingThread)_waitingThread.remove(0);
	    System.out.println("Providing for work: " + availableThread);
	    return availableThread;
	}
	return null;
    }

    static class DelegatingThread extends Thread {
	private Runnable _delegatee;
	
	public void setDelegatee(Runnable delegatee) {
	    _delegatee = delegatee;
	}
	
	public void run() {
	    _delegatee.run();
	}
    }
}
