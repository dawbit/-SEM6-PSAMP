import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class GUIComp2 extends JComponent {
    
    public GUIComp2() {
	setLayout(new FlowLayout());
	JButton performOperation1Button 
	    = new JButton("Click for slow operation 1");
	performOperation1Button.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    performOperation1();
		}
	    });
	add(performOperation1Button);

	JButton performOperation2Button 
	    = new JButton("Click for slow operation 2");
	performOperation2Button.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    performOperation2();
		}
	    });
	add(performOperation2Button);
    }

    public void performOperation1() {
	// Perform operation
	// Simulate slow operation
	try {
	    Thread.sleep(2000);
	} catch (Exception ex) {
	}
    }

    public void performOperation2() {
	// Perform operation
	// Simulate slow operation
	try {
	    Thread.sleep(3000);
	} catch (Exception ex) {
	}
    }

    public static aspect SlowMethodsParticipant extends SlowMethodAspect {
	pointcut slowMethods(Component uiComp) 
	    : (execution(void GUIComp2.performOperation1())
	       || execution(void GUIComp2.performOperation2()))
	    && this(uiComp);
    }
}

