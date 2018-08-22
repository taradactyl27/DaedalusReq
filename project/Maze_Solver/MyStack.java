import java.util.*;
public class MyStack implements Agenda{
    
    private Stack<Location> stack = new Stack<Location>();
    private int size = 0;
    
    public void add(Location loc){
	stack.push(loc);
	size++;
    }
    
    public Location next(){
	Location next = stack.pop();
	size--;
	return next;
    }
    
    public int size(){
	return size;
    }
}
