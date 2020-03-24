import java.util.*;
import javax.swing.event.*;
import javax.swing.*;

public aspect ListDataListenerWeakening extends EventListenerWeakening {
    pointcut modelAndListenerTypeMatch() 
	: target(AbstractListModel) && args(ListDataListener);

    public EventListener getWeakListener(EventListener listener) {
	System.out.println("Weakening " + listener);
	return new WeakListDataListener((ListDataListener)listener);
    }
}

public class WeakListDataListener extends WeakEventListener 
    implements ListDataListener {

    public WeakListDataListener(ListDataListener delegatee) {
	super(delegatee);
    }
    
    public void contentsChanged(ListDataEvent e) {
	((ListDataListener)getDelegatee()).contentsChanged(e);
    }

    public void intervalAdded(ListDataEvent e) {
	((ListDataListener)getDelegatee()).intervalAdded(e);
    }

    public void intervalRemoved(ListDataEvent e) {
	((ListDataListener)getDelegatee()).intervalRemoved(e);
    }

    static aspect ListRemoveGarbageCollectedListeners 
	extends WeakEventListener.RemoveGarbageCollectedListeners {

	pointcut lexicalScopeMatch() : within(WeakListDataListener);

	public void removeListener(EventObject event, EventListener listener) {
	    ((ListModel)event.getSource())
		.removeListDataListener((ListDataListener)listener);
	}
    }
}
