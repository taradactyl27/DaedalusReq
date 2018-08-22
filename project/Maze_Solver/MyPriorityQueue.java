public class MyPriorityQueue implements Agenda{
    private PriorityQueue p = new PriorityQueue(false);
    
    public void add(Location loc){
	p.add(loc);
    }
    
    public Location next(){
	return p.remove();
    }
    
  public int size(){
      return p.size();
  }
}
