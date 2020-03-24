// EventListenerWeakening.java
import java.lang.ref.*;
import java.util.*;
import javax.swing.event.*;

public abstract aspect EventListenerWeakening 
    extends EventListenerManagement dominates EventListenerUniqueness {
    void around(Object model, EventListener listener) 
	: addListenerCall(model, listener) {
	proceed(model, getWeakListener(listener));
    }

    public abstract EventListener getWeakListener(EventListener listener);
}


public abstract class WeakEventListener extends WeakReference 
    implements EventListener {
    public WeakEventListener(EventListener delegatee) {
	super(delegatee);
    }

    public EventListener getDelegatee() {
	return (EventListener)get();
    }

    public boolean equals(Object other) {
	if (getClass() != other.getClass()) {
	    return false;
	}
	return getDelegatee() == ((WeakEventListener)other).getDelegatee();
    }
    
    public String toString() {
	return "WeakReference(" + get() + ")";
    }
    
    abstract static aspect RemoveGarbageCollectedListeners {
	pointcut eventNotification(WeakEventListener weakListener,
				   EventObject event) 
	    : execution(void WeakEventListener+.*(EventObject+))
	    && this(weakListener) && args(event) 
	    && lexicalScopeMatch();

	abstract pointcut lexicalScopeMatch();
	
	public abstract void removeListener(EventObject event, 
					    EventListener listener);

	void around(WeakEventListener weakListener, EventObject event) 
	    : eventNotification(weakListener, event) {
	    if (weakListener.getDelegatee() != null) {
		proceed(weakListener, event);
	    } else {
		System.out.println("Removing listener: " + weakListener);
		removeListener(event, weakListener);
	    }
	}
    }
}
