package a3;
import java.util.ArrayList;
public class GameCollection implements Collection{
	//arraylist holds all game information, hidden inside GameCollection
	//does not reveal implementation method or data structure used in GameWorld/Game
	private ArrayList<GameObject> theCollection;
	private boolean sound=false;
	private int pointsFishInNet=0;
	private int pointsForFish=0;
	private int totalPoints=0;
	private int sharksScooped=0;
	private int elapsedTime=0;
	
	public GameCollection(){
		theCollection = new ArrayList<GameObject>();
	}
	@Override
	public void add(GameObject g){
		theCollection.add(g);
	}
	@Override
	public Iterator getIterator(){
		return new GameCollectionIterator();
	}
	public ArrayList<GameObject> getObjects(){
		return theCollection;
	}
	//accessor on behalf of GameWorld, returns GameObject without iterating through list
	public GameObject getObject(int index){
		return theCollection.get(index);
	}
	//accessor for GameWorld, removes objects without iterating
	public void removeObject(GameObject g){
		theCollection.remove(g);
	}
	//accessor for theCollection size
	public int getCollectionSize(){
		return theCollection.size();
	}
	//mutator for theCollection on behalf of GameCollectionIterator(allows main arraylist to remain private)
	public void removeFromCollection(int index){
		theCollection.remove(index);
	}
	//accessor to determine if Fish remain in collection
	public boolean fishExist(){
		//find fish in collection
		Iterator i=new GameCollectionIterator();
		while(i.hasNext()){
			GameObject g = i.getNext();
			if (g instanceof Fish){
				return true;
			}
		}
		return false;
	}
	//mutators for points/time elapsed
	void changePointsFishInNet(int n){
		pointsFishInNet+=n;
	}
	void resetPointsFishInNet(){
		pointsFishInNet=0;
	}
	void changeTotalPoints(int n){
		totalPoints+=n;
	}
	void changePointsForFish(int n){
		pointsForFish+=n;
	}
	void sharkScooped(){
		sharksScooped++;
	}
	void incTime(){
		elapsedTime++;
	}
	void setSound(boolean b){
		sound=b;
		//turn sounds ON or OFF for all GameObjects
		Iterator i=new GameCollectionIterator();
		while(i.hasNext()){
			GameObject g = i.getNext();
			g.setSound(b);
		}
	}
	//accessors for points and elapsed time
	int getElapsedTime(){
	   return elapsedTime;
	}
	int getPointsForFish(){
		return pointsForFish;
	}
	int getPointsFishInNet(){
		return pointsFishInNet;
	}
	int getTotalPoints(){
		return totalPoints;
	}
	int getSharksScooped(){
		return sharksScooped;
	}
	boolean getSound(){
		return sound;
	}
	//private iterator, does not reveal data structure/implementation
	private class GameCollectionIterator implements Iterator{
		int curIndex;
		public GameCollectionIterator(){
			curIndex=-1;
		}
		@Override
		public boolean hasNext(){
			if (getCollectionSize()<=0)return false;
			if (curIndex==getCollectionSize()-1)return false;
		return true;
		}
		@Override
		public GameObject getNext(){
			curIndex++;
			return (theCollection.get(curIndex));
		}
		@Override
		public void remove(){
			removeFromCollection(curIndex);
		}
	}

}