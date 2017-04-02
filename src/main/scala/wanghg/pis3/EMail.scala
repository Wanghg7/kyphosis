package wanghg.pis3

import java.util.regex.Pattern

/**
  * Created by wanghg on 2/4/2017.
  */
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
