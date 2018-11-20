object demo4 {



  def init(): String = {
    println("init方法执行")
    "嘿嘿嘿~~~~"
  }

  def main(args: Array[String]): Unit = {
    lazy val msg = init()
    println("lazy方法没有执行")
    println(msg)
  }
}
