package a3;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
public class MapView extends JPanel implements Observer, MouseListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Point curPoint;
	JPanel MainView;
	GameWorldProxy gw;
	//keep link to Game to check if game is in "pause" or "play"
	Game g;
	//register self to GameWorld
	public MapView(Observable o, Game target){
		o.addObserver(this);
		MainView = new JPanel();
		gw = (GameWorldProxy)o;
		this.addMouseListener(this);
		g = target;
	}
	//update view of state information (GameCollection) non-deterministically
	@Override
	public void update(Observable o, GameObject g){
		 //call paintComponent to repaint the entire collection
		 this.repaint();
	}
	@Override
	public void paintComponent(Graphics g){
		//draw all objects in collection according to current state information
		super.paintComponent(g);
		GameCollection gc = gw.getGameCollection();
		Iterator iterator = gc.getIterator();
		while(iterator.hasNext()){
			GameObject go = iterator.getNext();
			go.draw(g);
		}
	}
	@Override
	public void mouseClicked(MouseEvent e){}
	@Override
	public void mouseEntered(MouseEvent e){}
	@Override
	public void mouseExited(MouseEvent e){}
	@Override
	public void mousePressed(MouseEvent e){
		//if game is paused, allow selection
		if (g.getGameState()==1){
			curPoint = e.getPoint();
			GameCollection gc = gw.getGameCollection();
			Iterator iterator = gc.getIterator();
			while(iterator.hasNext()){
				GameObject go = iterator.getNext();
				//if current mouse point is inside a Fish, select it
				if (go.contains(curPoint))go.setSelected(true);
				//if user is not pressing Ctrl, unselect
				else{
					if (e.isControlDown()==false)go.setSelected(false);
				}
			}
			this.repaint();
		}
	}
	@Override
	public void mouseReleased(MouseEvent e){}
	
	
}

