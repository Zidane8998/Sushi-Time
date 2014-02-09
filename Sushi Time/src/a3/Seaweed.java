package a3;
import java.awt.Color;
import java.awt.Graphics;
public class Seaweed extends Plant{
			public Seaweed(GameWorld target){
				randomStartLoc();
				while (this.getX()<=100 && this.getY()<=115)randomStartLoc();
				setColor(Color.GREEN);
			}
			//override Object to display info about Seaweed
			@Override
			public String toString(){
				return "Seaweed: "+"Loc:("+this.getX()+","+this.getY()+") Color:"+(this.getColor().toString()).replace("java.awt.Color", "");
			}
			@Override
			public void draw(Graphics g){
				g.setColor(this.getColor());
				g.fillRect((int)this.getX()-5, (int)this.getY()+5, 5, 5);
    			g.drawRect((int)this.getX()-5, (int)this.getY()+5, 5, 5);
			}
			@Override
			public void collision(ICollidable object){
				//eats() handles Seaweed deletion
			}

		}