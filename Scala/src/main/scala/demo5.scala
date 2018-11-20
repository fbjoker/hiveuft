import scala.collection.mutable.ArrayBuffer

object demo5 {


  def main(args: Array[String]): Unit = {

    val arr2 = new Array[Int](10)
    arr2(0)=4
    arr2(3)=3

    for (x<- arr2;if x!=0){
      println(x)
    }

    val arr22 = new ArrayBuffer[Int]()
    arr22.append(222)
    arr22.append(3333)
    arr22.append(444)
    arr22.append(555)

   val arr3= for (x<- arr22) yield  x

    println(arr3)



    val tuple=(1,2,3,"a",'b')

    println(tuple._4)

    for (i <- tuple.productIterator){
      println(i)
    }

    tuple.productIterator.foreach(print(_))
   tuple.productIterator.foreach(i=>print(i))


    val list = List(1,2,3)
    println(list)
    print(list(1))

    val list2=list:+99

    println(list2)
    val list3=100+:list2
    println(list3)

    val list4 = 1 :: 2 :: 3 :: list3 :: Nil
    println(list4)
    val list5 = 1 :: 2 :: 3 :: list3
    println(list5)


  }

}
