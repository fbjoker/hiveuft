object scalademo3 {

  def main(args: Array[String]): Unit = {
    for(i <- 1 to 3; j = 4 - i) {
      print(j + " ")
      println(i + " ")
    }
    println()



    val for5 = for(i <- 1 to 10) yield i
    println(for5)





  }

}
