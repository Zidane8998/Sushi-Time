package a3;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
public class PointsView extends JPanel implements Observer{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//register self to GameWorld
	public PointsView(Observable o){
		o.addObserver(this);
		this.setLayout(new FlowLayout(1,20,10));
		this.add(totalPointsLabel);
		this.add(totalPoints);
		this.add(pointsFishLabel);
		this.add(pointsFish);
		this.add(sharksLabel);
		this.add(sharks);
		this.add(pointsFishNetLabel);
		this.add(pointsFishNet);
		this.add(soundLabel);
		this.add(sound);
		this.add(timeLabel);
		this.add(time);
	}
	//update view of state information
	
	//not used
	@Override
	public void update(Observable o, GameObject g){}
	
	//overload default upload method and provide method for all JLabel updates non-deterministically
	public void update(Observable o, String str, int val){
		String cur = ""+val;
		if (str=="time")time.setText(cur);
		else if(str=="pointsForFish")pointsFish.setText(cur);
		else if(str=="pointsFishInNet")pointsFishNet.setText(cur);
		else if(str=="totalPoints")totalPoints.setText(cur);
		else if(str=="sharksScooped")sharks.setText(cur);
		
	}
	//overloaded update for sound
	public void update(Observable o, boolean b){
		if (b==true)sound.setText("ON");
		else if (b==false)sound.setText("OFF");
	}
	//JLabels
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
	JLabel timeLabel = new JLabel("Time Left:");
	JLabel time = new JLabel("0");
	
}