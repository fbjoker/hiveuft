object demo13 {
  def main(args: Array[String]): Unit = {


    var map = Map("alex" -> 11, "bin" -> 22)

    println(map)

    println(map("alex"))
    println(map.updated("alex",123))
    println(map.values)
    println(map.keys)
    println(map.keySet)


    map+=("cc"->22,"dd"->33)
    println(map)


    map-=("cc")

    println(map)

    for (elem <- map) {
      println(elem)
    }
    for ((k,v) <- map) {
      println(k)
      println(v)
    }





  }

}
