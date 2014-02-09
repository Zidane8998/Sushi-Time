package a3;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
public class NetAtSushiBar extends AbstractAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GameWorld gw;
	public NetAtSushiBar(GameWorld target){
		super("Net At Sushi Bar");
		gw=target;
	}
	@Override
	public void actionPerformed(ActionEvent e){
		Net n = gw.findNet();
	   System.out.println("Net has reached sushibar!");
		//add points for fish in net to Total Points
		gw.getGameCollection().changeTotalPoints(gw.tempPointsFishInNet);
		gw.getGameCollection().changePointsForFish(gw.tempPointsFishInNet);
		//reset points in net to 0
		gw.getGameCollection().resetPointsFishInNet();
		gw.tempPointsFishInNet=0;
		//enable net to scoop again
	   n.setNetStatus(true);
		gw.notifyObservers();
	}



}









