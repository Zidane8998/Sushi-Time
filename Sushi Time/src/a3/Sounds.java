package a3;
import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
public class Sounds{
	AudioClip myClip;
	public Sounds(String filename){
		try{
			File file = new File(filename);
			if (file.exists())myClip=Applet.newAudioClip(file.toURI().toURL());
			else throw new RuntimeException("Sound: file not found");
		}
		catch(Exception e){
			throw new RuntimeException("Bad URL");
		}
	}
	public void play(){
		myClip.play();
	}
	public void loop(){
		myClip.loop();
	}
	public void stop(){
		myClip.stop();
	}
}