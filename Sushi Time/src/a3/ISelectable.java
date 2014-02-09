package a3;
import java.awt.Point;
public interface ISelectable{
	public void setSelected(boolean b);
	public boolean isSelected();
	public boolean contains(Point p);
}