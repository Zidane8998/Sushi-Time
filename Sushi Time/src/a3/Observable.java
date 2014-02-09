package a3;
public interface Observable{
	public void addObserver(Observer o);
	public void notifyObservers();
}