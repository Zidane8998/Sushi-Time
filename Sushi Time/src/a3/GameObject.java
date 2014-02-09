package a3;
import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.Random;
public abstract class GameObject implements IDrawable, ICollidable, ISelectable{
		private boolean isSelected=false;
		private Point2D.Float location = new Point2D.Float();
		private Color color;
		private boolean sound=false;
		public GameObject(){}
		public Point2D.Float getLocation(){
			return location;
		}
		public boolean getSound(){
			return sound;
		}
		public void setSound(boolean b){
			sound=b;
		}
		public void setLocation(Point2D.Float newLoc){
			location=newLoc;
		}
		public void setLocation(float x, float y){
			location.setLocation(x,y);
		}
		public void setX(float x){
			location.setLocation(x, this.getY());
		}
		public void setY(float y){
			location.setLocation(this.getX(), y);
		}
		public float getX(){
			return (float)location.getX();
		}
		public float getY(){
			return (float)location.getY();
		}
		public Color getColor(){
			return color;
		}
		public void setColor(Color newColor){
			color=newColor;
		}
		//code for ISelectable 
	   @Override
	public boolean contains(Point p){
			int px = (int)p.getX();
			int py = (int)p.getY();
			int xLoc = (int)getX();
			int yLoc = (int)getY();
			GameObject g = this;
			//if fish, test inside the circle for the point
			if (g instanceof Fish){
				Fish f = (Fish)g;
				int upperBoundX=xLoc+f.getRadius();
				int upperBoundY=yLoc+f.getRadius();
				int lowerBoundX=xLoc-f.getRadius();
				int lowerBoundY=yLoc-f.getRadius();
				if ( (px<upperBoundX && py<upperBoundY) && (px>lowerBoundX&&py>lowerBoundY) ){ 
					return true;
				}
			}
			
		return false;	
		}
		@Override
		public boolean isSelected(){
			return isSelected;
		}
		@Override
		public void setSelected(boolean b){
			isSelected=b;
		}
			
		void randomStartLoc(){
			Random r = new Random();
			float min = 0.0f;
			float max = 1024.0f;
			float x = r.nextFloat() * (max - min) + min;
			float y = r.nextFloat() * (max - min) + min;
			Point2D.Float startPoint=new Point2D.Float();
			startPoint.setLocation(x,y);
			location=startPoint;
		}
		//handles all GameObject "collidesWith" requests in TickClock
		@Override
		public boolean collidesWith(ICollidable object){
			//cast ICollidable as GameObject
			GameObject g = (GameObject)object;
			//get centers
			float curX = this.getX();
			float curY = this.getY();
			float objX = g.getX();
			float objY = g.getY();
			
			int dx = (int)(curX-objX);
			int dy = (int)(curY-objY);
			
			int distance = (dx*dx + dy*dy);
			int size=0;
			if (g instanceof Fish || g instanceof Shark)size=10;
			else if (g instanceof Seaweed)size=5;
			else if (g instanceof Sushibar){size=80;}
			else if (g instanceof Net){Net n=(Net)g; size=n.getSize()/2;}
			int thisRadius=size;
			int otherRadius=size;
			int radiiSqr = (thisRadius*thisRadius + 2*thisRadius*otherRadius + otherRadius*otherRadius);
			if (distance <= radiiSqr)return true;
		return false;
		}
		@Override
		public boolean eats(ICollidable obj, ICollidable obj2){
			if (obj instanceof Fish){
				if (obj2 instanceof Seaweed)return true;
				else return false;
			}
			if (obj instanceof Shark){
				if (obj2 instanceof Fish)return true;
				else return false;
			}
			if (obj instanceof Seaweed)return false;
		return false;
		}
		
	}
