import java.util.*;
import java.lang.*;
import java.io.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;


public class ProducerConsumer extends Thread{
private boolean valueReady = false;
private Object value;

synchronized void produce(Object o) {
try {
while (valueReady) wait();
value = o;
valueReady = true;
System.out.println("Producing...");
notifyAll();
}catch(InterruptedException ex) {System.err.println(ex.getMessage());}
}

synchronized Object consume() {
try {
while (!valueReady) wait();
valueReady = false;
Object o = value;
System.out.println("Consuming...");
notifyAll();
return o;
}catch(InterruptedException ex) {System.err.println(ex.getMessage());}
return null;
}


public static void main(String args[]) {
ProducerConsumer producer=new ProducerConsumer ();
ProducerConsumer consumer=new ProducerConsumer ();

Object ob=new Object();
Object ob2=new Object();
producer.produce(ob);
ob2=consumer.consume();
}


}
