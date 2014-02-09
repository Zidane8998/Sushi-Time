package a3;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.Timer;
import javax.swing.border.EtchedBorder;
public class Game extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GameWorld gw;
	private GameWorldProxy gwp;
	private MapView mv;
	private PointsView pv;
	private Timer gameClock = new Timer(100, this);
	private TickClock tick;
	private Scanner console=new Scanner(System.in);
	private int gameState=0;
	private Reverse reverseStore;
	private CommandPanel commandStore;
	private ArrayList<AbstractAction>commands;
	public Game(){ 
		gw = new GameWorld();
		gwp = new GameWorldProxy(gw);
		//create and register views, provide link back to GameWorldProxy
		//also make link for mapview back to Game to check game state ("pause" or "play")
		mv = new MapView(gwp, this);
		pv = new PointsView(gwp);
		
		//-----------GUI instantiation---------------//
		//menubar
		JMenuBar menu = new JMenuBar();
		JMenu file = new JMenu("File");
		JMenuItem fileNew = new JMenuItem("New");
		JMenuItem fileSave = new JMenuItem("Save");
		JMenuItem fileUndo = new JMenuItem("Undo");
		JCheckBoxMenuItem fileSound = new JCheckBoxMenuItem("Sound");
		JMenuItem fileAbout = new JMenuItem("About");
		JMenuItem fileQuit = new JMenuItem("Quit");
		JMenu command = new JMenu("Commands");
		
		//add file sub-items
		file.add(fileNew);
		file.add(fileSave);
		file.add(fileUndo);
		file.add(fileSound);
		//fileSoundCont.add(fileSound);
		file.add(fileAbout);
		file.add(fileQuit);
		
		//add both menus to main menubar
		menu.add(file);
		menu.add(command);
		//set main menu bar
		this.setJMenuBar(menu);
		
		//---------------Commands-----------------//
		//left panel (Commands)
		CommandPanel cp = new CommandPanel();
		
		//instantiate all Command classes with links back to GameWorld
		TickClock tc = new TickClock(gw);
		tick=tc;
		MoveNetDown mnd = new MoveNetDown(gw);
		MoveNetUp mnu = new MoveNetUp(gw);
		MoveNetLeft mnl = new MoveNetLeft(gw);
		MoveNetRight mnr = new MoveNetRight(gw);
		Scoop s = new Scoop(gw);
		ShrinkNet sn = new ShrinkNet(gw);
		ExpandNet en = new ExpandNet(gw);
		SoundToggle st = new SoundToggle(gw);
		Quit q = new Quit();
		playPause pp = new playPause(this, st);
		Reverse r = new Reverse(gw);
		reverseStore=r;
		
		//instantiate "empty" Command classes
		NewGame ng = new NewGame(gw);
		SaveGame sg = new SaveGame(gw);
		Undo u = new Undo(gw);
		About a = new About();
		
		//add Command classes to buttons explicitly
		cp.moveNetDown.setAction(mnd);
		cp.moveNetUp.setAction(mnu);
		cp.moveNetLeft.setAction(mnl);
		cp.moveNetRight.setAction(mnr);
		cp.scoop.setAction(s);
		cp.expandNet.setAction(en);
		cp.shrinkNet.setAction(sn);
		cp.playPause.setAction(pp);
		cp.reverse.setAction(r);
		
		//add commands to arraylist of commands for toggling on/off
		commands=new ArrayList<AbstractAction>();
		commands.add(mnd);
		commands.add(mnu);
		commands.add(mnl);
		commands.add(mnr);
		commands.add(s);
		commands.add(sn);
		commands.add(en);
		commands.add(mnd);
		
		//store link to CommandPanel for MapView/Command access
		commandStore=cp;
		
		//add Command classes to Menubar with implicit instantiation
		command.add(mnd);
		command.add(mnu);
		command.add(mnl);
		command.add(mnr);
		command.add(s);
		command.add(sn);
		command.add(en);
		command.add(pp);
		command.add(r);
		fileSound.setAction(st);
		fileQuit.setAction(q);
		fileNew.setAction(ng);
		fileSave.setAction(sg);
		fileUndo.setAction(u);
		fileAbout.setAction(a);
		
		//center panel (MapView)
		mv.setBorder(new EtchedBorder());
		
		//add panels to Frame
		//PointsView
		this.add(pv, BorderLayout.NORTH);
		//CommandPanel (buttons)
		this.add(cp, BorderLayout.WEST);
		//MapView (center panel)
		this.add(mv, BorderLayout.CENTER);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1180,1000);
		setVisible(true);
		
		//-----------Key Binding Set-up-------------//
		int mapName = JComponent.WHEN_IN_FOCUSED_WINDOW;
		InputMap imap = mv.getInputMap(mapName);
		
		//Key Strokes
		KeyStroke upKey=KeyStroke.getKeyStroke("UP");
		KeyStroke downKey=KeyStroke.getKeyStroke("DOWN");
		KeyStroke leftKey=KeyStroke.getKeyStroke("LEFT");
		KeyStroke rightKey=KeyStroke.getKeyStroke("RIGHT");
		KeyStroke sKey=KeyStroke.getKeyStroke('s');
		//Key Bindings
		imap.put(upKey, "up");
		imap.put(downKey, "down");
		imap.put(leftKey, "left");
		imap.put(rightKey, "right");
		imap.put(sKey, "scoop");
		
		ActionMap amap = mv.getActionMap();
		//Mapping to ActionMap
		amap.put("up", mnu);
		amap.put("down", mnd);
		amap.put("left", mnl);
		amap.put("right", mnr);
		amap.put("scoop", s);
		
		
		this.requestFocus();
		gw.createWorld(console);
		gw.notifyObservers();
		gameClock.start();
	}
	@Override
	public void actionPerformed(ActionEvent e){
		tick.actionPerformed(e);
	}
	public int getGameState(){
		return gameState;
	}
	public void setGameState(int x){
		gameState=x;
	}
	public Timer getTimer(){
		return gameClock;
	}
	//special accessor for playPause to toggle Reverse on/off independently
	public Reverse getReverse(){
		return reverseStore;
	}
	//accessor for playPause to change text/disable buttons
	public CommandPanel getCommandPanel(){
		return commandStore;
	}
	//accessor for all commands to toggle enable/disable
	public ArrayList<AbstractAction> getCommands(){
		return commands;
	}
	//accessor for sound in playPause
	public boolean getSound(){
		return gw.getGameCollection().getSound();
	}
	

} 