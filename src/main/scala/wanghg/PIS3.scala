package wanghg

/**
  * Created by wanghg on 25/3/2017.
  */
class PIS3 extends App {

  val d: Dollars = new Dollars(100)
  var x: Dollars = d + new Dollars(1)

}

class Dollars(val amount: Int) extends AnyVal {
  override def toString() = "$" + amount
}
