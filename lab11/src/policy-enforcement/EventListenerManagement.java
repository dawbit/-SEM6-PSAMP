// EventListenerManagement.java
import java.util.*;

public abstract aspect EventListenerManagement {
    pointcut addListenerCall(Object model, EventListener listener) 
	: call(void *.add*Listener(EventListener+)) 
	&& target(model) && args(listener) && modelAndListenerTypeMatch();

    abstract pointcut modelAndListenerTypeMatch();
}
