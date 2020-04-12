import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class FridgePanel extends JPanel {
	
	public void paintComponent(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
	}
	
}
