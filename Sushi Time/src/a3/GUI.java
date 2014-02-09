package a3;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
public class GUI extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GUI(){
	
		//menubar
		JMenuBar menu = new JMenuBar();
		setJMenuBar(menu);
		JMenu file = new JMenu("File");
		JMenu commands = new JMenu("Commands");
		JMenuItem fileOpen = new JMenuItem("Open");
		
		menu.add(file);
		menu.add(commands);
		
		file.add(fileOpen);
		
		
		//top panel
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new FlowLayout(1,20,10));
		
		//labels
		JLabel totalPointsLabel = new JLabel("Total Points:");
		JLabel totalPoints = new JLabel("0");
		JLabel pointsFishLabel = new JLabel("Points for Fish:");
		JLabel pointsFish = new JLabel("0");
		JLabel sharksLabel = new JLabel("Sharks Scooped:");
		JLabel sharks = new JLabel("0");
		JLabel pointsFishNetLabel = new JLabel("Points for Fish in Net:");
		JLabel pointsFishNet = new JLabel("0");
		JLabel soundLabel = new JLabel("Sound:");
		JLabel sound = new JLabel("OFF");
		JLabel timeLabel = new JLabel("Time:");
		JLabel time = new JLabel("0");
		//add labels
		topPanel.add(totalPointsLabel);
		topPanel.add(totalPoints);
		topPanel.add(pointsFishLabel);
		topPanel.add(pointsFish);
		topPanel.add(sharksLabel);
		topPanel.add(sharks);
		topPanel.add(pointsFishNetLabel);
		topPanel.add(pointsFishNet);
		topPanel.add(soundLabel);
		topPanel.add(sound);
		topPanel.add(timeLabel);
		topPanel.add(time);
		
		//left panel
		JPanel leftPanel = new JPanel();
		leftPanel.setBorder(new TitledBorder("Commands:"));
		leftPanel.setLayout(new GridLayout (11,1));
		
		//all command buttons
		JButton expandNet = new JButton("Expand Net");
		JButton shrinkNet = new JButton("Shrink Net");
		JButton scoop = new JButton("Scoop");
		JButton moveNetRight = new JButton("Move Net Right");
		JButton moveNetLeft = new JButton("Move Net Left");
		JButton moveNetUp = new JButton("Move Net Up");
		JButton moveNetDown = new JButton("Move Net Down");
		JButton spawnBabyFish = new JButton ("Spawn Baby Fish");
		new JButton("Shark Eat Fish");
		JButton fishEatFood = new JButton("Fish Eat Food");
		JButton netToSushibar = new JButton("Net At Sushibar");
		JButton tickClock = new JButton("Tick Clock");
		//add to left JPanel
		leftPanel.add(expandNet);
		leftPanel.add(shrinkNet);
		leftPanel.add(scoop);
		leftPanel.add(moveNetRight);
		leftPanel.add(moveNetLeft);
		leftPanel.add(moveNetUp);
		leftPanel.add(moveNetDown);
		leftPanel.add(spawnBabyFish);
		leftPanel.add(fishEatFood);
		leftPanel.add(netToSushibar);
		leftPanel.add(tickClock);
		
		
		JPanel centerPanel = new JPanel();
		centerPanel.setBorder(new EtchedBorder());
		
		this.add(topPanel, BorderLayout.NORTH);
		this.add(leftPanel, BorderLayout.WEST);
		this.add(centerPanel, BorderLayout.CENTER);
		
		setSize(1000,800);
		setVisible(true);
	}
}