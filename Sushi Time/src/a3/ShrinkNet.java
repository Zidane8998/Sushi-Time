package a3;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
public class ShrinkNet extends AbstractAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GameWorld gw;
	public ShrinkNet(GameWorld target){
		super("Shrink Net");
		gw=target;
	}
	@Override
	public void actionPerformed(ActionEvent e){
		System.out.println("Shrinking net size by 50."); 
		gw.findNet().decNetSize();
		gw.notifyObservers();
	}
}