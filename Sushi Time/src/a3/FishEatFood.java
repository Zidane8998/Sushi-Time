package a3;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
public class FishEatFood extends AbstractAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GameWorld gw;
	public FishEatFood(GameWorld target){
		super("Fish Eat Food");
		gw=target;
	}
	@Override
	public void actionPerformed(ActionEvent e){
		//if seaweed exists, find first fish in list and increment size, remove first seaweed in list
		if (gw.findSeaweed()!=gw.nullSeaweed){
			System.out.println("Collision between a fish and seaweed! Growing fish.");
			if (gw.findFish()!=gw.nullFish){
				Fish f = gw.findFish();
				Seaweed s = (Seaweed)gw.findSeaweed();
				gw.growFish(f);
				gw.removeFromWorldList(s);
				gw.notifyObservers();
			}
			else System.out.println("No fish to grow!");
		}
		else System.out.println("No seaweed to grow fish!");
	}

}