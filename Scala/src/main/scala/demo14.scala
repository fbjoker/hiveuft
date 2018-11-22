

object demo14 {
  def main(args: Array[String]): Unit = {


    val set = Set(1,2,3,3,4,33,33,5,5,6)
    println(set)
       val set2 =  scala.collection.mutable.Set(1,2,3)


    set2+=33
    set2.add(555)
    println(set2)




  }

}
