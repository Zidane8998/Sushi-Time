package a3;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
public class Reverse extends AbstractAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	GameWorld gw;
	public Reverse(GameWorld target){
		super("Reverse");
		gw = target;
		this.setEnabled(false);
	}

	@Override
	public void actionPerformed(ActionEvent e){
		Iterator iterator = gw.getGameCollection().getIterator();
		while(iterator.hasNext()){
			GameObject g = iterator.getNext();
			if (g instanceof Fish){
				Fish f = (Fish)g;
				//if Fish and selected
				if (f.isSelected()){
					//reverse direction
					f.reverseHeading();
				}
			}
			else continue;
		}
	}
	public void toggle(){
		if (this.isEnabled()==true){
			this.setEnabled(false);
		}
		else if (this.isEnabled()==false){
			this.setEnabled(true);
		}
	}


}