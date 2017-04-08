package wanghg.pis3

import scala.util.Random

object StreamTest {

  def main(args: Array[String]): Unit = {
    for (x <- rseq(100).take(10))
      println(x)
  }

  def rseq(limit: Int): Stream[Int] = Random.nextInt(limit) #:: rseq(limit)

  def goldenRatio(): Unit = {
    val fib = fibonacci(1000, 1)
    val pairs = fib.sliding(2).map(s => (s.head, s.tail.head)).take(30)
    for ((a, b) <- pairs) {
      println(1.0 * b / a)
    }
  }

  def fibonacci(a: Int, b: Int): Stream[Int] = a #:: fibonacci(b, a + b)

}
