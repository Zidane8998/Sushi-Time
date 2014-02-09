package a3;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.geom.Point2D;
import java.io.File;
public class Shark extends Animal{
			private int speed = 1;
			private double deltaX;
			private double deltaY;
			private double newX;
			private double newY;
			private Point2D.Float newLoc = new Point2D.Float();
			private Sounds sharkEatFish = new Sounds("Sounds/sharkEatFish.aif");
			public Shark(GameWorld target){
				super();
				this.setSize(10);
				setColor(Color.GRAY);
				//prevent spawning on sushibar
				while (this.getX()<=100 && this.getY()<=115)randomStartLoc();
				initSounds();
			}
			//instantiate sounds
			private void initSounds(){
				String dir="Sounds" + File.separator;
				String file="sharkEatFish.aif";
				String path=dir+file;
				sharkEatFish=new Sounds(path);
			}
			//override Object to display info about Shark
			@Override
			public String toString(){
				return "Shark: "+"Loc:("+this.getX()+","+this.getY()+") Color:"+(this.getColor().toString()).replace("java.awt.Color", "")+" Heading: "+this.getHeading();
			}
			@Override
			public void move(int time){
				//speed of shark is set to 10 (slower than fish)
				deltaX = (float)Math.cos(this.getHeading())*speed;
				deltaY = (float)Math.sin(this.getHeading())*speed;
				newX = this.getX()+deltaX;
				newY = this.getY()+deltaY;
				//set new location
				newLoc.setLocation(newX,newY);
				this.setLocation(newLoc);
			}
			@Override
			public void draw(Graphics g){
				g.setColor(this.getColor());
				
				//create triangle with current coordinates, using current location as center
   			Polygon po=new Polygon();
				po.addPoint((int)this.getX(),(int)this.getY()+10);
				po.addPoint((int)this.getX()-10,(int)this.getY()-10);
				po.addPoint((int)this.getX()+10,(int)this.getY()-10);
				g.fillPolygon(po);
				g.drawPolygon(po);
			}
			@Override
			public void collision(ICollidable object){
				//eats() method handles shark collision w/Fish
				if (object instanceof Sushibar){
					reverseHeading();
				}
				if (object instanceof Fish){
					if (this.getSound()==true)sharkEatFish.play();
				}
			}
		}