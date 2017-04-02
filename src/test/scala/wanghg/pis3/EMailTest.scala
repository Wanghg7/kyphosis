package wanghg.pis3

import org.junit.Test

/**
  * Created by wanghg on 2/4/2017.
  */
class EMailTest {

  @Test
  def testEMail: Unit = {
    "harry@hogwats.edu" match {
      case EMail(user, domain) => println(s"$user AT $domain")
      case _ => ???
    }
  }

}
