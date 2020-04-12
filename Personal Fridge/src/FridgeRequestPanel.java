import java.awt.*;

import javax.swing.*;

public class FridgeRequestPanel extends JPanel {
	
	public void paintComponent(Graphics g) {
		g.setColor(new Color(0, 170, 0));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
	}
	
}
