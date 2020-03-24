// EventListenerUniqueness.java
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;

import javax.swing.table.*;

public abstract aspect EventListenerUniqueness 
    extends EventListenerManagement {
    void around(Object model, EventListener listener) 
	: addListenerCall(model, listener) {

	EventListener[] listeners = getCurrentListeners(model);
	if (!Utils.isInArray(listeners, listener)) {
	    System.out.println("Accepting " + listener);
	    proceed(model, listener);
	} else {
	    System.out.println("Already listening " + listener);
	}
    }

    public abstract EventListener[] getCurrentListeners(Object model);
}
