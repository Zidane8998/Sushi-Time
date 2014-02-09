package a3;
import java.awt.Color;
import java.awt.Graphics;
public class Sushibar extends Bar{
			public Sushibar(){
				this.setLocation(0,0);
			}
			@Override
			public void draw(Graphics g){
				g.setColor(Color.BLACK);
				g.fillRect(0, 0, 90, 115);
			}
			@Override
			public void collision(ICollidable object){
				if (object instanceof Net){
					
				}
			}
		}