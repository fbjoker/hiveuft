import scala.collection.JavaConversions.bufferAsJavaList
import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.Buffer
object demo10 {
  def main(args: Array[String]): Unit = {


    val array = Array.ofDim[Int](3,4)

    val ar2 = array :+ Array.ofDim[Int](3,4)
    val arr3 = ar2 :+ Array(1,2,3,4)
    for (elem <- arr3) {
      println(elem.mkString("\t"))
    }
    val arrt = ArrayBuffer("1","2","3")

    import  scala.collection.JavaConversions._
    val javaarr= new ProcessBuilder(arrt)
    javaarr.command().add("10")
    println(javaarr.command().add("10"))
    println(javaarr.command())

    val arrb:Buffer[String] =javaarr.command()
    println(arrb)

  }

}
