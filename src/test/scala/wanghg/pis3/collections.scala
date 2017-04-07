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

  @Test
  def test_apply(): Unit = {
    val seq = Seq('a, 'b, 'c)
    assertEquals('b, seq(1))
    assertEquals('b, seq apply 1)
    assertEquals('b, seq.apply(1))
  }

  @Test
  def test_isDefinedAt(): Unit = {
    val seq = Seq('a, 'b, 'c)
    assertEquals(true, seq.isDefinedAt(1))
    assertEquals(false, seq.isDefinedAt(3))
  }

  @Test
  def test_length(): Unit = {
    val seq = Seq('a, 'b, 'c)
    assertEquals(3, seq.length)
    assertEquals(3, seq.size)
  }

  @Test
  def test_lengthCompare(): Unit = {
    val a: Seq[Int] = Stream.from(0)
    val b = Seq('a, 'b, 'c)
    assertEquals(0, b.lengthCompare(3))
    assertEquals(-1, b.lengthCompare(4))
    assertEquals(1, b.lengthCompare(2))
    assertEquals(1, a.lengthCompare(4321))
  }

  @Test
  def test_indices(): Unit = {
    val seq = Seq(1, 2, 3, 4, 5)
    assertEquals(0 to 4, seq.indices)
  }

  @Test
  def test_indexOf(): Unit = {
    val seq = Seq('a, 'b, 'c, 'a, 'b, 'c, 'd)
    assertEquals(1, seq.indexOf('b))
  }

  @Test
  def test_lastIndexOf(): Unit = {
    val seq = Seq('a, 'b, 'c, 'a, 'b, 'c, 'd)
    assertEquals(4, seq.lastIndexOf('b))
  }

  @Test
  def test_indexOfSlice(): Unit = {
    val seq = Seq('a, 'b, 'c, 'a, 'b, 'c, 'd)
    assertEquals(1, seq.indexOfSlice(List('b, 'c)))
    assertEquals(5, seq.indexOfSlice(List('c, 'd)))
  }

  @Test
  def test_lastIndexOfSlice(): Unit = {
    val seq = Seq('a, 'b, 'c, 'a, 'b, 'c, 'd)
    assertEquals(4, seq.lastIndexOfSlice(List('b, 'c)))
  }

  @Test
  def test_indexWhere(): Unit = {
    val seq = Seq(0, 1, -1, 2, 3, 4, -1, 5, 6)
    assertEquals(2, seq.indexWhere(_ < 0))
  }

  @Test
  def test_segmentLength(): Unit = {
    val seq = Seq(0, 1, -1, 2, 3, 4, -1, 5, 6)
    assertEquals(2, seq.segmentLength(_ >= 0, 0))
    assertEquals(0, seq.segmentLength(_ > 0, 2))
    assertEquals(3, seq.segmentLength(_ > 0, 3))
  }

  @Test
  def test_prefixLength(): Unit = {
    val seq = Seq(0, 1, -1, 2, 3, 4, -1, 5, 6)
    assertEquals(0, seq.prefixLength(_ > 0))
    assertEquals(2, seq.prefixLength(_ >= 0))
    assertEquals(3, seq.prefixLength(_ < 2))
  }

  @Test
  def test_:+(): Unit = {
    val seq: Seq[Int] = Seq(1, 2, 3)
    val expected: Seq[Any] = Seq(1, 2, 3, 'a)
    assertEquals(expected, seq :+ 'a)
  }

  @Test
  def test_+:(): Unit = {
    val seq: Seq[Int] = Seq(1, 2, 3)
    val expected: Seq[Any] = Seq('a, 1, 2, 3)
    assertEquals(expected, 'a +: seq)
  }

  @Test
  def test_padTo(): Unit = {
    val seq = Seq(1, 2, 3, 4, 5)
    val expected = Seq(1, 2, 3, 4, 5, 0, 0)
    assertEquals(expected, seq padTo(7, 0))
  }

  @Test
  def test_patch(): Unit = {
    val seq = Seq(1, 2, 3, 4, 5, 6, 7)
    val patch = Seq('a, 'b, 'c, 'd)
    val expected = Seq(1, 2, 'a, 'b, 'c, 'd, 6, 7)
    assertEquals(expected, seq.patch(2, patch, 3))
    assertEquals(Seq(1, 2, 6, 7), seq.patch(2, Seq(), 3))
    assertEquals(Seq(1, 2, 'a, 'b, 'c, 3, 4, 5, 6, 7), seq.patch(2, Seq('a, 'b, 'c), 0))
  }

  @Test
  def test_updated(): Unit = {
    val seq = Seq(1, 2, 3, 4, 5, 6, 7)
    assertEquals(Seq(1, 2, 3, 'x, 5, 6, 7), seq.updated(3, 'x))
  }

  @Test
  def test_update(): Unit = {
    val seq = mutable.Seq[Any](1, 2, 3, 4, 5, 6, 7)
    seq.update(3, 'x)
    assertEquals(Seq(1, 2, 3, 'x, 5, 6, 7), seq)
    seq(3) = 'w
    assertEquals(Seq(1, 2, 3, 'w, 5, 6, 7), seq)
  }

  @Test
  def test_sorted(): Unit = {
    val seq = Seq(1, 9, 2, 8, 3, 7)
    assertEquals(Seq(1, 2, 3, 7, 8, 9), seq.sorted)
  }

  @Test
  def test_sortWith(): Unit = {
    val seq = Seq((1, 'd'), (9, 'a'), (2, 'c'), (8, 'b'))
    assertEquals(Seq((1, 'd'), (2, 'c'), (8, 'b'), (9, 'a')), seq.sortWith(_._1 < _._1))
    assertEquals(Seq((1, 'd'), (2, 'c'), (8, 'b'), (9, 'a')), seq.sortWith(_._2 > _._2))
  }

  @Test
  def test_sortBy(): Unit = {
    val seq = Seq("One", "Four", "Two", "Three")
    assertEquals(Seq("Four", "One", "Three", "Two"), seq.sorted)
    assertEquals(Seq("One", "Two", "Three", "Four"), seq.sortBy({
      case "One" => 1
      case "Two" => 2
      case "Three" => 3
      case "Four" => 4
    }))
  }

  @Test
  def test_reverse(): Unit = {
    val seq = Seq(1, 2, 3)
    assertEquals(Seq(3, 2, 1), seq.reverse)
  }

  @Test
  def test_reverseIterator(): Unit = {
    val rit = Seq(1, 2, 3).reverseIterator
    assertEquals(3, rit.next)
    assertEquals(2, rit.next)
    assertEquals(1, rit.next)
    assertEquals(false, rit.hasNext)
  }

  @Test
  def test_reverseMap(): Unit = {
    val seq = Seq(1, 2, 3)
    assertEquals(Seq(6, 4, 2), seq.reverseMap(_ * 2))
  }

  @Test
  def test_startsWith(): Unit = {
    val seq = Seq('a, 'b, 'c, 'a, 'b, 'c, 'd)
    assertEquals(true, seq.startsWith(Seq('a, 'b)))
    assertEquals(false, seq.startsWith(Seq('c, 'd)))
  }

  @Test
  def test_endsWith(): Unit = {
    val seq = Seq('a, 'b, 'c, 'a, 'b, 'c, 'd)
    assertEquals(false, seq.endsWith(Seq('a, 'b)))
    assertEquals(true, seq.endsWith(Seq('c, 'd)))
  }

  @Test
  def test_corresponds(): Unit = {
    assertEquals(true, (Seq() corresponds Seq()) ((_, _) => true))
    assertEquals(true, (Seq() corresponds Seq()) ((_, _) => false))
    assertEquals(false, (Seq(1, 2, 3) corresponds Seq()) ((_, _) => true))
    assertEquals(false, (Seq() corresponds Seq(1, 2, 3)) ((_, _) => true))
    assertEquals(false, (Seq(1, 2) corresponds Seq(1, 2, 3)) ((_, _) => true))
    assertEquals(false, (Seq(1, 2, 3) corresponds Seq(1, 2)) ((_, _) => true))
    assertEquals(true, (Seq(1, 9, 3, 7) corresponds Seq(8, 0, 6, 2)) (_ + _ == 9))
    assertEquals(false, (Seq(1, 9, 3, 7, 5) corresponds Seq(8, 0, 6, 2)) (_ + _ == 9))
    assertEquals(false, (Seq(1, 9, 3, 7) corresponds Seq(8, 0, 6, 2, 5)) (_ + _ == 9))
  }

  @Test
  def test_intersect(): Unit = {
    assertEquals(Seq(), Seq(1, 2, 3) intersect Seq(4, 5, 6))
    assertEquals(Seq(3, 4), Seq(1, 2, 3, 4) intersect Seq(3, 4, 5, 6))
    assertEquals(Seq(3, 4), Seq(1, 2, 3, 4) intersect Seq(3, 4, 5, 6).reverse)
    assertEquals(Seq('a, 'b, 3, 4), Seq('a, 'b, 1, 2, 3, 4) intersect Seq(3, 4, 5, 6, 'a, 'b))
    assertEquals(Seq('a, 'b, 3, 4), Seq('a, 'b, 1, 2, 3, 4) intersect Seq(3, 4, 5, 6, 'a, 'b).reverse)
    assertEquals(Seq('b, 3, 'a, 4), Seq('b, 1, 2, 3, 'a, 4) intersect Seq(3, 4, 5, 6, 'a, 'b))
    assertEquals(Seq('b, 3, 'a, 4), Seq('b, 1, 2, 3, 'a, 4) intersect Seq(3, 4, 5, 6, 'a, 'b).reverse)
    assertEquals(Seq('a, 'b, 3, 4), Seq('a, 'b, 1, 2, 3, 'a, 4) intersect Seq(3, 4, 5, 6, 'a, 'b))
    assertEquals(Seq('a, 'b, 3, 4), Seq('a, 'b, 1, 2, 3, 'a, 4) intersect Seq(3, 4, 5, 6, 'a, 'b).reverse)
    assertEquals(Seq('a, 'b, 3, 'a, 4), Seq('a, 'b, 1, 2, 3, 'a, 4) intersect Seq(3, 'a, 4, 5, 6, 'a, 'b))
    assertEquals(Seq('a, 'b, 3, 'a, 4), Seq('a, 'b, 1, 2, 3, 'a, 4) intersect Seq(3, 'a, 4, 5, 6, 'a, 'b).reverse)
    assertEquals(Seq('a, 'b, 3, 'a, 4), Seq('a, 'b, 1, 2, 3, 'a, 4) intersect Seq(3, 4, 5, 'a, 6, 'a, 'b))
  }

  @Test
  def test_diff(): Unit = {
    assertEquals(Seq(1, 2, 3), Seq(1, 2, 3) diff Seq(4, 5, 6))
    assertEquals(Seq(1, 2), Seq(1, 2, 3, 4) diff Seq(3, 4, 5, 6))
    assertEquals(Seq(1, 2), Seq(1, 2, 3, 4) diff Seq(3, 4, 5, 6).reverse)
    assertEquals(Seq(1, 2), Seq('a, 'b, 1, 2, 3, 4) diff Seq(3, 4, 5, 6, 'a, 'b))
    assertEquals(Seq(1, 2), Seq('a, 'b, 1, 2, 3, 4) diff Seq(3, 4, 5, 6, 'a, 'b).reverse)
    assertEquals(Seq(1, 2), Seq('b, 1, 2, 3, 'a, 4) diff Seq(3, 4, 5, 6, 'a, 'b))
    assertEquals(Seq(1, 2), Seq('b, 1, 2, 3, 'a, 4) diff Seq(3, 4, 5, 6, 'a, 'b).reverse)
    assertEquals(Seq(1, 2, 'a), Seq('a, 'b, 1, 2, 3, 'a, 4) diff Seq(3, 4, 5, 6, 'a, 'b))
    assertEquals(Seq(1, 2, 'a), Seq('a, 'b, 1, 2, 3, 'a, 4) diff Seq(3, 4, 5, 6, 'a, 'b).reverse)
    assertEquals(Seq(1, 2), Seq('a, 'b, 1, 2, 3, 'a, 4) diff Seq(3, 'a, 4, 5, 6, 'a, 'b))
    assertEquals(Seq(1, 2), Seq('a, 'b, 1, 2, 3, 'a, 4) diff Seq(3, 'a, 4, 5, 6, 'a, 'b).reverse)
    assertEquals(Seq(2, 1, 'a), Seq('a, 'b, 2, 1, 3, 'a, 4) diff Seq(3, 4, 5, 'a, 6, 'b))
  }

  @Test
  def test_union(): Unit = {
    assertEquals(Seq('a, 'b, 1, 2, 3, 4, 3, 4, 5, 6, 'a, 'b), Seq('a, 'b, 1, 2, 3, 4) union Seq(3, 4, 5, 6, 'a, 'b))
  }

  @Test
  def test_distinct(): Unit = {
    assertEquals(Seq('a, 'b, 1, 2, 3, 4, 5, 6), Seq('a, 'b, 1, 2, 3, 4, 3, 4, 5, 6, 'a, 'b).distinct)
  }

  @Test
  def test_+=(): Unit = {
    assertEquals(mutable.Buffer(1, 2, 3, 0), mutable.Buffer(1, 2, 3) += 0)
    assertEquals(mutable.Buffer(1, 2, 3, 0, 1, 2), mutable.Buffer(1, 2, 3) += (0, 1, 2))
  }

  @Test
  def test_++=(): Unit = {
    assertEquals(mutable.Buffer(1, 2, 3, 0, 1, 2), mutable.Buffer(1, 2, 3) ++= mutable.Buffer(0, 1, 2))
  }

  @Test
  def test_+=:(): Unit = {
    assertEquals(mutable.Buffer(0, 1, 2, 3), 0 +=: mutable.Buffer(1, 2, 3))
  }

  @Test
  def test_++=:(): Unit = {
    assertEquals(mutable.Buffer(0, 1, 2, 1, 2, 3), List(0, 1, 2) ++=: mutable.Buffer(1, 2, 3))
  }

  @Test
  def test_insert(): Unit = {
    val buf = mutable.Buffer(1, 2)
    buf insert(1, 3)
    assertEquals(mutable.Buffer(1, 3, 2), buf)
  }

  @Test
  def test_insertAll(): Unit = {
    val buf = mutable.Buffer(1, 2)
    buf insertAll(1, List(3, 4, 5))
    assertEquals(mutable.Buffer(1, 3, 4, 5, 2), buf)
  }

  @Test
  def test_-=(): Unit = {
    val buf = mutable.Buffer(1, 2, 1, 2, 3)
    buf -= 2
    assertEquals(List(1, 1, 2, 3), buf)
  }

  @Test
  def test_remove(): Unit = {
    var buf = mutable.Buffer(1, 2, 1, 2, 3)
    buf remove 1
    buf remove 1
    assertEquals(List(1, 2, 3), buf)
    //
    buf = mutable.Buffer(1, 2, 1, 2, 3)
    buf remove(1, 2)
    assertEquals(List(1, 2, 3), buf)
  }

  @Test
  def test_trimStart(): Unit = {
    val buf = mutable.Buffer('a, 'b, 1, 2, 3, 'c, 'd, 'e)
    buf trimStart 2
    assertEquals(List(1, 2, 3, 'c, 'd, 'e), buf)
  }

  @Test
  def test_trimEnd(): Unit = {
    val buf = mutable.Buffer('a, 'b, 1, 2, 3, 'c, 'd, 'e)
    buf trimEnd 3
    assertEquals(List('a, 'b, 1, 2, 3), buf)
  }

  @Test
  def test_clear(): Unit = {
    val buf = mutable.Buffer('a, 'b, 1, 2, 3, 'c, 'd, 'e)
    buf.clear()
    assertEquals(List(), buf)
  }

  @Test
  def test_clone(): Unit = {
    val buf = mutable.Buffer(1, 2, 3)
    val buf2 = buf.clone()
    buf.clear()
    assertEquals(List(), buf)
    assertEquals(List(1, 2, 3), buf2)
  }

}

