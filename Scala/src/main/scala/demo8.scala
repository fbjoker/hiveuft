import scala.collection.mutable.ArrayBuffer

object demo8 {

  def main(args: Array[String]): Unit = {

    val ab = ArrayBuffer(1,2,3)
//    println(ab)
//    println(ab.mkString("\t"))
//
    ab(2)=22
//
//    println(ab)

    val cc=ab(1)
//    println(cc)

    ab.append(44)

    println(ab)
    val ac =ab:+33
    println(ac)
  }
}
