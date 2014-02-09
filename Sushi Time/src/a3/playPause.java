package a3;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;
public class playPause extends AbstractAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//save link to game to detect current game state
	Game game;
	//save link to SoundToggle to pause background music in "pause" state
	SoundToggle st;
	public playPause(Game g, SoundToggle target){
		super("Pause");
		game = g;
		st = target;
	}

	@Override
	public void actionPerformed(ActionEvent e){
		if (game.getGameState()==0){
			game.setGameState(1);
			game.getTimer().stop();
			game.getReverse().setEnabled(true);
			game.getCommandPanel().playPause.setText("Play");
			//disable all actions when in pause
			ArrayList<AbstractAction>commands=game.getCommands();
			for (AbstractAction aa : commands){
				aa.setEnabled(false);
			}
			//turn off background music while paused
			st.stopBckMusic();
		}
		else if (game.getGameState()==1){
			game.setGameState(0);
			game.getTimer().start();
			game.getReverse().setEnabled(false);
			game.getCommandPanel().playPause.setText("Pause");
			//enable all actions when play is resumed
			ArrayList<AbstractAction>commands=game.getCommands();
			for (AbstractAction aa : commands){
				aa.setEnabled(true);
			}
			//resume background music if sound enabled
			if (game.getSound()==true)st.resumeBckMusic();
		}
	}


}