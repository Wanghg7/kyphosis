package wanghg.pis3

import org.junit.Test
import org.junit.Assert._

/**
  * Created by wanghg on 2/4/2017.
  */
class TwiceTest {

  @Test
  def test: Unit = {
    "abcabc" match {
      case Twice(x) => println(x)
      case _ => ???
    }
  }

}
