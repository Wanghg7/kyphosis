package wanghg.pis3

import java.util.regex.Pattern

object EMail extends ((String, String) => String) {

  private val PTN = Pattern.compile("([^@]+)@([^@]+)")

  def apply(user: String, domain: String): String = s"$user@$domain"

  def unapply(email: String): Option[(String, String)] = {
    val mtr = PTN.matcher(email)
    if (mtr.matches())
      Some(mtr.group(1), mtr.group(2))
    else
      None
  }

}

object Twice extends (String => String) {

  def apply(s: String): String = s + s

  def unapply(s: String): Option[String] = s.splitAt(s.length() / 2) match {
    case (leading, trailing) if leading == trailing => Some(leading)
    case _ => None
  }

}

object Uppercase {

  def unapply(s: String): Boolean = s.toUpperCase() == s

}

