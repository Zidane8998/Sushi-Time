package a3;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

public class Undo extends AbstractAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Undo(GameWorld target){
		super("Undo");
	}
	@Override
	public void actionPerformed(ActionEvent e){
		System.out.println("Undo selected.");
	}

}