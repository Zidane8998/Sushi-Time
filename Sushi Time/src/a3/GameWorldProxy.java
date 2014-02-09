package a3;
public class GameWorldProxy implements IGameWorld, Observable{
	private GameWorld gw;
	public GameWorldProxy(GameWorld target){
		gw=target;
	}
	//used to override Observable/implement proxy as Observable type without typecast and pass to Observers
	@Override
	public void notifyObservers(){}
	@Override
	public void addObserver(Observer o){gw.addObserver(o);}
	
	//methods in IGameWorld to prevent changes or access to real GameWorld by overriding real GameWorld methods
	@Override
	public void addToWorldList(GameObject g){}
	@Override
	public void removeFromWorldList(GameObject g){}
	@Override
	public Fish findFish(){return null;}
	@Override
	public void createBabyFish(Fish f){}
	@Override
	public void growFish(Fish f){}
	@Override
	public GameObject findSeaweed(){return null;}
	@Override
	public Net findNet(){return null;}
	@Override
	public void moveNet(char c){}
	@Override
	public void netScoop(){}
	@Override
	public void reachedSushiBar(){}
	@Override
	public GameCollection getGameCollection(){return gw.getGameCollection();}
	@Override
	public void setSound(boolean b){}
	public boolean fishExist(){return gw.fishExist();}

}