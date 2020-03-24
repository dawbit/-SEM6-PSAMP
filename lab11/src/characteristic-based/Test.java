import javax.swing.*;

public class Test {
    public static void main(String[] args) {
	JFrame appFrame = new JFrame();
	appFrame.getContentPane().
	    add(new JSplitPane(JSplitPane.VERTICAL_SPLIT,
			       new GUIComp1(), 
			       new GUIComp2()));
	appFrame.setSize(400, 400);
	appFrame.setVisible(true);
    }
}
