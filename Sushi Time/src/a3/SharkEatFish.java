package a3;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
public class SharkEatFish extends AbstractAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GameWorld gw;
	public SharkEatFish(GameWorld target){
		super("Shark Eat Fish");
		gw=target;
	}
	@Override
	public void actionPerformed(ActionEvent e){
		System.out.println("Collision between a fish and a shark! Deleting fish.");
		if (gw.findFish()!=gw.nullFish){
			Fish f = gw.findFish();
			gw.removeFromWorldList(f);
			gw.notifyObservers();
		}
		else System.out.println("No fish to delete!");
	}
}