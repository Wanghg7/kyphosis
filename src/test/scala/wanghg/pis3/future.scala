package wanghg.pis3

import org.junit.Test
import org.junit.Assert._

import scala.concurrent.Future
import scala.util.Success

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

}