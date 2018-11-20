import scala.util.control.Breaks

object scalademo3 {

  def main(args: Array[String]): Unit = {
    for(i <- 1 to 3; j = 4 - i) {
      print(j + " ")
      println(i + " ")
    }
    println()



    val for5 = for(i <- 1 to 10) yield i
    println(for5)
      val breaks = new Breaks
      var flag=1
    breaks.breakable{
      while (flag<10){
        flag+=1;

        if(flag==6){
          breaks.break()
        }
        println(flag)

      }
    }





  }

}
