package a3;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
public class CommandPanel extends JPanel{
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private ArrayList<JButton>buttons;
		public CommandPanel(){
			//instantiate button arraylist
			buttons = new ArrayList<JButton>();
			//add commands to command panel
			this.setBorder(new TitledBorder("Commands:"));
			this.setLayout(new GridLayout (11,1));
			this.add(expandNet);
			this.add(shrinkNet);
			this.add(scoop);
			this.add(moveNetRight);
			this.add(moveNetLeft);
			this.add(moveNetUp);
			this.add(moveNetDown);
			this.add(playPause);
			this.add(reverse);
			//add buttons to arraylist for access in playPause
			buttons.add(expandNet);
			buttons.add(shrinkNet);
			buttons.add(scoop);
			buttons.add(moveNetRight);
			buttons.add(moveNetLeft);
			buttons.add(moveNetUp);
			buttons.add(moveNetDown);
		}
		
		public ArrayList<JButton> getButtons(){
			return buttons;
		}
		
		//all command buttons
		JButton expandNet = new JButton("Expand Net");
		JButton shrinkNet = new JButton("Shrink Net");
		JButton scoop = new JButton("Scoop");
		JButton moveNetRight = new JButton("Move Net Right");
		JButton moveNetLeft = new JButton("Move Net Left");
		JButton moveNetUp = new JButton("Move Net Up");
		JButton moveNetDown = new JButton("Move Net Down");
		JButton tickClock = new JButton("Tick Clock");
		JButton playPause = new JButton("Play");
		JButton reverse = new JButton("Reverse");
}