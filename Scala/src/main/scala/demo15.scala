object demo15 {
  def main(args: Array[String]): Unit = {


    val list= List("alex","bin","ccc")
    val list3= List(1,2,3,4,5)
    list3.reduceLeft()


    val list2 = list.map(x=>x.toUpperCase())
    println(list2)
  }

}
