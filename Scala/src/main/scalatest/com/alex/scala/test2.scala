package com.alex.scala

object test2 {
  def main(args: Array[String]): Unit = {

    var sum=1L
    for (x<-"Hello"){
      println(x)
      sum*=x.toLong
      println(sum)
    }
    println("====================")
  }

  var sum2=1L
  "Hello".foreach{
    i=>
      sum2*=i.toLong

  }
      println(sum2)

  def product(s:String): Long = {
    var sum=1L
    for (x<-"Hello"){
      println(x)
      sum*=x.toLong

    }
    sum
  }
  println("#===================")
   val ss = product("Hello")
  println(ss)
  println("#===================")
}
