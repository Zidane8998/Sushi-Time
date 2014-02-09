package a3;
import java.awt.Graphics;

import javax.swing.JPanel;
public class DisplayPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public DisplayPanel(){
	}
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawString("Hello" + 50, 200,200);
	}


}