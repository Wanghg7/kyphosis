package wanghg.pis3

object StreamTest {

  def main(args: Array[String]): Unit = {
    val fib = fibonacci(1000, 1)
    val pairs = fib.sliding(2).map(s => (s.head, s.tail.head)).take(30)
    for ((a, b) <- pairs) {
      println(1.0 * b / a)
    }
  }

  def fibonacci(a: Int, b: Int): Stream[Int] =
    a #:: fibonacci(b, a + b)

}
