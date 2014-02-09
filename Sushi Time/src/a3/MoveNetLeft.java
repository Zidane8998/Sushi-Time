package a3;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
public class MoveNetLeft extends AbstractAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GameWorld gw;
	public MoveNetLeft(GameWorld target){
		super("Move Net Left");
		gw=target;
	}
	@Override
	public void actionPerformed(ActionEvent e){
		gw.moveNet('l');
		gw.notifyObservers();
	}
}