package wanghg.fcalc

import scala.util.parsing.combinator._

object ExprParser extends JavaTokenParsers {

  override val skipWhitespace = false

  def main(args: Array[String]): Unit = {
    printf("[%s]\n", parseAll(expr, "1+2/3+🍎"))
  }

  def myexpr: Parser[Any] = rep(ident) ~ opt("?") ^^ {
    case a ~ b =>
      println("_1: " + a)
      println("_2: " + b)
  }

  def expr: Parser[Double] = term ~ rep("+" ~ term | "-" ~ term) ^^ {
    case num ~ list => (num /: list) {
      case (lh, "+" ~ rh) => lh + rh
      case (lh, "-" ~ rh) => lh - rh
    }
  }

  def term: Parser[Double] = factor ~ rep("*" ~ factor | "/" ~ factor) ^^ {
    case num ~ list => (num /: list) {
      case (lh, "*" ~ rh) => lh * rh
      case (lh, "/" ~ rh) => lh / rh
    }
  }

  def factor: Parser[Double] = "🍎" ^^ (_ => 100.0) | floatingPointNumber ^^ (_.toDouble) | "(" ~> expr <~ ")"

}
