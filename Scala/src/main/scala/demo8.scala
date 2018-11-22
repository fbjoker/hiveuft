import scala.collection.mutable.ArrayBuffer

object demo8 {

  def main(args: Array[String]): Unit = {

    val ab = ArrayBuffer(1,2,3)
    println(ab)
    println(ab.mkString("\t"))
  }
}
