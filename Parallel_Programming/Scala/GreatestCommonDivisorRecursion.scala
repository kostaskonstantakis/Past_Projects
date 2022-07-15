object GreatestCommonDivisorRecursion { //GCD/GCF

def GCD(a: Int, b: Int): Int = {
  if(b==0) return a;
  else return GCD(b, a%b);
  
}

def main(args: Array[String]): Unit = {

var a=scala.io.StdIn.readInt()
var b=scala.io.StdIn.readInt()

	
 var gcd=GCD(a, b);
 println("GCD("+a+", "+b+")="+gcd)
        
    }
}
