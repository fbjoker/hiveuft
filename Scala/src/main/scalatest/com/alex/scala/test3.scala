package com.alex.scala

object test3 {
  def main(args: Array[String]): Unit = {
    def product(s:String): Long = {
      var sum=1L
      for (x<-s){

        sum*=x.toLong

      }
      sum

    }

//    println(product("Hello"))
//
//    println("alex".charAt(2))
//    println("alex".take(3).charAt(1))
//    println("alex".drop(1))




    def product2(s:String) :Long={
      if(s.length==1){
       return s.charAt(0).toLong
      }else{
        s.take(1).charAt(0).toLong*product2(s.drop(1))
      }

    }
    println(product2("Hello"))

  }

}
