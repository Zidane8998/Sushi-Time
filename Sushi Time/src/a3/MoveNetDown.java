package a3;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
public class MoveNetDown extends AbstractAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GameWorld gw;
	public MoveNetDown(GameWorld target){
		super("Move Net Down");
		gw=target;
	}
	@Override
	public void actionPerformed(ActionEvent e){
		gw.moveNet('d');
		gw.notifyObservers();
	}
}