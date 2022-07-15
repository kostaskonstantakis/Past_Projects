import java.util.*;
import java.lang.*;
import java.io.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;
import java.nio.*;


public class BalkingBoundedBuffer implements Buffer {
private List data;
private final int capacity;
public BalkingBoundedBuffer(int capacity) {
data = new ArrayList(capacity);
this.capacity = capacity;
}
public synchronized Object take() throws Failure {
if (data.size() == 0) throw new Failure("Buffer Empty");
Object temp = data.get(0);
data.remove(0);
return temp;
}
public synchronized void put(Object o) throws Failure {
if (data.size() == capacity) throw new Failure("Buffer Full");
data.add(o);
}
public synchronized int size() { return data.size(); }
public int capacity() { return capacity; }
}
