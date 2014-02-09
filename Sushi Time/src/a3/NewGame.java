package a3;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

public class NewGame extends AbstractAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public NewGame(GameWorld target){
		super("New");
	}
	@Override
	public void actionPerformed(ActionEvent e){
		System.out.println("New Game selected.");
	}

}