package com.alex.scala

object test1 {
  def main(args: Array[String]): Unit = {


    for(x<- 0 to 10; y= 10-x){

      println(y)
    }

    def countdown(n:Int){

      for(x<- 0 to n; y= 10-x){

        println(y)
      }

    }
    countdown(10)
  }


}
