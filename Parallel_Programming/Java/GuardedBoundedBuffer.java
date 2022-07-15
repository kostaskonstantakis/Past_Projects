import java.util.*;
import java.lang.*;
import java.io.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;
import java.nio.*;


public class GuardedBoundedBuffer implements Buffer {
private List data;
private final int capacity;
public GuardedBoundedBuffer(int capacity) {
data = new ArrayList(capacity);
this.capacity = capacity;
}
public synchronized Object take() throws Failure {
while (data.size() == 0)
try { wait(); }
catch (InterruptedException e) { System.err.println(ex.getMessage());}
Object temp = data.get(0);
data.remove(0);
notifyAll();
return temp;
}
public synchronized void put(Object obj) throws Failure {
while (data.size() == capacity)
try { wait(); }
catch (InterruptedException e) {  System.err.println(ex.getMessage());}
data.add(obj);
notifyAll();
}
public synchronized int size() { return data.size(); }
public int capacity() { return capacity; }

public static void main(String args[]) {
GuardedBoundedBuffer gbb=new GuardedBoundedBuffer(10);
gbb.take();
gbb.take();
System.out.println("capacity="+gbb.capacity());
System.out.println("size="+gbb.size());
gbb.put(12);
System.out.println("capacity="+gbb.capacity());

}


}
