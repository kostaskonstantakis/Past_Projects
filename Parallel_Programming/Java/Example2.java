import java.util.*;
import java.lang.*;
import java.io.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;

public class Example2 extends Thread {
static Lock lock = new ReentrantLock();
static int counter = 0;
static Lock l = new ReentrantLock();
static Lock m = new ReentrantLock();

public void inc1() {
l.lock();
counter++;
System.out.println("inc1() counter="+counter);
l.unlock();
}

public void inc2() {
m.lock();
counter++;
System.out.println("inc2() counter="+counter);
m.unlock();
}

public void run() {
lock.lock();
int y = counter;
counter = y + 1;
System.out.println("run() counter="+counter);
inc1();
inc2();
lock.unlock();
}

public static void main(String args[]) {
Thread t1 = new Example2();
Thread t2 = new Example2();
try{
t1.start();
t2.start();
t1.join();
t2.join();
//t1.inc1();
//t1.inc2();
//t2.inc1();
//t2.inc2();
}catch(InterruptedException ie) {System.err.println(ie);}
}


}
