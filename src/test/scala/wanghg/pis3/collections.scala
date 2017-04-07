package wanghg.pis3

import org.junit.Assert._
import org.junit.Test

import scala.collection.immutable.HashSet
import scala.collection.mutable

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
    var sum = 0
    val fn = (x: Int) => {
      sum += x
      x * 2
    }
    (1 to 4).map(fn).filter(_ % 2 == 0)
    assertEquals(10, sum)
    sum = 0
    val list = (1 to 4).view.map(fn).filter(_ % 2 == 0)
    assertEquals(0, sum)
    list.foreach(_ => ())
    assertEquals(10, sum)
  }

  @Test
  def test_addString(): Unit = {
    val sb = new StringBuilder
    val t = Traversable(1, 2, 3)
    t.addString(sb, "[", ",", "]")
    assertEquals("[1,2,3]", sb.toString)
  }

  @Test
  def test_mkString(): Unit = {
    val t = Traversable('a, 'b, 'c)
    assertEquals("<'a,'b,'c>", t.mkString("<", ",", ">"))
  }

  @Test
  def test_stringPrefix(): Unit = {
    assertEquals("List", List(1, 2, 3).stringPrefix)
    assertEquals("Set", Set(1, 2, 3).stringPrefix)
    assertEquals("Set", HashSet(1, 2, 3).stringPrefix)
  }

  @Test
  def test__aggregates(): Unit = {
    assertEquals(10, List(1, 2, 3, 4).sum)
    assertEquals(24, List(1, 2, 3, 4).product)
    assertEquals(1, List(1, 2, 3, 4).min)
    assertEquals(4, List(1, 2, 3, 4).max)
  }

  @Test
  def test_foldLeft(): Unit = {
    assertEquals(10, (0 /: List(1, 2, 3, 4)) (_ + _))
    assertEquals(10, List(1, 2, 3, 4).foldLeft(0)(_ + _))
  }

  @Test
  def test_foldRight(): Unit = {
    assertEquals(List(2, 4, 6, 8), (List(1, 2, 3, 4) :\ List.empty[Int]) (_ * 2 :: _))
    assertEquals(List(2, 4, 6, 8), List(1, 2, 3, 4).foldRight(List.empty[Int])(_ * 2 :: _))
  }

  @Test
  def test__reduce(): Unit = {
    assertEquals(10, List(1, 2, 3, 4).reduceLeft(_ + _))
    assertEquals(4, List(1, 2, 3, 4).reduceRight((x, y) => if (x > y) x else y))
    assertEquals(1, List(1, 2, 3, 4).reduceRight((x, y) => if (x < y) x else y))
  }

  @Test
  def test_splitAt(): Unit = {
    val list = List(1, 2, 3, 4, 'a, 'b, 'c)
    assertEquals((List(1, 2, 3, 4), List('a, 'b, 'c)), list.splitAt(4))
  }

  @Test
  def test_span(): Unit = {
    val list = List(1, 2, 3, 0, -4, 0, 5)
    assertEquals((List(1, 2, 3), List(0, -4, 0, 5)), list.span(_ > 0))
  }

  @Test
  def test_partition(): Unit = {
    val list = List(1, 2, 3, 0, -4, 0, 5)
    assertEquals((List(1, 2, 3, 5), List(0, -4, 0)), list.partition(_ > 0))
  }

  @Test
  def test_groupBy(): Unit = {
    val list = List(1, 2, 3, 0, -4, 0, 5)
    val groups = list.groupBy({
      case x if x > 0 => 'Positive
      case x if x == 0 => 'Zero
      case x if x < 0 => 'Negative
    })
    assertEquals(List(1, 2, 3, 5), groups('Positive))
    assertEquals(List(0, 0), groups('Zero))
    assertEquals(List(-4), groups('Negative))
  }

  @Test
  def test_head(): Unit = {
    assertEquals('a, List('a, 'b, 'c).head)
  }

  @Test
  def test_headOption(): Unit = {
    assertEquals(Some('a), List('a, 'b, 'c).headOption)
    assertEquals(None, Nil.headOption)
  }

  @Test
  def test_last(): Unit = {
    assertEquals('c, List('a, 'b, 'c).last)
  }

  @Test
  def test_lastOption(): Unit = {
    assertEquals(Some('c), List('a, 'b, 'c).lastOption)
    assertEquals(None, Nil.lastOption)
  }

  @Test
  def test_find(): Unit = {
    val list = List(1, 2, 3, -4, 5)
    assertEquals(Some(-4), list.find(_ < 0))
    assertEquals(None, list.find(_ == 0))
  }

  @Test
  def test_tail(): Unit = {
    val list = List('a, 'b, 'c)
    assertEquals(List('b, 'c), list.tail)
  }

  @Test
  def test_init(): Unit = {
    val list = List('a, 'b, 'c)
    assertEquals(List('a, 'b), list.init)
  }

  @Test
  def test_slice(): Unit = {
    val list = List('a, 'b, 'c)
    assertEquals(List('b), list.slice(1, 2))
  }

  @Test
  def test_take(): Unit = {
    val list = List(1, 2, 3, 4, 'a, 'b, 'c)
    assertEquals(List(1, 2, 3, 4), list.take(4))
  }

  @Test
  def test_drop(): Unit = {
    val list = List(1, 2, 3, 4, 'a, 'b, 'c)
    assertEquals(List('a, 'b, 'c), list.drop(4))
  }

  @Test
  def test_takeWhile(): Unit = {
    val list = List(1, 2, 3, 0, -4, 0, 5)
    assertEquals(List(1, 2, 3), list.takeWhile(_ > 0))
  }

  @Test
  def test_dropWhile(): Unit = {
    val list = List(1, 2, 3, 0, -4, 0, 5)
    assertEquals(List(0, -4, 0, 5), list.dropWhile(_ > 0))
  }

  @Test
  def test_filter(): Unit = {
    val list = List(1, 2, 3, 0, -4, 0, 5)
    assertEquals(List(1, 2, 3, 5), list.filter(_ > 0))
  }

  @Test
  def test_filterNot(): Unit = {
    val list = List(1, 2, 3, 0, -4, 0, 5)
    assertEquals(List(0, -4, 0), list.filterNot(_ > 0))
  }

  @Test
  def test_iterator(): Unit = {
    val it = Iterable(1, 2, 3, 4, 5).iterator
    assertEquals(1, it.next)
    assertEquals(2, it.next)
    assertEquals(3, it.next)
    assertEquals(4, it.next)
    assertEquals(5, it.next)
    assertEquals(false, it.hasNext)
  }

  @Test
  def test_grouped(): Unit = {
    val git = Iterable(1, 2, 3, 4, 5).grouped(3)
    assertEquals(List(1, 2, 3), git.next)
    assertEquals(List(4, 5), git.next)
    assertEquals(false, git.hasNext)
  }

  @Test
  def test_sliding(): Unit = {
    val sit = Iterable(1, 2, 3, 4, 5).sliding(3)
    assertEquals(List(1, 2, 3), sit.next)
    assertEquals(List(2, 3, 4), sit.next)
    assertEquals(List(3, 4, 5), sit.next)
    assertEquals(false, sit.hasNext)
  }

  @Test
  def test_takeRight(): Unit = {
    val sub = Iterable(1, 2, 3, 4, 5).takeRight(3)
    assertEquals(List(3, 4, 5), sub)
  }

  @Test
  def test_dropRight(): Unit = {
    val sub = Iterable(1, 2, 3, 4, 5).dropRight(3)
    assertEquals(List(1, 2), sub)
  }

  @Test
  def test_zip(): Unit = {
    val zipped = Iterable(1, 2, 3) zip Iterable('a, 'b, 'c, 'd, 'e, 'f)
    assertEquals(List((1, 'a), (2, 'b), (3, 'c)), zipped)
  }

  @Test
  def test_zipAll(): Unit = {
    val zipped = Iterable(1, 2, 3) zipAll(Iterable('a, 'b, 'c, 'd, 'e, 'f), 0, 'x)
    assertEquals(List((1, 'a), (2, 'b), (3, 'c), (0, 'd), (0, 'e), (0, 'f)), zipped)
    val zipped2 = Iterable(1, 2, 3, 4, 5, 6) zipAll(Iterable('a, 'b, 'c, 'd), 0, 'x)
    assertEquals(List((1, 'a), (2, 'b), (3, 'c), (4, 'd), (5, 'x), (6, 'x)), zipped2)
  }

  @Test
  def test_zipWithIndex(): Unit = {
    val zipped = Iterable('a, 'b, 'c, 'd, 'e, 'f).zipWithIndex
    assertEquals(List(('a, 0), ('b, 1), ('c, 2), ('d, 3), ('e, 4), ('f, 5)), zipped)
  }

  @Test
  def test_sameElements(): Unit = {
    val lh = List(1, 2, 3, 4, 5)
    val rh = mutable.LinkedHashSet(1, 2, 3, 4, 5)
    assertNotEquals(lh, rh)
    assertEquals(true, lh sameElements rh)
  }

}

