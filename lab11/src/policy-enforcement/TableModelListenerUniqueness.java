// TableModelListenerUniqueness.java
import java.util.EventListener;
import javax.swing.event.TableModelListener;
import javax.swing.table.*;

aspect TableModelListenerUniqueness extends EventListenerUniqueness {
    pointcut modelAndListenerTypeMatch() 
	: target(AbstractTableModel) && args(TableModelListener);

    public EventListener[] getCurrentListeners(Object model) {
	return ((AbstractTableModel)model)
	    .getListeners(TableModelListener.class);
    }
}
