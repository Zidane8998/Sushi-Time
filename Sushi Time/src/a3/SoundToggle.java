package a3;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.AbstractAction;
public class SoundToggle extends AbstractAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GameWorld gw;
	private Sounds background;
	public SoundToggle(GameWorld target){
		super("Sound");
		gw=target;
		String dir="Sounds" + File.separator;
		String file="background.wav";
		String path=dir+file;
		background=new Sounds(path);
	}
	@Override
	public void actionPerformed(ActionEvent e){
		if (gw.getGameCollection().getSound()==true){
			System.out.println("Sound toggled OFF!");
			gw.getGameCollection().setSound(false);
			//stop background music
			background.stop();
		}
		else if (gw.getGameCollection().getSound()==false){
			System.out.println("Sound toggled ON!");
			gw.setSound(true);
			//resume background music
			background.loop();
		}
		gw.notifyObservers();
	}
	//accessors for playPause to stop/resume music during pause
	public void stopBckMusic(){
		background.stop();
	}
	public void resumeBckMusic(){
		background.loop();
	}
}