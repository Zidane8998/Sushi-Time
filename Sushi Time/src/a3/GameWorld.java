package a3;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
public class GameWorld implements Observable{
	//GameCollection is constructed upon instantiation, holds all GameObject state data
	//and does not expose internal structure of data collection/iterator
	private GameCollection gc = new GameCollection();
	//list of observers for easy updates
	private ArrayList<Observer> ol = new ArrayList<Observer>();
	private GameWorldProxy gwp = new GameWorldProxy(this);
	//null instances of GameObjects are provided if finding techniques fail
	public Fish nullFish;
	public Seaweed nullSeaweed;
	public Net nullNet;
	int tempPointsFishInNet=0;
	int fishCheck=0;
	public GameWorld(){}
	@Override
	public void notifyObservers(){
		//update all observers in Observer list
		for (Observer o : ol){
			//iterate through GameCollection to avoid passing iterator to views
			Iterator iterator = gc.getIterator();
			//if MapView, iterate through game list and provide new state information
			if (o instanceof MapView){
				while(iterator.hasNext()){
		   		GameObject g = iterator.getNext();
					o.update(gwp, g);
				}
			}
			//if PointsView, get all point values from GameCollection and provide values
			else if (o instanceof PointsView){
				//cast as PointsView to use overloaded update methods
				PointsView p = (PointsView)o;
				//update all point values using overloaded update method, providing string tag to PointsView to differentiate which value is which
				//also pass in "hard" values accessed from GameCollection to avoid giving views access directly to GameCollection
				p.update(gwp, "time", (600-gc.getElapsedTime())/10);
				p.update(gwp, "pointsForFish", gc.getPointsForFish());
				p.update(gwp, "pointsFishInNet", tempPointsFishInNet);
				p.update(gwp, "totalPoints", gc.getTotalPoints());
				p.update(gwp, "sharksScooped", gc.getSharksScooped());
				//use 2nd overloaded update() method in PointsView to pass sound as a boolean
				p.update(gwp,gc.getSound());
			}
		}
	}
	public boolean fishExist(){
		return gc.fishExist();
	}
	//add observers to Observer ArrayList ol
	@Override
	public void addObserver(Observer o){
		ol.add(o);
	}
	void addToWorldList(GameObject g){
		gc.add(g);
	}
	void removeFromWorldList(GameObject g){
		gc.removeObject(g);
	}
	//create an instance of GameWorld with user input
	void createWorld(Scanner console){
		Random r = new Random();
	  // System.out.println("Welcome to Sushi Time!");
		//System.out.println("Number of fish: "); 
		int fish = r.nextInt(40)+1;
		//System.out.println("Number of sharks: "); 
		int shark = r.nextInt(10)+1;
		//create random int for creating random number of seaweed
		int seaweed=r.nextInt(3)+1;
		//create fish, sharks, seaweed, net and sushibar, add them to GameCollection
		System.out.println("Creating "+fish+" fish");
		for(int i=0; i<fish; i++){
			System.out.println("Creating fish");
			Fish f = new Fish(this);
			addToWorldList(f);
		}
		System.out.println("Creating "+shark+" sharks");
		for(int i=0; i<shark; i++){
			System.out.println("Creating shark");
			Shark s = new Shark(this);
			addToWorldList(s);
		}
		System.out.println("Creating "+seaweed+" seaweed");
		for(int i=0; i<seaweed; i++){
			System.out.println("Creating seaweed");
			Seaweed sw=new Seaweed(this);
			addToWorldList(sw);
		}
		System.out.println("Creating net");
			Net n = new Net();
			addToWorldList(n);
		System.out.println("Creating sushibar");
		   Sushibar sb = new Sushibar();
			addToWorldList(sb);
	 }
	//print all objects in the GameObject ArrayList
	void printObjects(){
		Iterator iterator = gc.getIterator();
	   while(iterator.hasNext()){
		    GameObject g = iterator.getNext();
			 if (!(g instanceof Bar))System.out.println(g.toString());
		}
	}
	
	//methods to find, grow and create baby fish from parent fish
	Fish findFish(){
		//step through GameCollection using iterator to find first instance of a fish
		Iterator iterator = gc.getIterator();
		while(iterator.hasNext()){
			GameObject g = iterator.getNext();
			if (g instanceof Fish)return (Fish)g;
		}
	return nullFish;
	}
	void growFish(Fish f){
		f.incSize();
		notifyObservers();
	}
	void createBabyFish(Fish f){
		Fish bf = new Fish(f,this);
		addToWorldList(bf);
		System.out.println("Baby fish generated: "+bf.toString());
		notifyObservers();
	}
	
	GameObject findSeaweed(){
		//for (GameObject g: world)if (g instanceof Seaweed)return (Seaweed)g;
		Iterator iterator = gc.getIterator();
		while(iterator.hasNext()){
			GameObject g = iterator.getNext();
			if (g instanceof Seaweed)return g;
		}
	return nullSeaweed;
	}

	Net findNet(){
		//for(GameObject g: world)if (g instanceof Net)return (Net)g;
		Iterator iterator = gc.getIterator();
		while(iterator.hasNext()){
			GameObject g = iterator.getNext();
			if (g instanceof Net)return (Net)g;
		}
	return nullNet;
	}
	//move net corresponding to use input
	void moveNet(char c){
	   Net n = findNet();
		n.move(c);
	}

	//accessors to GameCollection on behalf of Game
	int checkElapsedTime(){
		return gc.getElapsedTime();
	}
	int getElapsedTime(){
	   return gc.getElapsedTime();
	}
	int getPointsForFish(){
		return gc.getPointsForFish();
	}
	int getPointsFishInNet(){
		return tempPointsFishInNet;
	}
	int getTotalPoints(){
		return gc.getTotalPoints();
	}
	int getSharksScooped(){
		return gc.getSharksScooped();
	}
	boolean getSound(){
		return gc.getSound();
	}
	//accessors to GameCollection for Command classes
	GameCollection getGameCollection(){
		return gc;
	}
	//mutator in GameCollection for sound
	void setSound(boolean b){
		gc.setSound(b);
	}

}