package a3;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

public class About extends AbstractAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public About(){
		super("About");
	}
	@Override
	public void actionPerformed(ActionEvent e){
		System.out.println("About selected.");
		JOptionPane.showMessageDialog(null, "Name: Andrew Hansen\nProgram: Sushi Time\nVersion: 3.0");
	}

}