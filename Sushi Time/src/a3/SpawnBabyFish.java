package a3;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
public class SpawnBabyFish extends AbstractAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GameWorld gw;
	public SpawnBabyFish(GameWorld target){
		super("Spawn Baby Fish");
		gw=target;
	}
	@Override
	public void actionPerformed(ActionEvent e){
		if (gw.findFish()!=gw.nullFish){
			System.out.println("Collision between two fish! Generating baby fish.");
			Fish f = gw.findFish();
			gw.createBabyFish(f);
			gw.notifyObservers();
		}
		else System.out.println("No fish to reproduce!");
	}
}