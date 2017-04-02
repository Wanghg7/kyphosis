package wanghg.pis3

/**
  * Created by wanghg on 2/4/2017.
  */
object Twice extends (String => String) {

  def apply(s: String): String = s + s

  def unapply(s: String): Option[String] = s.splitAt(s.length() / 2) match {
    case (leading, trailing) if leading == trailing => Some(leading)
    case _ => None
  }

}

