package a3;
public interface ICollidable{
	public boolean collidesWith(ICollidable object);
	public void collision(ICollidable object);
	public boolean eats(ICollidable obj, ICollidable obj2);
}