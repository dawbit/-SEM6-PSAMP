// TableModelListenerWeakening.java
import java.util.*;
import javax.swing.event.*;
import javax.swing.table.*;

public aspect TableModelListenerWeakening extends EventListenerWeakening {
    pointcut modelAndListenerTypeMatch() 
	: target(AbstractTableModel) && args(TableModelListener);

    public EventListener getWeakListener(EventListener listener) {
	System.out.println("Weakening " + listener);
	return new WeakTableModelListener((TableModelListener)listener);
    }
}

public class WeakTableModelListener extends WeakEventListener 
    implements TableModelListener {

    public WeakTableModelListener(TableModelListener delegatee) {
	super(delegatee);
    }
    
    public void tableChanged(TableModelEvent e) {
	TableModelListener listener = (TableModelListener)getDelegatee();
	listener.tableChanged(e);
    }
    
    static aspect TableRemoveGarbageCollectedListeners 
	extends WeakEventListener.RemoveGarbageCollectedListeners {
	
	pointcut lexicalScopeMatch() : within(WeakTableModelListener);

	public void removeListener(EventObject event, EventListener listener) {
	    ((TableModel)event.getSource())
		.removeTableModelListener((TableModelListener)listener);
	}
    }
}
