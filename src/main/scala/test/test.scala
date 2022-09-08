package test

import scala.math.exp

object test  {

  def main(args: Array[String]) {
    val stdin = scala.io.StdIn

    val n = stdin.readLine.trim.toInt

    for (nItr <- 1 to n) {
      val x = stdin.readLine.trim.toDouble
      val result:Double = math.exp(x)
      println(f"$result%.4f") //f"$value%.1f"
    }
    println(exp(20))
  }

}