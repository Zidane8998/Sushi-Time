package a3;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
public class MoveNetUp extends AbstractAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GameWorld gw;
	public MoveNetUp(GameWorld target){
		super("Move Net Up");
		gw=target;
	}
	@Override
	public void actionPerformed(ActionEvent e){
		gw.moveNet('u');
		gw.notifyObservers();
	}
}