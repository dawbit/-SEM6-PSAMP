// SlowMethodAspect.java
import java.util.*;
import java.awt.*;
import java.awt.event.*;

public abstract aspect SlowMethodAspect {
    abstract pointcut slowMethods(Component uiComp);

    void around(Component uiComp) : slowMethods(uiComp) {
	Cursor originalCursor = uiComp.getCursor();
	Cursor waitCursor = Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);
	uiComp.setCursor(waitCursor);

	try {
	    proceed(uiComp);
	} finally {
	    uiComp.setCursor(originalCursor);
	}
    }
}
