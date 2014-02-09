package a3;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
public class ExpandNet extends AbstractAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GameWorld gw;
	public ExpandNet(GameWorld target){
		super("Expand Net");
		gw=target;
	}
	@Override
	public void actionPerformed(ActionEvent e){
		System.out.println("Expanding net size by 50."); 
		gw.findNet().incNetSize();
		gw.notifyObservers();
	}
}