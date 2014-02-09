package a3;
public interface IGameWorld{
	//provide interface
	void addToWorldList(GameObject g);
	void removeFromWorldList(GameObject g);
	Fish findFish();
	void createBabyFish(Fish f);
	void growFish(Fish f);
	GameObject findSeaweed();
	Net findNet();
	void moveNet(char c);
	void netScoop();
	void reachedSushiBar();
	GameCollection getGameCollection();
	void setSound(boolean b);
}