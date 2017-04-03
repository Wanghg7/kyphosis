package wanghg.pis3

import org.junit.Test
import org.junit.Assert._

import scala.concurrent.Future
import scala.util.Success

/* outer /* inter */ */
class FutureTest {

  @Test
  def testFuture: Unit = {
    import scala.concurrent.ExecutionContext.Implicits.global
    val f = Future {
      Thread.sleep(2000)
      1 + 2 + 3 + 4
    }
    Thread.sleep(5000)
    assertEquals(true, f.isCompleted)
    assertEquals(Some(Success(10)), f.value)
  }

  @Test
  def testFutureTransforming: Unit = {
    import scala.concurrent.ExecutionContext.Implicits.global
    val f = Future {
      Thread.sleep(2000)
      1 + 2 + 3 + 4
    }
    val rv = f.map(_ * 2)
    Thread.sleep(5000)
    assertEquals(true, rv.isCompleted)
    assertEquals(Some(Success(20)), rv.value)
  }

  @Test
  def testFutureFor: Unit = {
    import scala.concurrent.ExecutionContext.Implicits.global
    val f = Future {
      Thread.sleep(2000)
      1 + 2 + 3 + 4
    }
    val rv = f.map(_ * 2)
    val f2 = Future {
      Thread.sleep(2000)
      1 + 2 + 3 + 4
    }
    val rv2 = for {
      x <- rv
      y <- f2
    } yield x + y
    Thread.sleep(5000)
    assertEquals(true, rv2.isCompleted)
    assertEquals(Some(Success(30)), rv2.value)
  }

  @Test
  def testFutureMap: Unit = {
    import scala.concurrent.ExecutionContext.Implicits.global
    val f = Future {
      Thread.sleep(2000)
      List(1, 2, 3, 4)
    }
    val rv = f.map(_.map(_ * 2))
    Thread.sleep(5000)
    assertEquals(true, rv.isCompleted)
    assertEquals(Some(Success(List(2, 4, 6, 8))), rv.value)
  }

  /**
    * Junk
    */
  @Test
  def junk: Unit = {

  }

}