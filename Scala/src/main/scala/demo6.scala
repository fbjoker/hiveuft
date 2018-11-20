object demo6 {

  def main(args: Array[String]): Unit = {
    val a = List(1,2,3)
    val b = List(55,32,99)

    val map = Map("alxe" -> a, "boom" -> b)
    val alex = map.get("alxe")
    val map2=scala.collection.mutable.Map("alex"->1,"bin"->2)

    println(alex.get(1))
    println(alex)
    println(a(1))

    map2 += ("kaka"->999)

    println(map2)
    for (elem <- map2) {
      println(elem)
    }

    println("==================")
    for (elem <- map2.keys) {
      println(elem)
    }
    println("==================")
    for (elem <- map2.values) {
      println(elem)

    }
    println("==================")
    for ((k, v) <- map2) println(k + " is mapped to " + v)



}
