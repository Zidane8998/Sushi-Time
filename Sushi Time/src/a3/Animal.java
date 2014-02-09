package a3;
import java.util.Random;
public abstract class Animal extends GameObject implements IMoveable{
		private int heading;
		private int size;
		Random r = new Random();
		public Animal(){
			super();
			randomStartLoc();
			this.randomStartHeading();
		}
		int getSize(){
			return this.size;
		}
		void setSize(int s){
			size = s;
		}
		void incrementSize(){
			size++;
		}
		void randomStartHeading(){
			Random r = new Random();
			int h = r.nextInt(359);
			heading=h;
		}
		int getHeading(){
			return heading;
		}
		void changeHeading(int x){
			heading+=x;
		}
		void reverseHeading(){
			if (heading<180)heading=heading+180;
			else if (heading>=180){heading=heading+180; heading=heading-360;}
		}
	}
