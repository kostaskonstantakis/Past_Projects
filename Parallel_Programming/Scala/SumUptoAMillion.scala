object SumUptoAMillion {
	def main(args: Array[String]): Unit = {

        //sequential
        val list = (1 to 1000000).toList
        val sum=list.sum
        println("1st sum="+sum)

        //parallel
        val par_list = (1 to 1000000).toList.par
        val par_sum=list.par.sum
        println("2nd sum="+par_sum)
        
        //optimized parallel
        val sum2 = (1 to 1000000).par.sum
        println("3rd sum="+sum2)
        
    }
}
