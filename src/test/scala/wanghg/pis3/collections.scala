package wanghg.pis3

import org.junit.Test
import org.junit.Assert._

import scala.collection.mutable.ListBuffer

class CollectionTest {

  @Test
  def test_flatMap(): Unit = {
    val actual = Traversable(1, 2, 3).flatMap(x => for (i <- 1 to x) yield i)
    val expected = List(1, 1, 2, 1, 2, 3)
    assertEquals(expected, actual)
  }

  @Test
  def test_collect(): Unit = {
    val list = List(1, 2, "three", 4, "five", 6)
    val actual = list.collect({ case x: Int => x * 2 })
    val expected = List(2, 4, 8, 12)
    assertEquals(expected, actual)
  }

  @Test
  def test_view(): Unit = {
    val fn = (x: Int) => {
      println("map: " + x);
      x * 2
    }
    val list = (0 until 10).view.map(fn).filter(_ % 2 == 0)
    val list2 = (0 until 10).map(fn).filter(_ % 2 == 0)
  }

}

