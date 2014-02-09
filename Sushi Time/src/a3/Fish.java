package a3;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.io.File;
public class Fish extends Animal{
		   //link to GameWorld for collisions
		   private GameWorld gw;
			//speed of fish is set to 20 (faster than sharks)
			private int speed=2;
			private int radius=10;
			private int baby=0;
			private boolean hadBaby=false;
			private int hadBabyTime=0;
			private double deltaX;
			private double deltaY;
			private double newX;
			private double newY;
			private Point2D.Float newLoc = new Point2D.Float();
			private Sounds fishMate;
			private Sounds fishEatFood;
			//constructor used for regular fish creation
			public Fish(GameWorld target){
				super();
				this.setSize(1);
				setColor(Color.ORANGE);
				baby=200;
				gw=target;
				//prevent spawning on sushibar
				while (this.getX()<=100 && this.getY()<=115)randomStartLoc();
				initSounds();
			}
			//override main Fish method for creation of baby fish
			public Fish(Fish f, GameWorld target){
				this.setSize(1);
				setColor(Color.ORANGE);
				//get parent fish location to spawn baby fish nearby
				float parentX=f.getX();
				float parentY=f.getY();
				//create random numbers for baby fish location near parent to insure no collision occurs at birth
				float x = (float)r.nextInt(10)+10;
				float y = (float)r.nextInt(10)+10;
				//set baby fish location near parent w/randomized values
				setLocation(parentX+x, parentY+y);
				randomStartHeading();
				baby=0;
				gw=target;
				initSounds();
			}
			//instantiate sounds
			private void initSounds(){
				String dir="Sounds" + File.separator;
				String file="fishMate.wav";
				String path=dir+file;
				fishMate=new Sounds(path);
				
				String dir2="Sounds" + File.separator;
				String file2="fishEatFood.wav";
				String path2=dir2+file2;
				fishEatFood=new Sounds(path2);
			}
			//increment size of fish
			void incSize(){
				if (this.getSize()<4){this.incrementSize(); radius+=5;}
				else System.out.println("Fish cannot grow any larger!");
			}
			public int getBaby(){
				return baby;
			}
			public int getRadius(){
				return radius;
			}
			//override Object.toString to display information about Fish
			@Override
			public String toString(){
				return "Fish: "+"Loc:("+this.getX()+","+this.getY()+") Color:"+(this.getColor().toString()).replace("java.awt.Color", "")+" Heading: "+this.getHeading()+" Size: "+this.getSize();
			}
			@Override
			public void move(int time){
				//generate new location using sine/cosine
				deltaX = Math.cos(Math.toRadians(this.getHeading()))*speed;
				deltaY = Math.sin(Math.toRadians(this.getHeading()))*speed;
				newX = this.getX()+deltaX;
				newY = this.getY()+deltaY;
				//add/subtract random values from heading
				int randX = r.nextInt(5);
				int add_subX = r.nextInt(2);
				if (add_subX==0)randX=-randX;
				changeHeading(randX);
				//keep fish in area if their X or Y value is about to exceed/go under the limit, reverse their heading
				if ((newX>=1024||newY>=1024||newX<=0||newY<=0)){
					if(newX>=1024)newX=1023;
					if(newY>=1024)newY=1023;
					if(newX<=0)newX=1;
					if(newY<=0)newY=1;
					reverseHeading();
				}
				//set new location
				newLoc.setLocation(newX,newY);
				this.setLocation(newLoc);
				//increment number of "turns" fish has been alive
				baby++;
				//remove limit on baby fish spawn rate after elapsed time
				if (baby-hadBabyTime > 200){hadBabyTime=0;hadBaby=false;}
			} 
			@Override
			public void draw(Graphics g){
				g.setColor(this.getColor());
				//create oval with current coordinates, using current location as center
				g.fillOval((int)this.getX()-radius, (int)this.getY()-radius, 2*radius, 2*radius);
				if (this.isSelected()){
					g.setColor(Color.BLACK);
					g.drawOval((int)this.getX()-radius, (int)this.getY()-radius, 2*radius, 2*radius);
				}
			}
			
			@Override
			public void collision(ICollidable object){
				//Fish handles collision with other fish, also seaweed
				if (object instanceof Fish){
					Fish objF = (Fish)object;
					if (objF.getBaby()>200 && this.baby>200 && this.hadBaby==false && objF.hadBaby==false){
						//create baby fish
						Fish f = new Fish(this, gw);
						//add to list
						gw.getGameCollection().add(f);
						//enable limits on fish spawning to prevent recursive loop of new fish
						this.hadBaby=true;
						this.hadBabyTime=this.baby;
						objF.hadBaby=true;
						objF.hadBaby=true;
						if (this.getSound()==true)fishMate.play();
					}
				}
				if (object instanceof Seaweed){
					this.incSize();
					if (this.getSound()==true)fishEatFood.play();
				}
				if (object instanceof Sushibar){
					reverseHeading();
				}
			}
			
		}
