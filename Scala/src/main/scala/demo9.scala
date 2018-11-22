object demo9 {
  def main(args: Array[String]): Unit = {

    val array = Array.ofDim[Int](10,10)

    println(array.mkString(","))

    for (elem <- array) {
      println(elem.mkString("\t"))

    }
    println("=============================================")

    array(1)(8)=18
    for(x<-0 to 9 ;y<- 0 to 9){
      array(x)(y)=x*y
    }
    for (elem <- array) {
      println(elem.mkString("\t"))

    }
    println("=============================================")
    for(x<-0 to 9 ;y<- 0 to 9){
     if (x==y){

      array(x)(y)=x*y
     }
    }
    for (elem <- array) {
      println(elem.mkString("\t"))

    }

  }
}
