import java.util.*;
public class MyQueue implements Agenda{
    
    private LinkedList<Location> list = new LinkedList<Location>();
    private int size = 0;
    
    public void add(Location loc){
      list.add(loc);
      size++;
    }
    
    public Location next(){
	Location next = list.remove();
	size--;
	return next;
    }
    
    public int size(){
	return size;
    }		
}
