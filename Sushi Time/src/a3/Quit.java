package a3;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
public class Quit extends AbstractAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Quit(){
		super("Quit");
	}
	@Override
	public void actionPerformed(ActionEvent e){
		//provide JOptionPane with yes/no option
		String str = "Are you sure you really want to quit?";
		String title = "Quit?";
   	int ans = JOptionPane.showConfirmDialog(null, str, title, JOptionPane.YES_NO_OPTION);
   	if (ans == JOptionPane.YES_OPTION){
			System.out.println("See you next time!");
			System.exit(0);
		}
	}

}