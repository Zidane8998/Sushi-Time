package a3;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

public class SaveGame extends AbstractAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public SaveGame(GameWorld target){
		super("Save");
	}
	@Override
	public void actionPerformed(ActionEvent e){
		System.out.println("Save Game selected.");
	}

}