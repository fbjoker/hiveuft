object demo7 {

  def main(args: Array[String]): Unit = {

    val set = Set(1,-22,31)

    println(set)
    import scala.collection.mutable.Set
    val mutableSet = Set(1, 2, 3)

    mutableSet.add(4)
    println(mutableSet)
    mutableSet+=6
    println(mutableSet)
    val set2 = mutableSet.+(5)

    println(mutableSet)
    println(set2)

    mutableSet-=1
    println(mutableSet)

    mutableSet.remove(6)
    println(mutableSet)

    for (elem <- mutableSet) {

      println(elem)
    }

    val names = List("Alice", "Bob", "Nick")
    println(set.map(_.abs))



    println(names.flatMap(_.toUpperCase()))


    val list = List(1, 2, 3, 4, 5)
    val i1 = list.reduceLeft(_ - _)
    val i2 = list.reduceRight(_ - _)
    println(i1)
    println(i1)
    println(i2)
    println(i2)

    val list2 = List(1, 9, 2, 8)
    val i4 = list2.fold(5)((sum, y) => sum + y)
    println(i4)

    val list3 = List(1, 9, 2, 8)
    val i5 = list3.foldRight(100)(_ - _)
    println(i5)
    val i8 = (1 to 10).scanLeft(0)(_ + _)
    println(i8)




  }

}
