package a3;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;

import javax.swing.AbstractAction;
public class Scoop extends AbstractAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GameWorld gw;
	private Sounds scoopNet;
	public Scoop(GameWorld target){
		super("Scoop");
		gw=target;
		String dir="src" + File.separator + "a3" + File.separator + "Sounds" + File.separator;
		String file="scoopNet.wav";
		String path=dir+file;
		scoopNet=new Sounds(path);
	}
	@Override
	public void actionPerformed(ActionEvent e){
		Net n = gw.findNet();
	   //only allow scooping if net is unscooped OR has just returned from sushibar
	   if (n.getNetStatus()){
		   System.out.println("Scooping now..."); 
			int size = n.getSize();
			//find the boundaries of the net
			float upperBoundX=n.getX()+(size/2);
			float upperBoundY=n.getY()+(size/2);
			float lowerBoundX=n.getX()-(size/2);
			float lowerBoundY=n.getY()-(size/2);
			//iterate through world GameObject list
			Iterator iterator = gw.getGameCollection().getIterator();
			ArrayList<GameObject>delete = new ArrayList<GameObject>();
			while(iterator.hasNext()==true){
				GameObject g = iterator.getNext();
				float x=g.getX();
				float y=g.getY();
				//if object is movable and NOT the net, test if within net boundaries
				if (g instanceof Animal&&((x<upperBoundX&&y<upperBoundY)&&(x>lowerBoundX&&y>lowerBoundY))){
					//if object is an animal, increment or decrement points based on size (shark size set to -10)
					Animal a = (Animal)g; 
					if (a instanceof Fish)gw.tempPointsFishInNet+=(a.getSize());
					if (a instanceof Shark){gw.getGameCollection().sharkScooped(); gw.getGameCollection().changeTotalPoints(-10);}
					delete.add(g);
				}
				else if (g instanceof Seaweed&&((x<upperBoundX&&y<upperBoundY)&&(x>lowerBoundX&&y>lowerBoundY))){
					delete.add(g);
				}
				//if net has already scooped, disable scooping
			   n.setNetStatus(false);
			}
				//for each object in delete array, delete from world
				for (GameObject g : delete){
					gw.removeFromWorldList(g);
				}
				gw.notifyObservers();
				if(n.getSound()==true)scoopNet.play();
		}
	   else System.out.println("Net disabled! You scooped already, pal.");
		
	}
}