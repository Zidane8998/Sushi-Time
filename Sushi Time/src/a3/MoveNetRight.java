package a3;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
public class MoveNetRight extends AbstractAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GameWorld gw;
	public MoveNetRight(GameWorld target){
		super("Move Net Right");
		gw=target;
	}
	@Override
	public void actionPerformed(ActionEvent e){
		gw.moveNet('r');
		gw.notifyObservers();
	}
}