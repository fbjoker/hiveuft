import scala.util.control.Breaks

object scalademo1 {
  def main(args: Array[String]): Unit = {
    println("helloworld")


    val a1 = 100
    val a2 =
      if (a1 > 20) {
        "大于20"
      } else {
        "小于20"
      }
    print(a2)


    var i = 100;

    val w = while (i < 100) {
      i += 1
      print(i)
    }

    print(i)
    println(w)
  }
  var c = 10
  private val breaks = new Breaks
  breaks.breakable {

    while (c < 100) {
      c+=1
      if (c == 50) {
        breaks.break()
      }
    }
  }

  println("----------------++-")
  println(c)


}
