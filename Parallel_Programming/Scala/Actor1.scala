import scala._
import java.lang._
//import akka.actor._


class Actor1 extends Actor {
def receive = {
case msg: String =>
println(msg)
case i: Int =>
sender ! "World!"
}
}
val a1 = system.actorOf(Props[Actor1])
class Actor2 extends Actor {
def receive = {
  case msg: String => 
  sender ! "Hello, World!";
/* fill this in */
}
}
val a2 = system.actorOf(Props[Actor2])
class Actor3 extends Actor {
def receive = {
case "Hi" =>
a1 ! 42
case msg: String =>
a2 ! ("Hello " + msg)
}
}
val a3 = system.actorOf(Props[Actor3])
a3 ! "Hi"
