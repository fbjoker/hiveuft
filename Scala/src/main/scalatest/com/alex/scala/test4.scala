package com.alex.scala

object test4 {
  def main(args: Array[String]): Unit = {


    def xn(x: Int, n: Int): Double = {

      if (n == 0) {
        1
      } else {
        if (n > 0) {
          x * xn(x, n - 1)
        } else {
          1 / xn(x, -n)
        }

      }

    }

    println(xn(2, -2))
    println(1/4)
  }
}
