import java.util.*;
import javax.swing.*;
import javax.swing.event.*;

aspect ListDataListenerUniqueness extends EventListenerUniqueness {
    pointcut modelAndListenerTypeMatch() 
	: target(AbstractListModel) && args(ListDataListener);

    public EventListener[] getCurrentListeners(Object model) {
	return ((AbstractListModel)model).getListeners(ListDataListener.class);
    }
}
