object demo11 {
  def main(args: Array[String]): Unit = {


    val t1=(1,23,"dd")

    println(t1._3)

    for(e<-t1.productIterator){
      println(e)
    }

    t1.productIterator.foreach(i=>println(i))
    t1.productIterator.foreach(println(_))




    val list = List(1,23,4,5)

  println(list(1))

    val list2=list:+5
    println(list2)
    for (elem <- list2) {

      println(elem)
    }

    val list3 = list2.updated(3,6)
    val reverse = list3.reverse
    println(list3)
    println(reverse  )
    val sl = reverse.slice(1,3)
    println(sl)

    val nn=1::2::3::Nil
    println(nn)




  }

}
