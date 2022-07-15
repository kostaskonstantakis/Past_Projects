import java.util.*;
import java.lang.*;
import java.io.*;

public class Example extends Thread {
private static int counter = 0; // shared state
public void run() {
int y = counter;
counter = y + 1;
System.out.println("counter="+counter);
}
public static void main(String args[]) {
Thread t1 = new Example();
Thread t2 = new Example();
try{
t1.start();
t2.start();
t1.join();
t2.join();
}catch(InterruptedException ie) {System.err.println(ie);}
}
}
