import scala.collection.mutable

object demo12 {
  def main(args: Array[String]): Unit = {

    val queue = mutable.Queue[Int](1,2,3,4)

    println(queue)


    val q2 = new mutable.Queue[Int]()

    q2++=List(1,2,33,4)


    println(q2)

    println(q2(2))
    q2(2)=55
    println(q2)
    q2+=77
    println(q2)
    q2.enqueue(8,9)
    println(q2)
    q2.dequeue()
    println(q2)
    println("============")
    println(q2.head)
    println(q2.tail)
    println(q2.last)
    println(q2.tail)




  }
}
