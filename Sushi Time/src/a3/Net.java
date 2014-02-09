package a3;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Point2D;
public class Net extends Catcher{
			private int size;
			private boolean ready;
			private Point2D.Float start = new Point2D.Float();
			
			public Net(){
				start.setLocation(512.0,512.0);
				setLocation(start);
				size=50;
				ready=true;
			}
			
			//implemented from IGuidable
			@Override
			public void move(char c){
			   //moving net up
				if (c=='u'){
					if (this.getY()<=1024 && (this.getY()+50)<1024){System.out.println("Moving net up by 50."); this.setY(this.getY()+50);}
					else System.out.println("Cannot move net up any further, at top boundary!");
				}
				//moving net down
				else if (c=='d'){
					if (this.getY()>=0 && (this.getY()-50)>50){System.out.println("Moving net down by 50."); this.setY(this.getY()-50);}
					else System.out.println("Cannot move net down any further, at bottom boundary!");
				}
				//moving net right
				else if (c=='r'){
					if (this.getX()<=1024 && (this.getX()+50)<1024){System.out.println("Moving net right by 50."); this.setX(this.getX()+50);}
					else System.out.println("Cannot move net right any further, at right boundary!");
				}
				//move net left
				else if (c=='l'){
					if (this.getX()>=0 && (this.getX()-50)>50){System.out.println("Moving net left by 50."); this.setX(this.getX()-50);}
					else System.out.println("Cannot move net left any further, at left boundary!");
				}
				
			}
			//method for Game to increment net size
			void incNetSize(){
				if (size<500)size+=50;
				else System.out.println("Max net size reached!");
			}
			//method for Game to decrement net size
			void decNetSize(){
				if (size>50)size-=50;
				else System.out.println("Minimum net size reached!");
			}
			int getSize(){
				return size;
			}
			boolean getNetStatus(){
				return ready;
			}
			//set net status after it has been scooped or reached bar
			void setNetStatus(boolean status){
				ready=status;
			}
			//override Object to display info about Net
			@Override
			public String toString(){
				return "Net: "+"Loc:("+this.getX()+","+this.getY()+") Size: "+this.size;
			}
			@Override
			public void draw(Graphics g){
				g.setColor(Color.black);
				g.drawRect((int)this.getX()-(size/2), (int)this.getY()-(size/2), size, size);
			}
			@Override
			public void collision(ICollidable object){}
		}
