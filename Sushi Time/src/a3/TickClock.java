package a3;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
public class TickClock extends AbstractAction{
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private GameWorld gw;
		private NetAtSushiBar nasb;
		private int time=0;
		//set GameWorld as target in constructor so it can access state information and GameCollection
		public TickClock(GameWorld target){
			super("Tick Clock");
			gw = target;
			nasb = new NetAtSushiBar(target);
		}
		@Override
		public void actionPerformed(ActionEvent e){
	   Random r = new Random();
	   //move all moveable GameObjects
		Iterator iterator = gw.getGameCollection().getIterator();
		ArrayList<GameObject>delete=new ArrayList<GameObject>();
		while(iterator.hasNext()){
			GameObject g = iterator.getNext();
			if (g instanceof IMoveable){
				IMoveable m = (IMoveable)g;
				m.move(time);
				//after move, check to see if g is a shark AND if it has moved out of bounds, mark for delete
				if (m instanceof Shark){
					Shark s = (Shark)m;
					if(s.getX()>=1024||s.getY()>=1024||s.getX()<=0||s.getY()<=0){
						//System.out.println("Shark moving out of area. Deleting...");
						delete.add(g);
					}
				}
			}
		}
		//check for collisions
		Iterator iterator2 = gw.getGameCollection().getIterator();
		while(iterator2.hasNext()){
			ICollidable ic = iterator2.getNext();
			Iterator iterator3 = gw.getGameCollection().getIterator();
			while(iterator3.hasNext()){
				ICollidable ic2 = iterator3.getNext();
				//if the objects are not the same object
				if (ic != ic2){
					//if the objects collide
					if (ic.collidesWith(ic2)){
						//if first object eats/deletes the other, add to deletion list
						//this avoids concurrency errors and making objects delete themselves
						if (ic.eats(ic, ic2)){
							delete.add((GameObject)ic2);
						}
						//collide the objects polymorphically
						ic.collision(ic2);
						
						if (ic instanceof Sushibar && ic2 instanceof Net || ic2 instanceof Net && ic instanceof Sushibar){
							nasb.actionPerformed(e);
						}
					}
				}
			}
		}
		//delete tagged sharks, fish and seaweed
		for (GameObject g : delete){
			gw.removeFromWorldList(g);
		}
		//random shark spawner
		if (r.nextInt(10)==7 && r.nextInt(10)==7){
			//System.out.println("Randomly creating shark!"); 
			Shark s = new Shark(gw);
			gw.addToWorldList(s);
		}
		//random seaweed spawner, 1/10 chance every tick
		else if (r.nextInt(10)==8){
			//System.out.println("Randomly spawning seaweed!");
			Seaweed s = new Seaweed(gw);
			gw.addToWorldList(s);
		}
		gw.getGameCollection().incTime();
		gw.notifyObservers();
		if (gw.getElapsedTime()==600 || gw.fishExist()==false){
			System.out.println("Time is up - Game Over!");
			JOptionPane.showMessageDialog(null, "Score: "+gw.getGameCollection().getTotalPoints()+"\nTime Elapsed: "+((600-gw.getElapsedTime())/10));
			System.exit(0);
		}
		time+=100;
		}
		
}
