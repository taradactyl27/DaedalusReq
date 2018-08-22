import java.util.NoSuchElementException;

public class PriorityQueue{
    private Location[] array =  new Location[10];
    private int max = 1, size;

    public PriorityQueue(){
      size = 0;
    }

    public PriorityQueue(boolean bool){
       size = 0;
       if(bool){
         max = 1;
       }else{
         max = -1;
       }
    }

    public void swap(int a, int b){
      Location swapper = array[b];
      array[b] = array[a];
      array[a] = swapper;
    }
    public Location peek(){
	     if(size == 0) throw new NoSuchElementException();
	     return array[1];
    }
    private int comp(Location a, Location b){
      return a.compareTo(b);
    }
	//learned pushup/down from md
    private void pushUp(int i){
	     while(i != 1 && comp(array[i],array[i/2]) * max > 0){
	     int dad = i/2;
       swap(i,dad);
	     i = dad;
	    }
    }

    //helped from MD
    private void pushDown(int i){
	    while(2 * i <= size){
	       if(2 * i + 1 <= size && comp(array[2 * i + 1], array[2 * i]) * max > 0){
		         if(comp(array[i], array[2 * i + 1]) * max < 0){
		             int little = 2 * i + 1;
		             swap(i,little);
		             i = little;
		         }else{ break; }
	       }else{ if(comp(array[i], array[2 * i]) * max < 0){
		              int kid = 2 * i;
		              swap(i,kid);
		              i = kid;
		              }else{ break;}
	             }
	   }
  }
  public void add(Location s){
     if(size+1 == array.length) getBig();
     array[size+1] = s;
     size++;
     pushUp(size);
  }
  public Location remove(){
     if(size == 0) throw new NoSuchElementException();
     Location prev = array[1];
     array[1] = array[size];
     size--;
     pushDown(1);
     return prev;
  }
  public String toString(){
	   return java.util.Arrays.toString(array);
  }
  private void getBig(){
    Location[] big = new Location[size*2];
    for(int i = 1; i <= size; i++){
      big[i] = array[i];
    }
	   array = big;
    }
  public int size(){
	   return size;
  }
}
