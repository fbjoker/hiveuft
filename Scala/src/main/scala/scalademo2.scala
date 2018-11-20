object scalademo2 {


  def main(args: Array[String]): Unit = {

    for (i<-1 to 3;j<-1 to 3){
      print(i*j+" ")
    }

    println()
  }

  for(i <- 1 until 3; j <- 1 until 3) {
    print(i * j + " ")
  }
  println()

  for(i <- 1 to 33 if i < 12) {
    print(i + " ")
  }
  println()



}
