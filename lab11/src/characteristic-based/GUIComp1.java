import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class GUIComp1 extends JComponent {
    
    public GUIComp1() {
	setLayout(new FlowLayout());
	JButton performOperation1Button 
	    = new JButton("Click for slow operation");
	performOperation1Button.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    performOperation1();
		}
	    });
	add(performOperation1Button);

	JButton performOperation2Button 
	    = new JButton("Click for fast operation");
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
    }

    public static aspect SlowMethodsParticipant extends SlowMethodAspect {
	pointcut slowMethods(Component uiComp) 
	    : execution(void GUIComp1.performOperation1())
	    && this(uiComp);
    }
}

